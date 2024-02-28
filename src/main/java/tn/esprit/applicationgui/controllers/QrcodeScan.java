package tn.esprit.applicationgui.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class QrcodeScan {
    @javafx.fxml.FXML
    private ImageView qrcodeQrView;

    @Deprecated
    public void SetImageQR(String source ){
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
}
