package com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud;

import com.test.proyectotelesai.domain.model.solicitud.SolicitudDTO;
import com.test.proyectotelesai.domain.model.solicitud.gateway.SolicitudGateway;
import com.test.proyectotelesai.infrastructure.helpers.utils.AdapterOperationsReactive;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class SolicitudRepositoryAdapter
        extends AdapterOperationsReactive<SolicitudDTO, SolicitudData, Integer, SolicitudRepository>
        implements SolicitudGateway {

    @Autowired
    protected SolicitudRepositoryAdapter(SolicitudRepository repository, ObjectMapper mapper) {
        super(repository,
                mapper,
                d -> mapper.mapBuilder(d, SolicitudDTO.SolicitudDTOBuilder.class).build());
    }

    @Override
    public Mono<Boolean> existSolicitud(Integer id) {
        return repository.existsById(id);
    }


    @Override
    public Mono<SolicitudDTO> getSolicitudById(Integer id) {
        return repository.getSolicitudByFilter(id,0)
                .map(solicitudData -> mapper.map(solicitudData, SolicitudDTO.class));
    }

    @Override
    public Flux<SolicitudDTO> getAllSolicituds() {
        return repository.findAllSolicitud()
                .map(solicitudData -> mapper.map(solicitudData, SolicitudDTO.class));
    }

    @Override
    public Mono<SolicitudDTO> saveSolicitud(SolicitudDTO dto) {
        return save(dto);
    }

    @Override
    public Mono<SolicitudResult> getSolicitudInfoById(Integer id) {
        return repository.getSolicitudInfoById(id);
    }

    @Override
    public Mono<InfoActaData> getInfoActaData(Integer id) {
        return repository.getInfoActaById(id);
    }

    @Override
    public Flux<SolicitudResult> getSolicitudInfoByIdCliente(Integer id) {
        return repository.getSolicitudInfoByIdCliente(id);
    }
}
