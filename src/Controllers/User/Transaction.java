package Controllers.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Wallet;
import models.transaction;
import models.user;
import services.TransactionService;
import services.UserService;
import services.WalletService;

import java.awt.datatransfer.FlavorEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Transaction {

    @FXML
    private Button Add;

    @FXML
    private TextField datetimeTextField;

    @FXML
    private TextField idcurrentTF;

    @FXML
    private TextField iddestinataireTextField;

    @FXML
    private TextField idwalletTextField;

    @FXML
    private TextField montantTextField;

    private user user;
    private Wallet w;
    public void initData(user user, Wallet w)
    {
        this.user=user;
        this.w=w;
    }
    @FXML
    void addTransaction(ActionEvent event) {
        WalletService ws=new WalletService();
        float oldb=w.getBalance();
        float newb=oldb-Float.parseFloat(montantTextField.getText());

        w.setBalance(newb);
        ws.update(w);

        TransactionService ts=new TransactionService();
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp currentTimestamp = new Timestamp(currentTimeMillis);


        int id=user.getId();
        int idw=w.getIdwallet();

        float montant=Float.parseFloat(montantTextField.getText());

        UserService us=new UserService();
        user rec=new user();



        rec=us.findcin(Integer.parseInt(iddestinataireTextField.getText()));

        transaction t=new transaction(idw,currentTimestamp,montant,rec.getId(),id);

        ts.add(t);
        //--------------------------------------------------TRANSACTION------------------------------------------------------------------


         List<Wallet> wallets = new ArrayList<>();

         wallets=ws.find(rec.getId());


         Wallet w1=new Wallet();

         w1=wallets.stream().findFirst().orElse(null);

         float oldb1= w1.getBalance();
         float newb1=oldb1+ Float.parseFloat(montantTextField.getText());
         w1.setBalance(newb1);
         ws.update(w1);






    }
}
