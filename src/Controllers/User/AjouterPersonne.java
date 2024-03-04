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
import org.mindrot.jbcrypt.BCrypt;
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
       if (!isEmailValid(EmailTF.getText())) {
            showAlert("Error", "Invalid email address!");
            return;

      }
        if (!isCinValid(cinTF.getText())) {
            showAlert("Error", "CIN must be 8 digits long!");
           return;
        }


            if (pwdTF.getText().equals(cpwdTF.getText()))
            {
                String salt = BCrypt.gensalt();
                System.out.println(salt);

                // Hash the password using the generated salt
                String hashedPassword = BCrypt.hashpw(pwdTF.getText(), salt);

                user user=new user( "Client",PrenomTF.getText(), NomTF.getText(), EmailTF.getText(), Integer.parseInt(cinTF.getText()), pwdTF.getText(),hashedPassword,salt,1);
                if(user.getPrenom().equals("root")&&user.getPwd().equals("root"))
                {
                    user.setRole("Admin");
                }


                us.add(user);

                Stage stage = (Stage) inscrire.getScene().getWindow();
                stage.close();
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/UI/Connectez.fxml"));
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
    private boolean isEmailValid(String email) {
        // Regular expression to validate email format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
       return email.matches(emailRegex);
    }

    private boolean isCinValid(String cin) {
       return cin.length() == 8 && cin.matches("\\d+");
   }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
   }






}
