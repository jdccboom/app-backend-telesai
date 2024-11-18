package com.test.proyectotelesai.domain.model.solicitud.response;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudResult {

    @Column( value = "idsolicitud")
    private Integer idSolicitud;

    @Column( value = "idCliente")
    private Integer idCliente;

    @Column( value = "Cliente")
    private String clienteNombre;

    @Column( value = "Ubicacion")
    private String ubicacion;

    @Column( value = "Servicio")
    private String servicio;

    @Column( value = "Operario")
    private String operario;

    @Column( value = "Supervisor")
    private String supervisor;

    @Column( value = "Fecha_Solicitud")
    private LocalDate fechaSolicitud;

    @Column( value = "Descripcion")
    private String descripcion;

    @Column( value = "Estado")
    private String estado;

}
