package services;

import Interfaces.CRUD;
import models.Wallet;
import models.user;
import util.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WalletService implements CRUD<Wallet> {

    Connection cnx= MyConnection.getInstance().getCnx();

    @Override
    public void add(Wallet wallet) {
        try {
            String req="INSERT INTO `wallet`(`balance`,`transactions`) VALUES ('"+wallet.getBalance()+"','"+wallet.getTransactions()+"')";
            Statement stat=cnx.createStatement();
            stat.executeUpdate(req);
            System.out.println("ADDED WALLET");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Wallet wallet) {
        try {
            String req="DELETE FROM `wallet` WHERE `idwallet`= '" +wallet.getId()+ "';";
            Statement stat=cnx.createStatement();
            stat.executeUpdate(req);
            System.out.println("DELETED WALLET");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Wallet> read() {
        List<Wallet> wallets = new ArrayList<>();
        String req="SELECT * FROM `wallet`";
        try {
        Statement stat=cnx.createStatement();
        ResultSet set=stat.executeQuery(req);
        while(set.next())
        {
            Wallet w=new Wallet();
            w.setId(set.getInt("idwallet"));
            w.setBalance(set.getInt("balance"));
            w.setTransactions(set.getString("transactions"));
            wallets.add(w);
            System.out.println(wallets) ;
        }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return wallets;
    }

    @Override
    public void update(Wallet wallet, String transaction)
    {
     String req="UPDATE `wallet` SET `idwallet`='"+wallet.getId()+"',`balance`='"+wallet.getBalance()+"',`transactions`='"+transaction+"' WHERE `idwallet`='"+wallet.getId()+"'";
        try {
            Statement stat= cnx.createStatement();
            stat.executeUpdate(req);
            System.out.println("UPDATED WALLET");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
