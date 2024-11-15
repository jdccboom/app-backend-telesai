package com.test.proyectotelesai.infrastructure.driven_adapters.repository.observacion;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@Table(name = "observacion", schema = "telesai_services_db")
@NoArgsConstructor
@AllArgsConstructor
public class ObservacionData {

    @Id
    @Column( value = "idobservacion")
    private Integer idObservacion;

    @Column( value = "idsolicitud")
    private Integer idSolicitud;

    @Column( value = "descripcion")
    private String descripcion;

    @Column( value = "fechaobservacion")
    private LocalDateTime fechaObservacion;
}
