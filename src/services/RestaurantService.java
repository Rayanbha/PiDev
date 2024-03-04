/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Interfaces.IRestaurantInterface;
import models.Restaurant;
import util.MyConnection;

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
    private Connection connection=MyConnection.getInstance().getCnx();;


    @Override
    public int Create(Restaurant rs) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Restaurant (name, location, category, image) VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, rs.getName());
            statement.setString(2, rs.getLocation());
            statement.setString(3, rs.getCategory());
            statement.setString(4, rs.getImage());
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
            connection = MyConnection.getInstance().getCnx();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Restaurant"
            );
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int restaurantId = resultSet.getInt("restaurantid");
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                String category = resultSet.getString("category");
                String image = resultSet.getString("image");
                Restaurant restaurant = new Restaurant(restaurantId, name, location, category, image);
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
                    "UPDATE Restaurant SET name = ?, location = ?, category = ? , image = ? WHERE restaurantid = ?"
            );
            statement.setString(1, R.getName());
            statement.setString(2, R.getLocation());
            statement.setString(3, R.getCategory());
            statement.setString(4, R.getImage());
            statement.setInt(5, id);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return result;
    }

    @Override
    public int Delete(int id) {
        int result = 0;
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
            result = deleteRestaurantStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return result;
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
                String image = resultSet.getString("image");
                restaurant = new Restaurant(restaurantId, name, location, category,image);
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
                String image = resultSet.getString("image");
                restaurant = new Restaurant(restaurantId, name, location, category,image);
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
                String image = resultSet.getString("image");

                restaurant = new Restaurant(restaurantId, name, location, category,image);
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
                String image = resultSet.getString("image");
                restaurant = new Restaurant(restaurantId, name, location, category,image);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return restaurant;
    }

}
