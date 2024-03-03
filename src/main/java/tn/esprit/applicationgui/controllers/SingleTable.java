package tn.esprit.applicationgui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import tn.esprit.applicationgui.models.Table;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import tn.esprit.applicationgui.services.TableService;
import tn.esprit.applicationgui.test.HelloApplication;


public class SingleTable {
    @javafx.fxml.FXML
    private Label NumTableLabe;
    private Table table;
    @javafx.fxml.FXML
    private Button actionButton;
    private TableService ts=new TableService();
    @javafx.fxml.FXML
    private ImageView bgTable;

    public void SetTable(Table table){
        this.table=table;
        NumTableLabe.setText(String.valueOf(table.getID_table()));
        String imageType="";
        if(table.getisReserver()){
            imageType="/images/redTable.jpg";
            actionButton.setText("Fin Reservation");
        }else{
            imageType="/images/greenTable.jpg";
            actionButton.setText("Reservation");
        }
        String imageUrl = SingleTable.class.getResource(imageType).toExternalForm();

        Image image = new Image(imageUrl);

        bgTable.setImage(image);
    }


    @javafx.fxml.FXML
    public void save(ActionEvent actionEvent) {
        if(table.getisReserver()){
            table.setisReserver(false);
            ts.modifier(table);
            SetTable(table);
        }else{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/applicationgui/AjouterReservation.fxml"));
                Parent root = fxmlLoader.load();
                AjouterReservation controller = fxmlLoader.getController();

                NumTableLabe.getScene().setRoot(root);

                // Now you can use the controller object to interact with the loaded FXML file's elements
                controller.setTableID(table.getID_table());
            } catch (IOException exp) {
                System.out.println(exp);
            }
        }

    }
}
