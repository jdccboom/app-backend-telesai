package com.test.proyectotelesai.domain.model.ubicacion;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UbicacionDTO {

    private Integer idUbicacion;

    private String nombreUbicacion;

    private String direccion;

    private Integer idcliente;
}
