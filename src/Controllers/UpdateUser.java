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

public class UpdateUser {

    @FXML
    private TextField NomTF;

    @FXML
    private TextField PrenomTF;

    @FXML
    private TextField cinTF;

    @FXML
    private TextField EmailTF;

    @FXML
    private TextField pwdTF;
    @FXML
    private TextField cpwdTF;
    private user userToUpdate;
    @FXML
    private Button updateuser;
    private UserService userService = new UserService();

    public void initData(user user) {
        this.userToUpdate = user;
        populateFields();
    }

    private void populateFields() {
        NomTF.setText(userToUpdate.getNom());
        PrenomTF.setText(userToUpdate.getPrenom());
        cinTF.setText(String.valueOf(userToUpdate.getCin())); // Convert to String for TextField
        EmailTF.setText(userToUpdate.getEmail());
        pwdTF.setText(userToUpdate.getPwd());
    }

    @FXML
    private void handleUpdate() {
        String newNom = NomTF.getText().trim();
        String newPrenom = PrenomTF.getText().trim();
        String newCin = cinTF.getText().trim();
        String newEmail = EmailTF.getText().trim();
        String newPassword = pwdTF.getText().trim();

        if (newNom.isEmpty() || newPrenom.isEmpty() || newCin.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty()) {
            showAlert("Error", "All fields are required!");
            return;
        }

        userToUpdate.setNom(newNom);
        userToUpdate.setPrenom(newPrenom);
        userToUpdate.setCin(Integer.parseInt(newCin));
        userToUpdate.setEmail(newEmail);
        userToUpdate.setPwd(newPassword);

        boolean success = userService.update(userToUpdate);
        if (success) {
            showAlert("Success", "User details updated successfully!");
        } else {
            showAlert("Error", "Failed to update user details!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void Updateuser(ActionEvent actionEvent) {
        if (pwdTF.getText().equals(cpwdTF.getText()))
        {
        userService.update(new user("Client", PrenomTF.getText(), NomTF.getText(), EmailTF.getText(), Integer.parseInt(cinTF.getText()), pwdTF.getText()));
            Stage stage=(Stage)updateuser.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= null;
            try {
                root = FXMLLoader.load(getClass().getResource("/AffichageUser.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            primaryStage.setTitle("Affichage");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }else
        {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("PASSWORD INCORRECT");
            alert.showAndWait();
        }

    }
}
