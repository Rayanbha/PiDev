package tn.esprit.applicationgui.models;

import java.sql.Date;
import java.sql.Timestamp;
public class Reservation {
    private int ID_reservation,ID_user,Nombre_personnes,ID_table,ID_restaurant;
    private Date Date_reservation;
    private String Heure_reservation;

    public Reservation(int ID_reservation, int ID_user, Date date_reservation, int nombre_personnes, int ID_table, int ID_restaurant,String Heure_reservation) {
        this.ID_reservation = ID_reservation;
        this.ID_user = ID_user;
        this.Date_reservation = date_reservation;
        this.Nombre_personnes = nombre_personnes;
        this.ID_table = ID_table;
        this.ID_restaurant = ID_restaurant;
        this.Heure_reservation = Heure_reservation;
    }
    public Reservation(int ID_user, Date date_reservation, int nombre_personnes, int ID_table, int ID_restaurant,String Heure_reservation) {
        this.ID_user = ID_user;
        this.Date_reservation = date_reservation;
        this.Nombre_personnes = nombre_personnes;
        this.ID_table = ID_table;
        this.ID_restaurant = ID_restaurant;
        this.Heure_reservation = Heure_reservation;
    }

    public Reservation() {

    }

    //getters
    public int getID_reservation() {
        return ID_reservation;
    }
    public int getID_user() {
        return ID_user;
    }
    public int getNombre_personnes() {
        return Nombre_personnes;
    }
    public int getID_table() {
        return ID_table;
    }
    public int getID_restaurant() {
        return ID_restaurant;
    }

    public Date getDate_reservation() {
        return Date_reservation;
    }
    public String getHeure_reservation() {
        return Heure_reservation;
    }

    //setter
    public void setID_reservation(int ID_reservation) {
        this.ID_reservation = ID_reservation;
    }
    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }
    public void setNombre_personnes(int nombre_personnes) {
        Nombre_personnes = nombre_personnes;
    }
    public void setID_table(int ID_table) {
        this.ID_table = ID_table;
    }
    public void setID_restaurant(int ID_restaurant) {
        this.ID_restaurant = ID_restaurant;
    }
    public void setDate_reservation(Date date_reservation) {
        Date_reservation = date_reservation;
    }
    public void setHeure_reservation(String heure_reservation) {
        this.Heure_reservation = heure_reservation;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "ID_reservation=" + ID_reservation +
                ", ID_user=" + ID_user +
                ", Nombre_personnes=" + Nombre_personnes +
                ", ID_table=" + ID_table +
                ", ID_restaurant=" + ID_restaurant +
                ", Date_reservation=" + Date_reservation +
                ", Heure_reservation='" + Heure_reservation + '\'' +
                '}';
    }
}
