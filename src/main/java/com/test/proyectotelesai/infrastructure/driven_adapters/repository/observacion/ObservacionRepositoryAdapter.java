package com.test.proyectotelesai.infrastructure.driven_adapters.repository.observacion;

import com.test.proyectotelesai.domain.model.observacion.ObservacionDTO;
import com.test.proyectotelesai.domain.model.observacion.gateway.ObservacionGateway;
import com.test.proyectotelesai.infrastructure.helpers.utils.AdapterOperationsReactive;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ObservacionRepositoryAdapter
        extends AdapterOperationsReactive<ObservacionDTO, ObservacionData, Integer, ObservacionRepository>
        implements ObservacionGateway {

    @Autowired
    protected ObservacionRepositoryAdapter(ObservacionRepository repository, ObjectMapper mapper) {
        super(repository,
                mapper,
                d -> mapper.mapBuilder(d, ObservacionDTO.ObservacionDTOBuilder.class).build());
    }

    @Override
    public Flux<ObservacionDTO> getAllObservaciones() {
        return repository.findAllObservacion()
                .map(observacionData -> mapper.map(observacionData, ObservacionDTO.class));
    }

    @Override
    public Flux<ObservacionDTO> getObservacionByIdSolictud(Integer id,Integer idsolicitud) {
        return repository.getObservacionByFilter(id,idsolicitud)
                .map(observacionData -> mapper.map(observacionData, ObservacionDTO.class));
    }

    @Override
    public Mono<ObservacionDTO> saveObservacion(ObservacionDTO observacion) {
        return save(observacion);
    }
}
