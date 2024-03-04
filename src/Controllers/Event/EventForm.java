package Controllers.Event;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Evenement;
import services.Serviceevenement;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.UUID;

public class EventForm implements Initializable {
    @javafx.fxml.FXML
    private TextArea description_input;
    @javafx.fxml.FXML
    private ComboBox <String>evenement_type_input;
    @javafx.fxml.FXML
    private ComboBox <String> evenement_status_input;
    @javafx.fxml.FXML
    private DatePicker date_input;
    @javafx.fxml.FXML
    private TextField location_input;
    private String formState="Add";
    private Evenement eventToEdit=null;

    Serviceevenement se = new Serviceevenement();

    @javafx.fxml.FXML
    public void backToView(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/Events.fxml"));
        try {
            location_input.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setEventToEdit(Evenement event){
        this.eventToEdit=event;
        this.formState="EDIT";
        description_input.setText(event.getDescription());
        location_input.setText(event.getLocation());
        Timestamp timestamp = event.getDate();
        LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
        date_input.setValue(localDate);
        evenement_type_input.setValue(event.getEvenement_type());
        evenement_status_input.setValue(event.getStatus());
        imagePath=event.getImageURL();


    }
    @javafx.fxml.FXML
    public void save(ActionEvent actionEvent) {
        try {
            String description = description_input.getText();
            String location = location_input.getText();
            LocalDate localDate = date_input.getValue();
            String type = evenement_type_input.getValue();
            String status = evenement_status_input.getValue();
            String imageURL= imagePath;

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
                    Evenement e = new  Evenement(1,  timestamp,  type,  description,  location,  status,imageURL);

                    se.addEvenement(e);
                   backToView(actionEvent);
                }else{

                    Evenement e = new  Evenement(eventToEdit.getId(),1,  timestamp,  type,  description,  location,  status,imageURL);

                    se.updateEvenement(e);
                    backToView(actionEvent);



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

    private String imagePath ;
    @javafx.fxml.FXML
    public void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(filter);
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            try {
                // Generate a unique file name
                String uniqueFileName = UUID.randomUUID().toString() + getFileExtension(selectedFile.getName());

                // Define the upload directory
                String uploadDir="src/main/resources/uploads/";
                // Create the uploads directory if it doesn't exist

                // Copy the selected file to the uploads directory with the unique file name
                Files.copy(selectedFile.toPath(), Paths.get(uploadDir + uniqueFileName), StandardCopyOption.REPLACE_EXISTING);

                // Set the imagePath to the path of the uploaded image
                imagePath = uniqueFileName;

                System.out.println("Fichier sélectionné : " + imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        evenement_status_input.getItems().addAll("encours","terminé","debute");
        evenement_type_input.getItems().addAll("asiatique","italien","tunisien","thailandai");

    }
}
