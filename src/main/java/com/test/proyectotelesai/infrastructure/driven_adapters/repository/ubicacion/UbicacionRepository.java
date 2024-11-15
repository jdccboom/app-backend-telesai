package com.test.proyectotelesai.infrastructure.driven_adapters.repository.ubicacion;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UbicacionRepository extends ReactiveCrudRepository<UbicacionData, Integer>, ReactiveQueryByExampleExecutor<UbicacionData> {

    Flux<UbicacionData> findAllByOrderByNombreUbicacionAsc();
}
