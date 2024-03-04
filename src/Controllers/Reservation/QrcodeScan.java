package Controllers.Reservation;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import util.SendSMS;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class QrcodeScan {
    @javafx.fxml.FXML
    private ImageView qrcodeQrView;
    @javafx.fxml.FXML
    private TextField numTellValue;
    @javafx.fxml.FXML
    private Button codeButton;

    private String MessageBody;

    @Deprecated
    public void SetImageQR(String source ){
        MessageBody=source;
        ByteArrayOutputStream out = QRCode.from(source).to(ImageType.PNG).withSize(192, 136).stream();
        ByteArrayInputStream imageData = new ByteArrayInputStream(out.toByteArray());
        Image image = new Image(imageData);
        qrcodeQrView.setImage(image);



    }

    @javafx.fxml.FXML
    public void closeWindow(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    public boolean verifyIsDigit(String ch) {
        for (int i = 0; i < ch.length(); i++) {
            if (!Character.isDigit(ch.charAt(i))) {
                return false; // If any character is not a digit, return false
            }
        }
        return true; // If all characters are digits, return true
    }
    @javafx.fxml.FXML
    public void TestButton(Event event) {
        if(numTellValue.getText().length()==8 && verifyIsDigit(numTellValue.getText())){
            codeButton.setDisable(false);
        }else{
            codeButton.setDisable(true);
        }
    }

    @javafx.fxml.FXML
    public void sendToNumber(ActionEvent actionEvent) {
        String num=numTellValue.getText();
        SendSMS sms= new SendSMS();
        sms.Send(num,MessageBody);
        closeWindow(actionEvent);

    }
}
