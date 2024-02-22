package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.models.recipe;
import org.example.services.recipeService;

import java.io.File;
import java.io.IOException;

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

    @FXML
    public void Browse(ActionEvent actionEvent) {
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
    void boutouna(ActionEvent event) {
        String nomRecette = txtin.getText();
        String ingredients = txting.getText();
        String instructions = txtrect.getText();
        if (!nomRecette.isEmpty() && !ingredients.isEmpty() && !instructions.isEmpty()) {
            ps.ajouter(new recipe(nomRecette, ingredients, instructions));
            txtin.clear();
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}




