package test;

import Controllers.WalletMenu;
import models.Wallet;
import models.transaction;
import models.user;
import services.EmailService;
import services.TransactionService;
import services.UserService;
import services.WalletService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// Press Shift twice to open the Search Everywhere dialog and type show whitespaces,
// then press Enter. You can now see whitespace characters in your code.
public class main {
    public static void main(String[] args) {



        TransactionService ts=new TransactionService();
        long currentTimeMillis = System.currentTimeMillis();

        Timestamp currentTimestamp = new Timestamp(currentTimeMillis);

        WalletService ws=new WalletService();
        Wallet w=new Wallet();


//
//        transaction t=new transaction(1,currentTimestamp,800,5,3);
//        ts.add(t);
            transaction t1=new transaction(3,2,currentTimestamp,5000,5,3);
//        ts.add(t1);
//        transaction t2=new transaction(3,currentTimestamp,800,5,3);
//        ts.add(t2);

        ts.read();
        ts.update(t1);






    }
}



