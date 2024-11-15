package com.test.proyectotelesai.domain.model.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Integer idusuario;

    private String password;

    private String nombrecompleto;

    private String cedula;

    private String telefono;

    private String email;

    private String username;

    private String idestado;
}
