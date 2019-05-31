package cs.labs.appdemo.products;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


@RestController
@RequestMapping("/products")
public class ProductsController {

    private static final List<Product> ALL_PRODUCTS = newArrayList(
            new Product("10", "prod1", new BigDecimal("10.50")),
            new Product("11", "prod2", new BigDecimal("23.00")),
            new Product("12", "prod3", new BigDecimal("90.50")));

    @GetMapping
    public List<Product> allProducts() {
        return ALL_PRODUCTS;
    }
}
