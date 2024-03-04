package services;

import javafx.collections.ObservableList;

public interface Interfaceevenement<T> {public void addEvenement (T t) ;
    public void updateEvenement (T t) ;
    public void deleteEvenement(T t) ;
    public ObservableList<T> showEvenement ();
}
