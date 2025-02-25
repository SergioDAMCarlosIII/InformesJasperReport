package org.example.informes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class MainInformeController implements Initializable {

    @FXML
    public ListView listViewArtistas;
    @FXML
    public Button btnCliente;
    @FXML
    public Button btnArtista;
    @FXML
    public Button btnCerrar;


    public void initialize(URL location, ResourceBundle resources) {
        cargarListaArtistas();
    }


    public void handlerInformeClientes(ActionEvent actionEvent) {
        ReportGenerator.generateCustomerReport();
    }

    public void handlerInformeArtistas(ActionEvent actionEvent) {
        String selectedArtist = listViewArtistas.getSelectionModel().getSelectedItem().toString();
        System.out.println("Artista seleccionado: " + selectedArtist);

        if(selectedArtist != null) {
            int artistId = obtenerIdArtista(selectedArtist);
            System.out.println("ID del artista: " + artistId);
            ReportGenerator.generateArtistReport(artistId);
        }
    }

    public void handlerCerrarAplicacion(ActionEvent actionEvent) {
        System.exit(0);
    }

    private void cargarListaArtistas() {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT ArtistId, Name FROM artists");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                listViewArtistas.getItems().add(rs.getString("Name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int obtenerIdArtista(String nombreArtista) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT ArtistId FROM artists WHERE Name = ?")) {
            stmt.setString(1, nombreArtista);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("ArtistId");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}