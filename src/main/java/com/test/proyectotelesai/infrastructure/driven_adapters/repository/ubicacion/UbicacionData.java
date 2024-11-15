package com.test.proyectotelesai.infrastructure.driven_adapters.repository.ubicacion;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table(name = "ubicacion", schema = "telesai_services_db")
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionData {

    @Id
    @Column( value = "idubicacion")
    private Integer idUbicacion;

    @Column( value = "nombreubicacion")
    private String nombreUbicacion;

    @Column( value = "direccion")
    private String direccion;

    @Column( value = "idcliente")
    private String idCliente;
}
