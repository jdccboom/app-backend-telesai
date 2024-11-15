package com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface SolicitudRepository extends ReactiveCrudRepository<SolicitudData, Integer>, ReactiveQueryByExampleExecutor<SolicitudData> {

    @Query("""
            SELECT *
            FROM telesai_services_db.solicitud s
            WHERE s.idestado != 1;
            """)
    Flux<SolicitudData> findAllSolicitud();

    @Query(value = """
                SELECT *
                FROM telesai_services_db.solicitud s
                WHERE (s.idsolicitud = :id OR 0 = :id)
                AND (s.idcliente = :idCliente OR 0 = :idCliente)
                AND s.idestado != 1;
            """)
    Mono<SolicitudData> getSolicitudByFilter(
            @Param("id") Integer idServicio,
            @Param("idCliente") Integer idCliente );

}
