package tn.koolart.esprit.demo2;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import tn.koolart.esprit.models.Restaurant;
import tn.koolart.esprit.service.RestaurantService;

import java.io.IOException;
import java.util.List;

public class ListeRestaurant {

    @FXML
    private TableView<Restaurant> restaurantTable;

    @FXML
    private TableColumn<Restaurant, Integer> restoId;

    @FXML
    private TableColumn<Restaurant, String> restoName;

    @FXML
    private TableColumn<Restaurant, String> restoLocation;

    @FXML
    private TableColumn<Restaurant, String> restoCategory;

    private RestaurantService service = new RestaurantService();

    @FXML
    void initialize() {
        //inisilization des collmun de la table dans l'interface FX
        restoId.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRestaurantId()));
        restoName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        restoLocation.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        restoCategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        GetList();
    }
    @FXML
    void onClick(ActionEvent event) {
        GetList();
    }
    void GetList(){
        List<Restaurant> list = service.Read(); //Jibt il lista taa les resto mil base bil service
        ObservableList<Restaurant> observableList = FXCollections.observableArrayList(list);
        //Convertir la liste "list" de object "Restaurant" a une liste observable pour l'affichage
        restaurantTable.setItems(observableList);
        //appel a la table de l'interface et ajouter la liste des elements a la table
    }
    @FXML
    void onAjouter(ActionEvent event) throws IOException {
        Stage stage = (Stage) restaurantTable.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CreateRestaurant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Create Resto");
        stage.setScene(scene);
        stage.show();
    }
}
