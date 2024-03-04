package Controllers.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import models.user;
import services.UserService;

public class UserDetail {


    @FXML
    private TextField EmailTF;

    @FXML
    private TextField NomTF;

    @FXML
    private TextField PrenomTF;

    @FXML
    private TextField cinTF;

    @FXML
    private Circle circle;

    @FXML
    private Button confirmer;

    @FXML
    private TextField pwdTF;
    private user userToUpdate;
    @FXML
    private TextField status;



    @FXML
    private UserService userService = new UserService();



    public void initData(user user) {
        this.userToUpdate = user;
        populateFields();
        System.out.println(userToUpdate.getHashedpwd()+"/"+userToUpdate.getSalt());
    }

    private void populateFields() {
        NomTF.setText(userToUpdate.getNom());
        PrenomTF.setText(userToUpdate.getPrenom());
        cinTF.setText(String.valueOf(userToUpdate.getCin())); // Convert to String for TextField
        EmailTF.setText(userToUpdate.getEmail());
        pwdTF.setText(userToUpdate.getPwd());
        status.setText(String.valueOf(userToUpdate.isStatus()));
    }


    @FXML
    void confirmer(ActionEvent event) {

    }

}

