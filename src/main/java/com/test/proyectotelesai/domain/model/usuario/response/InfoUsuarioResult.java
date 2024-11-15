package com.test.proyectotelesai.domain.model.usuario.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoUsuarioResult {

    private Integer idusuario;

    private String nombrecompleto;

    private String cedula;

    private String telefono;

    private String email;

    private String token;
}
