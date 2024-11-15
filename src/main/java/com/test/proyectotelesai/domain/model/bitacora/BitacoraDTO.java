package com.test.proyectotelesai.domain.model.bitacora;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BitacoraDTO {

    private int idBitacora;
    private int idSolicitud;
    private int idPersona;
    private LocalDateTime fechaAccion;
    private String accion;
    private String detalle;

}
