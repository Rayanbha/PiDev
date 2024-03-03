package tn.esprit.applicationgui.test;
import tn.esprit.applicationgui.models.Reservation;
import tn.esprit.applicationgui.models.Table;
import tn.esprit.applicationgui.services.ReservationService;
import tn.esprit.applicationgui.services.TableService;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.sql.SQLException;



public class Main {
    public static void main(String[] args) throws SQLException {
        ///////////////////////table :TABLE://///////////////////////////////////////////////////////////////
      /*  TableService tb = new TableService();
        try {
            //tb.ajouter(new Table(100, "mer", "vide", 1));
            //tb.ajouter(new Table(789, "salon", "vide", 78));
           //  tb.modifier(new Table(22,123,  "t", "h",78));
             tb.supprimer(22);
            System.out.println(tb.recuperer());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

*/
        ///////////////////////table :RESERVATION://///////////////////////////////////////////////////////////////
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ReservationService res = new ReservationService();
        try {
            // Convertir java.util.Date en java.sql.Date
            java.util.Date utilDate = dateFormat.parse("2000-05-25");
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            // Ajouter une nouvelle réservation
            Reservation r=(new Reservation(7, 123, sqlDate, 5, 4, 99, "11:00"));
            // res.ajouter(r);
            // Modifier une réservation existante
            //res.modifier(r);
           // Reservation rt=res.ReadById(r.getID_reservation());
           // System.out.println(rt);
            // supprimer une réservation existante
             res.supprimer(1);
            // Afficher toutes les réservations
            System.out.println(res.recuperer());
        } catch (SQLException | ParseException e) {
            System.err.println(e.getMessage());
        }
    }
}



