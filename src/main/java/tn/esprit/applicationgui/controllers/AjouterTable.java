package tn.esprit.applicationgui.controllers;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import tn.esprit.applicationgui.models.Table;
import tn.esprit.applicationgui.services.TableService;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterTable {

    private TableService tableService = new TableService();
    @FXML
    private TextField Num_tableTF;
    @FXML
    private TextField DescriptionTF;
    @FXML
    private TextField Type_tableTF;
    @FXML
    private Button ajouter;



    @FXML
    void ajouterTable(ActionEvent event) {
        String idText = Num_tableTF.getText();
        String type = Type_tableTF.getText();
        String description = DescriptionTF.getText();

        if (idText.isEmpty() || type.isEmpty() || description.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "L'ID doit être un nombre.");
            return;
        }

        // If all validations pass, proceed to add the table
        Table table = new Table();
        table.setID_table(id);
        table.setType_table(type);
        table.setDescription(description);

        try {

            tableService.ajouter(table);
            showAlert(Alert.AlertType.CONFIRMATION, "Succès", "Table ajoutée");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/applicationgui/AfficherTable.fxml"));
            Parent root = fxmlLoader.load();
            Num_tableTF.getScene().setRoot(root);
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
