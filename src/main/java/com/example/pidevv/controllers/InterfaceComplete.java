package com.example.pidevv.controllers;

import com.example.pidevv.models.forumpost;
import com.example.pidevv.services.CommentService;
import com.example.pidevv.services.ForumpostService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InterfaceComplete extends CommentService implements Initializable {

    @FXML
    private ListView<forumpost> listView;

    private ForumpostService forumpostService = new ForumpostService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(forumpost post, boolean empty) {
                super.updateItem(post, empty);

                if (empty || post == null) {
                    setText(null);
                } else {
                    // Créer un GridPane pour afficher le post
                    GridPane gridPane = new GridPane();
                    gridPane.setHgap(10);
                    gridPane.setVgap(5);

                    // Ajouter une colonne pour l'image
                    ColumnConstraints col1 = new ColumnConstraints();
                    col1.setHalignment(HPos.CENTER);
                    gridPane.getColumnConstraints().add(col1);

                    // Ajouter une colonne pour le texte
                    ColumnConstraints col2 = new ColumnConstraints();
                    col2.setHalignment(HPos.LEFT);
                    gridPane.getColumnConstraints().add(col2);

                    // Afficher l'image
                    ImageView imageView = new ImageView(new Image(post.getContent()));
                    imageView.setFitHeight(100); // Ajuster la hauteur de l'image
                    imageView.setPreserveRatio(true);
                    gridPane.add(imageView, 0, 0);

                    // Afficher le titre
                    Text titleText = new Text("Titre : " + post.getTitle());
                    gridPane.add(titleText, 1, 0);

                    // Afficher le commentaire
                    Text commentText = new Text("Commentaire : " + post.getComments());
                    gridPane.add(commentText, 1, 1);

                    // Ajouter le GridPane à la cellule de la ListView
                    setGraphic(gridPane);
                }
            }
        });

        // Charger les données des posts depuis le service et les afficher dans la ListView
        try {
            listView.setItems(FXCollections.observableArrayList(forumpostService.recuperer()));
        } catch (Exception e) {
            e.printStackTrace(); // Gérer l'exception correctement dans votre application
        }



    }}








