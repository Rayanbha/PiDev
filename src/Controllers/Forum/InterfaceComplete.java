package Controllers.Forum;

import models.comment;
import models.forumpost;
import services.CommentService;
import services.ForumpostService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

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
    public void initialize(URL location, ResourceBundle resources) {

        List<forumpost> posts = forumpostService.recuperer();

        // Trier les posts par le nombre total de likes sur les commentaires (ordre décroissant)
        Collections.sort(posts, (p1, p2) -> Integer.compare(getTotalLikes(p2), getTotalLikes(p1)));

        // Afficher les posts triés
        listView.setItems(FXCollections.observableArrayList(posts));

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

                    // Tri des commentaires par le nombre de likes (ordre décroissant)
                    post.getComments().sort(Comparator.comparingInt(comment::getLikes).reversed());

                    for (comment comment : post.getComments()) {
                        Text commentText = new Text("Commentaire : " + comment.getCommentContent());
                        vBox.getChildren().add(commentText);

                        // Créer un label pour représenter le "like" avec le symbole "thumbs up"
                        Label likeLabel = new Label("👍 " + comment.getLikes()); // Symbole "thumbs up" avec le nombre de likes
                        likeLabel.setStyle("-fx-text-fill: #121748;");
                        likeLabel.setFont(Font.font("Segoe UI Symbol", 16));// Définir la police et la taille

                        vBox.getChildren().add(likeLabel);
                        likeLabel.setOnMouseClicked(e -> {
                            // Incrémenter le nombre de likes
                            comment.setLikes(comment.getLikes() + 1);
                            // Mettre à jour le texte du label avec le nouveau nombre de likes
                            likeLabel.setText("👍 " + comment.getLikes());
                            // Vous pouvez également mettre à jour les likes dans la base de données ou toute autre source de données
                        });

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
    private int getTotalLikes(forumpost post) {
        int totalLikes = 0;
        List<comment> comments = post.getComments();
        if (comments != null) {
            for (comment comment : comments) {
                totalLikes += comment.getLikes();
            }
        }
        return totalLikes;
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
