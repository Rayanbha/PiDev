package Controllers.Reviews;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.review;
import services.reviewService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class AffichageReview {

    @FXML
    private ListView<review> reviewListView;

    private final reviewService rs = new reviewService();

    @FXML
    void initialize() {
        try {
            refreshDisplay();
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

    @FXML
    public void sortReviewsByRating() {
        ObservableList<review> reviews = reviewListView.getItems();
        reviews.sort(Comparator.comparingDouble(o -> Double.parseDouble(o.getRating())));
    }

    private void openUpdateReviewWindow(review selectedReview) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UpdateReview.fxml"));
            Parent root = loader.load();
            UpdateReviewController updateReviewController = loader.getController();
            updateReviewController.initData(selectedReview, this); // Pass reference to AffichageReview
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Mise à jour de la revue");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void refreshDisplay() {
        try {
            List<review> reviewList = rs.fetch();
            ObservableList<review> observableList = FXCollections.observableList(reviewList);
            reviewListView.setItems(observableList);
            reviewListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("clicked on " + reviewListView.getSelectionModel().getSelectedItem());
                    review selectedItem = reviewListView.getSelectionModel().getSelectedItem();

                   if (selectedItem.getImageUrl() != null) {
                       Image image = new Image(selectedItem.getImageUrl());
                       ImageView imageView = new ImageView(image);

                       imageView.setPreserveRatio(true);
                       imageView.isResizable();


                       final Stage dialog = new Stage();
                       dialog.initModality(Modality.APPLICATION_MODAL);
                       VBox dialogVbox = new VBox(20);
                       dialogVbox.getChildren().add(imageView);
                       imageView.fitWidthProperty().bind(dialogVbox.widthProperty());
                       imageView.fitHeightProperty().bind(dialogVbox.heightProperty());
                       Scene dialogScene = new Scene(dialogVbox, 400, 300);
                       dialog.setScene(dialogScene);
                       dialog.showAndWait();
                   }

                }
            });
            reviewListView.setCellFactory(param -> new ListCell<review>() {
                @Override
                protected void updateItem(review item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        if (item.getImageUrl() != null && !item.getImageUrl().isEmpty()) {
                            ImageView imageView = new ImageView(new Image(item.getImageUrl()));
                            imageView.setFitWidth(100);
                            imageView.setPreserveRatio(true);
                            setGraphic(imageView);
                            ///////


                        }
                        setText(" - " + item.getRating() + " - " + item.getCom());
                        double rating = Double.parseDouble(item.getRating());
                        if (rating == 5.0) {
                            setStyle("-fx-background-color: #00FF00;");
                        } else if (rating >= 2.0 && rating <= 4.0) {
                            setStyle("-fx-background-color: #FFA500;");
                        } else if (rating == 1.0) {
                            setStyle("-fx-background-color: #FF0000;");
                        } else {
                            setStyle(null);
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
