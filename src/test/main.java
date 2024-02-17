package test;

import models.Wallet;
import models.user;
import services.UserService;
import services.WalletService;

// Press Shift twice to open the Search Everywhere dialog and type show whitespaces,
// then press Enter. You can now see whitespace characters in your code.
public class main {
    public static void main(String[] args) {
        user u= new user("Client","Rayan","Hadj","sefg",4572,"rgdsgsg");
        user u1= new user("Client","Hmed","eez din","sefg",4522852,"rgdsgsg");
        user u2= new user("Client","Yassine","papi","sefg",2852,"rgdsgsg");
        System.out.println("-------------------------------------------------------------");



        user newuser= new user();
        
        UserService userserv = new UserService();
        String name="labib";


        userserv.delete(u2);

        userserv.update(u2);
        userserv.read();
        System.out.println("-------------------------------------------------------------");

        Wallet w1= new Wallet(112,"Lyoum fel lil ");
        Wallet w2= new Wallet(15,"Lyoum fel lil ");

        WalletService walletserv = new WalletService();

      /*  walletserv.add(w1);
        Wallet w2= new Wallet(15,"Lyoum fel lil ");
        walletserv.add(w2);
*/

        //Kifeh nekhou il valeur il bd/ o kifeh nestaamel il datetime 
        walletserv.delete(w2);

        walletserv.read();



    }
}



