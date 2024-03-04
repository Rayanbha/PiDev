package Controllers;

import Controllers.User.CompteUser;
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
import java.util.prefs.Preferences;

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
    private void hover(MouseEvent event) {
        forgetz.getStyleClass().add("label");
    }

    @FXML
    private void hovere(MouseEvent event) {
        forgetz.getStyleClass().remove("label");
    }
    @FXML
    void Login(ActionEvent event) {


        UserService us=new UserService();
        user u=us.findemail(EmailTF.getText());
        // Save the user into local storage using Preferences
        Preferences prefs = Preferences.userNodeForPackage(this.getClass());

        prefs.put("id", String.valueOf(u.getId()));
        prefs.put("role",u.getRole());
        prefs.put("nom", u.getNom());
        prefs.put("prenom", u.getPrenom());
        prefs.put("email", u.getEmail());
        prefs.put("cin",String.valueOf(u.getCin()));

        System.out.println("lot "+prefs);

        String salt = u.getSalt();
        // Hash the password using the generated salt
        String hashedPassword = BCrypt.hashpw(pwdTF.getText(),salt);



        if(u!=null)
        {

            if(u.getPwd().equals("root"))
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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/HomePage.fxml"));
                    Parent root = loader.load();
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
    @FXML
    private Label inscrire;
    @FXML
    void inscrire(MouseEvent event) {

        try {
            Stage stage=(Stage)inscrire.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= null;
            root = FXMLLoader.load(getClass().getResource("/UI/AjouterPersonne.fxml"));
            primaryStage.setTitle("Ajouter");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void hovereInscrire(MouseEvent event) {
        inscrire.getStyleClass().remove("label");

    }
    @FXML
    void hoverInscrire(MouseEvent event) {

        inscrire.getStyleClass().add("label");

    }
}
