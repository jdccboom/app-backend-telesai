package com.test.proyectotelesai.infrastructure.driven_adapters.repository.evidencia;

import com.test.proyectotelesai.domain.model.evidencia.EvidenciaDTO;
import com.test.proyectotelesai.domain.model.evidencia.gateway.EvidenciaGateway;
import com.test.proyectotelesai.infrastructure.helpers.utils.AdapterOperationsReactive;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class EvidenciaRepositoryAdapter
        extends AdapterOperationsReactive<EvidenciaDTO, EvidenciaData, Integer, EvidenciaRepository>
        implements EvidenciaGateway {

    @Autowired
    protected EvidenciaRepositoryAdapter(EvidenciaRepository repository, ObjectMapper mapper) {
        super(repository,
                mapper,
                d -> mapper.mapBuilder(d, EvidenciaDTO.EvidenciaDTOBuilder.class).build());
    }

    @Override
    public Mono<EvidenciaDTO> getEvidenciaById(Integer id) {
        return repository.findByIdEvidencia(id)
                .map(evidenciaData -> mapper.map(evidenciaData, EvidenciaDTO.class));
    }

    @Override
    public Mono<EvidenciaDTO> getEvidenciaByTipo(String tipo,Integer id) {
        return repository.getEvidenciaByFilter(tipo,id)
                .map(evidenciaData -> mapper.map(evidenciaData, EvidenciaDTO.class));
    }

    @Override
    public Mono<EvidenciaDTO> saveEvidencia(EvidenciaDTO evidenciaDTO) {
        return save(evidenciaDTO);
    }
}
