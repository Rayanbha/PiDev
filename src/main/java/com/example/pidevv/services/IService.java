package com.example.pidevv.services;

import java.sql.SQLException;
import java.util.List;

public interface IService <T> {
    void ajouter (T t ) throws SQLException;
    void modifier (T t) throws SQLException;
    void supprimer (int postId ) throws SQLException;
    List<T> recuperer() throws SQLException;
}
