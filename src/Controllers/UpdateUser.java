package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import models.Wallet;
import models.user;
import services.UserService;
import services.WalletService;
import javafx.scene.Node;


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

    @FXML
    private Circle circle;
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
                root = FXMLLoader.load(getClass().getResource("/UI/AffichageUser.fxml"));
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
    private WalletService walletser=new WalletService();
    @FXML
    void ajoutwallet(ActionEvent event) {
        int id=userToUpdate.getId();
        Wallet wallet=new Wallet(0,id);
        walletser.add(wallet);
    }

    @FXML
    private Label wallet;

    WalletService walletService=new WalletService();

    @FXML
    void wallet(MouseEvent event) {


        try {
            Stage currentStage=(Stage)wallet.getScene().getWindow();
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/WalletMenu.fxml"));
            Parent root = loader.load();
            WalletMenu walletc=loader.getController();
            walletc.initWallet(userToUpdate.getId());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
