package Controllers.User;

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
import services.EmailService;

import java.io.IOException;
import java.util.Random;


public class PutEmail {
    @FXML
    private Button valider;


    @FXML
    private TextField email;

    @FXML
    private Button validerB;

    @FXML
    void validerB(ActionEvent event) {
        UserService us=new UserService();
        user u=new user();
        u=us.findemail(email.getText());

        if(u.getNom()==null)
        {
            showAlert("Email","Email Introuvable");
        }
        else
        {

            try {
                EmailService emails=new EmailService();
                Random random=new Random();
                int randomnumber=generateRandomNumber(8);


                emails.sendEmail(email.getText(),"Code De confirmation","Vous Avez Oublier Votre Mot De Passe,\n Merci D'Ecrire Ce Code Pour Confirmer La modification : "+randomnumber+".");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Confirmation.fxml"));
                Parent root = loader.load();
                Confirmation updateController = loader.getController();
                Stage stage1=(Stage)valider.getScene().getWindow();
                stage1.close();
                updateController.initCode(randomnumber,email.getText());
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static int generateRandomNumber(int maxDigits) {
        Random random = new Random();
        // Calculate the maximum value based on the number of digits
        int max = (int) Math.pow(10, maxDigits) - 1;
        // Generate a random number within the specified range
        return random.nextInt(max);
    }


}
