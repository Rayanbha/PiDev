package com.example.pidevv.services;

import com.example.pidevv.models.forumpost;
import com.example.pidevv.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ForumpostService implements IService<forumpost> {

    private Connection connection;
    public ForumpostService(){
        connection = MyDatabase.getInstance().getConnexion();
    }

    @Override
    public void ajouter(forumpost forumpost) throws SQLException {

        //String req = "INSERT INTO forumpost (titre , contenu ) VALUES ('" +forumpost.getTitle()+"','" +forumpost.getContent()+")";
        //Statement st= connection.createStatement();
        //st.executeUpdate(req);
        String req = "INSERT INTO forumpost (title, content) VALUES ('" + forumpost.getTitle() + "','" + forumpost.getContent() + "')";
        Statement st = connection.createStatement();
        st.executeUpdate(req);

    }

    @Override


        public void modifier(forumpost forumpost) throws SQLException {
            String req = "UPDATE forumpost SET title = ?, content = ? WHERE postId = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, forumpost.getTitle());
            ps.setString(2, forumpost.getContent());
            ps.setInt(3, forumpost.getPostId());

            ps.executeUpdate();
        }





    @Override
    public void supprimer(int postID) throws SQLException{
        String req = "DELETE FROM forumpost WHERE postId = ? ";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,
                postID);
        ps.executeUpdate();


    }

    @Override
    public List<forumpost> recuperer() throws SQLException{
        List<forumpost> forumposts = new ArrayList<>();
        String req = "SELECT * FROM forumpost";
        Statement st = connection.createStatement();
        ResultSet rs =st.executeQuery(req);

        while (rs.next()){
            forumpost forumpost = new forumpost();
            forumpost.setPostId(rs.getInt("postId"));
            forumpost.setTitle(rs.getString("title"));
            forumpost.setContent((rs.getString("content")));
            forumposts.add(forumpost);
        }


        return forumposts;
    }
}
