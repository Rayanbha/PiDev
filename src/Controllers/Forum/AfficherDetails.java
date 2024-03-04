package Controllers.Forum;

import models.comment;
import services.CommentService;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class AfficherDetails extends comment {

    @FXML
    private Label selectedItemLabel;

    @FXML
    private TextArea com;

    @FXML
    private ImageView imv;
    private comment selectedPost;

    @FXML
    private TextArea commentTextArea;

    private int selectedPostId;

    @FXML
    private VBox commentsContainer;

    @FXML
    private HBox emojiContainer;

    @FXML
    public void initData(String selectedItem, Image image, int postId) {
        selectedItemLabel.setText(selectedItem);
        imv.setImage(image);
        selectedPostId = postId;
        selectedPost = new comment(selectedPostId);
    }

    @FXML
    public void saveComment(javafx.event.ActionEvent event) throws SQLException {
        String comment = commentTextArea.getText();
        selectedPost.setCommentContent(comment);

        // Vérification du commentaire avant de l'ajouter
        if (verif(comment)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Merci de ne pas utiliser de gros mots dans votre commentaire.");
            alert.showAndWait();
            return; // Arrêter l'exécution de la méthode si le commentaire contient des gros mots
        }

        // Ajouter le commentaire si aucun gros mot n'est détecté
        CommentService commentService = new CommentService();
        commentService.ajouter(selectedPost);

        Label newCommentLabel = new Label(getCommentContent());
        commentsContainer.getChildren().add(newCommentLabel);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Merci !");
        alert.setHeaderText(null);
        alert.setContentText("Merci pour votre commentaire !");
        alert.showAndWait();

        // Redirection vers l'interface InterfaceComplete
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/InterfaceComplete.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void sendMessage(javafx.event.ActionEvent event) {
        String userMessage = commentTextArea.getText();

        // Obtenir la réponse du modèle GPT
        String response = ChatGPT.getResponse(userMessage);

        // Afficher la réponse dans la zone de commentaire
        Label responseLabel = new Label("ChatGPT: " + response);
        commentsContainer.getChildren().add(responseLabel);

        // Effacer le champ de saisie après avoir envoyé le message
        commentTextArea.clear();
    }


    @FXML
    public void handleEmojiButton(javafx.event.ActionEvent event) {
        emojiContainer.getChildren().clear();

        List<Emoji> emojis = (List<Emoji>) EmojiManager.getAll();
        for (Emoji emoji : emojis) {
            if (isHeartOrFaceRelated(emoji)) {
                Button emojiButton = new Button(emoji.getUnicode());
                emojiButton.setOnAction(e -> {
                    commentTextArea.appendText(emoji.getUnicode());
                });
                emojiContainer.getChildren().add(emojiButton);
            }
        }
    }

    private boolean isHeartOrFaceRelated(Emoji emoji) {
        Pattern pattern = Pattern.compile("(heart|face)", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(emoji.getDescription()).find();
    }

    public boolean verif(String message) {
        List<String> badWords = List.of("Connard", "Va te faire foutre", "Ferme ta gueule", "Bâtard",
                "Sac à merde", "Casse-couilles", "Enfoiré", "Tête de bite",
                "Fini à la pisse", "Pétasse", "Abruti", "Pas la lumière à tous les étages", "Cn", "Sale merde",
                "Tocard", "Sous-merde", "Mange-merde", "Pouffiasse", "Va te faire cuire le cul","Enfoiré de chef", "Sac à merde de cuisinier", "Pétasse de serveuse", "Cul de sac à vin", "Branleur de barman", "Va te faire cuire le cul de cuisinier" ,
                "Sale cuisine", "Service déplorable", "Repas infecte", "Plat immangeable", "Hygiène douteuse",


                "Bercé un peu trop près du mur", "Petite bite", "Bouffon", "Branleur","bonjour", "Bonjour" , "Grognasse", "Couille molle",
                "Branquignole", "Fils de chien", "Salaud", "cul", "fuck", "pute", "ass", "bite", "cnne");

        for (String badWord : badWords) {
            if (message.toLowerCase().contains(badWord.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
