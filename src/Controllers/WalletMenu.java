package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Wallet;
import services.WalletService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WalletMenu implements Initializable {
    private int id;
    private Wallet w= new Wallet();

    private WalletService walletService= new WalletService();


    @FXML
    private TextField balanceTF;

    public void initWallet(int id)
    {
        this.id=id;
        w=walletService.find(id);
        System.out.println(walletService.count(id));

        populateFields();
    }


    private void populateFields() {
        balanceTF.setText(String.valueOf(w.getBalance()));
    }
    @FXML
    private Button AddWallet;

    @FXML
    private ChoiceBox<Integer> Walletnumber;
    @FXML
    void AddWallet(ActionEvent event) {

        Wallet wallet=new Wallet(0,id);
        walletService.add(wallet);
        Stage current=(Stage) AddWallet.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/WalletMenu.fxml"));
        try {
            Parent root = loader.load();
            WalletMenu walletc=loader.getController();
            walletc.initWallet(w.getId());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
