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
        FROM telesai_services_db.observacion o
        WHERE (o.idobservacion = :id OR 0 = :id)
        AND (o.idobservacion = :idsolicitud OR :idsolicitud = 0);
    """)
    Flux<ObservacionData> getObservacionByFilter(@Param("id") Integer idObservacion, @Param("idsolicitud") Integer idSolicitud);

}
