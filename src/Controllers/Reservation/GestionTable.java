package Controllers.Reservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.Table;
import services.TableService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GestionTable {

    private final TableService tableService = new TableService();

    private Table selectedTable;

    @FXML
    private Button modifierButton_TF;

    @FXML
    private Button supprimerbutton_TF;

    @FXML
    private TextField searchField;
    @FXML
    private ListView <Table> listView;

    @FXML
    void initialize() {
        try {
            refreshTableList();
        } catch (SQLException e) {
            showErrorAlert("Error", e.getMessage());
        }
    }

    @FXML
    void deleteTable(ActionEvent event) {
        if (selectedTable != null) {
            try {
                tableService.supprimer(selectedTable.getID_table());
                refreshTableList();
            } catch (SQLException e) {
                showErrorAlert("Error", e.getMessage());
            }
        } else {
            showErrorAlert("Error", "No item selected");
        }
    }







    private void refreshTableList() throws SQLException {
        List<Table> tables = tableService.recuperer();
        ObservableList<Table> observableList = FXCollections.observableList(tables);
        listView.setItems(observableList);
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedTable = newValue;
                supprimerbutton_TF.setDisable(false);
                modifierButton_TF.setDisable(false);
            }
        });
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void navigateToModiftTable(ActionEvent actionEvent) {
        if (selectedTable != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ModifierTable.fxml"));
            Parent root = null;
            try {
                root = loader.load();
                ModifierTable controller=loader.getController();
                controller.setTable(selectedTable);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            listView.getScene().setRoot(root);
        } else {
            showErrorAlert("Error", "No item selected");
        }
    }


    @FXML
    public void searchAction(Event event) {
        String searchText = searchField.getText().trim();
        if (!searchText.isEmpty()) {
            List<Table> searchResults = tableService.recuperer(searchText);
            ObservableList<Table> observableList = FXCollections.observableArrayList(searchResults);
            listView.setItems(observableList);

        }else{
            List<Table> searchResults = tableService.recuperer();
            ObservableList<Table> observableList = FXCollections.observableArrayList(searchResults);
            listView.setItems(observableList);

        }
    }
    @FXML
    void reservation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AfficherReservation.fxml"));
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

}