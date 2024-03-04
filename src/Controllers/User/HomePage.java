package Controllers.User;

import Controllers.Restaurant.ListeProduitClient;
import Controllers.Restaurant.ListeRestaurant;
import Controllers.Restaurant.ListeRestaurantClient;
import Controllers.Reviews.AffichageRecipe;
import Controllers.User.CompteUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.user;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class HomePage {


    @FXML
    private Pane EventB;

    @FXML
    private Pane ForumB;

    @FXML
    private Pane RecetteB;

    @FXML
    private Label acceuil;

    @FXML
    private Label booking;

    @FXML
    private Pane bookingB;

    @FXML
    private Label compte;

    @FXML
    private Pane compteB;

    @FXML
    private Label forum;

    @FXML
    private Label recette;

    @FXML
    private Label restaurant;

    @FXML
    private Pane restoB;

    @FXML
    private Label wallet;

    //------------------------------------------------------------------------------------------------------------------------------------------------
    Preferences prefs = Preferences.userNodeForPackage(this.getClass());

    UserService us = new UserService();
    user u = us.findcin(Integer.parseInt(prefs.get("cin", "not found")));

    @FXML
    void CompteB(MouseEvent event) {
        if (u.getRole().equals("Client")) {
            try {

                Stage stage=(Stage)compteB.getScene().getWindow();
                stage.close();
                Stage primaryStage=new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/CompteUser.fxml"));
                Parent root = loader.load();
                CompteUser compte=loader.getController();
                compte.initData(u);
                primaryStage.setTitle("Add");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Stage stage=(Stage)compteB.getScene().getWindow();
                stage.close();
                Stage primaryStage=new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AffichageUser.fxml"));
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
    void EventB(MouseEvent event) {
        if (u.getRole().equals("Admin")) {
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
    void ForumB(MouseEvent event) {

        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AjouterPost.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void RecetteB(MouseEvent event) {

        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AffichageRecipe.fxml"));
            Parent root = loader.load();
            AffichageRecipe controller = loader.getController();
            controller.initialize();
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void RestoB(MouseEvent event) {
        if (u.getRole().equals("Admin")) {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ListeRestaurant.fxml"));
                Parent root = loader.load();

                // Get the controller instance from the loader
                ListeRestaurant controller = loader.getController();

                // Pass the user information to the controller
                controller.InitUser(Integer.parseInt(prefs.get("cin", "not found"))); // Parse the integer value from preferences
                // Set up the stage and scene
                primaryStage.setTitle("Restaurant List");
                primaryStage.setScene(new Scene(root));

                // Show the stage
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ListeRestaurantClient.fxml"));
                Parent root = loader.load();

                // Get the controller instance from the loader
                ListeRestaurantClient controller = loader.getController();

                // Pass the user information to the controller
                controller.InitUser(Integer.parseInt(prefs.get("cin", "not found"))); // Parse the integer value from preferences
                System.out.println("AHAHAHAHAHAHAHA"+prefs.get("nom","not foundf"));
                // Set up the stage and scene
                primaryStage.setTitle("Restaurant List");
                primaryStage.setScene(new Scene(root));

                // Show the stage
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void acceuil(MouseEvent event) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/HomePage.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void compte(MouseEvent event) {
        if (u.getRole().equals("Client")) {
            try {

                Stage stage=(Stage)compteB.getScene().getWindow();
                stage.close();
                Stage primaryStage=new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/CompteUser.fxml"));
                Parent root = loader.load();
                CompteUser compte=loader.getController();
                compte.initData(u);
                primaryStage.setTitle("Add");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AffichageUser.fxml"));
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
    void forum(MouseEvent event) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AjouterPost.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void recette(MouseEvent event) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AffichageRecipe.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void restaurant(MouseEvent event) {
        if (u.getRole().equals("Admin")) {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ListeRestaurant.fxml"));
                Parent root = loader.load();

                // Get the controller instance from the loader
                ListeRestaurant controller = loader.getController();

                // Pass the user information to the controller
                controller.InitUser(Integer.parseInt(prefs.get("cin", "not found"))); // Parse the integer value from preferences

                // Set up the stage and scene
                primaryStage.setTitle("Restaurant List");
                primaryStage.setScene(new Scene(root));

                // Show the stage
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ListeRestaurantClient.fxml"));
                Parent root = loader.load();

                // Get the controller instance from the loader
                ListeRestaurantClient controller = loader.getController();

                // Pass the user information to the controller
                controller.InitUser(Integer.parseInt(prefs.get("cin", "not found"))); // Parse the integer value from preferences

                // Set up the stage and scene
                primaryStage.setTitle("Restaurant List");
                primaryStage.setScene(new Scene(root));

                // Show the stage
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void wallet(MouseEvent event) {
        if(u.getRole().equals("Client"))
        {
            try {

                Stage currentStage=(Stage)wallet.getScene().getWindow();
                currentStage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/WalletMenu.fxml"));
                Parent root = loader.load();
                WalletMenu walletc=loader.getController();
                int id=u.getId();
                walletc.initWallet(u, u.getId());
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    else
    {
        showAlert("ADMIN","YOU DONT HAVE WALLET");
    }

}

    @FXML
    void event(MouseEvent event) {

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}