package Controllers.Restaurant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Product;
import models.Restaurant;
import services.ProductService;
import services.RestaurantService;

import java.io.IOException;
import java.util.List;

public class EditProduit {
    @FXML
    private ComboBox<String> restaurantComboBox;
    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField categoryField;

    private Product product;
    private ProductService productService;
    private final RestaurantService restaurantService = new RestaurantService();


    public void initData(Product product) {
        this.product = product;
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        categoryField.setText(product.getCategory());
        productService = new ProductService();

        List<Restaurant> restaurants = restaurantService.Read();
        ObservableList<String> restaurantsObservableList = FXCollections.observableArrayList();
        for(Restaurant res: restaurants){
            restaurantsObservableList.add(res.getName());
        }
        restaurantComboBox.setItems(restaurantsObservableList);
    }

    @FXML
    public void onSave(ActionEvent actionEvent) throws IOException {
        String name = nameField.getText();
        double price = 0;
        try {
            price = Double.parseDouble(priceField.getText());
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Check price not integer");
            alert.showAndWait();
            return;
        }
        String category = categoryField.getText();

        Restaurant selectedRestaurant = restaurantService.ReadByName(restaurantComboBox.getValue());

        if (selectedRestaurant == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a restaurant.");
            alert.showAndWait();
            return;
        }

        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setRestaurantId(selectedRestaurant.getRestaurantId());

        productService.Update(product,product.getProductId());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Product Edited Successfully.");
        alert.showAndWait();

        goToList();
    }

    private void goToList() throws IOException {
        Stage stage = (Stage) nameField.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/UI/ListeProduit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onCancel(ActionEvent actionEvent) throws IOException {
        goToList();
    }
}
