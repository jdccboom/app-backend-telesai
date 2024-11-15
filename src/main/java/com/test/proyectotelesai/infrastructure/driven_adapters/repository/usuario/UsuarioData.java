package com.test.proyectotelesai.infrastructure.driven_adapters.repository.usuario;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table(name = "usuario", schema = "telesai_services_db")
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioData {

    @Id
    @Column( value = "idusuario")
    private Integer idusuario;

    @Column( value = "password")
    private String password;

    @Column( value = "nombrecompleto")
    private String nombrecompleto;

    @Column( value = "cedula")
    private String cedula;

    @Column( value = "telefono")
    private String telefono;

    @Column( value = "email")
    private String email;

    @Column( value = "username")
    private String username;

    @Column( value = "idestado")
    private String idestado;

}
