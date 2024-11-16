package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.evidencia.EvidenciaDTO;
import com.test.proyectotelesai.domain.model.evidencia.gateway.EvidenciaGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EvidenciaUseCase {

    private final EvidenciaGateway evidenciaGateway;

    public Mono<EvidenciaDTO> getEvidenciaByTipo(String tipo, Integer id) {
        return evidenciaGateway.getEvidenciaByTipo(tipo,id);
    }

    public Mono<EvidenciaDTO> save(EvidenciaDTO evidenciaDTO) {
        return evidenciaGateway.saveEvidencia(evidenciaDTO);
    }
}
