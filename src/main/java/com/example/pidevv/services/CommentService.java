package com.example.pidevv.services;

import com.example.pidevv.models.comment;
import com.example.pidevv.models.forumpost;
import com.example.pidevv.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentService implements IService<comment>{
    private  Connection connection;
    public CommentService() {
        connection = MyDatabase.getInstance().getConnexion();
    }
    @Override
    public void ajouter(comment comment) throws SQLException {
        String req = "INSERT INTO comment (postId, commentContent) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, comment.getPostId()); // Utilisation de setInt() pour l'ID du post
        ps.setString(2, comment.getCommentContent());
        ps.executeUpdate();
    }








    @Override


    public void modifier(comment comment) throws SQLException {
        String req = "UPDATE comment SET commentContent = ? WHERE commentId = ? AND postId = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, comment.getCommentContent());
        ps.setInt(2, comment.getCommentId());
        ps.setInt(3, comment.getPostId());

        ps.executeUpdate();
    }

    @Override
    public void supprimer(int commentId) throws SQLException {
             String req = "DELETE FROM comment WHERE commentId = ? ";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,
                commentId);
        ps.executeUpdate();



    }

    public List<comment> getCommentsByPostId(int postId) throws SQLException {
        List<comment> comments = new ArrayList<>();
        String req = "SELECT * FROM comment WHERE postId = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, postId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            comment comment = new comment();
            comment.setPostId(rs.getInt("postId"));
            comment.setCommentId(rs.getInt("commentId"));
            comment.setCommentContent(rs.getString("commentContent"));
            comments.add(comment);
        }
        return comments;
    }

    @Override
    public List<comment> recuperer() throws SQLException {
        List<comment> comments = new ArrayList<>();
        String req = "SELECT * FROM comment";
        Statement st = connection.createStatement();
        ResultSet rs =st.executeQuery(req);

        while (rs.next()) {
            comment comment = new comment();
            comment.setPostId(rs.getInt("postId"));
            comment.setCommentId((rs.getInt("commentId")));
            comment.setCommentContent(rs.getString("commentContent"));

            comments.add(comment);
        }
        return comments;
    }
}
