package tn.esprit.koolart.test;

import tn.esprit.koolart.models.Evenement;
import tn.esprit.koolart.models.Participation;
import tn.esprit.koolart.services.Serviceevenement;
import tn.esprit.koolart.services.Serviceparticipation;
import tn.esprit.koolart.utils.Connexion;

import java.sql.Timestamp;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Connexion db=Connexion.getInstance();
        Evenement ev=new Evenement(10,1,new Timestamp(System.currentTimeMillis()),"culinaire","postburger","marsa","encours");
        Serviceevenement se=new Serviceevenement();
        //se.addEvenement(ev);
       // se.updateEvenement(ev);
        se.deleteEvenement(ev);

        List<Evenement> evenement = se.showEvenement();
        for (Evenement event: evenement
        ) {
            System.out.println(event);

        }
        Participation p=new Participation(1,1,1,new Timestamp(System.currentTimeMillis()),"tb");
        Serviceparticipation ps=new Serviceparticipation();
        ps.addParticipation(p);
        ps.updateParticipation(p);
        List<Participation> allparticipation = ps.showParticipation();
        for (Participation participation: allparticipation
        ) {
            System.out.println(participation);


        }
        ps.deleteParticipation(p);


    }
}