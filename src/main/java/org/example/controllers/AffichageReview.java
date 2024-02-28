package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.example.models.review;
import org.example.services.reviewService;

import java.io.IOException;
import java.util.List;

public class AffichageReview {

    @FXML
    private ListView<review> reviewListView;

    private final reviewService rs = new reviewService();

    @FXML
    void initialize() {
        try {
            List<review> reviewList = rs.fetch();
            ObservableList<review> observableList = FXCollections.observableList(reviewList);
            reviewListView.setItems(observableList);
            reviewListView.setCellFactory(param -> new ListCell<review>() {
                @Override
                protected void updateItem(review item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        // Customize cell display as per your requirement
                        setText(" - " + item.getRating() + " - " + item.getCom() + " - " + item.getImageUrl());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Delete() {
        review selectedReview = reviewListView.getSelectionModel().getSelectedItem();
        if (selectedReview != null) {
            reviewListView.getItems().remove(selectedReview);
            rs.supprimer(selectedReview.getIdrevw());
        } else {
            System.out.println("Veuillez sélectionner une revue à supprimer.");
        }
    }

    @FXML
    public void Update() {
        review selectedReview = reviewListView.getSelectionModel().getSelectedItem();
        if (selectedReview != null) {
            System.out.println("Ouverture de la fenêtre de mise à jour pour la revue : " + selectedReview.getIdrevw());
            openUpdateReviewWindow(selectedReview);
        } else {
            System.out.println("Veuillez sélectionner une revue à mettre à jour.");
        }
    }

    private void openUpdateReviewWindow(review selectedReview) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateReview.fxml"));
            Parent root = loader.load();

            // Passer la revue sélectionnée au contrôleur de la fenêtre de mise à jour
            UpdateReviewController updateReviewController = loader.getController();
            updateReviewController.initData(selectedReview);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Mise à jour de la revue");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
