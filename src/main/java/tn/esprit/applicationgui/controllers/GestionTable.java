package tn.esprit.applicationgui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import tn.esprit.applicationgui.models.Table;
import tn.esprit.applicationgui.services.TableService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GestionTable {
    private final TableService tableService = new TableService();

    private Table selectedTable;
    @FXML
    private Button modifierButton_TF;
    @FXML
    private Button supprimerbuttton_TF;
    @FXML
    private ListView<Table> listviewTables;


    @FXML
    void initialize() {
        try {
            refreshTableList();
        } catch (SQLException e) {
            showErrorAlert("Error", e.getMessage());
        }
    }
    @Deprecated
    void navigateToModifyTable(ActionEvent event) {
        if (selectedTable != null) {
            loadFXML("/ModifierTable.fxml");
        } else {
            showErrorAlert("Error", "No item selected");
        }
    }

    @Deprecated
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
        listviewTables.setItems(observableList);
        listviewTables.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedTable = newValue;
                supprimerbuttton_TF.setDisable(false);
                modifierButton_TF.setDisable(false);
            }
        });
    }

    private void loadFXML(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            listviewTables.getScene().setRoot(root);
        } catch (IOException e) {
            showErrorAlert("Error", e.getMessage());
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void deletecours(ActionEvent actionEvent) {
    }

    @FXML
    public void naviguezVersmodifiertable(ActionEvent actionEvent) {
    }
}