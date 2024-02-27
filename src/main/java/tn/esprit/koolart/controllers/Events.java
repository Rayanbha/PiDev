package tn.esprit.koolart.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.koolart.models.Evenement;
import tn.esprit.koolart.services.Serviceevenement;

import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class Events implements Initializable {

    @javafx.fxml.FXML
    private TextArea description_input;
    @javafx.fxml.FXML
    private ComboBox <String>evenement_type_input;
    @javafx.fxml.FXML
    private ComboBox  <String> evenement_status_input;
    @javafx.fxml.FXML
    private DatePicker date_input;
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
    private TextField location_input;
    @javafx.fxml.FXML
    private TableColumn <Evenement,Integer>id_col;
    private Evenement SelectedEvent;
    @javafx.fxml.FXML
    private Button deleteButton;
    @javafx.fxml.FXML
    private Button editButton;
    private String formState="Add";

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
        evenement_status_input.getItems().addAll("encours","terminé","debute");
        evenement_type_input.getItems().addAll("asiatique","italien","tunisien","thailandai");
        fetchData();

    }
    public void fetchData(){
        eventTable.setItems(se.showEvenement());
        eventTable.refresh();
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        formState="Add";
        initForm();
        SelectedEvent=null;
    }
    public void initForm(){
        description_input.setText("");
        location_input.setText("");
        date_input.setValue(null);
        evenement_type_input.setValue("");
        evenement_status_input.setValue("");
    }

    @javafx.fxml.FXML
    public void save(ActionEvent actionEvent) {

        try {
            String description = description_input.getText();
            String location = location_input.getText();
            LocalDate localDate = date_input.getValue();
            String type = evenement_type_input.getValue();
            String status = evenement_status_input.getValue();
            if (description.isEmpty() || location.isEmpty() || localDate == null || type == null || status == null) {

                // Afficher un message d'erreur si un champ est vide
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("All fields are required!");
                alert.showAndWait();
                return;
            }else{
                Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                Timestamp timestamp = Timestamp.from(instant);
                if(formState.equals("Add")){
                    Evenement e = new  Evenement(1,  timestamp,  type,  description,  location,  status);
                    se.addEvenement(e);
                    fetchData();
                }else{
                    Evenement e = new  Evenement(SelectedEvent.getId(),1,  timestamp,  type,  description,  location,  status);

                    se.updateEvenement(e);
                    fetchData();


                }

            }
        }catch (Exception e ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("All fields are required!");
            alert.showAndWait();
        }
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
        String type = evenement_type_input.getValue();
        String status = evenement_status_input.getValue();
        if(SelectedEvent!=null){
            description_input.setText(SelectedEvent.getDescription());
            location_input.setText(SelectedEvent.getLocation());
            java.sql.Timestamp timestamp = SelectedEvent.getDate();
            LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
            date_input.setValue(localDate);
            evenement_type_input.setValue(SelectedEvent.getEvenement_type());
            evenement_status_input.setValue(SelectedEvent.getStatus());
            formState="Edit";
        }
    }
}
