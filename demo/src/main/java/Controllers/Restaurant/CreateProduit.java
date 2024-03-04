package Controllers.Restaurant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Product;
import models.Restaurant;
import services.ProductService;
import services.RestaurantService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CreateProduit {

    public Button cancelButton;
    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField categoryField;
    @FXML
    private ComboBox<String> restaurantComboBox;

    private final ProductService productService;
    private final RestaurantService restaurantService = new RestaurantService();

    public CreateProduit() {
        productService = new ProductService();
    }

    @FXML
    void onSave(ActionEvent event) throws IOException {
        String name = nameField.getText();
        double price = 0;
        try {
            price = Double.parseDouble(priceField.getText());
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Check price not int");
            alert.showAndWait();
            return;
        }
        String category = categoryField.getText();

        Restaurant selectedRestaurant = restaurantService.ReadByName(restaurantComboBox.getValue());

        if(Objects.equals(name, "name") && price < 0 && Objects.equals(category, "")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Check Fields Again");
            alert.showAndWait();
            return;
        }
        Product product = new Product(name, price, category, selectedRestaurant.getRestaurantId());
        productService.Create(product);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Product Added Successfully.");
        alert.showAndWait();

        Stage stage = (Stage) nameField.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/UI/ListeProduit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize() {
        List<Restaurant> restaurants = restaurantService.Read();
        ObservableList<String> restaurantsObservableList = FXCollections.observableArrayList();
        for(Restaurant res: restaurants){
            restaurantsObservableList.add(res.getName());
        }
        restaurantComboBox.setItems(restaurantsObservableList);
    }
    @FXML
    void onCancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) nameField.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/UI/ListeProduit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
