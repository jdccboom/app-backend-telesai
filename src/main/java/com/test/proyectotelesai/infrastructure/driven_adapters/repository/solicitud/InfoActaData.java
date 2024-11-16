package com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoActaData {

    @Column( value = "idsolicitud")
    private Integer idSolicitud;

    @Column( value = "Cliente")
    private String cliente;

    @Column( value = "email")
    private String email;

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
