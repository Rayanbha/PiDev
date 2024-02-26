package tn.esprit.applicationgui.services;
import tn.esprit.applicationgui.utils.MyDatabase;
import tn.esprit.applicationgui.models.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationService implements IService<Reservation> {

    private Connection connection;

    public ReservationService() throws SQLException {

        connection = MyDatabase.getInstance().getConnection();
    }



    @Override
    ///////////////////Ajouter////////////////////////////////////////////////////////////

    public void ajouter(Reservation reservation) throws SQLException {
        String req = "INSERT INTO `reservation` (ID_user, Date_reservation, Nombre_personnes, Etat_reservation, ID_table, ID_restaurant, Heure_reservation) VALUES ("
                + reservation.getID_user() + ", '"
                + reservation.getDate_reservation() + "', '"
                + reservation.getNombre_personnes() + "', '"
                + reservation.getEtat_reservation() + "', '"
                + reservation.getID_table() + "', '"
                + reservation.getID_restaurant() + "', '"
                + reservation.getHeure_reservation() + "')";

        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    ///MODIFIER////////////////////////////////////////////////////////////////////////////
    @Override
    public void modifier(Reservation reservation) throws SQLException {
        String req = "UPDATE `reservation` SET ID_user=?,Date_reservation=?, Nombre_personnes=?, Etat_reservation=?, ID_table=?,  ID_restaurant =?,Heure_reservation=? WHERE ID_reservation=?";
        PreparedStatement tb = connection.prepareStatement(req);
        tb.setInt(1, reservation.getID_user());
        tb.setDate(2, reservation.getDate_reservation()); // Correction ici
        tb.setInt(3, reservation.getNombre_personnes());
        tb.setString(4, reservation.getEtat_reservation());
        tb.setInt(5, reservation.getID_table());
        tb.setInt(6, reservation.getID_restaurant());
        tb.setString(7, reservation.getHeure_reservation());
        tb.setInt(8, reservation.getID_reservation());
        tb.executeUpdate();
    }

    ///SUPPRIMER//////////////////////////////////////////////////////////////////////////
    @Override
    public void supprimer(int ID_reservation) throws SQLException {
        String req = "DELETE FROM `reservation` WHERE ID_reservation = ?";
        PreparedStatement tb = connection.prepareStatement(req);
        tb.setInt(1, ID_reservation);
        tb.executeUpdate();

    }

    ///RECUPERER (READ)//////////////////////////////////////////////////////////////////
    public List<Reservation> recuperer() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String req = "SELECT * FROM `reservation`";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);//recupere des lignes  de la base donner
        while (rs.next()) {
            Reservation reservation = new Reservation();
            reservation.setID_reservation(rs.getInt("ID_reservation"));
            reservation.setID_user(rs.getInt("ID_user"));
            reservation.setDate_reservation(rs.getDate("Date_reservation"));
            reservation.setNombre_personnes(rs.getInt("Nombre_personnes"));
            reservation.setEtat_reservation(rs.getString("Etat_reservation"));
            reservation.setID_table(rs.getInt("ID_table"));
            reservation.setID_restaurant(rs.getInt("ID_restaurant"));
            reservation.setHeure_reservation(rs.getString("Heure_reservation"));

            reservations.add(reservation);
        }
        return reservations;
    }

    ///READ BY ID////////////////////////////////////////////////////////////////////////
    public Reservation ReadById(int ID_reservation) {
        Reservation reservation = null;

        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM reservation WHERE ID_reservation = ?");
            statement.setInt(1, ID_reservation);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ID_reservation = resultSet.getInt("ID_reservation");
                int ID_user = resultSet.getInt("ID_user");
                Date Date_reservation = resultSet.getDate("Date_reservation");
                int Nombre_personnes = resultSet.getInt("Nombre_personnes");
                String Etat_reservation = resultSet.getString("Etat_reservation");
                int ID_table = resultSet.getInt("ID_table");
                int ID_restaurant = resultSet.getInt("ID_restaurant");
                String Heure_reservation = resultSet.getString("Heure_reservation");
                reservation = new Reservation(ID_reservation, ID_user, Date_reservation, Nombre_personnes, Etat_reservation, ID_table, ID_restaurant, Heure_reservation);
            }
        } catch (SQLException var11) {
            System.err.println(var11);
        }
        return reservation;
    }
}


