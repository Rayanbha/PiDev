package Controllers.Restaurant;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Product;
import services.ProductService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListeProduitClient {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Product> productListView;

    @FXML
    private TextField searchField;
    private final ProductService productService;

    public ListeProduitClient() {
        productService = new ProductService();
    }



    @FXML
    void onRecherche(ActionEvent event) {
        List<Product> productList = productService.searchByName(searchField.getText());
        ObservableList<Product> observableProductList = javafx.collections.FXCollections.observableArrayList(productList);
        productListView.setItems(observableProductList);
    }

    @FXML
    void GoToRestaurant(ActionEvent event) {
        try {
            Stage stage = (Stage) productListView.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/UI/ListeRestaurantClient.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Liste Restrant");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void refreshList() {
        List<Product> productList = productService.Read();
        ObservableList<Product> observableProductList = javafx.collections.FXCollections.observableArrayList(productList);
        productListView.setItems(observableProductList);
    }

    private void configureListView() {
        productListView.setCellFactory(param -> new ListeProduit.ProductListCell());
    }

    @FXML
    void initialize() {
        assert productListView != null : "fx:id=\"productListView\" was not injected: check your FXML file '/UI/ListeProduitClient.fxml'.";

        configureListView();
        refreshList();
    }

}
