package services;

import models.Participation;
import util.MyConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Serviceparticipation implements Interfaceparticipation<Participation>{
    Connection cnx = MyConnection.getInstance().getCnx();
    @Override
    public void addParticipation(Participation participation) {
        final String INSERT = "INSERT INTO participation( user_id, event_id, participation_date,participation_status,numero) VALUES (?, ?, ?, ?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(INSERT);
            ps.setInt(1,   participation.getUser_id());
            ps.setInt(2, participation.getEvent_id());
            ps.setTimestamp(3, participation.getParticipation_date());
            ps.setString(4, participation.getParticipation_status());
            ps.setString(5,participation.getNumero());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new participation  has been created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Participation isAlreadyInEvent(int userId, int eventId){
        final String Verify = "SELECT * FROM `participation` WHERE user_id=? and event_id=?";
        try {

            PreparedStatement ps = cnx.prepareStatement(Verify);
            ps.setInt(1,userId);
            ps.setInt(2,eventId);
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Participation p = new Participation(
                        result.getInt("participation_id"),
                        result.getInt("user_id"),
                        result.getInt("event_id"),
                        result.getTimestamp("participation_date"),
                        result.getString("participation_status")
                );

                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    @Override
    public void updateParticipation(Participation participation) {  final String UPDATE = "UPDATE participation SET  user_id = ?, event_id = ?, participation_date = ?,participation_status =?  WHERE participation_id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(UPDATE);
            ps.setInt(1,participation.getUser_id());
            ps.setInt(2, participation.getEvent_id());
            ps.setTimestamp(3,participation.getParticipation_date());
            ps.setString(4,participation.getParticipation_status());
            ps.setInt(5, participation.getParticipation_id());

            int rowInserted = ps.executeUpdate();
            if (rowInserted > 0 ){
                System.out.println("participation with ID " + participation.getParticipation_id() + " has been updated successfully.");
            }else {
                System.out.println("participation with ID " + participation.getParticipation_id() + "doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteParticipation(Participation participation) { final String DELETE = "DELETE FROM participation WHERE participation_id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(DELETE);
            ps.setInt(1,participation.getParticipation_id());
            int rowDeleted = ps.executeUpdate();
            if (rowDeleted > 0 ){
                System.out.println("participation with ID"+participation.getParticipation_id()+"has been deleted successfully.");
            }else {
                System.out.println("participation  with ID"+participation.getParticipation_id()+"doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Participation> showParticipation() {
        final String GetAll = "SELECT * FROM participation ";
        List<Participation> participationList = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement(GetAll);
            ResultSet result = ps.executeQuery();
            while (result.next()){
                Participation p = new Participation(
                        result.getInt("participation_id"),
                        result.getInt("user_id"),
                        result.getInt("event_id"),
                        result.getTimestamp("participation_date"),
                        result.getString("participation_status")





                );

                participationList.add(p);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return participationList;

    }
}
