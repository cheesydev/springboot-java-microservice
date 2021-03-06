package cs.labs.appdemo.products;

import cs.labs.appdemo.rating.RatingService;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductsController.class);

    private static final List<Product> ALL_PRODUCTS = newArrayList(
            new Product("10", "prod1", new BigDecimal("10.50")),
            new Product("11", "prod2", new BigDecimal("23.00")),
            new Product("12", "prod3", new BigDecimal("90.50")));

    @Autowired
    private RatingService ratingService;

    @GetMapping
    @Timed("products.all")
    public List<Product> allProducts() {
        return ALL_PRODUCTS;
    }

    @GetMapping("/{id}")
    @Timed(value = "products.by-id")
    public ProductWithReviews getProduct(@PathVariable("id") String id) {

        Optional<Product> product = ALL_PRODUCTS.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");

        int rating;
        try {
            rating = ratingService.ratingForProductId(product.get().getId());
        } catch (RuntimeException e) {
            LOG.error("Failed to call RatingService: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return new ProductWithReviews(product.get(), new Rating(rating));
    }
}
