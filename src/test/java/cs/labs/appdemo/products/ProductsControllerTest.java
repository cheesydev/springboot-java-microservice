package cs.labs.appdemo.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs.labs.appdemo.rating.RatingHandler;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${rating.service.port}")
    private Integer port;

    private Server server;

    @Before
    public void startRatingService() throws Exception {
        server = new Server(port);
        server.setHandler(new RatingHandler());
        server.start();
    }

    @Test
    public void getProductById() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/products/10"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        ProductWithReviews returnedProduct = new ObjectMapper()
                .readValue(response.getContentAsByteArray(), ProductWithReviews.class);

        assertThat(returnedProduct.getProduct().getId()).isEqualTo("10");
    }

    @After
    public void stopRatingService() throws Exception {
        server.stop();
    }
}