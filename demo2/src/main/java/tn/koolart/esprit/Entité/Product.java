package Entité;

public class Product {
    private int productId;
    private String name;
    private double price;
    private String category;
    private int restaurantId;

    public Product(String name, double price, String category, int restaurantId) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.restaurantId = restaurantId;
    }

    public Product(int productId, String name, double price, String category, int restaurantId) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.restaurantId = restaurantId;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", restaurantId=" + restaurantId +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
