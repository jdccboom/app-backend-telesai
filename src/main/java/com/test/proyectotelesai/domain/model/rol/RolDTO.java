package com.test.proyectotelesai.domain.model.rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolDTO {

    private Integer idRol;

    private String nombreRol;
}
