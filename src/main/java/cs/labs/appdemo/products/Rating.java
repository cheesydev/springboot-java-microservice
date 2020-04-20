package cs.labs.appdemo.products;

public class Rating {
    private int stars;

    // used by jackson's ObjectMapper
    public Rating() {}

    public Rating(int stars) {
        this.stars = stars;
    }

    public int getStars() {
        return stars;
    }
}
