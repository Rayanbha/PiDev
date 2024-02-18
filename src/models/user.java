package models;

public class user {

    private int id;
    private String role;
    private String Prenom;
    private String nom;
    private String email;
    private int cin;
    private String pwd;

    public user() {
    }

    public user(int id,String role, String prenom, String nom, String email, int cin, String pwd) {
        this.id = id;
        this.role=role;
        Prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.cin = cin;
        this.pwd = pwd;
    }

    public user(String role,String prenom, String nom, String email, int cin, String pwd) {
        this.role=role;
        Prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.cin = cin;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return  id + " ," + role + "  ," + Prenom +  " ," + nom + " ," + email + " ," + cin + " ," + pwd  ;
    }
}

