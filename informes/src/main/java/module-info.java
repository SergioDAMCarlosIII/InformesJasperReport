module org.example.informes {
    requires javafx.controls;
    requires javafx.fxml;
    requires jasperreports;
    requires org.xerial.sqlitejdbc;
    requires java.sql;
    requires log4j;


    opens org.example.informes to javafx.fxml;
    exports org.example.informes;
}