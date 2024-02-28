package org.example.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import org.example.models.review;
import org.example.services.reviewService;

import java.io.File;
import java.io.IOException;

public class AjouterReview {

    @FXML
    private TextField com;

    @FXML
    private ImageView logo2;

    @FXML
    private TextField affichei;

    @FXML
    private Rating rate;

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
    void hezni(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AffichageReview.fxml"));
            Parent root = loader.load();
            Scene scene = rate.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}