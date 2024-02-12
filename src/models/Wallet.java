package models;

public  class Wallet {
    private int id;
    private float balance;
    private String transactions;

    public Wallet(int id, float balance,String Transactions) {
        this.id = id;
        this.balance = balance;
        this.transactions=Transactions;
    }

    public Wallet(float balance,String transactions) {
        this.balance = balance;
        this.transactions=transactions;
    }

    public Wallet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", balance=" + balance +
                ", transactions='" + transactions + '\'' +
                '}';
    }

}
