package com.test.proyectotelesai.infrastructure.driven_adapters.repository.estado;

import com.test.proyectotelesai.domain.model.estado.EstadoDTO;
import com.test.proyectotelesai.domain.model.estado.gateway.EstadoGateway;
import com.test.proyectotelesai.infrastructure.helpers.utils.AdapterOperationsReactive;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class EstadoRepositoryAdapter
        extends AdapterOperationsReactive<EstadoDTO, EstadoData, Integer, EstadoRepository>
        implements EstadoGateway {

    @Autowired
    protected EstadoRepositoryAdapter(EstadoRepository repository, ObjectMapper mapper) {
        super(repository,
                mapper,
                d -> mapper.mapBuilder(d, EstadoDTO.EstadoDTOBuilder.class).build());
    }

    @Override
    public Mono<EstadoDTO> getEstadoById(Integer id) {
        return repository.findByIdEstado(id)
                .map(estadoData -> mapper.map(estadoData, EstadoDTO.class));
    }
}
