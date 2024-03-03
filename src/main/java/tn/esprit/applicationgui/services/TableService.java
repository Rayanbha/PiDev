package tn.esprit.applicationgui.services;

import tn.esprit.applicationgui.models.Table;

import tn.esprit.applicationgui.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableService implements IService<Table> {
    private Connection connection;

    public TableService() {
        connection = MyDatabase.getInstance().getConnection();
    }

    ///////////////////Ajouter////////////////////////////////////////////////////////////
    @Override
    public void ajouter(Table table) {
        String req = "INSERT INTO `table` (`ID_table`, `Type_table`, `Description`) VALUES (?, ?, ?)";

        try {
            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(req);

            // Set the parameters
            preparedStatement.setInt(1, table.getID_table());

            preparedStatement.setString(2, table.getType_table());
            preparedStatement.setString(3, table.getDescription());

            // Execute the update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // or log the exception
        }
    }

    ///MODIFIER////////////////////////////////////////////////////////////////////////////
    @Override
    public void modifier(Table table)  {
        String req = "UPDATE `table` SET type_table=?, emplacement=?, etat_table=?,description=? , ID_restaurant=? , isReserver=?  WHERE ID_table=?";
        PreparedStatement tb = null;
        try {
            tb = connection.prepareStatement(req);
            tb.setString(1, table.getType_table());
            tb.setString(2, table.getEmplacement());
            tb.setString(3, table.getEtat_table());
            tb.setString(4, table.getDescription());
            tb.setInt(5, table.getID_restaurant());
            tb.setBoolean(6,table.getisReserver());
            tb.setInt(7, table.getID_table());
            tb.executeUpdate();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

    }

    ///SUPPRIMER//////////////////////////////////////////////////////////////////////////
    @Override
    public void supprimer(int ID_table) throws SQLException {
        String req = "DELETE FROM `table` WHERE ID_table = ?";
        PreparedStatement tb = connection.prepareStatement(req);
        tb.setInt(1, ID_table);
        tb.executeUpdate();

    }

    ///RECUPERER (READ)//////////////////////////////////////////////////////////////////
    public List<Table> recuperer()  {
        List<Table> tables = new ArrayList<>();
        String req = "SELECT * FROM `table`";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);//recupere des lignes  de la base donner
            while (rs.next()) {
                Table table = new Table();
                table.setID_table(rs.getInt("id_table"));
                table.setType_table(rs.getString("Type_table"));
                table.setEmplacement(rs.getString("Emplacement"));
                table.setEtat_table(rs.getString("Etat_table"));
                table.setDescription(rs.getString("Description"));
                table.setID_restaurant(rs.getInt("ID_restaurant"));
                table.setisReserver(rs.getBoolean("isReserver"));
                tables.add(table);
            }
            return tables;
        }catch ( SQLException exp){
            System.out.println(exp);
        }
        return  null;
    }
    public List<Table> recuperer(String search)  {

        List<Table> tables = new ArrayList<>();
        String req = "SELECT * FROM `table` where Type_table like ? or Emplacement like ? order by id_table ";
        try {
            PreparedStatement st = connection.prepareStatement(req);
            st.setString(1,"%"+search+"%");
            st.setString(2,"%"+search+"%");

            ResultSet rs = st.executeQuery();//recupere des lignes  de la base donner
            while (rs.next()) {
                Table table = new Table();
                table.setID_table(rs.getInt("id_table"));
                table.setType_table(rs.getString("Type_table"));
                table.setEmplacement(rs.getString("Emplacement"));
                table.setEtat_table(rs.getString("Etat_table"));
                table.setDescription(rs.getString("Description"));
                table.setID_restaurant(rs.getInt("ID_restaurant"));
                table.setisReserver(rs.getBoolean("isReserver"));
                tables.add(table);
            }
            return tables;
        }catch ( SQLException exp){
            System.out.println(exp);
        }
        return  null;
    }

    ///READ BY ID////////////////////////////////////////////////////////////////////////

    public Table ReadById(int ID_table) {
        Table table = null;

        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM `Table` WHERE ID_table = ?")) {
            statement.setInt(1, ID_table);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idTable = resultSet.getInt("ID_table");
                    String typetable = resultSet.getString("Type_table");
                    String emplacement = resultSet.getString("Emplacement");
                    String etatTable = resultSet.getString("Etat_table");
                    String Description = resultSet.getString("Description");
                    int restaurantId = resultSet.getInt("ID_restaurant");
                    boolean isReserved = resultSet.getBoolean("isReserver");
                    table = new Table(idTable, typetable, emplacement, etatTable,Description, restaurantId, isReserved);
                }
            }
        } catch (SQLException e) {
            // Handle exceptions appropriately, e.g., log them
            e.printStackTrace();
            System.out.println(e);
        }

        return table;
    }

}



