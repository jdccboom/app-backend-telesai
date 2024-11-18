package com.test.proyectotelesai.infrastructure.driven_adapters.repository.servicio;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Builder
@Table(name = "servicio", schema = "telesai_services_db")
@NoArgsConstructor
@AllArgsConstructor
public class ServicioData {

    @Id
    @Column( value = "idservicio")
    private Integer idServicio;

    @Column( value = "nombreservicio")
    private String nombreServicio;

    @Column( value = "idestado")
    private Integer idEstado;
}
