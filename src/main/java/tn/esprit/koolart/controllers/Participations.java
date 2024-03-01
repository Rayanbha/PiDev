package tn.esprit.koolart.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.esprit.koolart.models.Evenement;
import tn.esprit.koolart.models.Participation;
import tn.esprit.koolart.services.Serviceparticipation;
import tn.esprit.koolart.utils.CodeGenerator;
import tn.esprit.koolart.utils.SmSSender;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Participations implements Initializable {

    @FXML
    private DatePicker particip_date;
    @FXML
    private TextField numero;
    @FXML
    private TextField eventname;
    private Evenement event;
    private int idUser;
    @FXML
    public void ajouterP(ActionEvent actionEvent) {
        Serviceparticipation serviceparticipation = new Serviceparticipation();
        Participation participation = new Participation();
        participation.setUser_id(idUser);
        participation.setEvent_id(event.getId());
        participation.setParticipation_status("In");
        LocalDateTime localDateTime = particip_date.getValue().atStartOfDay();
        participation.setNumero(numero.getText());
        // Convert LocalDateTime to Timestamp
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        participation.setParticipation_date(timestamp);
        boolean result =showVerificationWindow();
        if(result){
            try{
                serviceparticipation.addParticipation(participation);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Ajout réussi");
                alert.showAndWait();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/EventFront.fxml"));
                try {
                    Parent parent = fxmlLoader.load();
                    numero.getScene().setRoot(parent);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Problem With Verification Process");
            alert.setContentText("Problem With Verification Process");
            alert.showAndWait();
        }

    }
    private boolean showVerificationWindow() {
        try {
            // Load the verification FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/verifPhone.fxml"));
            Parent root = loader.load();
            VerifPhone verificationController = loader.getController();
            String code= CodeGenerator.generateCode(4);
            System.out.println(code);
            SmSSender.SendSms(numero.getText(),"Votre Code de verification "+code);
            verificationController.setCodeValue(code);
            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("Verification");
            stage.setScene(new Scene(root));


            // Show the verification window and wait for it to be closed
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // Retrieve the verification result from the controller of the verification window

           // return verificationController.isVerified();
            return  verificationController.getIsValid();
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Return false if there's an error loading the verification window
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        particip_date.setValue(LocalDate.now());
    }


    public void setEvent(Evenement event,int idUser) {

        this.event = event;
        this.eventname.setText(event.getDescription());
        this.idUser=idUser;
    }
}
