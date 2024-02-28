package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.models.recipe;
import org.example.services.recipeService;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AjouterRecipe {

    @FXML
    private TextField txtin;

    @FXML
    private Button imagem;

    @FXML
    private Button ajout;

    @FXML
    private TextField imagemt;

    @FXML
    private TextField txting;

    @FXML
    private TextField txtrect;

    @FXML
    private Button boutouna;

    private final recipeService ps = new recipeService();

    private final List<String> bannedWords = Arrays.asList("fuck", "shit", "bitch");

    @FXML
    public void Browse(ActionEvent event) {
        Stage primaryStage = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            String fileUrl = selectedFile.toURI().toString();
            imagemt.setText(fileUrl);
        }
    }

    @FXML
    public void setAjout(ActionEvent actionEvent) throws IOException {
        String nomRecette = txtin.getText();
        String image = imagemt.getText();
        String ingredients = txting.getText();
        String instructions = txtrect.getText();

        if (containsBannedWords(nomRecette) || containsBannedWords(ingredients) || containsBannedWords(instructions)) {

            showAlert("Veuillez ne pas utiliser de langage inapproprié.");
            return;
        }

        if (!nomRecette.isEmpty() && !ingredients.isEmpty() && !instructions.isEmpty()) {
            ps.ajouter(new recipe(nomRecette, ingredients, instructions));
            showAlert("Recette ajoutée avec succès!");
            clearFields();
        } else {
            showAlert("Veuillez remplir tous les champs.");
        }
    }

    @FXML
    void boutouna(ActionEvent event) {
        String nomRecette = txtin.getText();
        String image= imagemt.getText();
        String ingredients = txting.getText();
        String instructions = txtrect.getText();

        if (!nomRecette.isEmpty() && !ingredients.isEmpty() && !instructions.isEmpty()) {
            ps.ajouter(new recipe(nomRecette, ingredients, instructions));
            txtin.clear();
            imagemt.clear();
            txting.clear();
            txtrect.clear();

        } else {
            System.out.println("Veuillez remplir tous les champs.");
        }
    }

    @FXML
    void naviguer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AffichageRecipe.fxml"));
            Parent root = loader.load();
            Scene scene = txtin.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(root));
            AffichageRecipe controller = loader.getController();
            controller.initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean containsBannedWords(String text) {
        for (String word : bannedWords) {
            if (text.toLowerCase().contains(word)) {
                return true;
            }
        }
        return false;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        txtin.clear();
        imagemt.clear();
        txting.clear();
        txtrect.clear();
    }


}
