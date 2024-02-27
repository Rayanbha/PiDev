package com.example.pidevv.controllers;

import com.example.pidevv.models.comment;
import com.example.pidevv.models.forumpost;
import com.example.pidevv.services.CommentService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherDetails extends comment {

    @FXML
    private Label selectedItemLabel;

    @FXML
    private ImageView imv;
    private comment selectedPost;
    @FXML
    private TextArea commentTextArea;

    private int selectedPostId;

    @FXML
    private VBox commentsContainer;


    @FXML
    public void initData(String selectedItem, Image image, int postId) {

        selectedItemLabel.setText(selectedItem);

        // Afficher l'image dans l'ImageView
        imv.setImage(image);

        selectedPostId = postId;
        selectedPost = new comment(selectedPostId);


    }


    @FXML
    public void saveComment(javafx.event.ActionEvent event) throws SQLException {

        String comment = commentTextArea.getText();
        selectedPost.setCommentContent(comment);
        // Enregistrer le commentaire dans votre service de commentaires
        CommentService commentService = new CommentService();
        commentService.ajouter(selectedPost);

        Label newCommentLabel = new Label(getCommentContent());
        commentsContainer.getChildren().add(newCommentLabel);

        // Vous pouvez également rafraîchir l'affichage ou afficher un message de confirmation

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Merci !");
        alert.setHeaderText(null);
        alert.setContentText("Merci pour votre commentaire !");

        alert.showAndWait();

        // Rediriger vers l'interface InterfaceComplete
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfaceComplete.fxml"));
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
}




