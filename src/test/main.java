package test;

import models.Wallet;
import models.user;
import services.UserService;
import services.WalletService;

// Press Shift twice to open the Search Everywhere dialog and type show whitespaces,
// then press Enter. You can now see whitespace characters in your code.
public class main {
    public static void main(String[] args) {
        UserService userserv=new UserService();
        userserv.findemail("rayan@gmail.com");
        user u= new user();
        u=userserv.findemail("rayan@gmail.com");
        System.out.println("-----------------");
        System.out.println(u);



    }
}



