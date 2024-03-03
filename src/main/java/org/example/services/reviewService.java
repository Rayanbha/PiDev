package org.example.services;
import org.example.models.review;
import org.example.utils.MyDatabase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public  class reviewService implements IService<review> {
       Connection connection = MyDatabase.getInstance().getConnection();
    @Override
    public boolean ajouter(review r) {
        try {
            String req = "INSERT INTO review (rating, com, photo) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, r.getRating());
            ps.setString(2, r.getCom());
            ps.setString(3, r.getImageUrl());
            ps.executeUpdate();
            System.out.println("Review Added successfully!");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    @Override
    public void modifier(review review, String a) {

    }


    public void modifier(review r, String newComment, double newRating) {
        try {
            String req = "UPDATE `Review` SET `com`= ?, `rating`= ? WHERE idrevw = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, newComment);
            ps.setDouble(2, newRating);
            ps.setInt(3, r.getIdrevw());
            ps.executeUpdate();
            System.out.println("Review updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void supprimer(int a) {

        try {
            String req = "DELETE FROM review  WHERE idrevw = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, a);
            ps.executeUpdate();
            System.out.println(" review supprimée avec succée!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<review> fetch()
    {

        List<review> reviews = new ArrayList<>();
        try {

            String req = "SELECT * FROM review";
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                review r = new review();
                r.setIdrevw(rs.getInt(1));
                r.setRating(rs.getString(2));
                r.setCom(rs.getString(3));
                r.setImageUrl(rs.getString(4));


                reviews.add(r);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reviews;

    }
    public List<review> recherchereview(int Idrevw) {
        List<review> reviews = new ArrayList<>();
        try {

            String req = "SELECT * FROM review WHERE Idrevw LIKE CONCAT(?, '%')";
            PreparedStatement st = connection.prepareStatement(req);
            st.setInt(1, Idrevw);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                review r = new review();
                r.setIdrevw(rs.getInt(1));
                r.setRating(rs.getString(2));
                r.setCom(rs.getString(3));
                reviews.add(r);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reviews;
    }
    public List<review> filtrereview(String name) {
        List<review> reviews = new ArrayList<>();
        try {

            String req = "SELECT * FROM review WHERE Idrevw=?";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                review r = new review();
                r.setIdrevw(rs.getInt(1));
                r.setRating(rs.getString(2));
                r.setCom(rs.getString(3));


                reviews.add(r);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reviews;}

    public boolean ajouter(String commentaire, String image, double ratingValue) {
        return false;
    }
}
