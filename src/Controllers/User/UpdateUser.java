package Controllers.User;

import Controllers.User.WalletMenu;
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
import org.mindrot.jbcrypt.BCrypt;
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
    private String oldpwd;

    @FXML
    private Circle circle;
    private UserService userService = new UserService();



    public void initData(user user) {
        this.userToUpdate = user;
        populateFields();
        System.out.println(userToUpdate.getHashedpwd()+"/"+userToUpdate.getSalt());
    }

    private void populateFields() {
        oldpwd=userToUpdate.getPwd();
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
            if(!oldpwd.equals(userToUpdate))
            {
                String salt = BCrypt.gensalt();
                System.out.println(salt);

                // Hash the password using the generated salt
                String hashedPassword = BCrypt.hashpw(pwdTF.getText(), salt);
                userService.update(new user(userToUpdate.getId(),"Client", PrenomTF.getText(), NomTF.getText(), EmailTF.getText(), Integer.parseInt(cinTF.getText()), pwdTF.getText(),hashedPassword, salt,1));

            }else {
                userService.update(new user(userToUpdate.getId(),"Client", PrenomTF.getText(), NomTF.getText(), EmailTF.getText(), Integer.parseInt(cinTF.getText()), pwdTF.getText(),userToUpdate.getHashedpwd(), userToUpdate.getSalt(),1));}
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
            int id=userToUpdate.getId();
            walletc.initWallet(userToUpdate, userToUpdate.getId());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
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
    @FXML
    private Label acceuil;
    @FXML
    void acceuil(MouseEvent event) {
        try {
            Stage currentStage=(Stage)acceuil.getScene().getWindow();
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/HomePage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void event(MouseEvent event) {
        if (userToUpdate.getRole().equals("Admin")) {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/events.fxml"));
                Parent root = loader.load();
                primaryStage.setTitle("Add");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/eventFront.fxml"));
                Parent root = loader.load();
                primaryStage.setTitle("Add");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    @FXML
    void compte(MouseEvent event) {


    }

    @FXML
    private Label forum;
    @FXML
    void forum(MouseEvent event) {
        try {
            Stage currentStage=(Stage)forum.getScene().getWindow();
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AjouterPost.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Label recette;
    @FXML
    void recette(MouseEvent event) {
        try {
            Stage currentStage=(Stage)recette.getScene().getWindow();
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AffichageRecipe.fxml"));
            Parent root = loader.load();
            WalletMenu walletc=loader.getController();
            int id=userToUpdate.getId();
            walletc.initWallet(userToUpdate, userToUpdate.getId());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private Label restaurant;
    @FXML
    void restaurant(MouseEvent event) {
        if(userToUpdate.getRole().equals("Client"))
        {
        try {
            Stage currentStage=(Stage)restaurant.getScene().getWindow();
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ListeRestaurantClient.fxml"));
            Parent root = loader.load();
            WalletMenu walletc=loader.getController();
            int id=userToUpdate.getId();
            walletc.initWallet(userToUpdate, userToUpdate.getId());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }}
        else {
            try {
                Stage currentStage=(Stage)restaurant.getScene().getWindow();
                currentStage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ListeRestaurant.fxml"));
                Parent root = loader.load();
                WalletMenu walletc=loader.getController();
                int id=userToUpdate.getId();
                walletc.initWallet(userToUpdate, userToUpdate.getId());
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


}