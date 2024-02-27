package tn.esprit.koolart.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import tn.esprit.koolart.models.Evenement;
import tn.esprit.koolart.services.Serviceevenement;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EventFront implements Initializable {
    private Serviceevenement se=new Serviceevenement();
    @javafx.fxml.FXML
    private VBox vboxItems;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Evenement> data = se.showEvenement();


        try {
            for (int i = 0; i < data.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/singleEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                SingleEvent single= fxmlLoader.getController();
                single.SetData(data.get(i));
                vboxItems.getChildren().add(anchorPane);



            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }





}
