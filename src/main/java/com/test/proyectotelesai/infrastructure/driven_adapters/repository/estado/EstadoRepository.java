package com.test.proyectotelesai.infrastructure.driven_adapters.repository.estado;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface EstadoRepository extends ReactiveCrudRepository<EstadoData, Integer>, ReactiveQueryByExampleExecutor<EstadoData> {


    Mono<EstadoData> findByIdEstado(@Param("id") Integer idServicio);

    @Query("""
            SELECT *
            FROM telesai_services_db.estado;
            """)
    Flux<EstadoData> findAllEstado();

    @Query("""
        SELECT *
        FROM telesai_services_db.estado s
        WHERE (s.idestado = :id OR '0' = :id)
        AND (s.nombreestado = :nombre OR '0' = :nombre);
    """)
    Mono<EstadoData> getEstadoByFilter(@Param("id") Integer idServicio, @Param("nombre") String nombreEstado );

}
