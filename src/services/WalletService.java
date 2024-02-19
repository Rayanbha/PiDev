package services;

import Interfaces.CRUD;
import models.Wallet;
import util.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WalletService implements CRUD<Wallet> {

    Connection cnx= MyConnection.getInstance().getCnx();

    @Override
    public void add(Wallet wallet) {
        try {
            String req="INSERT INTO `wallet`(`balance`,`iduser`) VALUES ('"+wallet.getBalance()+"','"+wallet.getIduser()+"')";
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
            String req="DELETE FROM `wallet` WHERE `idwallet`= '" +wallet.getIdwallet()+ "';";
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
            w.setIduser(set.getInt("iduser"));
            wallets.add(w);
            System.out.println(wallets) ;
        }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return wallets;
    }

    @Override
    public boolean update(Wallet wallet)
    {
     String req="UPDATE `wallet` SET `balance`='"+wallet.getBalance()+"' WHERE `idwallet`='"+wallet.getId()+"'";
        try {
            Statement stat= cnx.createStatement();
            stat.executeUpdate(req);
            System.out.println("UPDATED WALLET");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Wallet> find(int id)
    {

        try {

            String req = "SELECT * FROM `wallet` WHERE `iduser`='" + id + "'";
            Statement stat = cnx.createStatement();
            ResultSet set= stat.executeQuery(req);
            List<Wallet> wallets = new ArrayList<>();
                while(set.next()) {
                    Wallet w=new Wallet();
                    w.setId(set.getInt("idwallet"));
                    w.setBalance(set.getInt("balance"));
                    w.setIduser(set.getInt("iduser"));
                    wallets.add(w);
                    System.out.println(w);
                }
                    return wallets;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int count(int id)
    {   int s = 0;

        try {
            String req="SELECT COUNT(*) AS count FROM `wallet` WHERE `iduser`='" + id + "'";

            Statement stat= cnx.createStatement();
            ResultSet res= stat.executeQuery(req);
            while(res.next())
            {
                s=res.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return s;
    }



}
