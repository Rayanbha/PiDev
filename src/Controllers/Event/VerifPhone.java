package Controllers.Event;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VerifPhone {

    @javafx.fxml.FXML
    private TextField code;
    @javafx.fxml.FXML
    private Button VerifCodeButton;
    private String codeValue;
    private boolean isValide =false;




    @javafx.fxml.FXML
    public void VerifCode(ActionEvent actionEvent) {
        isValide=codeValue.equals(code.getText());
        Stage stage = (Stage) VerifCodeButton.getScene().getWindow();
        stage.close();
    }
    public Boolean getIsValid(){

        return isValide;
    }



    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }
}
