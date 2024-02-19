package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.user;
import services.UserService;

import java.io.IOException;

public class AjouterPersonne {


    @FXML
    private TextField EmailTF;

    @FXML
    private TextField NomTF;

    @FXML
    private TextField PrenomTF;

    @FXML
    private TextField cinTF;

    @FXML
    private TextField cpwdTF;

    @FXML
    private TextField pwdTF;

    private UserService us=new UserService();


    @FXML
    private Button inscrire;



    @FXML
    void AjouterUser(ActionEvent event) throws IOException {
            if (pwdTF.getText().equals(cpwdTF.getText()))
            {
                us.add(new user("Client", PrenomTF.getText(), NomTF.getText(), EmailTF.getText(), Integer.parseInt(cinTF.getText()), pwdTF.getText()));

                Stage stage=(Stage)inscrire.getScene().getWindow();
                stage.close();
                Stage primaryStage=new Stage();
                Parent root= FXMLLoader.load(getClass().getResource("/UI/AffichageUser.fxml"));
                primaryStage.setTitle("Add");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }else
        {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Mot De passe Incorrecte");
            alert.showAndWait();
        }

    }







}
