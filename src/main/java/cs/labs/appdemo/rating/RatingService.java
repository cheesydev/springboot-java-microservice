package cs.labs.appdemo.rating;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class RatingService {

    private URI serviceUri;

    public RatingService(@Value("${rating.service.host}") String host,
                         @Value("${rating.service.port}") Integer port) {
        init(host, port);
    }

    private void init(String host, Integer port) {
        String url = "http://" + host + ":" + port + "/rating";
        try {
            serviceUri = new URI(url);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid Rating Service URL: " + url, e);
        }
    }

    public int ratingForProductId(String productId) {
        HttpRequest request = HttpRequest.newBuilder(serviceUri)
                .build();

        try {
            InputStream body = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofInputStream())
                    .body();

            Rating rating = new ObjectMapper().readValue(body, Rating.class);
            return Integer.parseInt(rating.getRating());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to call " + serviceUri, e);
        }
    }
}
