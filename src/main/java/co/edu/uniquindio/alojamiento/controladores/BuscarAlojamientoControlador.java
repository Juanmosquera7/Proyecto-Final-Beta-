package co.edu.uniquindio.alojamiento.controladores;

import co.edu.uniquindio.alojamiento.modelo.Alojamiento;
import co.edu.uniquindio.alojamiento.modelo.factory.TipoAlojamientos;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BuscarAlojamientoControlador implements Initializable {

    @FXML
    private ComboBox<TipoAlojamientos> tipoAlojamiento;

    @FXML
    private ComboBox<String> ciudadAlojamiento;

    @FXML
    private TextField precioMinimo;

    @FXML
    private TextField precioMaximo;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private TableView<Alojamiento> tablaAlojamientos;

    @FXML
    private TableColumn<Alojamiento, String> columnaNombre;

    @FXML
    private TableColumn<Alojamiento, TipoAlojamientos> columnaTipo;

    @FXML
    private TableColumn<Alojamiento, String> columnaCiudad;

    @FXML
    private TableColumn<Alojamiento, Float> columnaPrecio;

    private ControladorPrincipal controladorPrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar columnas de la tabla
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnaCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precioPorNoche"));

        // Cargar ciudades y tipos de alojamiento en los ComboBox
        cargarCiudades();
        cargarTiposAlojamientos();

        // Configurar la habilitación del botón de búsqueda
        configurarHabilitacionBotonBuscar();

        cargarAlojamientos();

    }

    private void configurarHabilitacionBotonBuscar() {
        btnBuscar.setDisable(true);

        ChangeListener<Object> listener = (observable, oldValue, newValue) -> {
            btnBuscar.setDisable(tipoAlojamiento.getValue() == null &&
                    ciudadAlojamiento.getValue() == null &&
                    precioMinimo.getText().isEmpty() &&
                    precioMaximo.getText().isEmpty());
        };

        tipoAlojamiento.valueProperty().addListener(listener);
        ciudadAlojamiento.valueProperty().addListener(listener);
        precioMinimo.textProperty().addListener(listener);
        precioMaximo.textProperty().addListener(listener);
    }

    @FXML
    public void buscarAlojamientos(ActionEvent event) {
        try {
            // Obtener los valores seleccionados por el usuario
            TipoAlojamientos tipoSeleccionado = tipoAlojamiento.getValue();
            String ciudadSeleccionada = ciudadAlojamiento.getValue();
            Float precioMinimoValor = precioMinimo.getText().isEmpty() ? 0f : Float.parseFloat(precioMinimo.getText());
            Float precioMaximoValor = precioMaximo.getText().isEmpty() ? Float.MAX_VALUE : Float.parseFloat(precioMaximo.getText());

            // Realizar la búsqueda utilizando los filtros seleccionados
            List<Alojamiento> alojamientosEncontrados = ControladorPrincipal.getInstancia().buscarAlojamiento(
                    null,
                    tipoSeleccionado != null ? tipoSeleccionado.toString() : null,
                    ciudadSeleccionada,
                    precioMinimoValor,
                    precioMaximoValor
            );

            // Verificar si se encontraron alojamientos
            if (alojamientosEncontrados.isEmpty()) {
                mostrarAlerta("No se encontraron alojamientos que coincidan con los filtros.", Alert.AlertType.INFORMATION);
            } else {
                // Actualizar la tabla con los alojamientos encontrados
                tablaAlojamientos.getItems().setAll(alojamientosEncontrados);
                mostrarAlerta("Se han encontrado " + alojamientosEncontrados.size() + " alojamientos.", Alert.AlertType.INFORMATION);
            }

        } catch (Exception e) {
            mostrarAlerta("Ocurrió un error al realizar la búsqueda: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    @FXML
    public void limpiarFiltros() {
        tipoAlojamiento.setValue(null);
        ciudadAlojamiento.setValue(null);
        precioMinimo.clear();
        precioMaximo.clear();
        tablaAlojamientos.getItems().clear();
        btnBuscar.setDisable(true);
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // Método para cargar las ciudades de los alojamientos disponibles
    private void cargarCiudades() {
        try {
            List<String> ciudades = ControladorPrincipal.getInstancia().obtenerCiudadesUnicas();
            ciudadAlojamiento.setItems(FXCollections.observableArrayList(ciudades));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para cargar los tipos de alojamiento disponibles
    private void cargarTiposAlojamientos() {
        try {
            List<TipoAlojamientos> tipos = ControladorPrincipal.getInstancia().obtenerTiposUnicos();
            tipoAlojamiento.setItems(FXCollections.observableArrayList(tipos));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarAlojamientos() {
        try {
            // Obtener los alojamientos de la base de datos o fuente de datos
            List<Alojamiento> alojamientos = ControladorPrincipal.getInstancia().buscarAlojamiento(null, null, null, 0, Float.MAX_VALUE);

            // Actualizar la tabla con los alojamientos obtenidos
            tablaAlojamientos.setItems(FXCollections.observableArrayList(alojamientos));
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Ocurrió un error al cargar los alojamientos.", Alert.AlertType.ERROR);
        }
    }

}






