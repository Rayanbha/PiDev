package Controllers.Restaurant;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Restaurant;

public class RestaurantCardController {

    @FXML
    private ImageView restaurantImageView;

    @FXML
    private Label restaurantNameLabel;

    @FXML
    private Label restaurantLocationLabel;

    @FXML
    private Label restaurantCategoryLabel;

    public void setRestaurant(Restaurant restaurant) {
        restaurantNameLabel.setText(restaurant.getName());
        restaurantLocationLabel.setText("");
        restaurantCategoryLabel.setText(restaurant.getCategory());
        try {
            String imageUrl = restaurant.getImage();
            System.out.println("Image URL: " + imageUrl);  // Print or log the URL for debugging
            restaurantImageView.setImage(new Image(imageUrl));
        } catch (Exception e) {
            System.out.println("No Image Found");
        }

    }
}
