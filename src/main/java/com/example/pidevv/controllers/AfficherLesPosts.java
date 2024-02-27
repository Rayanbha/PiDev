package com.example.pidevv.controllers;

import com.example.pidevv.models.forumpost;
import com.example.pidevv.services.ForumpostService;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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


 /*  public void initialize(URL url, ResourceBundle resourceBundle) {
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
                   String nameStyle = "-fx-fill: #FFDE59; -fx-font-size: 20;";
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

                   // Définition du style pour la sélection
                   String selectedStyle = "-fx-background-color: #FFDE59";
                   // Applique le style lors de la sélection
                   selectedProperty().addListener((observable, oldValue, newValue) -> {
                       if (newValue) {
                           container.setStyle(selectedStyle);
                       } else {
                           container.setStyle(null); // Réinitialise le style lorsque la sélection est annulée
                       }
                   });
               }
           }
       });

       listView.getItems().addAll(fp.recuperer());
   }
*/
//                // Charger les données depuis la source de données
//                List<forumpost> forumposts = fetchDataFromDataSource();
//
//                // Afficher les données dans la ListView
//                listView.setItems(FXCollections.observableList(forumposts));
//
//                // Définir comment les éléments de la ListView seront affichés
//                listView.setCellFactory(param -> new ListCell<>() {
//                    @Override
//                    protected void updateItem(forumpost forumpost, boolean empty) {
//                        super.updateItem(forumpost, empty);
//                        if (empty || forumpost == null) {
//                            setText(null);
//                        } else {
//                            String s = forumpost.getTitle() + " - " + forumpost.getContent();
//                        }
//                    }
//                });
//            }
//
//            // Méthode de simulation pour récupérer les données
//            private List<forumpost> fetchDataFromDataSource() {
//                List<forumpost> forumposts = new ArrayList<>();
//                // Ajouter des articles simulés
//                forumposts.add(new forumpost("Titre de l'article 1", "Contenu de l'article 1"));
//                forumposts.add(new forumpost("Titre de l'article 2", "Contenu de l'article 2"));
//                forumposts.add(new forumpost("Titre de l'article 3", "Contenu de l'article 3"));
//                return forumposts;
//            }


    

//    @FXML
//
//    public void detail(ActionEvent actionEvent) {
//        forumpost selectedPost = tableView.getSelectionModel().getSelectedItem();
//        if (selectedPost != null) {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherDetails.fxml"));
//                Parent root = loader.load();
//
//                // Passer les données du post sélectionné à AfficherDetailsController
//                AfficherLesPosts controller = loader.getController();
//                controller.initData(selectedPost);
//
//                // Afficher la nouvelle interface utilisateur
//                Scene scene = new Scene(root);
//                Stage stage = new Stage();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Attention");
//            alert.setContentText("Veuillez sélectionner un post pour afficher les détails.");
//            alert.showAndWait();
//        }
//    }



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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherDetails.fxml"));
                Parent root = loader.load();

                // Passez les données à l'interface AfficherDetails
                AfficherDetails af = loader.getController();
                af.initData(selectedItem.getTitle(), image,selectedItem.getPostId());

                // Affichez la nouvelle interface utilisateur
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
            });
        }
    }

    public void deleteItem(ActionEvent event) {
        forumpost selectedPost = listView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            // Supprimer selectedPost de la liste et rafraîchir la ListView
            listView.getItems().remove(selectedPost);
        }
    }
}





            // Récupération des données depuis votre service
         /*   List<forumpost> forumposts = fps.recuperer();


            // Conversion de la liste en ObservableList
            ObservableList<forumpost> observableList = FXCollections.observableList(forumposts);

            // Définition de l'ObservableList comme les éléments de la ListView
            listView.setItems(observableList);

            // Facultatif : Personnalisation de l'affichage des éléments dans la ListView
            listView.setCellFactory(param -> new ListCell<forumpost>() {
                @Override
                protected void updateItem(forumpost item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getTitle() + " - " + item.getContent()); // Vous pouvez personnaliser l'affichage selon vos besoins
                    }
                }
            });
        } catch (SQLException e) {
            // En cas d'erreur, affichez une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
    }
}*/









