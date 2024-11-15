package com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@Table(name = "solicitud", schema = "telesai_services_db")
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudData {

    @Id
    @Column( value = "idsolicitud")
    private Integer idSolicitud;

    @Column( value = "idcliente")
    private int idCliente;

    @Column( value = "idubicacion")
    private int idUbicacion;

    @Column( value = "idservicio")
    private int idServicio;

    @Column( value = "idoperario")
    private int idOperario;

    @Column( value = "idsupervisor")
    private int idSupervisor;

    @Column( value = "idestado")
    private int idEstado;

    @Column( value = "fechasolicitud")
    private LocalDate fechaSolicitud;

    @Column( value = "fecharevision")
    private LocalDateTime fechaRevision;

    @Column( value = "fechavalidacion")
    private LocalDateTime fechaValidacion;

    @Column( value = "descripcion")
    private String descripcion;
}
