package org.example.controllers;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.models.recipe;
import org.example.services.recipeService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AffichageRecipe {

    @FXML
    private ListView<recipe> recipeLV;

    private final recipeService rc = new recipeService();


    void initialize() {
        try {
            List<recipe> recipeList = rc.fetch();
            recipeLV.setItems(FXCollections.observableList(recipeList));
            recipeLV.setCellFactory(param -> new ListCell<recipe>() {
                @Override
                protected void updateItem(recipe item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item.getName() + " - " + item.getIngrs() + " - " + item.getInstrs());

                        if (item.getImageUrl() != null && !item.getImageUrl().isEmpty()) {
                            ImageView imageView = new ImageView(new Image(item.getImageUrl()));
                            imageView.setFitWidth(100);
                            imageView.setPreserveRatio(true);
                            setGraphic(imageView);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Delete(ActionEvent event) {
        recipe selectedRecipe = recipeLV.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Supprimer la recette");
            alert.setContentText("Voulez-vous vraiment supprimer cette recette ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                recipeLV.getItems().remove(selectedRecipe);
                rc.supprimer(selectedRecipe.getIdrecp());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucune recette sélectionnée");
            alert.setContentText("Veuillez sélectionner une recette à supprimer.");
            alert.showAndWait();
        }
    }

    @FXML
    void Update(ActionEvent event) {
        recipe selectedRecipe = recipeLV.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            showUpdateRecipeUI(selectedRecipe);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucune recette sélectionnée");
            alert.setContentText("Veuillez sélectionner une recette à mettre à jour.");
            alert.showAndWait();
        }
    }
    private void showUpdateRecipeUI(recipe selectedRecipe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateRecipe.fxml"));
            Parent root = loader.load();

            UpdateRecipeController controller = loader.getController();

            controller.initData(selectedRecipe);

            Stage stage = new Stage();
            stage.setTitle("Mise à jour de la recette");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
