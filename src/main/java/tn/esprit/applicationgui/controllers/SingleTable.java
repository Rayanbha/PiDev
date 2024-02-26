package tn.esprit.applicationgui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import tn.esprit.applicationgui.models.Table;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import tn.esprit.applicationgui.services.TableService;
import tn.esprit.applicationgui.test.HelloApplication;


public class SingleTable implements Initializable {
    @javafx.fxml.FXML
    private Label NumTableLabe;
    @javafx.fxml.FXML
    private Rectangle bgTable;
    private Table table;
    @javafx.fxml.FXML
    private Button actionButton;
    private TableService ts=new TableService();

    public void SetTable(Table table){
        this.table=table;
        NumTableLabe.setText(String.valueOf(table.getID_table()));
        if(table.getisReserver()){
            bgTable.setFill(Color.web("#F70000"));
            actionButton.setText("Fin Reservation");

        }else{
            bgTable.setFill(Color.web("#0cc465"));
            actionButton.setText("Reservation");

        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
