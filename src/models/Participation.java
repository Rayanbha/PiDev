package models;

import java.sql.Timestamp;

public class Participation {
    private int participation_id; private int user_id;private int event_id; private Timestamp participation_date; private String participation_status;
    private String numero;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Participation(int participation_id, int user_id, int event_id, Timestamp participation_date, String participation_status) {
        this.participation_id = participation_id;
        this.user_id = user_id;
        this.event_id = event_id;
        this.participation_date = participation_date;
        this.participation_status = participation_status;

    }

    public Participation() {

    }

    public int getParticipation_id() {
        return participation_id;
    }

    public void setParticipation_id(int participation_id) {
        this.participation_id = participation_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public Timestamp getParticipation_date() {
        return participation_date;
    }

    public void setParticipation_date(Timestamp participation_date) {
        this.participation_date = participation_date;
    }

    public String getParticipation_status() {
        return participation_status;
    }

    public void setParticipation_status(String participation_status) {
        this.participation_status = participation_status;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "participation_id=" + participation_id +
                ", user_id=" + user_id +
                ", event_id=" + event_id +
                ", participation_date=" + participation_date +
                ", participation_status='" + participation_status + '\'' +
                '}';
    }
}
