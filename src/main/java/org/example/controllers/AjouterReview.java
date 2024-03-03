package org.example.controllers;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import org.example.models.recipe;
import org.example.models.review;
import org.example.services.reviewService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AjouterReview {

    @FXML
    private TextField com;

    @FXML
    private TextField affichei;

    @FXML
    private Rating rate;

    @FXML
    private Label recipeNameLabel;

    @FXML
    private HBox emojiContainer;

    private recipe selectedRecipe;

    private final reviewService rs = new reviewService();
    public void initialize() {
        if (selectedRecipe != null) {
            recipeNameLabel.setText(selectedRecipe.getName());
        }
    }

    public void initData(recipe selectedRecipe) {
        this.selectedRecipe = selectedRecipe;
    }

    @FXML
    public void Browse(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String fileUrl = selectedFile.toURI().toString();
            affichei.setText(fileUrl);
        }
    }

    @FXML
    public void ajouterAvis() {

        String commentaire = com.getText().toLowerCase();
        String image = affichei.getText();
        double ratingValue = rate.getRating();
        String[] motsVulgaires = {"bite", "pute", "fuck","salope","merde","chier","fdp"};
        boolean commentaireValide = true;
        for (String mot : motsVulgaires) {
            if (commentaire.contains(mot)) {
                commentaireValide = false;
                break;
            }
        }
        if (commentaireValide) {
            review r = new review();
            r.setCom(com.getText().toLowerCase());
            r.setImageUrl(affichei.getText());
            r.setRating(String.valueOf(rate.getRating()));

            // Associate the review with the selected recipe
            if (selectedRecipe != null) {
                r.setRecipeName(selectedRecipe.getName()); // Set the recipe name for the review
                boolean ajoutReussi = rs.ajouter(r);
                if (ajoutReussi) {
                    com.clear();
                    affichei.clear();
                    rate.setRating(0);
                } else {
                    System.out.println("Erreur lors de l'ajout de l'avis dans la base de données.");
                }
            } else {
                System.out.println("Aucune recette sélectionnée pour ajouter la critique.");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le commentaire contient des mots vulgaires. Veuillez le modifier.");
            alert.showAndWait();
        }
    }

    @FXML
    void handleEmojiButton(ActionEvent event) {
        emojiContainer.getChildren().clear();

        List<Emoji> emojis = (List<Emoji>) EmojiManager.getAll();
        for (Emoji emoji : emojis) {
            if (isHeartOrFaceRelated(emoji)) {
                Button emojiButton = new Button(emoji.getUnicode());
                emojiButton.setOnAction(e -> com.appendText(emoji.getUnicode()));
                emojiContainer.getChildren().add(emojiButton);
            }
        }
    }

    private boolean isHeartOrFaceRelated(Emoji emoji) {
        String[] keywords = {"heart", "face"};
        for (String keyword : keywords) {
            if (emoji.getDescription().toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    void hezni(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AffichageReview.fxml"));
            Parent root = loader.load();
            Scene scene = com.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // You can implement navigation back to the recipe display window here
    }
}
