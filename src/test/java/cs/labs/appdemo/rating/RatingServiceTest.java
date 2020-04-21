package cs.labs.appdemo.rating;

import org.eclipse.jetty.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RatingServiceTest {

    private static Server server;

    @BeforeClass
    public static void setUp() throws Exception {
        server = new Server(9998);
        server.setHandler(new RatingHandler());
        server.start();
    }

    @Test
    public void testExternalRatingService() {
        RatingService ratingService = new RatingService("http://localhost:9998");
        int rating = ratingService.ratingForProductId("10");
        assertEquals(rating, 2);
    }

    @AfterClass
    public static void ShutdownServer() throws Exception {
        server.stop();
    }
}