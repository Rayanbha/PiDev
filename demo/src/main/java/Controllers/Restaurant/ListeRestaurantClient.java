package Controllers.Restaurant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Restaurant;
import services.RestaurantService;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class ListeRestaurantClient {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Restaurant> restaurantListView;

    @FXML
    private TextField searchField;
    private final RestaurantService service = new RestaurantService();

    @FXML
    void goToProduit(ActionEvent event) throws IOException {
        Stage stage = (Stage) restaurantListView.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/ListeProduitClient.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Liste Produit");
        stage.setScene(scene);
        stage.show();
    }
    private void refreshList() {
        RestaurantService service = new RestaurantService();
        List<Restaurant> list = service.Read();
        ObservableList<Restaurant> observableList = FXCollections.observableArrayList(list);
        restaurantListView.setItems(observableList);
    }
    @FXML
    void onClick(ActionEvent event) {
        refreshList();
    }

    @FXML
    void onRecherche(ActionEvent actionEvent) throws IOException {
        String searchText = searchField.getText().toLowerCase().trim();
        if (searchText.isEmpty()) {
            refreshList();
        } else {
            Restaurant newValue = service.ReadByName(searchText);
            if(newValue != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ShowRestaurantClient.fxml"));
                Parent root = loader.load();
                ShowRestaurantClient controller = loader.getController();
                controller.setRestaurant(newValue);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Doesn't Exist");
                alert.setHeaderText(null);
                alert.setContentText("Restaurant with that name doesn't exist yet");
                alert.show();
            }
        }
    }

    @FXML
    public void onSort(ActionEvent actionEvent) {
        ObservableList<Restaurant> items = restaurantListView.getItems();
        items.sort(Comparator.comparing(Restaurant::getName));
        restaurantListView.setItems(items);
    }

    @FXML
    void initialize() {
        assert restaurantListView != null : "fx:id=\"restaurantListView\" was not injected: check your FXML file '/UI/ListeRestaurantClient.fxml'.";
        assert searchField != null : "fx:id=\"searchField\" was not injected: check your FXML file '/UI/ListeRestaurantClient.fxml'.";

        configureListView();
        refreshList();
    }

    private void configureListView() {
        restaurantListView.setCellFactory(param -> new ListeRestaurant.RestaurantCardCell());
        restaurantListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ShowRestaurantClient.fxml"));
                    Parent root = loader.load();
                    ShowRestaurantClient controller = loader.getController();
                    controller.setRestaurant(newValue);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    System.out.println("Opened");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
