package com.example.pidevv.test;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;





import java.io.IOException;
import java.sql.*;

public class MainFX extends Application {


    // public static void main(String[] args) {
    //  launch(args);
    //}

    @Override
    public void start(Stage primaryStage) {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterPost.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("AjouterPost");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }}

    public static void main(String[] args) {
        launch(args);
    }
}





    /*ObservableList<String> items = FXCollections.observableArrayList(
    );

    // Création de la ListView
    ListView<String> listView = new ListView<>(items);

    // Création du conteneur pour la ListView
    VBox root = new VBox(listView);

    // Création de la scène
    Scene scene = new Scene(root, 300, 200);

    // Définition de la scène et affichage de la fenêtre
        primaryStage.setScene(scene);
        primaryStage.setTitle("Liste View avec JavaFX");
        primaryStage.show();

}



   */


