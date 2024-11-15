package com.test.proyectotelesai.domain.model.solicitud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDTO {
    private int idSolicitud;
    private int idCliente;
    private int idUbicacion;
    private int idServicio;
    private int idOperario;
    private int idSupervisor;
    private int idEstado;
    private LocalDate fechaSolicitud;
    private LocalDateTime fechaRevision;
    private LocalDateTime fechaValidacion;
    private String descripcion;
}
