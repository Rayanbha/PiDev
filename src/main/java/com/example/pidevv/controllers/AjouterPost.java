package com.example.pidevv.controllers;

import com.example.pidevv.models.forumpost;
import com.example.pidevv.services.ForumpostService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class AjouterPost {
    public javafx.scene.control.Button importerId;
    @FXML
    private TextField titTF;
    @FXML
    private TextField conTF;

    @FXML
    private Button ajoutid;

    @FXML
    private TextField malek ;

    private String imagePath ;

    @FXML
    public void choisirImage(ActionEvent event) {
        Stage primaryStage = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            String fileUrl = selectedFile.toURI().toString();
            malek.setText(fileUrl);
        }
        }


@FXML
    public void IMPOST(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListLesPosts.fxml"));
//        Parent root = loader.load();
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show();
//
//    }

       try {
           ForumpostService fps =new ForumpostService() ;
           String t=malek.getText().replace("%20", " ");
           t=t.replace("file:/", "").replace("/", "\\\\");
           forumpost forumpost=new forumpost(
                   titTF.getText(), t);
           System.out.println(t);

           fps.ajouter(forumpost);
       } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText(e.getMessage());
           alert.showAndWait();
       }}


   // @FXML
   @FXML
   void naviguer (ActionEvent event)  {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/AfficherLesPosts.fxml"));
                titTF.getScene().setRoot(root);

            }catch (IOException e ){
                e.printStackTrace();
            }

        }


//   void naviguer (ActionEvent event) throws IOException {
//       try {
//           // Charger le fichier FXML de la nouvelle fenêtre
//           FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherLesPosts.fxml"));
//           Parent root = loader.load();
//
//           // Créer une nouvelle scène
//           Scene scene = new Scene(root);
//
//           // Créer une nouvelle fenêtre (stage)
//           Stage stage = new Stage();
//           stage.setScene(scene);
//
//           // Afficher la nouvelle fenêtre
//           stage.show();
//
//           // Récupérer la fenêtre actuelle
//           Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//           // Fermer la fenêtre actuelle
//           currentStage.close();
//       } catch (IOException e) {
//           e.printStackTrace();
//       }

//       FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListLesPosts.fxml"));
//       Parent root = loader.load();
//       Stage stage = new Stage();
//       stage.setScene(new Scene(root));
//       stage.show();
//
//   }




}

/*void naviguer (ActionEvent event)  {
    try {
    // Création de la liste d'éléments pour la ListView
   /* ObservableList<String> items = FXCollections.observableArrayList(
    );
        while (resultSet.next()) {
            String titre = resultSet.getString("titre");
            items.add(titre);
        }

    // Création de la ListView
    ListView<String> listView = new ListView<>(items);

    // Création du conteneur pour la ListView
    VBox root = new VBox(listView);

    // Création de la scène avec le nouveau contenu
    Scene scene = new Scene(root, 300, 200);

    // Récupération de la référence à la scène à partir de n'importe quel nœud dans la scène actuelle
    Stage stage = (Stage) titTF.getScene().getWindow();

    // Changement de la scène dans la fenêtre
    stage.setScene(scene);

} catch (Exception e) {
    e.printStackTrace();
}




}}






*/
