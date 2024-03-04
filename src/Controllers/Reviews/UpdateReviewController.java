package Controllers.Reviews;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.controlsfx.control.Rating;
import models.review;
import services.reviewService;

import java.util.List;

public class UpdateReviewController {

    @FXML
    private TextField commentTextField;

    @FXML
    private HBox emojiContainer;
    @FXML
    private Rating rate;
    private final reviewService rs = new reviewService();
    private review selectedReview;
    private AffichageReview affichageReviewController;

    public void initData(review selectedReview, AffichageReview affichageReviewController) {
        this.selectedReview = selectedReview;
        this.affichageReviewController = affichageReviewController;
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
            rs.modifier(selectedReview, newComment, newRating);
            System.out.println("Revue mise à jour avec succès : " + selectedReview.getIdrevw());
            affichageReviewController.refreshDisplay();
            closeUpdateWindow();
        } else {
            System.out.println("Erreur lors de la mise à jour de la revue.");
        }
    }
    @FXML
    void handleEmojiButton(ActionEvent event) {
        emojiContainer.getChildren().clear();
        List<Emoji> emojis = (List<Emoji>) EmojiManager.getAll();
        for (Emoji emoji : emojis) {
            if (isHeartOrFaceRelated(emoji)) {
                Button emojiButton = new Button(emoji.getUnicode());
                emojiButton.setOnAction(e -> {
                    commentTextField.appendText(emoji.getUnicode());
                });
                emojiContainer.getChildren().add(emojiButton);
            }
        }
    }

    private boolean isHeartOrFaceRelated(Emoji emoji) {
        String[] keywords = {"heart", "face"};
        for (String keyword : keywords) {
            if (emoji.getDescription().toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private void closeUpdateWindow() {
        commentTextField.getScene().getWindow().hide();
    }
}
