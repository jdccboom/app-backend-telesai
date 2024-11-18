package com.test.proyectotelesai.infrastructure.driven_adapters.repository.servicio;

import com.test.proyectotelesai.domain.model.servicio.ServicioDTO;
import com.test.proyectotelesai.domain.model.servicio.gateway.ServicioGateway;
import com.test.proyectotelesai.infrastructure.helpers.utils.AdapterOperationsReactive;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ServicioRepositoryAdapter
        extends AdapterOperationsReactive<ServicioDTO, ServicioData, Integer, ServicioRepository>
        implements ServicioGateway{

    @Autowired
    protected ServicioRepositoryAdapter(ServicioRepository repository, ObjectMapper mapper) {
        super(repository,
                mapper,
                d -> mapper.mapBuilder(d, ServicioDTO.ServicioDTOBuilder.class).build());
    }

    @Override
    public Mono<ServicioDTO> getServicio(Integer id) {
        return repository.getServicioByFilter(id,null)
                .map(servicioData -> mapper.map(servicioData, ServicioDTO.class));
    }

    @Override
    public Flux<ServicioDTO> getServicioAll() {
        return repository.findAllServicios()
                .map(servicioData -> mapper.map(servicioData, ServicioDTO.class));
    }

    @Override
    public Mono<ServicioDTO> saveServicio(ServicioDTO servicio) {
        return save(servicio);
    }

}
