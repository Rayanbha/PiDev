package Controllers.Event;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Evenement;
import services.Serviceevenement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Events implements Initializable {

    @javafx.fxml.FXML
    private TableColumn <Evenement,String> description_col;
    @javafx.fxml.FXML
    private TableColumn <Evenement,String> location_col;
    @javafx.fxml.FXML
    private TableColumn <Evenement,String> status_col;
    @javafx.fxml.FXML
    private TableColumn <Evenement,java.sql.Date> date_col;
    @javafx.fxml.FXML
    private TableColumn <Evenement,String> eventTypeCol;
    private Serviceevenement se =  new Serviceevenement();
    @javafx.fxml.FXML
    private TableView<Evenement> eventTable;
    @javafx.fxml.FXML
    private Label error;
    @javafx.fxml.FXML
    private TableColumn <Evenement,Integer>id_col;
    private Evenement SelectedEvent;
    @javafx.fxml.FXML
    private Button deleteButton;
    @javafx.fxml.FXML
    private Button editButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        description_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        eventTypeCol.setCellValueFactory(new PropertyValueFactory<>("evenement_type"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
        location_col.setCellValueFactory(new PropertyValueFactory<>("location"));
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Single-click event
                // Get the selected row index
                int selectedIndex = eventTable.getSelectionModel().getSelectedIndex();
                SelectedEvent=eventTable.getItems().get(selectedIndex);
                editButton.setDisable(false);
                deleteButton.setDisable(false);
                // Do something with the selected row index
            }else {
                editButton.setDisable(true);
                deleteButton.setDisable(true);
            }
        });
        fetchData();

    }
    public void fetchData(){
        eventTable.setItems(se.showEvenement());
        eventTable.refresh();
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        SelectedEvent=null;
    }


    @javafx.fxml.FXML
    public void deleteEvent(ActionEvent actionEvent) {
        if(SelectedEvent!=null){
            se.deleteEvenement(SelectedEvent);
            fetchData();
        }
    }

    @javafx.fxml.FXML
    public void editButtonAction(ActionEvent actionEvent) {
        if(SelectedEvent!=null){
            FXMLLoader fxmlLoader = new FXMLLoader(Events.class.getResource("/UI/EventForm.fxml"));
            try {
                Parent root = fxmlLoader.load();

                EventForm eventFormController = fxmlLoader.getController();
                eventFormController.setEventToEdit(SelectedEvent);

                editButton.getScene().setRoot(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @javafx.fxml.FXML
    public void Ajouter(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Events.class.getResource("/UI/EventForm.fxml"));
        try {
            editButton.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
