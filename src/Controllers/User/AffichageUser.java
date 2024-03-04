package Controllers.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.user;
import services.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class AffichageUser {

    @FXML
    private Button Update;
    @FXML
    private ListView<user> Listview;

    private final UserService us=new UserService();
    @FXML
    private Label selection;
    @FXML
    void initialize() {
        List<user> users = us.read();
        ObservableList<user> observableList = FXCollections.observableList(users);
        Listview.setItems(observableList);
        Listview.setCellFactory(new Callback<ListView<user>, ListCell<user>>() {
            @Override
            public ListCell<user> call(ListView<user> param) {
                return new ListCell<user>() {
                    @Override
                    protected void updateItem(user item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            // Create HBox for horizontal layout
                            HBox hbox = new HBox(5);
                            hbox.setPadding(new Insets(5));
                            hbox.setAlignment(Pos.CENTER_LEFT); // Align to the left
                            hbox.setStyle("-fx-background-color: #WHITE;"); // Set item background color

                            // Create labels for user properties
                            Label prenomLabel = createLabel("Prénom : ", item.getPrenom());
                            Label nomLabel = createLabel("Nom : ", item.getNom());
                            Label emailLabel = createLabel("Email : ", item.getEmail());

                            // Create button for details
                            Button detailsButton = new Button("Détails");
                            detailsButton.setOnAction(event -> {
                                // Open a new window to display more information
                                openUserDetailsWindow(item);
                            });
                            detailsButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");

                            // Create button for deletion
                            Button deleteButton = new Button("Supprimer");
                            deleteButton.setOnAction(event -> {
                                // Delete the user
                                deleteuser(event, item);
                            });
                            deleteButton.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white; -fx-font-weight: bold;");

                            // Create ban or unban button based on user status
                            Button statusButton = null;
                            if (item.isStatus() == 1) {
                                statusButton = new Button("Ban");
                                statusButton.setOnAction(event -> {
                                    // Ban the user
                                    us.banuser(item);
                                    refreshListView();
                                });
                                statusButton.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white; -fx-font-weight: bold;");
                            } else {
                                statusButton = new Button("Unban");
                                statusButton.setOnAction(event -> {
                                    // Unban the user
                                    us.unbanuser(item);
                                    refreshListView();
                                });
                                statusButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
                            }

                            // Add labels and buttons to HBox
                            hbox.getChildren().addAll(prenomLabel, nomLabel, emailLabel, detailsButton, deleteButton, statusButton);

                            // Set cell content
                            setGraphic(hbox);
                        }
                    }
                };
            }
        });
    }

    // Helper method to create labels
    private Label createLabel(String labelText, String value) {
        Label label = new Label(labelText + " " + value);
        label.setStyle("-fx-font-weight: bold;");
        return label;
    }



    // Method to open user details window
    private void openUserDetailsWindow(user item) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UserDetail.fxml"));
            Parent root = loader.load();
            UserDetail userDetailController = loader.getController();
            userDetailController.initData(item);

            // Create a new stage for the user details window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteuser(ActionEvent event, user selectedUser) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete User");
        alert.setContentText("Are you sure you want to delete user " + selectedUser.getNom()+ " "+selectedUser.getPrenom()+ "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Call your UserService to delete the user
            UserService userService = new UserService();
            userService.delete(selectedUser);

            // Remove the user from the listview
            Listview.getItems().remove(selectedUser);
        }
    }
    private void refreshListView() {
        List<user> users = us.read();
        ObservableList<user> observableList = FXCollections.observableList(users);
        Listview.setItems(observableList);
    }
    @FXML
    private Label acceuil;

    @FXML
    private Label compte;

    @FXML
    private Label forum;

    @FXML
    private Label recette;

    @FXML
    private Label restaurant;


    @FXML
    private Label wallet;

    @FXML
    void acceuil(MouseEvent event) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/HomePage.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @FXML
    void compte(MouseEvent event) {


    }


    @FXML
    void forum(MouseEvent event) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AfficherDetails.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void recette(MouseEvent event) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AffichageRecipe.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void event(MouseEvent event) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/events.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void restaurant(MouseEvent event) {
       try {
           Stage primaryStage = new Stage();
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ListeRestaurant.fxml"));
           Parent root = loader.load();
           primaryStage.setTitle("Add");
           primaryStage.setScene(new Scene(root));
           primaryStage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);


    }}}

