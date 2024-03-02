package tn.esprit.koolart.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;
import tn.esprit.koolart.models.Evenement;
import tn.esprit.koolart.models.Participation;
import tn.esprit.koolart.services.Serviceevenement;
import tn.esprit.koolart.services.Serviceparticipation;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
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
    @FXML
    private ImageView image ;
    @FXML
    private Label noteAVG;
    private Serviceevenement se= new Serviceevenement();
    @FXML
    private AnchorPane ratingZone;
    private boolean userHasRatingOnEvent;

    public  void setEvent(Evenement eventData){
       this.e=eventData;

    }
    private Serviceparticipation sp =new Serviceparticipation();
    private Rating r;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        r=new Rating(5);
        r.ratingProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.intValue()!=0){
            if(userHasRatingOnEvent){
                se.updateRating(userId,e.getId(),newValue.intValue());
            }else{
                se.addRatingValue(userId,e.getId(),newValue.intValue());
            }
            this.noteAVG.setText(String.format("%.2f", se.getRatingByEvent(e.getId())));

            // You can perform any actions you want here when the rating changes
        }
        }
            );

    }
    public void SetData(Evenement eventData){
        date.setText(eventData.getDate().toString());
        description.setText(eventData.getDescription());
        this.e=eventData;

        this.noteAVG.setText(String.format("%.2f", se.getRatingByEvent(e.getId())));

        InputStream inputStream = getClass().getResourceAsStream("/uploads/"+eventData.getImageURL());

        try {
            Image image1 = new Image(inputStream);
            image.setImage(image1);
        } catch (Exception e) {
          System.out.println("event without image");

        }
        Participation p=sp.isAlreadyInEvent(userId,eventData.getId());
        if(p!=null){
           if(p.getParticipation_status().equals("In")){
               actionbutton.setText("Annuler Participation");
           }else{
               actionbutton.setText("Re-Particper");
           }
            int note=se.getRatingByUserAndEvent(userId,eventData.getId());
            this.userHasRatingOnEvent=note!=0;

            r.setRating(Double.valueOf(note));
            if(ratingZone.getChildren().size()==0){
                System.out.println("Im null value");
                ratingZone.getChildren().add(r);

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
                partForm.setEvent(this.e,userId);
                description.getScene().setRoot(parent);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }




    }
}
