module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires jbcrypt;
    requires com.sothawo.mapjfx;
    requires java.sql;
    requires itextpdf;
    requires java.datatransfer;
    requires java.mail;
    requires java.desktop;
    requires emoji.java;
    requires org.controlsfx.controls;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports test;
    opens test;
    exports Controllers.Restaurant;
    opens Controllers.Restaurant;
}