package models;

public class Table {
    private int ID_table,ID_restaurant;
    private String Emplacement,	Etat_table,Type_table,Description;
    private boolean isReserver;
    public Table(int ID_table, String type_table, String emplacement, String etat_table ,String Description  , int ID_restaurant,boolean isReserver) {
        this.ID_table = ID_table;
        this.Type_table = type_table;
        this.Emplacement = emplacement;
        this.Etat_table = etat_table;
        this.Description = Description;
        this.ID_restaurant = ID_restaurant;
        this.isReserver=isReserver;
    }
public Table()
{

}
    public Table(String type_table, String emplacement, String etat_table,String Description,int ID_restaurant) {
        this.Type_table = type_table;
        this.Emplacement = emplacement;
        this.Etat_table = etat_table;
        this.Description = Description;
        this.ID_restaurant = ID_restaurant;
        this.isReserver=false;

    }
//getters
    public int getID_table() {
        return ID_table;
    }
    public int getID_restaurant() {
        return ID_restaurant;
    }
    public String getEmplacement() {
        return Emplacement;
    }
    public String getEtat_table() {
        return Etat_table;
    }
    public String getType_table() {
        return Type_table;
    }
    public String getDescription() {
        return Description;
    }

    public boolean getisReserver() {
        return isReserver;
    }
    //satters
    public void setID_table(int ID_table) {
        this.ID_table = ID_table;
    }
    public void setID_restaurant(int ID_restaurant) {
        this.ID_restaurant = ID_restaurant;
    }
    public void setEmplacement(String emplacement) {
        Emplacement = emplacement;
    }
    public void setEtat_table(String etat_table) {
        Etat_table = etat_table;
    }
    public void setType_table(String type_table) {
        Type_table = type_table;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public void setisReserver(boolean reserver) {
        isReserver = reserver;
    }
    //tostring
    @Override
    public String toString() {
        return "Table{" +
                "ID_table=" + ID_table +
                ", ID_restaurant=" + ID_restaurant +
                ", Emplacement='" + Emplacement + '\'' +
                ", Etat_table='" + Etat_table + '\'' +
                ", Type_table='" + Type_table + '\'' +
                ", Description='" + Description + '\'' +
                ", isReserver=" + isReserver +
                '}';
    }


}

