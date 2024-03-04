package Interfaces;

import java.sql.SQLException;

public interface IServiceR<T>{
void ajouter(T t) throws SQLException;
void modifier(T t) throws SQLException;
void supprimer(int id) throws SQLException;
}
