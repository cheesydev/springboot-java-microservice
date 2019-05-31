package cs.labs.appdemo.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    public void getProductById() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/products/10"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        Product returnedProduct = new ObjectMapper()
                .readValue(response.getContentAsByteArray(), Product.class);

        assertThat(returnedProduct.getId()).isEqualTo("10");
    }
}