package tn.esprit.applicationgui.controllers;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.control.ButtonType;
        import tn.esprit.applicationgui.models.Reservation;
        import tn.esprit.applicationgui.services.ReservationService;
        import javafx.collections.FXCollections;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.ListView;

        import java.io.IOException;
        import java.net.URL;
        import java.sql.SQLException;
        import java.util.List;
        import java.util.Optional;
        import java.util.ResourceBundle;
        import javafx.event.ActionEvent;
public class AfficherReservation implements Initializable {
    @FXML
    private ListView<Reservation> listView;
    @FXML
    private Button deleteButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ReservationService reservationService = null;
        try {
            reservationService = new ReservationService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Reservation> services = null;
        try {
            services = reservationService.recuperer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        listView.setItems(FXCollections.observableArrayList(services));
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                deleteButton.setDisable(false);
            }
        });

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
    public void navigerGestionTable(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/applicationgui/GestionTable.fxml"));
            Parent root = loader.load();
            listView.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println("Error"+ e.getMessage());
        }
    }
}
