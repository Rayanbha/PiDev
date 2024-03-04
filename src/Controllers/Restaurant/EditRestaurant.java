package Controllers.Restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Restaurant;
import services.RestaurantService;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditRestaurant {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField categoryTextField;

    @FXML
    private TextField imageTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private Restaurant restaurant;

    private RestaurantService restaurantService = new RestaurantService();

    public void setRestaurantService() {
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        nameTextField.setText(restaurant.getName());
        locationTextField.setText(restaurant.getLocation());
        categoryTextField.setText(restaurant.getCategory());
        imageTextField.setText(restaurant.getImage());
    }

    @FXML
    void onSave(ActionEvent event) {
        if (restaurant != null && restaurantService != null) {
            restaurant.setName(nameTextField.getText());
            restaurant.setLocation(locationTextField.getText());
            restaurant.setCategory(categoryTextField.getText());
            restaurant.setImage(imageTextField.getText());

            String regex = "^(-?\\d+(\\.\\d+)?),\\s*(-?\\d+(\\.\\d+)?)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(locationTextField.getText());
            if (!matcher.matches())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Location should be lat,long");
                alert.showAndWait();
                return;
            }

            int updated = restaurantService.Update(restaurant, restaurant.getRestaurantId());
            if (updated == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Restaurant details updated successfully.");
                alert.showAndWait();
                Stage currentStage = (Stage) categoryTextField.getScene().getWindow();
                currentStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to update restaurant details.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void onCancel(ActionEvent event) {
        Stage currentStage = (Stage) categoryTextField.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imageTextField.setText(selectedFile.getAbsolutePath());
        }
    }
}
