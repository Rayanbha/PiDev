package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.user;
import org.mindrot.jbcrypt.BCrypt;
import services.UserService;

import java.io.IOException;

public class Connectez {




    @FXML
    private TextField EmailTF;

    @FXML
    private Label forgetz;

    @FXML
    private PasswordField pwdTF;
    @FXML
    private Button connectezBut;

    @FXML
    void Login(ActionEvent event) {

        UserService us=new UserService();
        user u=us.findemail(EmailTF.getText());
        String salt = u.getSalt();
        // Hash the password using the generated salt
        String hashedPassword = BCrypt.hashpw(pwdTF.getText(),salt);



        if(u!=null)
        {

            if(u.getEmail().equals("root")&&u.getPwd().equals("root"))
            {

                try {
                    Stage stage=(Stage)connectezBut.getScene().getWindow();
                    stage.close();
                    Stage primaryStage=new Stage();
                    Parent root= null;
                    root = FXMLLoader.load(getClass().getResource("/UI/AffichageUser.fxml"));
                    primaryStage.setTitle("Add");
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            else
            if(u.getHashedpwd().equals(hashedPassword))

                try {
                    Stage stage=(Stage)connectezBut.getScene().getWindow();
                    stage.close();
                    Stage primaryStage=new Stage();
                    Parent root=FXMLLoader.load(getClass().getResource("/UI/UpdateUser.fxml"));
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
    @FXML
    void forgetpwd(MouseEvent event) {


        try {
            Stage stage=(Stage)forgetz.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= null;
            root = FXMLLoader.load(getClass().getResource("/UI/putEmail.fxml"));
            primaryStage.setTitle("Email");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

}
