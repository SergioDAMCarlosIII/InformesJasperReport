package org.example.informes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:sqlite:db/chinook.db"; // Ruta a la base de datos

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}