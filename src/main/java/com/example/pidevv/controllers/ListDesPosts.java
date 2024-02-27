package com.example.pidevv.controllers;

import com.example.pidevv.models.forumpost;
import com.example.pidevv.services.ForumpostService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListDesPosts implements Initializable {
    @FXML
    private FlowPane flow;
    @FXML
    private ScrollPane scroll;

    // @FXML
    // private Button ajoutid;

    private forumpost oeuvreSelectionnee;

    @FXML
    public void afficherPlat() {

        ForumpostService forumService = new ForumpostService();
        try {
            List<forumpost> forumpostList = forumService.recuperer();
            for (forumpost fp : forumpostList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/postItem.FXML"));
                Parent forumNode = loader.load();
                postItem controller = loader.getController();
                controller.setData(fp);
                // forumNode.setOnMouseClicked(event -> afficherDetailsOeuvre(oeuvre));
                flow.getChildren().add(forumNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scroll.setFitToWidth(true);
        flow.setHgap(10); // Espacement horizontal entre les éléments
        flow.setVgap(10);
        afficherPlat();

    }


}
