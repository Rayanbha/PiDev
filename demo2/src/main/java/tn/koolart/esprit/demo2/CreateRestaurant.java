package tn.koolart.esprit.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.koolart.esprit.models.Restaurant;
import tn.koolart.esprit.service.RestaurantService;

import java.io.IOException;

public class CreateRestaurant {
    //creation des instance des elements de l'interfaces avec leurs nom dans FXID (interfaces)
    @FXML
    private TextField restaurantCategoryField;

    @FXML
    private TextField restaurantLocationField;

    @FXML
    private TextField restaurantNameField;
    RestaurantService service = new RestaurantService();
    @FXML
    void createRestaurant(ActionEvent event) throws IOException { // appel mte3ha mawjouda fel ressources
        //w fil controller implementation / code logic

        //convertir les variable entré manuellement de l'interface en String
        String Category = restaurantCategoryField.getText();
        String Location = restaurantLocationField.getText();
        String Name = restaurantNameField.getText();

        if(Category != "" && Location != "" && Name !="") {
            //nouvelle instance Resto
            Restaurant newResto = new Restaurant(Name, Location, Category);

            //Ajouter a la base
            service.Create(newResto);

            //partie Configuration si success
            Stage stage = (Stage) restaurantCategoryField.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ListeRestaurant.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }else {
            Stage stage = new Stage();
            stage.setTitle("ERROR DE SESSIE!");
            stage.show();
        }
    }

}
