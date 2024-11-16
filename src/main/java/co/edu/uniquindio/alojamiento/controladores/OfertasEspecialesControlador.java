package co.edu.uniquindio.alojamiento.controladores;

import co.edu.uniquindio.alojamiento.controladores.ControladorPrincipal;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.time.LocalDate;

public class OfertasEspecialesControlador {

    @FXML
    private TextField nombreOferta;

    @FXML
    private TextField descuentoOferta;

    @FXML
    private DatePicker fechaInicioOferta;

    @FXML
    private DatePicker fechaFinOferta;

    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();

    @FXML
    public void guardarOferta(ActionEvent event) {
        try {
            // Obtener los valores de los campos
            String idAlojamiento = nombreOferta.getText();
            float descuento = Float.parseFloat(descuentoOferta.getText());
            LocalDate fechaInicio = fechaInicioOferta.getValue();
            LocalDate fechaFin = fechaFinOferta.getValue();

            // Validar los campos de entrada
            if (idAlojamiento.isEmpty() || descuentoOferta.getText().isEmpty() || fechaInicio == null || fechaFin == null) {
                mostrarAlerta("Por favor completa todos los campos.", "Error", Alert.AlertType.ERROR);
                return;
            }

            // Llamar al método crearOferta de ControladorPrincipal para agregar la oferta
            controladorPrincipal.crearOferta(idAlojamiento, descuento, fechaInicio, fechaFin);

            // Mostrar mensaje de éxito
            mostrarAlerta("La oferta se ha guardado correctamente.", "Éxito", Alert.AlertType.INFORMATION);

            // Limpiar los campos después de guardar
            limpiarCampos();

        } catch (Exception e) {
            mostrarAlerta("No se pudo guardar la oferta.", "Error", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        // Limpiar los campos de texto
        nombreOferta.clear();
        descuentoOferta.clear();

        // Limpiar los campos de fecha
        fechaInicioOferta.setValue(null);
        fechaFinOferta.setValue(null);
    }

    // Método general para mostrar una alerta
    private void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);  // Puedes agregar un encabezado si lo necesitas
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para mostrar una alerta de error
    private void mostrarAlertaError(String mensaje, String titulo) {
        mostrarAlerta(mensaje, titulo, Alert.AlertType.ERROR);
    }

    // Método para mostrar una alerta de éxito
    private void mostrarAlertaExito(String mensaje, String titulo) {
        mostrarAlerta(mensaje, titulo, Alert.AlertType.INFORMATION);
    }

    @FXML
    public void eliminarOferta(ActionEvent event) {
        try {
            String idOferta = nombreOferta.getText();
            if (idOferta.isEmpty()) {
                controladorPrincipal.mostrarAlerta("Por favor ingresa el ID de la oferta a eliminar.", "Error", Alert.AlertType.ERROR);
                return;
            }

            controladorPrincipal.eliminarOferta(idOferta);
            controladorPrincipal.mostrarAlerta("La oferta se ha eliminado correctamente.", "Éxito", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("No se pudo eliminar la oferta.", "Error", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}


