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
    private Integer idCliente;

    @Column( value = "idubicacion")
    private Integer idUbicacion;

    @Column( value = "idservicio")
    private Integer idServicio;

    @Column( value = "idoperario")
    private Integer idOperario;

    @Column( value = "idsupervisor")
    private Integer idSupervisor;

    @Column( value = "idestado")
    private Integer idEstado;

    @Column( value = "fechasolicitud")
    private LocalDate fechaSolicitud;

    @Column( value = "fecharevision")
    private LocalDate fechaRevision;

    @Column( value = "fechavalidacion")
    private LocalDateTime fechaValidacion;

    @Column( value = "descripcion")
    private String descripcion;
}
