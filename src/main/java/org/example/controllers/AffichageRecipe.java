package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import org.example.models.recipe;
import org.example.services.recipeService;

import java.util.List;

public class AffichageRecipe {

    @FXML
    private ListView<recipe> nomtLV;

    @FXML
    private ListView<recipe> phototLV;

    @FXML
    private ListView<recipe> ingtLV;

    @FXML
    private ListView<recipe> instLV;




    private final recipeService rc = new recipeService();

    @FXML
    void initialize() {
        try {
            List<recipe> recipeList = rc.fetch();
            ObservableList<recipe> observableList = FXCollections.observableList(recipeList);
            nomtLV.setItems(observableList);
            phototLV.setItems(observableList);
            ingtLV.setItems(observableList);
            instLV.setItems(observableList);

            nomtLV.setCellFactory(param -> new ListCell<recipe>() {
                @Override
                protected void updateItem(recipe item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getName());
                    }
                }
            });

            phototLV.setCellFactory(param -> new ListCell<recipe>() {
                @Override
                protected void updateItem(recipe item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        ImageView imageView = new ImageView(item.getImageUrl());
                        imageView.setFitWidth(80);
                        imageView.setFitHeight(80);
                        setGraphic(imageView);
                    }
                }
            });

            ingtLV.setCellFactory(param -> new ListCell<recipe>() {
                @Override
                protected void updateItem(recipe item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getIngrs());
                    }
                }
            });

            instLV.setCellFactory(param -> new ListCell<recipe>() {
                @Override
                protected void updateItem(recipe item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getInstrs());
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Delete(ActionEvent actionEvent) {
        recipe selectedRecipe = nomtLV.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            nomtLV.getItems().remove(selectedRecipe);
            rc.supprimer(selectedRecipe.getIdrecp());
        } else {
            System.out.println("Veuillez sélectionner une recette à supprimer.");
        }
    }
    public void Update(ActionEvent actionEvent) {
        recipe selectedRecipe = nomtLV.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            System.out.println("Vous pouvez mettre à jour la recette sélectionnée : " + selectedRecipe.getName());
        } else {
            System.out.println("Veuillez sélectionner une recette à mettre à jour.");
        }
    }
}