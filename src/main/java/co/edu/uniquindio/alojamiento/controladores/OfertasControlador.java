package co.edu.uniquindio.alojamiento.controladores;

import co.edu.uniquindio.alojamiento.modelo.Oferta;
import co.edu.uniquindio.alojamiento.servicio.ServiciosAlojamientos;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.List;

public class OfertasControlador implements Initializable {

    @FXML
    private TableView<Oferta> tablaOfertas;

    @FXML
    private TableColumn<Oferta, String> colAlojamiento;

    @FXML
    private TableColumn<Oferta, String> colFechaInicio;

    @FXML
    private TableColumn<Oferta, String> colFechaFin;

    @FXML
    private TableColumn<Oferta, String> colDescuento;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarColumnas();
        cargarOfertas();
    }

    private void configurarColumnas() {
        colAlojamiento.setCellValueFactory(celda -> new SimpleStringProperty(celda.getValue().getAlojamiento().getNombre()));
        colFechaInicio.setCellValueFactory(celda -> new SimpleStringProperty(celda.getValue().getFechaInicio().format(dateFormatter)));
        colFechaFin.setCellValueFactory(celda -> new SimpleStringProperty(celda.getValue().getFechaFin().format(dateFormatter)));
        colDescuento.setCellValueFactory(celda -> new SimpleStringProperty(String.valueOf(celda.getValue().getDescuento())));
    }

    private void cargarOfertas() {
        try {
            List<Oferta> ofertas = ControladorPrincipal.getInstancia().buscarOferta(null);
            tablaOfertas.setItems(FXCollections.observableArrayList(ofertas));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




