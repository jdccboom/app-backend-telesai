package com.test.proyectotelesai.domain.model.estado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDTO {

    private int idEstado;
    private String nombreEstado;
    private String descripcion;
}
