package com.test.proyectotelesai.infrastructure.driven_adapters.repository.evidencia;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface EvidenciaRepository extends ReactiveCrudRepository<EvidenciaData, Integer>, ReactiveQueryByExampleExecutor<EvidenciaData> {


    Mono<EvidenciaData> findByIdEvidencia(@Param("id") Integer idServicio);

    @Query("""
            SELECT *
            FROM telesai_services_db.evidencia;
            """)
    Flux<EvidenciaData> findAllEvidencia();

    @Query("""
        SELECT *
        FROM telesai_services_db.evidencia s
        WHERE (s.tipo = :tipo OR '0' = :id)
        AND (s.idsolicitud = :idsolicitud OR '0' = :idsolicitud);
    """)
    Mono<EvidenciaData> getEvidenciaByFilter(@Param("tipo") String tipo, @Param("idsolicitud") Integer idsolicitud );

}
