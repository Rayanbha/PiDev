package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.models.recipe;
import org.example.services.recipeService;

public class UpdateRecipeController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField ingredientsField;
    @FXML
    private TextField instructionsField;
    private recipe selectedRecipe;
    private final recipeService rc = new recipeService();
    private AffichageRecipe affichageRecipeController; // Add reference

    public void initData(recipe selectedRecipe, AffichageRecipe affichageRecipeController) {
        this.selectedRecipe = selectedRecipe;
        this.affichageRecipeController = affichageRecipeController; // Set reference
        nameField.setText(selectedRecipe.getName());
        ingredientsField.setText(selectedRecipe.getIngrs());
        instructionsField.setText(selectedRecipe.getInstrs());
    }

    @FXML
    void updateRecipe(ActionEvent event) {
        if (selectedRecipe != null) {
            selectedRecipe.setName(nameField.getText());
            selectedRecipe.setIngrs(ingredientsField.getText());
            selectedRecipe.setInstrs(instructionsField.getText());
            rc.modifier(selectedRecipe);
            affichageRecipeController.refreshDisplay(); // Call refresh method
            nameField.getScene().getWindow().hide();
        }
    }
}
