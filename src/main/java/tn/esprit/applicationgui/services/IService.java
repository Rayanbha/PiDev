package tn.esprit.applicationgui.services;

import java.sql.SQLException;

public interface IService <T>{
void ajouter(T t) throws SQLException;
void modifier(T t) throws SQLException;
void supprimer(int id) throws SQLException;
}
