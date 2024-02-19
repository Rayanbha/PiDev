package Controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Wallet;
import services.UserService;
import services.WalletService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class WalletMenu  {
    private int id;
    private List<Wallet> wallets = new ArrayList<>();
    private Wallet w=new Wallet();


    private WalletService walletService= new WalletService();


    @FXML
    private TextField balanceTF;

    public void initWallet(int id)
    {

        this.id=id;
        wallets=walletService.find(id);
        System.out.println(wallets);

        populateFields();
            //-------------------------------------------------CHOICEBOX---------------------------------------------------------------
        ObservableList<Integer> walletCountList= FXCollections.observableArrayList();
        int count=walletService.count(id);
        int l=1;
        for(int i=0;i<count;i++)
        {
            walletCountList.add(l);
            l++;
        }
        Walletnumber.setItems(walletCountList);
        Walletnumber.setOnAction(this::getnumber);
        //------------------------------------------------------------------------------------------------------------------------------
    }

    private void getnumber(ActionEvent actionEvent) {
        int choice=Walletnumber.getValue();
        int count=walletService.count(id);
        int l=0;
        for(int i=0;i<count+1;i++)
        {
            if(choice==i)
            {
                for (Wallet wallet:wallets)
                {   l++;
                    if(l==i)
                    {
                        w=wallet;
                        balanceTF.setText(String.valueOf(wallet.getBalance()));
                        break;

                    }
                }

            }
        }

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


        try {
            Wallet wallet=new Wallet(0,id);
            walletService.add(wallet);


            Stage current=(Stage) AddWallet.getScene().getWindow();
            current.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/WalletMenu.fxml"));
            Parent root = loader.load();
            WalletMenu walletc=loader.getController();
            walletc.initWallet(id);
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Wallet");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private Button ChangeBal;
    @FXML
    private TextField newBalTF;
    @FXML
    void ChangeBal(ActionEvent event) {
        float jdid;
        float newbal=Float.parseFloat(newBalTF.getText());
        jdid=w.getBalance()+newbal;
        w.setBalance(jdid);
        walletService.update(w);

        Stage current=(Stage) ChangeBal.getScene().getWindow();
        current.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/WalletMenu.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        WalletMenu walletc=loader.getController();
        walletc.initWallet(id);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Wallet");
        stage.setScene(scene);
        stage.show();
        balanceTF.setText(String.valueOf(jdid));

    }

    @FXML
    private Button DeleteWallet;

    @FXML
    void DeleteWallet(ActionEvent event) {
        if (Walletnumber.getValue() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete User");
            alert.setContentText("Are you sure you want to delete Wallet ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Call your UserService to delete the user
               walletService.delete(w);

                Stage current=(Stage) DeleteWallet.getScene().getWindow();
                current.close();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/WalletMenu.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                WalletMenu walletc=loader.getController();
                walletc.initWallet(id);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Wallet");
                stage.setScene(scene);
                stage.show();

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No user selected!");
            alert.showAndWait();
        }

    }


}
