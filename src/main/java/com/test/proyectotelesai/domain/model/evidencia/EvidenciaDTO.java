package com.test.proyectotelesai.domain.model.evidencia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvidenciaDTO {

    private int idEvidencia;

    private int idSolicitud;

    private String urlEvidencia;
}
