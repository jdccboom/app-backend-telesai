package com.test.proyectotelesai.infrastructure.helpers.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Constante {

    ERROR_EMAIL_NO_EXISTE("El correo no existe o no se encuentra registrado"),

    //SERVER
    SERVER_ERROR("Error interno del servidor"),
    NOT_FOUND("No se encontraron registros."),
    ERROR_DB_CONNECTION("Error de conexión con BD."),
    BAD_REQUEST("Error en la petición."),
    SERVICE_UNAVAILABLE("Servicio temporalmente no disponible"),

    // QUERIES
    PROCESS_ERROR("Error de ejecución durante el proceso."),
    SUCCESS_QUERY("Finalizó correctamente la consulta."),
    ERROR_QUERY("No fue posible realizar la consulta."),

    //UPDATES
    SUCCESS_UPDATE("Registro modificado exitosamente."),
    SUCCESS_PUT("Registro creado exitosamente."),
    NOT_UPDATE("No se actualizaron registros."),
    ERROR_UPDATE("No fue posible actualizar el registro.");

    private String texto;
}
