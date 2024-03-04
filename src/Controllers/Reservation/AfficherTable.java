package Controllers.Reservation;

import io.reactivex.Single;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import models.Restaurant;
import models.Table;
import models.user;
import services.TableService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherTable {
    private TableService ts = new TableService();
    private List<Table> tables;
    @FXML
    private GridPane gridTable;
    private Restaurant restaurant;
    private user user;

    public void InitResto(Restaurant restaurant, user u) {
        this.restaurant = restaurant;
        this.user=u;
        System.out.println("AFFICHAAGE TABLE "+user);
        table();
    }

    public void table() {
        tables = ts.recuperer();
        int column = 0;
        int row = 1;
        try {
            for (Table table : tables) {
                if (table.getID_restaurant() == restaurant.getRestaurantId()) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/singleTable.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    SingleTable controller = fxmlLoader.getController();
                    controller.InitResto(restaurant,user);

                    SingleTable itemController = fxmlLoader.getController();
                    itemController.SetTable(table);

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
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void acceuil(MouseEvent event) {

    }

    @FXML
    void booking(MouseEvent event) {

    }

    @FXML
    void compte(MouseEvent event) {

    }

    @FXML
    void forum(MouseEvent event) {

    }

    @FXML
    void recette(MouseEvent event) {

    }

    @FXML
    void restaurant(MouseEvent event) {

    }

    @FXML
    void wallet(MouseEvent event) {

    }

}
