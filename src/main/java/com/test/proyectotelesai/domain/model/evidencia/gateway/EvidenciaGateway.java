package com.test.proyectotelesai.domain.model.evidencia.gateway;

import com.test.proyectotelesai.domain.model.evidencia.EvidenciaDTO;
import reactor.core.publisher.Mono;

public interface EvidenciaGateway {
    Mono<EvidenciaDTO> getEvidenciaById(Integer id);
    Mono<EvidenciaDTO> saveEvidencia(EvidenciaDTO evidenciaDTO);
}
