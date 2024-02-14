package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion_database {

    public static Connexion_database instance;

    private Connexion_database() {
    }
    private Connection connection;
    private final String host = "localhost:3306";
    private final String user = "root";
    private final String password = "";
    private final String database = "koolart";

    public static Connexion_database getInstance() {
        if (instance == null) {
            instance = new Connexion_database();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if(connection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password);
            System.out.println("Database is successfully connected.");
        }
        return connection;
    }
}
