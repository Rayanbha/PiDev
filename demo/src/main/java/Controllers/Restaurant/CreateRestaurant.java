package Controllers.Restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Restaurant;
import services.RestaurantService;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateRestaurant {
        @FXML
        private TextField restaurantImageField;
        @FXML
        private TextField restaurantCategoryField;

        @FXML
        private TextField restaurantLocationField;

        @FXML
        private TextField restaurantNameField;

        private String imagePath;

    RestaurantService service = new RestaurantService();

    @FXML
    void createRestaurant(ActionEvent event) throws IOException {
        String category = restaurantCategoryField.getText();
        String location = restaurantLocationField.getText();
        String name = restaurantNameField.getText();


        String regex = "^(-?\\d+(\\.\\d+)?),\\s*(-?\\d+(\\.\\d+)?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(location);
        if (!matcher.matches())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Location should be lat,long");
            alert.showAndWait();
            return;
        }
        if (!category.isEmpty() && !location.isEmpty() && !name.isEmpty()) {
            Restaurant newResto = new Restaurant(name, location, category, imagePath);

            service.Create(newResto);

            Stage stage = (Stage) restaurantCategoryField.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/UI/ListeRestaurant.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("liste restaurants!");
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Restaurant Missing Fields Check Again.");
            alert.showAndWait();
        }
    }

    @FXML
    void uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imagePath = selectedFile.getAbsolutePath();
            restaurantImageField.setText(imagePath);
        }
    }
    @FXML
    public void cancelRestaurant(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) restaurantCategoryField.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/UI/ListeRestaurant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Liste Restaurant");
        stage.setScene(scene);
        stage.show();
    }
}
