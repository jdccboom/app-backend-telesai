package com.test.proyectotelesai.infrastructure.driven_adapters.repository.bitacora;

import com.test.proyectotelesai.domain.model.bitacora.BitacoraDTO;
import com.test.proyectotelesai.domain.model.bitacora.gateway.BitacoraGateway;
import com.test.proyectotelesai.infrastructure.helpers.utils.AdapterOperationsReactive;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class BitacoraRepositoryAdapter
        extends AdapterOperationsReactive<BitacoraDTO, BitacoraData, Integer, BitacoraRepository>
        implements BitacoraGateway {

    @Autowired
    protected BitacoraRepositoryAdapter(BitacoraRepository repository, ObjectMapper mapper) {
        super(repository,
                mapper,
                d -> mapper.mapBuilder(d, BitacoraDTO.BitacoraDTOBuilder.class).build());
    }

    @Override
    public Mono<BitacoraDTO> getBitacoraByIdSolicitud(Integer id) {
        return repository.findByIdBitacora(id)
                .map(bitacoraData -> mapper.map(bitacoraData, BitacoraDTO.class));
    }

    @Override
    public Flux<BitacoraDTO> getAllBitacoras() {
        return repository.findAllBitacora()
                .map(bitacoraData -> mapper.map(bitacoraData, BitacoraDTO.class));
    }

    @Override
    public Mono<BitacoraDTO> saveBitacora(BitacoraDTO bitacora) {
        return save(bitacora);
    }


}
