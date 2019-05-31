package cs.labs.appdemo.products;

import java.math.BigDecimal;

public class Product {
    private String id;
    private String name;
    private BigDecimal price;

    // used by Jackson's ObjectMapper
    public Product() {}

    public Product(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
