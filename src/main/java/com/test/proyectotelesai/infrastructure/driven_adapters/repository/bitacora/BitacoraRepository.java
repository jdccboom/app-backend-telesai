package com.test.proyectotelesai.infrastructure.driven_adapters.repository.bitacora;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BitacoraRepository extends ReactiveCrudRepository<BitacoraData, Integer>, ReactiveQueryByExampleExecutor<BitacoraData> {


    Mono<BitacoraData> findByIdBitacora(@Param("id") Integer idServicio);

    @Query("""
            SELECT *
            FROM telesai_services_db.servicio;
            """)
    Flux<BitacoraData> findAllBitacora();

    @Query("""
        SELECT *
        FROM telesai_services_db.bitacora s
        WHERE (s.idbitacora = :id OR '0' = :id)
        AND (s.idsolicitud = :idsolicitud OR '0' = :idsolicitud);
    """)
    Mono<BitacoraData> getBitacoraByFilter(@Param("id") Integer idServicio, @Param("idsolicitud") String idsolicitud );

}
