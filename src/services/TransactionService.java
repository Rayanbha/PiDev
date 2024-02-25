package services;

import Interfaces.CRUD;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import models.transaction;
import util.MyConnection;

import java.sql.Connection;
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

    }

    @Override
    public List<transaction> read() {
        return null;
    }

    @Override
    public boolean update(transaction transaction) {
        return false;
    }
}
