package com.test.proyectotelesai.domain.model.servicio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicioDTO {

    private Integer idServicio;

    private String nombreServicio;

    private Integer idEstado;

}
