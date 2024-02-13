package com.example.pidevv.test;

import com.example.pidevv.models.comment;
import com.example.pidevv.models.forumpost;
import com.example.pidevv.services.CommentService;
import com.example.pidevv.services.ForumpostService;
import com.example.pidevv.utils.MyDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // MyDatabase db = MyDatabase.getInstance();
        //MyDatabase db2 =MyDatabase.getInstance();

        // System.out.println(db);
        //System.out.println(db2);
//ajouter post
       /* ForumpostService fs = new ForumpostService();
        try {
          fs.ajouter(new forumpost("photo", "un plat"));
            fs.ajouter(new forumpost("video", "escalope"));
            fs.ajouter(new forumpost("image", "pasta"));
            fs.ajouter(new forumpost("photo", "lasagne"));
            fs.supprimer(2);
            System.out.println((fs.recuperer()));
        } catch (SQLException e) {
            System.err.println((e.getMessage()));

            */
//ajouter comment
       CommentService cs = new CommentService();
            try {

                cs.ajouter(new comment( 1,6,"ababababab"));
                cs.ajouter(new comment( 15,17,"jaime pas"));
                cs.ajouter(new comment( 15,15,"c'est tres delicieuuuuse"));
                //fs.supprimer(2);
                //System.out.println((fs.recuperer()));
            } catch (SQLException e) {
                System.err.println((e.getMessage()));
            }}


//modif post
        /*
            ForumpostService fs = new ForumpostService();
            try {
             fs.modifier(new forumpost(1,"taswira","kosksi"));
            }catch(SQLException e){
              System.err.println(e.getMessage());
             }}
             */
//modifier commentaire
         /*
               CommentService cs = new CommentService();
                try {
                cs.modifier(new comment(9,6,"maset baaaaaaaaaarcha"));
                }catch(SQLException e){
                  System.err.println(e.getMessage());
                 
           */

//supprimer post

       /*
       ForumpostService fs = new ForumpostService();
        try {
        fs.supprimer(1);
    }catch (SQLException e){
            System.err.println((e.getMessage()));
        }




    }}
    */

    /*CommentService cs = new CommentService();
        try {
        cs.supprimer(8);
    }catch (SQLException e){
            System.err.println((e.getMessage()));
    }}}

     */

    /*CommentService cs = new CommentService();
            try {

                   // cs.ajouter(new comment( 9,6,"ababababab"));
                //cs.supprimer(2);
                    System.out.println((cs.recuperer()));
                    } catch (SQLException e){
                    System.err.println((e.getMessage()));
                    }
    }
    */

}