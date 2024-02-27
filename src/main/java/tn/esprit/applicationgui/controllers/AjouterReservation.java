package tn.esprit.applicationgui.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import tn.esprit.applicationgui.models.Reservation;
import tn.esprit.applicationgui.models.Table;
import tn.esprit.applicationgui.services.ReservationService;
import tn.esprit.applicationgui.services.TableService;
import tn.esprit.applicationgui.test.HelloApplication;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class AjouterReservation {

    @FXML
    private TextField heure_reservationTF;


    @FXML
    private TextField Nombre_personnesTF;

    @FXML
    private DatePicker date_reservationTF;

    @FXML
    private TextField ID_tableTF;

    @FXML
    private TextField etat_reservationTF;

    private ReservationService reservationService;

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
        Reservation reservation = new Reservation();
        reservation.setHeure_reservation(heure_reservationTF.getText());
        reservation.setNombre_personnes(Integer.parseInt(Nombre_personnesTF.getText()));
        reservation.setDate_reservation(Date.valueOf(date_reservationTF.getValue()));
        reservation.setID_table(Integer.parseInt(ID_tableTF.getText()));
        reservation.setEtat_reservation(etat_reservationTF.getText());
        try {
            reservationService.ajouter(reservation);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("Réservation ajoutée");
            alert.showAndWait();
            TableService ts=new TableService();
            Table t=ts.ReadById(Integer.valueOf(ID_tableTF.getText()));
            t.setisReserver(true);
            ts.modifier(t);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/applicationgui/AfficherTable.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            etat_reservationTF.getScene().setRoot(root);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}