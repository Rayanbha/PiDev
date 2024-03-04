package Controllers.Reservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Reservation;
import models.Restaurant;
import models.Table;
import models.user;
import services.ReservationService;
import services.TableService;

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
    Restaurant restaurant;
    private user user;

    public void InitResto(Restaurant restaurant, user u)
    {
        this.restaurant=restaurant;
        this.user=u;
    }

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
    void ajouterReservation(ActionEvent event) {
        System.out.println("LOOOOOOOL"+user);
        String heureReservation = heure_reservationTF.getText();
        String nombrePersonnesText = Nombre_personnesTF.getText();
        LocalDate dateReservation = date_reservationTF.getValue();
        String idTableText = ID_tableTF.getText();

        if (heureReservation.isEmpty() || nombrePersonnesText.isEmpty() || dateReservation == null || idTableText.isEmpty()) {
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

        if (!validateTimeFormat(heureReservation)) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Format de l'heure invalide. Utilisez le format HH:MM.");
            return;
        }

        Reservation reservation = new Reservation();
        reservation.setHeure_reservation(heure_reservationTF.getText());
        reservation.setNombre_personnes(Integer.parseInt(Nombre_personnesTF.getText()));
        reservation.setDate_reservation(Date.valueOf(date_reservationTF.getValue()));
        reservation.setID_table(Integer.parseInt(ID_tableTF.getText()));
        reservation.setID_restaurant(restaurant.getRestaurantId());
        reservation.setID_user(user.getId());

        try {
            reservationService.ajouter(reservation);
            showAlert(Alert.AlertType.CONFIRMATION, "Succès", "Réservation ajoutée");

            TableService ts = new TableService();
            Table t = ts.ReadById(Integer.valueOf(ID_tableTF.getText()));
            t.setisReserver(true);
            ts.modifier(t);

            String ValueQrCode = "Heure Reservation: " + reservation.getHeure_reservation() + "\n" +
                    "Nombre Personnes: " + reservation.getNombre_personnes() + "\n" +
                    "Date Reservation: " + reservation.getDate_reservation() + "\n" +
                    "Table : " + reservation.getID_table() + "\n";

            FXMLLoader windowLoader = new FXMLLoader(getClass().getResource("/UI/QrcodeScan.fxml"));
            Parent rootWindow = windowLoader.load();

            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setTitle("Scan Me");
            newStage.setScene(new Scene(rootWindow));

            QrcodeScan controller = windowLoader.getController();
            controller.SetImageQR(ValueQrCode);

            newStage.showAndWait();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/AfficherTable.fxml"));
            Parent root = fxmlLoader.load();
            AfficherTable controller1 = fxmlLoader.getController();
            controller1.InitResto(restaurant,user);

            ((Stage) heure_reservationTF.getScene().getWindow()).close();
            newStage.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", e.getMessage());
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
