package services;

import Interfaces.CRUD;

import java.sql.*;

import models.transaction;
import util.MyConnection;

import java.util.ArrayList;
import java.util.List;

public class TransactionService implements CRUD<transaction> {

    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void add(transaction transaction) {


        String req="INSERT INTO `transaction`(`idwallet`, `date`, `montant`, `iddestinataire`, `idcurrent`) VALUES ('"+ transaction.getIdwallet() +"','"+transaction.getDatetime()+"','"+transaction.getMontant()+"','"+transaction.getIddestinataire()+"','"+transaction.getIdcurrent()+"')";
        try {
            Statement stat= cnx.createStatement();
            stat.executeUpdate(req);
            System.out.println("Transaction Done");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(transaction transaction) {
        String req="DELETE FROM `transaction` WHERE `idtransaction`='"+transaction.getIdtransaction()+"';";
        try {
            Statement stat=cnx.createStatement();
            stat.executeUpdate(req);
            System.out.println("Deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<transaction> read() {
        List<transaction> transaction=new ArrayList<>();
        try {
            String req="SELECT * FROM `transaction`";

            Statement stat=cnx.createStatement();
            ResultSet res= stat.executeQuery(req);
            while(res.next())
            {
                transaction s=new transaction();
                s.setDatetime(res.getTimestamp("date"));
                s.setIdwallet(res.getInt("idwallet"));
                s.setMontant(res.getFloat("montant"));
                s.setIddestinataire(res.getInt("iddestinataire"));
                s.setIdcurrent(res.getInt("idcurrent"));
                transaction.add(s);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return transaction;
    }

    @Override
    public boolean update(transaction transaction) {
        String req="UPDATE `transaction` SET " +
                "`idwallet`='"+transaction.getIdwallet()+"'," +
                "`date`='"+transaction.getDatetime() +"'," +
                "`montant`='"+transaction.getMontant()+"'," +
                "`iddestinataire`='"+transaction.getIddestinataire()+"'," +
                "`idcurrent`='"+transaction.getIdcurrent()+"'" +
                " WHERE `idtransaction`='"+transaction.getIdtransaction()+"'";
        try {
            Statement stat=cnx.createStatement();
            stat.executeUpdate(req);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
