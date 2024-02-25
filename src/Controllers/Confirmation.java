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

import java.io.IOException;

public class Confirmation {

    @FXML
    private Button Confirmation;


    @FXML
    private TextField code;
    private int coder;
    private String email;
    public  void initCode(int code,String email)
    {
        this.coder=code;
        this.email=email;
    }

    @FXML
    void Confirmation(ActionEvent event) {

        if(Integer.parseInt(code.getText()) != coder)
        {

            try {
                showAlert("Code","Code n'est pas correct");
                Stage stage=(Stage)Confirmation.getScene().getWindow();
                stage.close();
                Stage primaryStage=new Stage();
                Parent root= null;
                root = FXMLLoader.load(getClass().getResource("/UI/Connectez.fxml"));
                primaryStage.setTitle("Confirmer");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Newpwd.fxml"));
                Parent root = loader.load();
                Newpwd updateController = loader.getController();
                Stage stage1=(Stage)Confirmation.getScene().getWindow();
                stage1.close();
                updateController.initData(email);
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

}
