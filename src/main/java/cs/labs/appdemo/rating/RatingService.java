package cs.labs.appdemo.rating;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RatingService {

    private URI serviceUri;

    public RatingService(String url) {
        try {
            this.serviceUri = new URI(url + "/rating");
        } catch (URISyntaxException e) {
            throw new RuntimeException("Invalid Rating Service URL. ", e);
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
            throw new RuntimeException(e);
        }
    }
}
