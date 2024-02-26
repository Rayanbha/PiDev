package tn.esprit.applicationgui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.esprit.applicationgui.models.Table;
import tn.esprit.applicationgui.services.TableService;

import java.io.IOException;

public class ModifierTable {
    @FXML
    private TextField Num_tableTF;
    @FXML
    private TextField DescriptionTF;
    @FXML
    private TextField Type_tableTF;
    @FXML
    private Button Modifier;

    private Table table;
    private final TableService tableService = new TableService();

    public void setTable(Table table) {
        this.table = table;
        if (table != null) {
            // Remplir les champs avec les données de la table existante
            Num_tableTF.setText(String.valueOf(table.getID_table()));
            DescriptionTF.setText(table.getDescription());
            Type_tableTF.setText(table.getType_table());
        }
    }

    @FXML
    public void modifierTable(ActionEvent actionEvent) {
        if (table != null) {
            // Mettre à jour les attributs de la table avec les nouvelles valeurs
            table.setType_table(Type_tableTF.getText());
            table.setDescription(DescriptionTF.getText());

            // Appeler la méthode de modification du service
            tableService.modifier(table);

            // Naviguer vers une autre vue après la modification
            naviguezVersAffichage(actionEvent);
        }
    }

    @FXML
    void naviguezVersAffichage(ActionEvent event) {
        try {
            // Naviguer vers une autre vue après la modification
            // (vous devez adapter ce code selon votre application)
            // Ici, nous supposons que vous avez un fichier FXML nommé "Affichercour.fxml"
            // qui représente la vue d'affichage des tables.
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherTable.fxml"));
            Modifier.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}