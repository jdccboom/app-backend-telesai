package com.test.proyectotelesai.infrastructure.driven_adapters.repository.ubicacion;

import com.test.proyectotelesai.domain.model.ubicacion.UbicacionDTO;
import com.test.proyectotelesai.domain.model.ubicacion.gateway.UbicacionGateway;
import com.test.proyectotelesai.infrastructure.helpers.utils.AdapterOperationsReactive;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class UbicacionRepositoryAdapter extends
        AdapterOperationsReactive<UbicacionDTO, UbicacionData, Integer, UbicacionRepository>
        implements UbicacionGateway {

    protected UbicacionRepositoryAdapter(UbicacionRepository repository, ObjectMapper mapper) {
        super(repository, mapper,
                d -> mapper.mapBuilder(d, UbicacionDTO.UbicacionDTOBuilder.class).build());
    }

    @Override
    public Flux<UbicacionDTO> getAllUbicacion() {
        return repository.findAllByOrderByNombreUbicacionAsc()
                .map(ubicacionData ->mapper.map(ubicacionData, UbicacionDTO.class));
    }
}
