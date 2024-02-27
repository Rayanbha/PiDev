package tn.esprit.koolart.services;

import java.util.List;

public interface Interfaceparticipation<T> {public void addParticipation (T t) ;
    public void updateParticipation (T t) ;
    public void deleteParticipation(T t) ;
    public List<T> showParticipation ();
}
