package com.example.pidevv.controllers;

import com.example.pidevv.models.forumpost;
import com.example.pidevv.services.ForumpostService;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class AjouterPost {
    public javafx.scene.control.Button importerId;
    @FXML
    private TextField titTF;
    @FXML
    private TextField conTF;

    @FXML
    private Button ajoutid;

    @FXML
    private Pane panee;


    @FXML
    private TextField malek ;

    private String imagePath ;

    @FXML
    public void choisirImage(ActionEvent event) {
        Stage primaryStage = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            String fileUrl = selectedFile.toURI().toString();
            malek.setText(fileUrl);
        }
        }



    @FXML
    public void IMPOST(ActionEvent event) throws IOException {
        String captcha = generateCaptcha();

        TextInputDialog dialog = new TextInputDialog();
        //ouvrir dialogue
        dialog.setTitle("CAPTCHA Verification");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter the CAPTCHA code:\n");
        Optional<String> result = dialog.showAndWait(); //afficher le dialogue  //optional se traite lorsque tu retournes une valeur nul
        if (result.isPresent()) {
            String userInput = result.get();
            if (!userInput.equals(captcha)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("CAPTCHA Verification Failed");
                alert.setHeaderText(null);
                alert.setContentText("The CAPTCHA code you entered is incorrect. Please try again.");
                alert.showAndWait();
                return;
            }
        }
        try {
            ForumpostService fps = new ForumpostService();
            String t = malek.getText().replace("%20", " ");
            t = t.replace("file:/", "").replace("/", "\\\\");
            String titre = titTF.getText();

            if (titre.isEmpty()) {
                afficherErreur("Veuillez entrer un titre.");
                return;
            }

            if (!estTitreValide(titre)) {
                // Titre invalide, afficher un message d'erreur
                afficherErreur("Le titre contient des caractères non autorisés.");
                return;
            }

            forumpost forumpost = new forumpost(titre, t);
            System.out.println(t);

            fps.ajouter(forumpost);
        } catch (SQLException e) {
            afficherErreur(e.getMessage());
        }

    }

    public boolean estTitreValide(String titre) {
        // Définir l'expression régulière autorisée
        String regex = "^[a-zA-Z0-9\\s\\p{Punct}&&[^/\\\\]]+$";
        // Vérifier si le titre correspond à l'expression régulière
        return titre.matches(regex) && !titre.contains("@");
    }

    public void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return stringBuilder.toString();
    }
    private String generateCaptcha() {
        // Generate a random 4-character CAPTCHA code consisting of letters and numbers
        String captcha = generateRandomString(4);

        // Clear the existing content in the pane
        panee.getChildren().clear();

        // Set up random number generator for colors
        Random random = new Random();

        // Add shapes and text to the CAPTCHA
        for (int i = 0; i < captcha.length(); i++) {
            char character = captcha.charAt(i);

            // Determine shape based on character type (letter or number)
            Shape shape;
            if (Character.isDigit(character)) {
                // If character is a number, add a rectangle
                shape = new Rectangle(30, 30);
            } else {
                // If character is a letter, do not add any shape
                shape = null;
            }

            if (shape != null) {

                shape.setLayoutX(i * 50 + 20);
                shape.setLayoutY(30);

                shape.setRotate(random.nextInt(40) - 20);

                // Add the shape to the pane
                panee.getChildren().add(shape);
            }
            Text text = new Text(String.valueOf(character));
            text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            text.setFill(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

            // Position the text
            text.setLayoutX(i * 50 + 30);
            text.setLayoutY(50);

            // Rotate the text slightly
            text.setRotate(random.nextInt(40) - 15); // Rotate between -5 and 5 degrees

            // Add the text to the pane
            panee.getChildren().add(text);
        }

        return captcha;
    }


   // @FXML
   @FXML
   void naviguer (ActionEvent event)  {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/AfficherLesPosts.fxml"));
                titTF.getScene().setRoot(root);

            }catch (IOException e ){
                e.printStackTrace();
            }

        }


//   void naviguer (ActionEvent event) throws IOException {
//       try {
//           // Charger le fichier FXML de la nouvelle fenêtre
//           FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherLesPosts.fxml"));
//           Parent root = loader.load();
//
//           // Créer une nouvelle scène
//           Scene scene = new Scene(root);
//
//           // Créer une nouvelle fenêtre (stage)
//           Stage stage = new Stage();
//           stage.setScene(scene);
//
//           // Afficher la nouvelle fenêtre
//           stage.show();
//
//           // Récupérer la fenêtre actuelle
//           Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//           // Fermer la fenêtre actuelle
//           currentStage.close();
//       } catch (IOException e) {
//           e.printStackTrace();
//       }

//       FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListLesPosts.fxml"));
//       Parent root = loader.load();
//       Stage stage = new Stage();
//       stage.setScene(new Scene(root));
//       stage.show();
//
//   }




}

/*void naviguer (ActionEvent event)  {
    try {
    // Création de la liste d'éléments pour la ListView
   /* ObservableList<String> items = FXCollections.observableArrayList(
    );
        while (resultSet.next()) {
            String titre = resultSet.getString("titre");
            items.add(titre);
        }

    // Création de la ListView
    ListView<String> listView = new ListView<>(items);

    // Création du conteneur pour la ListView
    VBox root = new VBox(listView);

    // Création de la scène avec le nouveau contenu
    Scene scene = new Scene(root, 300, 200);

    // Récupération de la référence à la scène à partir de n'importe quel nœud dans la scène actuelle
    Stage stage = (Stage) titTF.getScene().getWindow();

    // Changement de la scène dans la fenêtre
    stage.setScene(scene);

} catch (Exception e) {
    e.printStackTrace();
}




}}






*/
