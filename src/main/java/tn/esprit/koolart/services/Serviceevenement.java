package tn.esprit.koolart.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.koolart.services.Interfaceevenement;
import tn.esprit.koolart.models.Evenement;
import tn.esprit.koolart.utils.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Serviceevenement implements Interfaceevenement<Evenement> {
    Connection cnx = Connexion.getInstance().getCnx();
    @Override
    public void addEvenement(Evenement evenement) {final String INSERT = "INSERT INTO evenement(id_user, date, evenement_type,  description,location, status) VALUES (?, ?, ?, ?,?, ?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(INSERT);
            ps.setInt(1,  evenement.getId_user());
            ps.setTimestamp(2,evenement.getDate());
            ps.setString(3, evenement.getEvenement_type());
            ps.setString(4, evenement.getDescription());
            ps.setString(5, evenement.getLocation());
            ps.setString(6, evenement.getStatus());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new event has been created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEvenement(Evenement evenement) {
        final String UPDATE = "UPDATE evenement SET  date = ?, evenement_type = ?, description = ?, location = ?,status =?  WHERE id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(UPDATE);
            ps.setTimestamp(1,evenement.getDate());
            ps.setString(2, evenement.getEvenement_type());
            ps.setString(3,evenement.getDescription());
            ps.setString(4,evenement.getLocation());
            ps.setString(5, evenement.getStatus());
            ps.setInt(6,evenement.getId());
            int rowInserted = ps.executeUpdate();
            if (rowInserted > 0 ){
                System.out.println("Evenement with ID " + evenement.getId() + " has been updated successfully.");
            }else {
                System.out.println("Evenement with ID " + evenement.getId() + "doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEvenement(Evenement evenement) {
        final String DELETE = "DELETE FROM evenement WHERE id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(DELETE);
            ps.setInt(1,evenement.getId());
            int rowDeleted = ps.executeUpdate();
            if (rowDeleted > 0 ){
                System.out.println("Evenement with ID"+evenement.getId()+"has been deleted successfully.");
            }else {
                System.out.println("Evenemnt  with ID"+evenement.getId()+"doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Evenement> showEvenement() {
        final String GetAll = "SELECT * FROM evenement ";
        ObservableList<Evenement> evenementList = FXCollections.observableArrayList(); // Créer une ObservableList directement
        try {
            PreparedStatement ps = cnx.prepareStatement(GetAll);
            ResultSet result = ps.executeQuery();
            while (result.next()){
                Evenement e = new Evenement(
                        result.getInt("id"),
                        result.getInt("id_user"),
                        result.getTimestamp("date"),
                        result.getString("evenement_type"),
                        result.getString("description"),
                        result.getString("location"),
                        result.getString("status")
                );
                evenementList.add(e);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return evenementList; // Retourner directement l'ObservableList
    }
}
