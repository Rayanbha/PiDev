package tn.esprit.koolart.services;

import javafx.collections.ObservableList;

import java.util.List;

public interface Interfaceevenement<T> {public void addEvenement (T t) ;
    public void updateEvenement (T t) ;
    public void deleteEvenement(T t) ;
    public ObservableList<T> showEvenement ();
}
