package models;

import java.util.Date;

public class transaction {

    private int idtransaction;
    private Date date;
    private float montant;

    public transaction() {
    }

    public transaction(Date date, float montant) {
        this.date = date;
        this.montant = montant;
    }

    public transaction(int idtransaction, Date date, float montant) {
        this.idtransaction = idtransaction;
        this.date = date;
        this.montant = montant;
    }

    public int getIdtransaction() {
        return idtransaction;
    }

    public void setIdtransaction(int idtransaction) {
        this.idtransaction = idtransaction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return
                "idtransaction=" + idtransaction +
                ", date=" + date +
                ", montant=" + montant +
                '}';
    }
}
