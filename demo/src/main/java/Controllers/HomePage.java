package Controllers;

import Controllers.User.CompteUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class HomePage implements Initializable {

    Preferences prefs = Preferences.userNodeForPackage(this.getClass());

    @FXML
    private Pane EventB;

    @FXML
    private Pane ForumB;

    @FXML
    private Pane RecetteB;

    @FXML
    private Label acceuil;

    @FXML
    private Label booking;

    @FXML
    private Pane bookingB;

    @FXML
    private Label compte;

    @FXML
    private Pane compteB;

    @FXML
    private Label forum;

    @FXML
    private Label recette;

    @FXML
    private Label restaurant;

    @FXML
    private Pane restoB;

    @FXML
    private Label wallet;

    @FXML
    void BookingB(MouseEvent event) {
        try {
            Stage stage = (Stage) bookingB.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AffichageUser.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }




    @FXML
    void CompteB(MouseEvent event) {

    }

    @FXML
    void EventB(MouseEvent event) {

    }

    @FXML
    void ForumB(MouseEvent event) {

    }

    @FXML
    void RecetteB(MouseEvent event) {

    }

    @FXML
    void RestoB(MouseEvent event) {

    }

    @FXML
    void acceuil(MouseEvent event) {

    }

    @FXML
    void booking(MouseEvent event) {

    }

    @FXML
    void bookingB(MouseEvent event) {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prefs.get("role","not found");
        System.out.println("IL PAPI"+prefs.get("role","not found"));
    }
}
