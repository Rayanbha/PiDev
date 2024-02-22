package org.example.services;

import org.example.models.recipe;
import org.example.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class recipeService implements IService<recipe> {
    private Connection connection;

    public recipeService() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public boolean ajouter(recipe recipe) {
        try {
            String req = "INSERT INTO `recipe`(`name`, `ingrs`, `instrs`) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, recipe.getName());
            ps.setString(2, recipe.getIngrs());
            ps.setString(3, recipe.getInstrs());
            ps.executeUpdate();
            System.out.println("Recipe Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void modifier(recipe r, String a) {
        try {
            String req = "UPDATE `Recipe` SET `instrs`= ? WHERE name = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, a);
            ps.setString(2, r.getName());
            ps.executeUpdate();
            System.out.println("Recipe updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(int a) {
        try {
            String req = "DELETE FROM recipe  WHERE idrecp = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, a);
            ps.executeUpdate();
            System.out.println(" recette supprimée avec succée!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<recipe> fetch() {
        List<recipe> recipes = new ArrayList<>();
        try {
            String req = "SELECT * FROM recipe";
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                recipe r = new recipe();
                r.setIdrecp(rs.getInt(1));
                r.setName(rs.getString(2));
                r.setIngrs(rs.getString(3));
                r.setInstrs(rs.getString(4));

                recipes.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return recipes;
    }

    public List<recipe> rechercherecipe(int Idrecp) {
        List<recipe> recipes = new ArrayList<>();
        try {
            String req = "SELECT * FROM recipe WHERE Idrecp LIKE CONCAT(?, '%')";
            PreparedStatement st = connection.prepareStatement(req);
            st.setInt(1, Idrecp);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                recipe r = new recipe();
                r.setIdrecp(rs.getInt(1));
                r.setName(rs.getString(2));
                r.setInstrs(rs.getString(3));
                r.setIngrs(rs.getString(4));
                recipes.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return recipes;
    }

    public List<recipe> filtrerecipe(String name) {
        List<recipe> recipes = new ArrayList<>();
        try {
            String req = "SELECT * FROM recipe WHERE name = ?";
            PreparedStatement st = connection.prepareStatement(req);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                recipe r = new recipe();
                r.setIdrecp(rs.getInt(1));
                r.setName(rs.getString(2));
                r.setIngrs(rs.getString(3));
                r.setIngrs(rs.getString(4));
                recipes.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return recipes;
    }
}
