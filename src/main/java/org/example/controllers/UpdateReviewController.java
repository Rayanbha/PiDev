package org.example.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controlsfx.control.Rating;
import org.example.models.review;
import org.example.services.reviewService;

public class UpdateReviewController {

    @FXML
    private TextField commentTextField;

    @FXML
    private Rating rate;

    private final reviewService rs = new reviewService();

    private review selectedReview;

    public void initData(review selectedReview) {
        this.selectedReview = selectedReview;
        commentTextField.setText(selectedReview.getCom());

        if (selectedReview != null) {
            try {
                double ratingValue = Double.parseDouble(selectedReview.getRating());

                if (ratingValue >= 0 && ratingValue <= 5) {
                    rate.setRating(ratingValue);
                } else {
                    System.out.println("La note (rating) est invalide : " + ratingValue);
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur de format pour la note (rating) : " + selectedReview.getRating());
            }
        }
    }

    @FXML
    public void updateReview() {
        String newComment = commentTextField.getText();
        double newRating = rate.getRating();
        if (selectedReview != null) {
            selectedReview.setRating(String.valueOf(newRating));
            selectedReview.setCom(newComment);
            rs.modifier(selectedReview, newComment, newRating); // Mettez à jour avec le nouveau rating
            System.out.println("Revue mise à jour avec succès : " + selectedReview.getIdrevw());
        } else {
            System.out.println("Erreur lors de la mise à jour de la revue.");
        }
    }

}


