package com.test.proyectotelesai.domain.model.observacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObservacionDTO {

    private Integer idObservacion;

    private Integer idSolicitud;

    private String descripcion;

    private LocalDateTime fechaObservacion;
}
