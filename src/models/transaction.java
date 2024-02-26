    package models;
    
    import java.sql.Timestamp;
    import java.util.Date;
    
    public class transaction {
    
     private int idtransaction;
     private int idwallet;
     private Timestamp datetime;
     private float montant;
     private int iddestinataire;
     private int idcurrent;
    
        public transaction(int idtransaction, int idwallet, Timestamp datetime, float montant, int iddestinataire, int idcurrent) {
            this.idtransaction = idtransaction;
            this.idwallet = idwallet;
            this.datetime = datetime;
            this.montant = montant;
            this.iddestinataire = iddestinataire;
            this.idcurrent = idcurrent;
        }
    
        public transaction(int idwallet, Timestamp datetime, float montant, int iddestinataire, int idcurrent) {
            this.idwallet = idwallet;
            this.datetime = datetime;
            this.montant = montant;
            this.iddestinataire = iddestinataire;
            this.idcurrent = idcurrent;
        }

        public transaction() {
        }
    
        public int getIdtransaction() {
            return idtransaction;
        }
    
        public void setIdtransaction(int idtransaction) {
            this.idtransaction = idtransaction;
        }
    
        public int getIdwallet() {
            return idwallet;
        }
    
        public void setIdwallet(int idwallet) {
            this.idwallet = idwallet;
        }
    
        public Timestamp getDatetime() {
            return datetime;
        }
    
        public void setDatetime(Timestamp datetime) {
            this.datetime = datetime;
        }
    
        public float getMontant() {
            return montant;
        }
    
        public void setMontant(float montant) {
            this.montant = montant;
        }
    
        public int getIddestinataire() {
            return iddestinataire;
        }
    
        public void setIddestinataire(int iddestinataire) {
            this.iddestinataire = iddestinataire;
        }
    
        public int getIdcurrent() {
            return idcurrent;
        }
    
        public void setIdcurrent(int idcurrent) {
            this.idcurrent = idcurrent;
        }
    
        @Override
        public String toString() {
            return "id wallet = " + idwallet +
                    ", date and time = " + datetime +
                    ", montant = " + montant +
                    ", Other Person id = " + iddestinataire +
                    ", Your Id = " + idcurrent +
                    '}';
        }
    }
