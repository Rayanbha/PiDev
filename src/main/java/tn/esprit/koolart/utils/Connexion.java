package tn.esprit.koolart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion { static final String URL ="jdbc:mysql://localhost:3306/koolart";
    static final String USER ="root";
    static final String PASSWORD ="";
    private Connection cnx;
    //1
    static Connexion instance;

    //const
    //2
    private Connexion(){
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("connected successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public Connection getCnx() {
        return cnx;
    }

    //3
    public static Connexion getInstance() {
        if(instance == null)
            instance = new Connexion();

        return instance;
    }
}
