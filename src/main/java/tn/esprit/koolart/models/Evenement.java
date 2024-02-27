package tn.esprit.koolart.models;

import java.sql.Timestamp;

public class Evenement {private int id ; private int id_user;
    private Timestamp date ; private String evenement_type; private String description ; private String location ; private String status;

    public Evenement(int id, int id_user, Timestamp date, String evenement_type, String description, String location, String status) {
        this.id = id;
        this.id_user = id_user;
        this.date = date;
        this.evenement_type = evenement_type;
        this.description = description;
        this.location = location;
        this.status = status;
    }
    public Evenement(int id_user, Timestamp date, String evenement_type, String description, String location, String status) {
        this.id_user = id_user;
        this.date = date;
        this.evenement_type = evenement_type;
        this.description = description;
        this.location = location;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getEvenement_type() {
        return evenement_type;
    }

    public void setEvenement_type(String evenement_type) {
        this.evenement_type = evenement_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", date=" + date +
                ", evenement_type='" + evenement_type + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
