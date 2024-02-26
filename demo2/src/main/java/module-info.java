module tn.koolart.esprit.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens tn.koolart.esprit.demo2 to javafx.fxml;
    exports tn.koolart.esprit.demo2;

}