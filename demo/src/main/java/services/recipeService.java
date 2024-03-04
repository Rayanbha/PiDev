package services;

import Interfaces.IService;
import models.recipe;
import util.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class recipeService implements IService<recipe> {
    private Connection connection;
    public recipeService() {
        connection = MyConnection.getInstance().getCnx();
    }

    @Override
    public boolean ajouter(recipe recipe) {
        try {
            String req = "INSERT INTO `recipe`(`name`,`ImageURL`, `ingrs`, `instrs`) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, recipe.getName());
            ps.setString(2,recipe.getImageUrl());
            ps.setString(3, recipe.getIngrs());
            ps.setString(4, recipe.getInstrs());
            ps.executeUpdate();
            System.out.println("Recipe Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void modifier(recipe recipe, String a) {

    }


    public void modifier(recipe r) {
        try {
            String req = "UPDATE `Recipe` SET `name`= ?, `ingrs`= ?, `instrs`= ? WHERE idrecp = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, r.getName());
            ps.setString(2, r.getIngrs());
            ps.setString(3, r.getInstrs());
            ps.setInt(4, r.getIdrecp());
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
                r.setIdrecp(rs.getInt("idrecp"));
                r.setName(rs.getString("name"));
                r.setImageUrl(rs.getString("ImageURL"));
                r.setIngrs(rs.getString("ingrs"));
                r.setInstrs(rs.getString("instrs"));

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
                r.setImageUrl(rs.getString(3));
                r.setInstrs(rs.getString(4));
                r.setIngrs(rs.getString(5));
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
                r.setImageUrl(rs.getString(3));
                r.setIngrs(rs.getString(4));
                r.setIngrs(rs.getString(5));
                recipes.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return recipes;
    }
}
