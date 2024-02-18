package Controllers;

import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
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
        Listview.getItems().addAll(observableList);


    }

    @FXML
    void Update(ActionEvent event) {
        user selecteduser=Listview.getSelectionModel().getSelectedItem();
        if(selecteduser != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateUser.fxml"));
                Parent root = loader.load();
                UpdateUser updateController = loader.getController();
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
