package com.test.proyectotelesai.infrastructure.driven_adapters.repository.observacion;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ObservacionRepository extends ReactiveCrudRepository<ObservacionData, Integer>, ReactiveQueryByExampleExecutor<ObservacionData> {


    Mono<ObservacionData> findByIdObservacion(@Param("id") Integer idObservacion);

    @Query("""
            SELECT *
            FROM telesai_services_db.observacion;
            """)
    Flux<ObservacionData> findAllObservacion();

    @Query("""
        SELECT *
        FROM telesai_services_db.observacion s
        WHERE (s.idsolicitud = :tipo OR '0' = :id)
        AND (s.idsolicitud = :idsolicitud :idsolicitud is null);
    """)
    Flux<ObservacionData> getObservacionByFilter(@Param("id") Integer idServicio, @Param("descripcion") String descripcion);

}
