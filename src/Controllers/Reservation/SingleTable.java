package Controllers.Reservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Restaurant;
import models.Table;
import models.user;
import services.TableService;

import java.io.IOException;


public class SingleTable {
    @javafx.fxml.FXML
    private Label NumTableLabe;
    private Table table;
    @javafx.fxml.FXML
    private Button actionButton;
    private TableService ts=new TableService();
    @javafx.fxml.FXML
    private ImageView bgTable;
    private Restaurant restaurant;
    private user user;
    public void InitResto(Restaurant restaurant1, user u)
    {
        this.restaurant=restaurant1;
        this.user=u;


    }

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
        if (table.getisReserver()) {
            table.setisReserver(false);
            ts.modifier(table);
            SetTable(table);
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/AjouterReservation.fxml"));
                Parent root = fxmlLoader.load();

                AjouterReservation controller1 = fxmlLoader.getController();
                controller1.InitResto(restaurant,user);
                controller1.setTableID(table.getID_table());

                NumTableLabe.getScene().setRoot(root);
            } catch (IOException exp) {
                System.out.println(exp);
            }
        }

    }
}
