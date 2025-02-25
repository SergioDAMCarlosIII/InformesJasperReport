package org.example.informes;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ReportGenerator {

    public static void generateCustomerReport() {
        // Obtenemos la conexion ya directamente desde el try mismo, asi es mas eficiente
        try (Connection conn = DatabaseConnector.getConnection()) {

            String jasperFilePath = "/org/example/informes/informes/customer_report.jrxml";

            InputStream reportStream = ReportGenerator.class.getResourceAsStream(jasperFilePath);

            if (reportStream == null) {
                System.err.println("Error: No se encontró el archivo customer_report.jrxml");
                return;
            }

            // Compilar el informe JRXML a un archivo Jasper
            System.out.println("Compilando : " + jasperFilePath);
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Al informe compilado le cargamos los parametros y la conexión a la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), conn);

            // Mostramos el informe (el valor true al cerrar el informe se cierra la aplicación.
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateArtistReport(int artistId) {
        System.out.println("Generando informe para el artista con ID: " + artistId);
        // Obtenemos la conexion ya directamente desde el try mismo, asi es mas eficiente
        try (Connection conn = DatabaseConnector.getConnection()) {

            String jasperFilePath = "/org/example/informes/informes/artist_report_4.jrxml";

            InputStream reportStream = ReportGenerator.class.getResourceAsStream(jasperFilePath);

            if (reportStream == null) {
                System.err.println("Error: No se encontró el archivo artist_report_4.jrxml");
                return;
            }

            // Compilamos el informe JRXML a un archivo Jasper
            System.out.println("Compilando : " + jasperFilePath);
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);


            // Generamos los parametros
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("ARTISTA_ID", String.valueOf(artistId));

            // Al informe compilado le cargamos los parametros y la conexión a la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
            System.out.println("Informe generado correctamente.");


            // Mostramos el informe (el valor true al cerrar el informe se cierra la aplicación.
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

