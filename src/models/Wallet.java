package models;

public  class Wallet {
    private int idwallet;
    private float balance;
    private  int iduser;

    public Wallet(int id, float balance,int iduser) {
        this.idwallet = id;
        this.balance = balance;
        this.iduser=iduser;
    }

    public Wallet(float balance,int iduser ) {
        this.balance = balance;
        this.iduser=iduser;
    }

    public Wallet() {
    }

    public int getId() {
        return idwallet;
    }

    public void setId(int id) {
        this.idwallet = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getIdwallet() {
        return idwallet;
    }

    public void setIdwallet(int idwallet) {
        this.idwallet = idwallet;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "idwallet=" + idwallet +
                ", balance=" + balance +
                ", iduser=" + iduser +
                '}';
    }
}
