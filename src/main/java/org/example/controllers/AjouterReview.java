package org.example.controllers;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
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
    private HBox emojiContainer;

    private final reviewService rs = new reviewService();

    @FXML
    public void Browse(ActionEvent actionEvent) {
        Stage primaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            String fileUrl = selectedFile.toURI().toString();
            affichei.setText(fileUrl);
        }
    }

    @FXML
    public void ajouterAvis() {
        String commentaire = com.getText();
        String image = affichei.getText();
        double ratingValue = rate.getRating();
        review r = new review();
        r.setCom(commentaire);
        r.setRating(String.valueOf(ratingValue));
        boolean ajoutReussi = rs.ajouter(r);
        if (ajoutReussi) {
            com.clear();
            affichei.clear();
            rate.setRating(0);
        } else {
            System.out.println("Erreur lors de l'ajout de l'avis dans la base de données.");
        }
    }

    @FXML
    void handleEmojiButton(ActionEvent event) {
        emojiContainer.getChildren().clear(); // Clear existing emojis

        // Add heart-related and face-related emojis to the HBox
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
    }
}
