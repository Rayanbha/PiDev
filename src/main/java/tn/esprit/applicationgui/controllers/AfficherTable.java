package tn.esprit.applicationgui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import tn.esprit.applicationgui.models.Table;
import tn.esprit.applicationgui.services.TableService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherTable implements Initializable {
    private TableService ts=new TableService();
    private List<Table> tables;
    @FXML
    private GridPane gridTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tables=ts.recuperer();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < tables.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/tn/esprit/applicationgui/singleTable.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                SingleTable itemController = fxmlLoader.getController();
                itemController.SetTable(tables.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                gridTable.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                gridTable.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridTable.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridTable.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridTable.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridTable.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridTable.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }
}