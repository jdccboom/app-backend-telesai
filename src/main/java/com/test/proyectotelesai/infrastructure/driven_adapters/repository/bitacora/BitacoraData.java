package com.test.proyectotelesai.infrastructure.driven_adapters.repository.bitacora;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@Table(name = "bitacora", schema = "telesai_services_db")
@NoArgsConstructor
@AllArgsConstructor
public class BitacoraData {

    @Id
    @Column( value = "idbitacora")
    private int idBitacora;

    @Column( value = "idsolicitud")
    private int idSolicitud;

    @Column( value = "idpersana")
    private int idPersona;

    @Column( value = "fechaaccion")
    private LocalDateTime fechaAccion;

    @Column( value = "accion")
    private String accion;

    @Column( value = "detalle")
    private String detalle;
}
