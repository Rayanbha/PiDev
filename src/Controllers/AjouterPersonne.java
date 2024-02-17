package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.user;
import services.UserService;

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
    void AjouterUser(ActionEvent event) {
            if (pwdTF.getText().equals(cpwdTF.getText()))
            {
                us.add(new user("Client", PrenomTF.getText(), NomTF.getText(), EmailTF.getText(), Integer.parseInt(cinTF.getText()), pwdTF.getText()));

            }else
        {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Mot De passe Incorrecte");
            alert.showAndWait();
        }

    }







}
