package com.test.proyectotelesai.infrastructure.driven_adapters.repository.rol;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Builder
@Table(name = "rol", schema = "telesai_services_db")
@NoArgsConstructor
@AllArgsConstructor
public class RolData {

    @Id
    @Column( value = "idrol")
    private Integer idRol;

    @Column( value = "nombrerol")
    private String nombreRol;
}
