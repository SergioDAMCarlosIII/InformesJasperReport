package org.example.informes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainInforme extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainInforme.class.getResource("MainInforme-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 440);
        stage.setTitle("Generador de informes");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        //BasicConfigurator.configure();
        launch();
    }
}