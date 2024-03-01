package com.example.pidevv.controllers;

import com.example.pidevv.models.forumpost;
import com.example.pidevv.services.CommentService;
import com.example.pidevv.services.ForumpostService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import com.example.pidevv.models.comment;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class InterfaceComplete extends CommentService implements Initializable {

    @FXML
    private ListView<forumpost> listView;

    private ForumpostService forumpostService = new ForumpostService();
    private CommentService commentService = new CommentService();

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(forumpost post, boolean empty) {
                super.updateItem(post, empty);

                if (empty || post == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    VBox vBox = new VBox();
                    vBox.setSpacing(10);

                    Text titleText = new Text("Titre : " + post.getTitle());
                    titleText.setStyle("-fx-font-weight: bold");
                    vBox.getChildren().add(titleText);

                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);

                    try {
                        String path = post.getContent();
                        File imageFile = new File(path);
                        Image image = new Image(imageFile.toURI().toString());
                        imageView.setImage(image);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    vBox.getChildren().add(imageView);

                    try {
                        post.setComments(commentService.getCommentsByPostId(post.getPostId()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setContentText("Erreur lors de la récupération des commentaires");
                        alert.showAndWait();
                    }

                    for (comment comment : post.getComments()) {
                        Text commentText = new Text("Commentaire : " + comment.getCommentContent());
                        vBox.getChildren().add(commentText);
                    }

                    setGraphic(vBox);
                }
            }
        });

        MenuItem editItem = new MenuItem("Éditer");
        MenuItem deleteItem = new MenuItem("Supprimer");

        editItem.setOnAction(this::editItem);
        deleteItem.setOnAction(this::deleteItem);

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(deleteItem, editItem);
        listView.setContextMenu(contextMenu);

        try {
            listView.setItems(FXCollections.observableArrayList(forumpostService.recuperer()));
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Erreur lors de la récupération des posts");
            alert.showAndWait();
        }
    }

    private void deleteItem(ActionEvent event) {
        forumpost selectedPost = listView.getSelectionModel().getSelectedItem();

        if (selectedPost != null) {
            comment selectedComment = getSelectedCommentFromPost(selectedPost);

            if (selectedComment != null) {
                Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationDialog.setTitle("Confirmation de suppression");
                confirmationDialog.setHeaderText(null);
                confirmationDialog.setContentText("Êtes-vous sûr de vouloir supprimer ce commentaire ?");

                Optional<ButtonType> result = confirmationDialog.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    try {
                        commentService.supprimer(selectedComment.getCommentId());
                        refreshListView();
                        showAlert(Alert.AlertType.INFORMATION, "Suppression réussie", "Le commentaire a été supprimé avec succès.");
                    } catch (SQLException e) {
                        showAlert(Alert.AlertType.ERROR, "Erreur lors de la suppression", "Une erreur s'est produite lors de la suppression du commentaire : " + e.getMessage());
                    }
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Aucun commentaire sélectionné", "Veuillez sélectionner un commentaire à supprimer.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Aucun post sélectionné", "Veuillez sélectionner un post contenant le commentaire à supprimer.");
        }
    }

    private void editItem(ActionEvent event) {
        forumpost selectedPost = listView.getSelectionModel().getSelectedItem();

        if (selectedPost != null) {
            comment selectedComment = getSelectedCommentFromPost(selectedPost);

            if (selectedComment != null) {
                try {
                    TextInputDialog dialog = new TextInputDialog(selectedComment.getCommentContent());
                    dialog.setTitle("Édition de commentaire");
                    dialog.setHeaderText("Modifier le commentaire");
                    dialog.setContentText("Nouveau contenu du commentaire:");

                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        String newContent = result.get();
                        selectedComment.setCommentContent(newContent);
                        commentService.modifier(selectedComment);
                        refreshListView();
                        showAlert(Alert.AlertType.INFORMATION, "Modification réussie", "Le commentaire a été modifié avec succès.");
                    }
                } catch (SQLException e) {
                    showAlert(Alert.AlertType.ERROR, "Erreur lors de l'édition", "Une erreur s'est produite lors de l'édition du commentaire : " + e.getMessage());
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Aucun commentaire sélectionné", "Veuillez sélectionner un commentaire à éditer.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Aucun post sélectionné", "Veuillez sélectionner un post contenant le commentaire à éditer.");
        }
    }

    private void refreshListView() {
        try {
            listView.setItems(FXCollections.observableArrayList(forumpostService.recuperer()));
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur de rafraîchissement", "Erreur lors du rafraîchissement de la liste des posts : " + e.getMessage());
        }
    }

    private comment getSelectedCommentFromPost(forumpost post) {
        List<comment> comments = post.getComments();
        if (comments != null && !comments.isEmpty()) {
            return comments.get(0);
        } else {
            return null;
        }
    }
}
