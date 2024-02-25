package services;

import Interfaces.CRUD;
import models.user;
import util.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements CRUD<user> {

    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void add(user user ) {

        String req="INSERT INTO `user`(`role`,`prenom`, `nom`, `email`, `cin`, `pwd`,`hashedpwd`,`salt`) VALUES ('"+user.getRole() +"','"+user.getPrenom()+"','"+user.getNom()+"','"+user.getEmail()+"','"+user.getCin()+"','"+user.getPwd()+"','"+user.getHashedpwd()+"','"+user.getSalt()+"')";
        try {
            Statement st=cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("ADDED");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(user user) {
        String req = "DELETE FROM `user` WHERE `cin` = '" + user.getCin() + "';";
        try {
            Statement st=cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<user> read() {
        List<user> users = new ArrayList<>();
        try {
            String req = "SELECT * FROM user";

            Statement stat= cnx.createStatement();
            ResultSet res = stat.executeQuery(req);
            while(res.next())
            {
                user s=new user();
                s.setId(res.getInt("id"));
                s.setRole(res.getString("role"));
                s.setNom(res.getString("nom"));
                s.setPrenom(res.getString("prenom"));
                s.setCin(res.getInt("cin"));
                s.setEmail(res.getString("email"));
                s.setPwd(res.getString("pwd"));
                s.setHashedpwd(res.getString("hashedpwd"));
                s.setSalt(res.getString("salt"));


                users.add(s);
                System.out.println(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    @Override
    public boolean update(user user)
    {
        try {
            String req="UPDATE `user` SET `prenom`='"+user.getPrenom()+"',`nom`='"+user.getNom()+"',`email`='"+user.getEmail()+"',`cin`='"+user.getCin()+"',`pwd`='"+user.getPwd()+"',`hashedpwd` ='"+user.getHashedpwd()+"',`salt`='"+user.getSalt()+"' WHERE `cin`='"+user.getCin()+"'";
          /*  Statement state=cnx.createStatement();
            state.executeUpdate(req);*/
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("updated");
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public user findemail(String email)
    {
        user s=new user();
        try {
            String req="SELECT * FROM `user` WHERE `email`='"+email+"'";
            Statement stat=cnx.createStatement();
            ResultSet res=stat.executeQuery(req);
            while(res.next())
            {

                s.setId(res.getInt("id"));
                s.setRole(res.getString("role"));
                s.setNom(res.getString("nom"));
                s.setPrenom(res.getString("prenom"));
                s.setCin(res.getInt("cin"));
                s.setEmail(res.getString("email"));
                s.setPwd(res.getString("pwd"));
                s.setHashedpwd(res.getString("hashedpwd"));
                s.setSalt(res.getString("salt"));


                System.out.println("Found");
            System.out.println(s);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return s;
    }



}
