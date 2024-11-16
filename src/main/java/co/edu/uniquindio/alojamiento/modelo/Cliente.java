package co.edu.uniquindio.alojamiento.modelo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cliente {
    private String cedula;
    private String nombreCompleto;
    private String telefono;
    private String email;
    private String contrasena;
    private boolean cuentaActivada;
    private float saldoBilletera;
    private Billetera billetera;

    public void setCuentaActiva(boolean b) {

    }
}
