package service;

import Entité.Product;
import Interfaces.IProductInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductInterface
{
    private Connection connection;

    public ProductService() {
        try {
            this.connection = Connexion_database.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.print(e.getMessage());
        }
    }
    @Override
    public int Create(Product product) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Product (name, price, category, restaurantid) VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getCategory());
            statement.setInt(4, product.getRestaurantId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return result;
    }

    @Override
    public List<Product> Read() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Product"
            );
            ResultSet resultSet = statement.executeQuery();//traja3 tab

            while (resultSet.next()) { // pour chaque element fil tab
                int productId = resultSet.getInt("productid");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String category = resultSet.getString("category");
                int restaurantId = resultSet.getInt("restaurantid");

                Product product = new Product(productId, name, price, category, restaurantId);
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return products;
    }

    @Override
    public int Update(Product product, int id) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Product SET name = ?, price = ?, category = ?, restaurantid = ? WHERE productid = ?"
            );
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getCategory());
            statement.setInt(4, product.getRestaurantId());
            statement.setInt(5, id);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return result;
    }

    @Override
    public void Delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Product WHERE productid = ?"
            );
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    public Product ReadById(int id) {
        Product product = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Product WHERE productid = ?"
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int productId = resultSet.getInt("productid");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String category = resultSet.getString("category");
                int restaurantId = resultSet.getInt("restaurantid");

                product = new Product(productId, name, price, category, restaurantId);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return product;
    }
    @Override
    public Product ReadByNom(String Nom) {
        Product product = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Product WHERE name = ?"
            );
            statement.setString(1, Nom);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int productId = resultSet.getInt("productid");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String category = resultSet.getString("category");
                int restaurantId = resultSet.getInt("restaurantid");

                product = new Product(productId, name, price, category, restaurantId);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return product;
    }

    @Override
    public Product ReadByCategory(String Category) {
        Product product = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Product WHERE category = ?"
            );
            statement.setString(1, Category);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int productId = resultSet.getInt("productid");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String category = resultSet.getString("category");
                int restaurantId = resultSet.getInt("restaurantid");

                product = new Product(productId, name, price, category, restaurantId);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return product;
    }

    @Override
    public Product ReadByRestaurantId(int id) {
        Product product = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Product WHERE restaurantid = ?"
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int productId = resultSet.getInt("productid");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String category = resultSet.getString("category");
                int restaurantId = resultSet.getInt("restaurantid");

                product = new Product(productId, name, price, category, restaurantId);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return product;
    }
}
