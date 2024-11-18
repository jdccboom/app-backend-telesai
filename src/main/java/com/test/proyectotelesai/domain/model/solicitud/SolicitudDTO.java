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
    private Integer idSolicitud;
    private Integer idCliente;
    private Integer idUbicacion;
    private Integer idServicio;
    private Integer idOperario;
    private Integer idSupervisor;
    private Integer idEstado;
    private LocalDate fechaSolicitud;
    private LocalDate fechaRevision;
    private LocalDateTime fechaValidacion;
    private String descripcion;
}
