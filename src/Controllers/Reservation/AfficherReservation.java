package Controllers.Reservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.Reservation;
import services.ReservationService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
public class AfficherReservation implements Initializable {
    @FXML
    private ListView<Reservation> listView;

    @FXML
    private TextField searchField;
    private final ReservationService reservationService = new ReservationService();
    @FXML
    private Button deleteButton;

    public AfficherReservation() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshList();

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                deleteButton.setDisable(false);
            }
        });
    }

    private void refreshList() {
        try {
            List<Reservation> services = reservationService.recuperer();
            ObservableList<Reservation> observableList = FXCollections.observableArrayList(services);
            listView.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'erreur
        }
    }

    @FXML
    public void SupprimerButton(ActionEvent actionEvent) {
        Reservation reservation = listView.getSelectionModel().getSelectedItem();
        if (reservation != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette conversation ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    ReservationService rs= new ReservationService();
                    rs.supprimer(reservation.getID_reservation());
                    listView.getItems().remove(reservation);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void navigerGestionTable() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/GestionTable.fxml"));
            loader.load();
            // Passer des données à la prochaine vue si nécessaire
            // GestionTableController controller = loader.getController();
            // controller.setData(data);
            listView.getScene().setRoot(loader.getRoot());
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
            // Gérer l'erreur
        }
    }


    @FXML
    public void onRecherche(Event event) {
        String searchText = searchField.getText().trim();
        if (!searchText.isEmpty()) {
            try {
                int searchValue = Integer.parseInt(searchText);
                List<Reservation> searchResults = reservationService.recuperer(searchValue);
                ObservableList<Reservation> observableList = FXCollections.observableArrayList(searchResults);
                listView.setItems(observableList);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Gérer l'erreur
            } catch (SQLException e) {
                e.printStackTrace();
                // Gérer l'erreur
            }
        } else {
            refreshList();
        }
    }
}