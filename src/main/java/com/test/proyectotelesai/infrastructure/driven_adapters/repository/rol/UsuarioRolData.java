package com.test.proyectotelesai.infrastructure.driven_adapters.repository.rol;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Builder
@Table(name = "usuariorol", schema = "telesai_services_db")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsuarioRolData {

    @Id
    @Column( value = "idrol")
    private Integer idRol;

    @Column( value = "idusuario")
    private String idUsuario;
}
