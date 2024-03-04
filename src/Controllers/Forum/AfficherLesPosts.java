package Controllers.Forum;

import models.forumpost;
import services.ForumpostService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AfficherLesPosts extends forumpost implements Initializable {


    @FXML
    public Button detail;
    @FXML
    private ListView<forumpost> listView;

    @FXML
    private ContextMenu contextMenu;

    private ForumpostService fp = new ForumpostService();

  /* public void initialize(URL url, ResourceBundle resourceBundle) {
       listView.setCellFactory(param -> new ListCell<>() {
           @Override
           protected void updateItem(forumpost forumpost, boolean empty) {
               super.updateItem(forumpost, empty);

               if (empty || forumpost == null) {
                   setText(null);
                   setGraphic(null);
               } else {
                   GridPane container = new GridPane();

                   // Définition du style pour le texte et l'image
                   String nameStyle = "-fx-fill: #8b7080; -fx-font-size: 20;";
                   String imageStyle = "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0.5, 0, 0);";

                   // Création du texte et de l'image
                   Text nameData = new Text(forumpost.getTitle() + "\n");
                   nameData.setStyle(nameStyle);

                   String Path = forumpost.getContent();
                   Image demonstrationImage = new Image(new File(Path).toURI().toString());
                   ImageView imageView = new ImageView(demonstrationImage);
                   imageView.setFitHeight(100);
                   imageView.setFitWidth(100);
                   imageView.setStyle(imageStyle);

                   // Ajout des éléments à la grille
                   container.add(imageView, 0, 0); // Ajoute l'image dans la colonne 0
                   container.add(nameData, 1, 0);  // Ajoute le texte dans la colonne 1

                   // Configuration des contraintes de colonne
                   ColumnConstraints col1 = new ColumnConstraints(100);
                   ColumnConstraints col2 = new ColumnConstraints(225);
                   container.getColumnConstraints().addAll(col1, col2);

                   container.setHgap(30); // Espacement horizontal entre les éléments

                   setGraphic(container);

                   // Applique le style lors de la sélection
                   listView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<forumpost>) change -> {
                       while (change.next()) {
                           if (change.wasAdded()) {
                               container.setStyle("-fx-background-color: #FFDE59;");
                           } else {
                               container.setStyle(null); // Réinitialise le style lorsque la sélection est annulée
                           }
                       }
                   });
               }
           }
       });

       listView.getItems().addAll(fp.recuperer());

   }
   */
  public void initialize(URL url, ResourceBundle resourceBundle) {



      listView.setCellFactory(param -> new ListCell<>() {
          @Override
          protected void updateItem(forumpost forumpost, boolean empty) {
              super.updateItem(forumpost, empty);

              if (empty || forumpost == null) {
                  setText(null);
                  setGraphic(null);
              } else {
                  GridPane container = new GridPane();

                  // Définition du style pour le texte et l'image
                  String nameStyle = "-fx-fill: #000000; -fx-font-size: 20;";
                  String imageStyle = "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0.5, 0, 0);";

                  // Création du texte et de l'image
                  Text nameData = new Text(forumpost.getTitle() + "\n");
                  nameData.setStyle(nameStyle);

                  String Path = forumpost.getContent();
                  Image demonstrationImage = new Image(new File(Path).toURI().toString());
                  ImageView imageView = new ImageView(demonstrationImage);
                  imageView.setFitHeight(100);
                  imageView.setFitWidth(100);
                  imageView.setStyle(imageStyle);


                  // Ajout des éléments à la grille et centrage
                  container.add(imageView, 0, 0);
                  container.add(nameData, 1, 0);
                  container.setAlignment(Pos.CENTER); // Centre les éléments dans la grille

                  // Configuration des contraintes de colonne
                  ColumnConstraints col1 = new ColumnConstraints();
                  col1.setHalignment(HPos.CENTER); // Centre la colonne pour l'image
                  ColumnConstraints col2 = new ColumnConstraints();
                  col2.setHalignment(HPos.CENTER); // Centre la colonne pour le texte
                  container.getColumnConstraints().addAll(col1, col2);

                  container.setHgap(30); // Espacement horizontal entre les éléments

                  setGraphic(container);
              }

          }
      });
      ContextMenu contextMenu = new ContextMenu();

      // Création des éléments du menu
      MenuItem editItem = new MenuItem("Éditer");
      MenuItem deleteItem = new MenuItem("Supprimer");

      // Liaison des actions aux éléments du menu
      editItem.setOnAction(this::editItem);
      deleteItem.setOnAction(this::deleteItem);

      // Ajout des éléments au menu
      contextMenu.getItems().addAll(editItem, deleteItem);

      // Définition du ContextMenu pour la ListView
      listView.setContextMenu(contextMenu);


      listView.getItems().addAll(fp.recuperer());
  }

    public void editItem(ActionEvent event) {
        forumpost selectedPost = listView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            // Créer une boîte de dialogue d'édition
            TextInputDialog dialog = new TextInputDialog(selectedPost.getTitle());
            dialog.setTitle("Modifier le titre");
            dialog.setHeaderText(null);
            dialog.setContentText("Nouveau titre:");

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<String> result = dialog.showAndWait();

            // Mettre à jour le titre si l'utilisateur a saisi une nouvelle valeur
            result.ifPresent(newTitle -> {
                selectedPost.setTitle(newTitle);
                // Rafraîchir la cellule de la ListView pour refléter les changements
                listView.refresh();
                // Afficher un message pour indiquer que la modification est terminée
                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                confirmationAlert.setTitle("Modification terminée");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("Le titre du post a été modifié avec succès.");
                confirmationAlert.showAndWait();
            });
        }
    }

    public void deleteItem(ActionEvent event) {
        forumpost selectedPost = listView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            // Afficher une boîte de dialogue de confirmation
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation de la suppression");
            confirmationDialog.setHeaderText(null);
            confirmationDialog.setContentText("Êtes-vous sûr de vouloir supprimer ce post ?");

            // Obtenir la réponse de l'utilisateur
            Optional<ButtonType> result = confirmationDialog.showAndWait();

            // Supprimer le post si l'utilisateur confirme la suppression
            result.ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    listView.getItems().remove(selectedPost);
                    // Afficher un message pour indiquer que la suppression est terminée
                    Alert deletionAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletionAlert.setTitle("Suppression terminée");
                    deletionAlert.setHeaderText(null);
                    deletionAlert.setContentText("Le post a été supprimé avec succès.");
                    deletionAlert.showAndWait();
                }
            });
        }
    }


    @FXML
    public void detail (ActionEvent event) {
        ObservableList<forumpost> forumposts = listView.getSelectionModel().getSelectedItems();

        // Assurez-vous qu'il y a au moins un élément sélectionné
        if (!forumposts.isEmpty()) {
            forumpost selectedItem = forumposts.get(0);
            String selectedItemContent = selectedItem.getContent(); // Supposons que selectedItem.getContent() retourne l'URL de l'image sous forme de chaîne

// Créer une instance de Image en utilisant l'URL de l'image en tant que chaîne
            Image image = new Image(selectedItemContent);

            // Chargez l'interface AfficherDetail
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AfficherDetails.fxml"));
                Parent root = loader.load();

                // Passez les données à l'interface AfficherDetails
                AfficherDetails af = loader.getController();
                af.initData(selectedItem.getTitle(), image, selectedItem.getPostId());

                // Affichez la nouvelle interface utilisateur
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }}







