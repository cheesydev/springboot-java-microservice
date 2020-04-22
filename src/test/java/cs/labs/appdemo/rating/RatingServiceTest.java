package cs.labs.appdemo.rating;

import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingServiceTest {

    private static Server server;

    @Value("${rating.service.port}")
    private Integer port;

    @Autowired
    private RatingService ratingService;

    @Before
    public void setUp() throws Exception {
        server = new Server(port);
        server.setHandler(new RatingHandler()); // returns rating value 2
        server.start();
    }

    @Test
    public void testExternalRatingService() {
        int rating = ratingService.ratingForProductId("10");
        assertEquals(rating, 2);
    }

    @After
    public void ShutdownServer() throws Exception {
        server.stop();
    }
}