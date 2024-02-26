package org.example.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.models.review;
import org.example.services.reviewService;

import java.util.List;

public class AffichageReview {

    @FXML
    private ListView<review> notea;

    @FXML
    private ListView<review> coma;

    @FXML
    private ListView<review> pha;

    private final reviewService rs = new reviewService();

    @FXML
    void initialize() {
        try {
            List<review> reviewList = rs.fetch();
            ObservableList<review> observableList = FXCollections.observableList(reviewList);
            notea.setItems(observableList);
            coma.setItems(observableList);
            pha.setItems(observableList);
            notea.setCellFactory(param -> new ListCell<review>() {
                @Override
                protected void updateItem(review item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getRating());
                    }
                }
            });

            coma.setCellFactory(param -> new ListCell<review>() {
                @Override
                protected void updateItem(review item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getCom());
                    }
                }
            });

            pha.setCellFactory(param -> new ListCell<review>() {
                @Override
                protected void updateItem(review item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        // Assuming your review class has a method to get image URL
                        String imageUrl = item.getImageUrl();
                        if (imageUrl != null && !imageUrl.isEmpty()) {
                            ImageView imageView = new ImageView(new Image(imageUrl));
                            imageView.setFitWidth(80);
                            imageView.setFitHeight(80);
                            setGraphic(imageView);
                        } else {
                            setText("No Image");
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Delete(ActionEvent actionEvent) {
        review selectedRecipe = notea.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            notea.getItems().remove(selectedRecipe);
            reviewService rc = null;
            rc.supprimer(selectedRecipe.getIdrevw());
        } else {
            System.out.println("Veuillez sélectionner une recette à supprimer.");
        }
    }

    public void Update(ActionEvent actionEvent) {
        review selectedRecipe = notea.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            System.out.println("Vous pouvez mettre à jour la recette sélectionnée : " + selectedRecipe.getIdrevw());
        } else {
            System.out.println("Veuillez sélectionner une recette à mettre à jour.");
        }
    }

}
