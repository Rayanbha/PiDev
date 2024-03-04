package Controllers.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.user;
import org.mindrot.jbcrypt.BCrypt;
import services.UserService;

import java.io.IOException;

public class Newpwd {
    private String email;

    public void initData(String email)
    {
        this.email=email;
    }

    @FXML
    private Button annuler;

    @FXML
    private Button confirmer;

    @FXML
    private TextField cpwdTF;

    @FXML
    private TextField pwdTF;

    @FXML
    void annuler(ActionEvent event) {


        try {
            Stage stage = (Stage) annuler.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/UI/Connectez.fxml"));
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void confirmer(ActionEvent event) {
        UserService us=new UserService();
        user u=new user();
        u=us.findemail(email);

        System.out.println(u);
        try {
            System.out.println("lena");
            u.setPwd(pwdTF.getText());
            String salt = BCrypt.gensalt();
            System.out.println(salt);

            // Hash the password using the generated salt
            String hashedPassword = BCrypt.hashpw(pwdTF.getText(), salt);
            u.setSalt(salt);
            u.setHashedpwd(hashedPassword);
            us.update(u);
            Stage stage = (Stage) confirmer.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/UI/Connectez.fxml"));
            primaryStage.setTitle("Connectez");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }






    }
}
