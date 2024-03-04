package Controllers.Restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Restaurant;
import services.RestaurantService;

import java.io.IOException;

public class ShowRestaurant {

    @FXML
    private Label categoryLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Label idLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private ImageView imageView; // Added ImageView

    private Restaurant restaurant;

    private RestaurantService restaurantService = new RestaurantService();

    public ShowRestaurant() {
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        nameLabel.setText(restaurant.getName());
        locationLabel.setText(restaurant.getLocation());
        categoryLabel.setText(restaurant.getCategory());

        if (restaurant.getImage() != null && !restaurant.getImage().isEmpty()) {
            try {
                String imageUrl = restaurant.getImage();
                System.out.println("Image URL: " + imageUrl);
                imageView.setImage(new Image(imageUrl));
            } catch (Exception e) {
                System.out.println("No Image Found");
            }
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
        if (restaurant != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to delete this restaurant?");

            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    int deleted = restaurantService.Delete(restaurant.getRestaurantId());
                    if (deleted == 1) {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Success");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Restaurant deleted successfully.");
                        successAlert.showAndWait();
                        Stage stage = (Stage) categoryLabel.getScene().getWindow();
                        stage.close();
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Failed to delete restaurant.");
                        errorAlert.showAndWait();
                    }
                }
            });
        }
    }


    @FXML
    void onEdit(ActionEvent event) {
        if (restaurant != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/EditRestaurant.fxml"));
                Parent root = loader.load();
                EditRestaurant controller = loader.getController();
                controller.setRestaurant(restaurant);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Edit Restaurant");
                stage.show();
                Stage currentStage = (Stage) editButton.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
