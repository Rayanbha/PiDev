package Controllers;

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
import javafx.scene.layout.GridPane;
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
    void initialize(){

        List<user> users=us.read();
        ObservableList<user> observableList= FXCollections.observableList(users);
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
                        } else {
                            // Create VBox for vertical layout
                            VBox vbox = new VBox(5);
                            vbox.setPadding(new Insets(5));
                            vbox.setAlignment(Pos.CENTER_LEFT);
                            vbox.setStyle("-fx-background-color: #FFDE59;"); // Set item background color


                            // Create labels for each user property
                            Label idLabel = createLabel("ID :", Integer.toString(item.getId()));
                            Label roleLabel = createLabel("Role :", item.getRole());
                            Label prenomLabel = createLabel("Prénom :", item.getPrenom());
                            Label nomLabel = createLabel("Nom :", item.getNom());
                            Label emailLabel = createLabel("Email :", item.getEmail());
                            Label cinLabel = createLabel("CIN :", Integer.toString(item.getCin()));
                            Label pwdLabel = createLabel("Password :", item.getPwd());
                            Label hashedpwdLabel = createLabel("Hashed Password :", item.getHashedpwd());
                            Label saltLabel = createLabel("Salt :", item.getSalt());

                            // Add labels to VBox
                            vbox.getChildren().addAll(idLabel, roleLabel, prenomLabel, nomLabel, emailLabel, cinLabel, pwdLabel, hashedpwdLabel, saltLabel);

                            // Set cell content
                            setGraphic(vbox);
                        }
                    }
                };
            }
        });



    }
    private Label createLabel(String labelText, String value) {
        Label label = new Label(labelText + " " + value);
        label.setStyle("-fx-font-weight: bold");
        return label;
    }

    @FXML
    void Update(ActionEvent event) {
        user selecteduser=Listview.getSelectionModel().getSelectedItem();
        if(selecteduser != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UpdateUser.fxml"));
                Parent root = loader.load();
                UpdateUser updateController = loader.getController();
                Stage stage1=(Stage)Update.getScene().getWindow();
                stage1.close();
                updateController.initData(selecteduser);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No user selected!");
            alert.showAndWait();
        }

    }


    public void deleteuser(ActionEvent actionEvent) {
        user selectedUser = Listview.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
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
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No user selected!");
            alert.showAndWait();
        }
    }
}
