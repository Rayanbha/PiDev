package tn.esprit.applicationgui.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import tn.esprit.applicationgui.models.Reservation;
import tn.esprit.applicationgui.models.Table;
import tn.esprit.applicationgui.services.ReservationService;
import tn.esprit.applicationgui.services.TableService;
import tn.esprit.applicationgui.test.HelloApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AjouterReservation {

    @FXML
    private TextField heure_reservationTF;


    @FXML
    private TextField Nombre_personnesTF;

    @FXML
    private DatePicker date_reservationTF;

    @FXML
    private TextField ID_tableTF;

    private ReservationService reservationService;
    @FXML
    private Button ajout;

    public AjouterReservation() {
        try {
            reservationService = new ReservationService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setTableID(int id){
        ID_tableTF.setText(String.valueOf(id));
        ID_tableTF.setDisable(true);

    }

    @FXML
    void afficherReservation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/applicationgui/AfficherReservation.fxml"));
            Parent root = loader.load();
            ID_tableTF.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println("Error"+ e.getMessage());
        }
    }

    @FXML
    void ajouterReservation(ActionEvent event) {
        String heureReservation = heure_reservationTF.getText();
        String nombrePersonnesText = Nombre_personnesTF.getText();
        LocalDate dateReservation = date_reservationTF.getValue();
        String idTableText = ID_tableTF.getText();
        Reservation reservation = new Reservation();
        if (heureReservation.isEmpty() || nombrePersonnesText.isEmpty() || dateReservation == null || idTableText.isEmpty() ) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        int nombrePersonnes;
        try {
            nombrePersonnes = Integer.parseInt(nombrePersonnesText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Nombre de personnes et ID de table doivent être des nombres entiers.");
            return;
        }

        // Validate the format of heureReservation (HH:MM)
        if (!validateTimeFormat(heureReservation)) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Format de l'heure invalide. Utilisez le format HH:MM.");
            return;
        }
        reservation.setHeure_reservation(heure_reservationTF.getText());
        reservation.setNombre_personnes(Integer.parseInt(Nombre_personnesTF.getText()));
        reservation.setDate_reservation(Date.valueOf(date_reservationTF.getValue()));
        reservation.setID_table(Integer.parseInt(ID_tableTF.getText()));

        try {
            reservationService.ajouter(reservation);

            showAlert(Alert.AlertType.CONFIRMATION,"Succès","Réservation ajoutée");

            TableService ts=new TableService();
            Table t=ts.ReadById(Integer.valueOf(ID_tableTF.getText()));
            t.setisReserver(true);
            ts.modifier(t);
            String ValueQrCode = "Heure Reservation: " + reservation.getHeure_reservation() + "\n" +
                    "Nombre Personnes: " + reservation.getNombre_personnes() + "\n" +
                    "Date Reservation: " + reservation.getDate_reservation() + "\n" +
                    "Table : " + reservation.getID_table() + "\n" ;
                    FXMLLoader Windowloader = new FXMLLoader(getClass().getResource("/tn/esprit/applicationgui/QrcodeScan.fxml"));
            Parent rootWindow = Windowloader.load();

            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setTitle("Scan Me");
            newStage.setScene(new Scene(rootWindow));

            QrcodeScan controller = Windowloader.getController();
            controller.SetImageQR(ValueQrCode);
            newStage.showAndWait();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/applicationgui/AfficherTable.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR,"Erreur",e.getMessage());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean validateTimeFormat(String time) {
        try {
            java.time.LocalTime.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
