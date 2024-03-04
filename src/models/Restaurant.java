package models;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String location;
    private String category;
    private String image; // New field for the image

    public Restaurant() {
    }

    public Restaurant(int restaurantId, String name, String location, String category, String image) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.category = category;
        this.image = image;
    }

    public Restaurant(String name, String location, String category, String image) {
        this.name = name;
        this.location = location;
        this.category = category;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
