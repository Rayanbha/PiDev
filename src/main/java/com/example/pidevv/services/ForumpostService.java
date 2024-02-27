package com.example.pidevv.services;

import com.example.pidevv.models.forumpost;
import com.example.pidevv.utils.MyDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
       /* String req = "INSERT INTO forumpost (title, content) VALUES ('" + forumpost.getTitle() + "','" + forumpost.getContent() + "')";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
*/
        try {
            // Vérifiez si la connexion est null avant d'utiliser createStatement()
            if (connection != null) {
                String req = "INSERT INTO forumpost (title, content) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(req);
                // Remplacez les placeholders (?) par les valeurs réelles
                preparedStatement.setString(1, forumpost.getTitle());
                preparedStatement.setString(2, forumpost.getContent());
                // Exécutez la requête préparée
                preparedStatement.executeUpdate();
                System.out.println("Post ajouté avec succès !");
            } else {
                System.err.println("La connexion à la base de données n'a pas été initialisée correctement.");
            }
        } catch (SQLException e) {
            // Gérez les exceptions SQL ici
            e.printStackTrace();
        }
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
    public ObservableList<forumpost> recuperer() {
        ObservableList<forumpost> forumposts = FXCollections.observableArrayList();
        String req = "SELECT * FROM forumpost";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(req);

            while (rs.next()){
                forumpost forumpost = new forumpost();
                forumpost.setPostId(rs.getInt("postId"));
                forumpost.setTitle(rs.getString("title"));
                forumpost.setContent((rs.getString("content")));
                forumposts.add(forumpost);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




        return forumposts;
    }




}
