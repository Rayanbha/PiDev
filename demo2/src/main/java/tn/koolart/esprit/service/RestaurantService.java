/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.koolart.esprit.service;

import tn.koolart.esprit.models.Restaurant;
import tn.koolart.esprit.Interfaces.IRestaurantInterface;
import tn.koolart.esprit.utils.Connexion_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class RestaurantService implements IRestaurantInterface {
    private Connection connection;

    public RestaurantService() {
        try {
            this.connection = Connexion_database.getInstance().getConnection();
        } catch (SQLException e) {
            System.err.print(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RestaurantService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int Create(Restaurant rs) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Restaurant (restaurantid, name, location, category) "
                            + "VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, rs.getRestaurantId());
            statement.setString(2, rs.getName());
            statement.setString(3, rs.getLocation());
            statement.setString(4, rs.getCategory());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return result;
    }

    @Override
    public List<Restaurant> Read() {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Restaurant"
            );
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int restaurantId = resultSet.getInt("restaurantid");
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                String category = resultSet.getString("category");

                Restaurant restaurant = new Restaurant(restaurantId, name, location, category);
                restaurants.add(restaurant);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return restaurants;
    }

    @Override
    public int Update(Restaurant R, int id) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Restaurant SET name = ?, location = ?, category = ? WHERE restaurantid = ?"
            );
            statement.setString(1, R.getName());
            statement.setString(2, R.getLocation());
            statement.setString(3, R.getCategory());
            statement.setInt(4, id);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return result;
    }

    @Override
    public void Delete(int id) {
        try {
            PreparedStatement deleteProductsStatement = connection.prepareStatement(
                    "DELETE FROM Product WHERE restaurantid = ?"
            );
            deleteProductsStatement.setInt(1, id);
            deleteProductsStatement.executeUpdate();
            PreparedStatement deleteRestaurantStatement = connection.prepareStatement(
                    "DELETE FROM Restaurant WHERE restaurantid = ?"
            );
            deleteRestaurantStatement.setInt(1, id);
            deleteRestaurantStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    public Restaurant ReadById(int id) {
        Restaurant restaurant = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Restaurant WHERE restaurantid = ?"
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int restaurantId = resultSet.getInt("restaurantid");
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                String category = resultSet.getString("category");

                restaurant = new Restaurant(restaurantId, name, location, category);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return restaurant;
    }

    @Override
    public Restaurant ReadByName(String n) {
        Restaurant restaurant = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Restaurant WHERE `restaurant`.`name` = ?"
            );
            statement.setString(1, n);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int restaurantId = resultSet.getInt("restaurantid");
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                String category = resultSet.getString("category");

                restaurant = new Restaurant(restaurantId, name, location, category);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return restaurant;
    }

    @Override
    public Restaurant ReadByLocation(String Location) {
        Restaurant restaurant = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Restaurant WHERE `restaurant`.`location` = ?"
            );
            statement.setString(1, Location);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int restaurantId = resultSet.getInt("restaurantid");
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                String category = resultSet.getString("category");

                restaurant = new Restaurant(restaurantId, name, location, category);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return restaurant;
    }

    @Override
    public Restaurant ReadByCategory(String Category) {
        Restaurant restaurant = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Restaurant WHERE `restaurant`.`category` = ?"
            );
            statement.setString(1, Category);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int restaurantId = resultSet.getInt("restaurantid");
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                String category = resultSet.getString("category");

                restaurant = new Restaurant(restaurantId, name, location, category);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return restaurant;
    }

}
