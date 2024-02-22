package org.example.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabase {
    private static MyDatabase instance;

    private final String URL = "jdbc:mysql://localhost:3306/recipereview";
    private final String USER = "root";
    private final String PASSWORD = "";
    private Connection Connection;

    private MyDatabase() {
        try {
            Connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("connection established");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public static MyDatabase getInstance(){
        if(instance==null)
            instance = new MyDatabase();
        return instance;
    }

    public java.sql.Connection getConnection() {
        return Connection;
    }
}