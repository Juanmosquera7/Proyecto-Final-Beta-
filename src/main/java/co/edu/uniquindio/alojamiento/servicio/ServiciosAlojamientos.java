package co.edu.uniquindio.alojamiento.servicio;

import co.edu.uniquindio.alojamiento.modelo.*;
import co.edu.uniquindio.alojamiento.modelo.factory.TipoAlojamientos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ServiciosAlojamientos {

    // Métodos para autenticación y gestión de usuarios
    Cliente loginCliente(String email, String contrasena) throws Exception;
    void registrarCliente(String cedula, String nombreCompleto, String telefono, String email, String contrasena) throws Exception;
    void activarCuentaCliente(String email, String codigoActivacion) throws Exception;
    void actualizarCliente(String cedula, String nombreCompleto, String telefono, String email, String contrasena) throws Exception;
    void eliminarCuentaCliente(String cedula) throws Exception;
    void solicitarCambioContrasena(String email) throws Exception;
    void cambiarContrasena(String email, String codigo, String nuevaContrasena) throws Exception;
    List<Alojamiento> buscarAlojamientoPorCiudadTipoYPrecio(String ciudad, TipoAlojamientos tipo, float precioMin, float precioMax);

     List<String> obtenerCiudadesUnicas() throws Exception;

     List<TipoAlojamientos> obtenerTiposUnicos();


    // Métodos para la gestión de alojamientos por el administrador
    void crearAlojamiento(String nombre, String ciudad, String descripcion, String imagenUrl, float precioPorNoche,
                          int capacidadMaxima, List<String> servicios, TipoAlojamientos tipo, Oferta oferta) throws Exception;
    void modificarAlojamiento(String idAlojamiento, String nombre, String ciudad, String descripcion, String imagenUrl,
                              float precioPorNoche, int capacidadMaxima, List<String> servicios) throws Exception;


    void eliminarAlojamiento(String idAlojamiento) throws Exception;

    List<Alojamiento> buscarAlojamiento(String nombre, String tipo, String ciudad, float precioMin, float precioMax);
    // Métodos para la gestión de ofertas en los alojamientos
    void crearOferta(String idAlojamiento, float descuento, LocalDate fechaInicio, LocalDate fechaFin) throws Exception;
    void modificarOferta(String idOferta, float descuento, LocalDate fechaInicio, LocalDate fechaFin) throws Exception;
    void eliminarOferta(String idOferta) throws Exception;
    List<Oferta> buscarOferta(String idAlojamiento);

    // Métodos de estadísticas y reportes
    List<Alojamiento> obtenerAlojamientosPopulares(String ciudad);
    List<TipoAlojamiento> obtenerTiposAlojamientoRentables();
    float obtenerOcupacionAlojamiento(String idAlojamiento);
    float obtenerGananciasAlojamiento(String idAlojamiento);

    // Métodos para la gestión de reservas por clientes
    Reserva crearReserva(String idAlojamiento, String cedulaCliente, LocalDate fechaInicio, LocalDate fechaFin,
                         int numHuespedes) throws Exception;
    void cancelarReserva(UUID idReserva) throws Exception;
    List<Reserva> listarReservasPorCliente(String cedulaCliente);
    List<Reserva> listarReservasPorAlojamiento(String idAlojamiento);

    // Métodos para la gestión de la billetera del cliente
    void recargarBilletera(String cedulaCliente, float monto) throws Exception;
    float consultarSaldoBilletera(String cedulaCliente);

    // Métodos para la gestión de reseñas y valoraciones de alojamientos
    void agregarResena(String idAlojamiento, String cedulaCliente, String comentario, int valoracion) throws Exception;
    List<Resena> listarResenasPorAlojamiento(String idAlojamiento);

    // Métodos para la generación y envío de códigos QR y facturas
    Factura generarFactura(UUID idReserva) throws Exception;
    String generarCodigoQR(Factura factura) throws Exception;
    void enviarCorreoConQR(String emailCliente, String codigoQR, String detallesReserva) throws Exception;
}

