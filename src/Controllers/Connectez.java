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

public class Connectez {


    @FXML
    private TextField EmailTF;

    @FXML
    private TextField pwdTF;
    @FXML
    private Button connectezBut;

    @FXML
    void Login(ActionEvent event) {
        UserService us=new UserService();
        user u=us.findemail(EmailTF.getText());

        if(u!=null)
        {

            if(u.getPwd().equals(pwdTF.getText()))

                try {
                    Stage stage=(Stage)connectezBut.getScene().getWindow();
                    stage.close();
                    Stage primaryStage=new Stage();
                    Parent root=FXMLLoader.load(getClass().getResource("/UI/AjouterPersonne.fxml"));
                    primaryStage.setTitle("Add");
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            else
            {
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Mot De Passe Incorrect");
                alert.showAndWait();
            }

        }
        else
        {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Email Incorrect");
            alert.showAndWait();
        }

    }


}
