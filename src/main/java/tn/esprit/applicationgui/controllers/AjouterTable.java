package tn.esprit.applicationgui.controllers;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.esprit.applicationgui.models.Table;
import tn.esprit.applicationgui.services.TableService;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterTable {

    private TableService tableService;
    @FXML
    private TextField Num_tableTF;
    @FXML
    private TextField DescriptionTF;
    @FXML
    private TextField Type_tableTF;
    @FXML
    private Button ajouter;

    public AjouterTable() throws SQLException {
        tableService = new TableService();
    }

    @FXML
    void ajouterTable(ActionEvent event) {
        Table table = new Table();
        table.setID_table(Integer.parseInt(Num_tableTF.getText()));
        table.setType_table(Type_tableTF.getText());
        table.setDescription(DescriptionTF.getText());
        try {
            tableService.ajouter(table);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("Table ajoutée");
            alert.showAndWait();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/applicationgui/AfficherTable.fxml"));
            Parent root = fxmlLoader.load();
            Num_tableTF.getScene().setRoot(root);
        } catch ( IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
