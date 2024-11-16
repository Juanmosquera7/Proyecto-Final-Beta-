package co.edu.uniquindio.alojamiento.controladores;

import co.edu.uniquindio.alojamiento.modelo.*;
import co.edu.uniquindio.alojamiento.modelo.factory.TipoAlojamientos;
import co.edu.uniquindio.alojamiento.servicio.ServiciosAlojamientos;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ControladorPrincipal implements ServiciosAlojamientos {

    private static ControladorPrincipal INSTANCIA;
    private final ServiciosAlojamientos serviciosAlojamientos;


    private ControladorPrincipal() {
        serviciosAlojamientos = new AlojamientoPrincipal(); // Interactuamos con la clase AlojamientoPrincipal
    }

    public static ControladorPrincipal getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }

    



    @Override
    public Cliente loginCliente(String email, String contrasena) throws Exception {
        return serviciosAlojamientos.loginCliente(email, contrasena);
    }

    @Override
    public void registrarCliente(String cedula, String nombreCompleto, String telefono, String email, String contrasena) throws Exception {
        serviciosAlojamientos.registrarCliente(cedula, nombreCompleto, telefono, email, contrasena);
    }



    @Override
    public void activarCuentaCliente(String email, String codigoActivacion) throws Exception {
        serviciosAlojamientos.activarCuentaCliente(email, codigoActivacion);
    }

    @Override
    public void actualizarCliente(String cedula, String nombreCompleto, String telefono, String email, String contrasena) throws Exception {
        serviciosAlojamientos.actualizarCliente(cedula, nombreCompleto, telefono, email, contrasena);
    }

    @Override
    public void eliminarCuentaCliente(String cedula) throws Exception {
        serviciosAlojamientos.eliminarCuentaCliente(cedula);
    }

    @Override
    public void solicitarCambioContrasena(String email) throws Exception {
        serviciosAlojamientos.solicitarCambioContrasena(email);
    }

    @Override
    public void cambiarContrasena(String email, String codigo, String nuevaContrasena) throws Exception {
        serviciosAlojamientos.cambiarContrasena(email, codigo, nuevaContrasena);
    }

    @Override
    public List<Alojamiento> buscarAlojamientoPorCiudadTipoYPrecio(String ciudad, TipoAlojamientos tipo, float precioMin, float precioMax) {
        return serviciosAlojamientos.buscarAlojamientoPorCiudadTipoYPrecio(ciudad, tipo, precioMin, precioMax);
    }

    @Override
    public List<String> obtenerCiudadesUnicas() throws Exception {
        // Obtener las ciudades únicas de la lista de alojamientos
        return serviciosAlojamientos.obtenerCiudadesUnicas();
    }

    @Override
    public List<TipoAlojamientos> obtenerTiposUnicos() {
        // Obtener los tipos únicos de la lista de alojamientos
        return serviciosAlojamientos.obtenerTiposUnicos();
    }

    @Override
    public void crearAlojamiento(String nombre, String ciudad, String descripcion, String imagenUrl, float precioPorNoche, int capacidadMaxima, List<String> servicios, TipoAlojamientos tipo, Oferta oferta) throws Exception {
        serviciosAlojamientos.crearAlojamiento(nombre, ciudad, descripcion, imagenUrl, precioPorNoche, capacidadMaxima, servicios, tipo, oferta);
    }



    @Override
    public void modificarAlojamiento(String idAlojamiento, String nombre, String ciudad, String descripcion, String imagenUrl, float precioPorNoche, int capacidadMaxima, List<String> servicios) throws Exception {
        serviciosAlojamientos.modificarAlojamiento(idAlojamiento, nombre, ciudad, descripcion, imagenUrl, precioPorNoche, capacidadMaxima, servicios);
    }

    @Override
    public void eliminarAlojamiento(String idAlojamiento) throws Exception {
        serviciosAlojamientos.eliminarAlojamiento(idAlojamiento);
    }

    @Override
    public List<Alojamiento> buscarAlojamiento(String nombre, String tipo, String ciudad, float precioMin, float precioMax) {
        return serviciosAlojamientos.buscarAlojamiento(nombre, tipo, ciudad, precioMin, precioMax);
    }

    @Override
    public void crearOferta(String idAlojamiento, float descuento, LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        serviciosAlojamientos.crearOferta(idAlojamiento, descuento, fechaInicio, fechaFin);
    }

    @Override
    public void modificarOferta(String idOferta, float descuento, LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        serviciosAlojamientos.modificarOferta(idOferta, descuento, fechaInicio, fechaFin);
    }

    @Override
    public void eliminarOferta(String idOferta) throws Exception {
        serviciosAlojamientos.eliminarOferta(idOferta);
    }

    @Override
    public List<Oferta> buscarOferta(String idAlojamiento) {
        return serviciosAlojamientos.buscarOferta(idAlojamiento);
    }

    @Override
    public List<Alojamiento> obtenerAlojamientosPopulares(String ciudad) {
        return serviciosAlojamientos.obtenerAlojamientosPopulares(ciudad);
    }

    @Override
    public List<TipoAlojamiento> obtenerTiposAlojamientoRentables() {
        return serviciosAlojamientos.obtenerTiposAlojamientoRentables();
    }

    @Override
    public float obtenerOcupacionAlojamiento(String idAlojamiento) {
        return serviciosAlojamientos.obtenerOcupacionAlojamiento(idAlojamiento);
    }

    @Override
    public float obtenerGananciasAlojamiento(String idAlojamiento) {
        return serviciosAlojamientos.obtenerGananciasAlojamiento(idAlojamiento);
    }

    @Override
    public Reserva crearReserva(String idAlojamiento, String cedulaCliente, LocalDate fechaInicio, LocalDate fechaFin, int numHuespedes) throws Exception {
        return serviciosAlojamientos.crearReserva(idAlojamiento, cedulaCliente, fechaInicio, fechaFin, numHuespedes);
    }

    @Override
    public void cancelarReserva(UUID idReserva) throws Exception {
        serviciosAlojamientos.cancelarReserva(idReserva);
    }

    @Override
    public List<Reserva> listarReservasPorCliente(String cedulaCliente) {
        return serviciosAlojamientos.listarReservasPorCliente(cedulaCliente);
    }

    @Override
    public List<Reserva> listarReservasPorAlojamiento(String idAlojamiento) {
        return serviciosAlojamientos.listarReservasPorAlojamiento(idAlojamiento);
    }

    @Override
    public void recargarBilletera(String cedulaCliente, float monto) throws Exception {
        serviciosAlojamientos.recargarBilletera(cedulaCliente, monto);
    }

    @Override
    public float consultarSaldoBilletera(String cedulaCliente) {
        return serviciosAlojamientos.consultarSaldoBilletera(cedulaCliente);
    }

    @Override
    public void agregarResena(String idAlojamiento, String cedulaCliente, String comentario, int valoracion) throws Exception {
        serviciosAlojamientos.agregarResena(idAlojamiento, cedulaCliente, comentario, valoracion);
    }

    @Override
    public List<Resena> listarResenasPorAlojamiento(String idAlojamiento) {
        return serviciosAlojamientos.listarResenasPorAlojamiento(idAlojamiento);
    }

    @Override
    public Factura generarFactura(UUID idReserva) throws Exception {
        return serviciosAlojamientos.generarFactura(idReserva);
    }

    @Override
    public String generarCodigoQR(Factura factura) throws Exception {
        return serviciosAlojamientos.generarCodigoQR(factura);
    }

    @Override
    public void enviarCorreoConQR(String emailCliente, String codigoQR, String detallesReserva) throws Exception {
        serviciosAlojamientos.enviarCorreoConQR(emailCliente, codigoQR, detallesReserva);
    }

    // Métodos para manejo de ventanas y alertas
    public void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrarVentana(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();
            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

