package cs.labs.appdemo.products;

public class ProductWithReviews {
    private Product product;
    private Rating rating;

    // used by jackson's ObjectMapper
    public ProductWithReviews() {}

    public ProductWithReviews(Product product, Rating rating) {
        this.product = product;
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public Rating getRating() {
        return rating;
    }
}
