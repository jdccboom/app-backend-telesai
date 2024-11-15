package com.test.proyectotelesai.infrastructure.driven_adapters.repository.estado;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Builder
@Table(name = "estado", schema = "telesai_services_db")
@NoArgsConstructor
@AllArgsConstructor
public class EstadoData {

    @Id
    @Column( value = "idestado")
    private int idEstado;

    @Column( value = "nombreestado")
    private String nombreEstado;

    @Column( value = "descripcion")
    private String descripcion;
}
