package tn.esprit.koolart.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import tn.esprit.koolart.models.Evenement;
import tn.esprit.koolart.models.Participation;
import tn.esprit.koolart.services.Serviceparticipation;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SingleEvent  implements Initializable {
    @javafx.fxml.FXML
    private Label date;
    @javafx.fxml.FXML
    private Label description;
    private Evenement e;
    private int userId=1;
    @FXML
    private Button actionbutton;

    public  void setEvent(Evenement eventData){
       this.e=eventData;

    }
    private Serviceparticipation sp =new Serviceparticipation();

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void SetData(Evenement eventData){
        date.setText(eventData.getDate().toString());
        description.setText(eventData.getDescription());
        this.e=eventData;
        Participation p=sp.isAlreadyInEvent(userId,eventData.getId());
        if(p!=null){
           if(p.getParticipation_status().equals("In")){
               actionbutton.setText("Annuler Participation");
           }else{
               actionbutton.setText("Re-Particper");

           }
        }else{
            actionbutton.setText("Particper");

        }



    }


    @FXML


    public void partFunction(javafx.event.ActionEvent actionEvent) {
        Participation p=sp.isAlreadyInEvent(userId,e.getId());
        //Function to cancel/re participer or add participer
        if(p!=null){
            if(p.getParticipation_status().equals("In")){
                //cancel Participation
                p.setParticipation_status("CANCEL");
                sp.updateParticipation(p);
            }else{
                //re Participer
                p.setParticipation_status("In");
                sp.updateParticipation(p);

            }
            SetData(e);

        }else{
            //Add participaton
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/participations.fxml"));
            try {
                Parent parent = fxmlLoader.load();
                Participations partForm= fxmlLoader.getController();
                partForm.setEvent(this.e);
                description.getScene().setRoot(parent);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }




    }
}
