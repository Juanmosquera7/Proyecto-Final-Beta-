package co.edu.uniquindio.alojamiento.controladores;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;

public class PanelClienteControlador {

    private final ControladorPrincipal controladorPrincipal;

    @FXML
    private Button btnBuscarAlojamiento;

    @FXML
    private Button btnMisReservas;

    @FXML
    private Button btnRecargarBilletera;

    @FXML
    private Button btnEditarPerfil;

    @FXML
    private Button btnCerrarSesion;

    public PanelClienteControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public StackPane contentArea;

    @FXML
    private void buscarAlojamiento(ActionEvent event) {
        Parent node = controladorPrincipal.cargarPanel("/buscarAlojamiento.fxml");
        contentArea.getChildren().setAll(node);
    }

    @FXML
    private void verMisReservas(ActionEvent event) {
        Parent node = controladorPrincipal.cargarPanel("/misReservas.fxml");
        contentArea.getChildren().setAll(node);
    }

    @FXML
    private void recargarBilletera(ActionEvent event) {
        Parent node = controladorPrincipal.cargarPanel("/recargarBilletera.fxml");
        contentArea.getChildren().setAll(node);
    }

    @FXML
    private void editarPerfil(ActionEvent event) {
        Parent node = controladorPrincipal.cargarPanel("/editarPerfil.fxml");
        contentArea.getChildren().setAll(node);
    }

    //@FXML
    //private void cerrarSesion(ActionEvent event) {
        //controladorPrincipal.cerrarSesion();
        //controladorPrincipal.navegarVentana("/vistas/login.fxml", "Iniciar Sesión");
    //}
}

