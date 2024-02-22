package org.example.services;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {

    public boolean ajouter (T t) throws SQLException;
    public void modifier (T t,String a);
    public void supprimer (int t);
    public List<T> fetch();

}

