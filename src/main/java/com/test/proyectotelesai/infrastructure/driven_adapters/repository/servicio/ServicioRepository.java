package com.test.proyectotelesai.infrastructure.driven_adapters.repository.servicio;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ServicioRepository extends ReactiveCrudRepository<ServicioData, Integer>, ReactiveQueryByExampleExecutor<ServicioData> {


    Mono<ServicioData> findByIdServicio(@Param("id") Integer idServicio);

    @Query("""
            SELECT *
            FROM telesai_services_db.servicio s
            WHERE s.idestado != 1;
            """)
    Flux<ServicioData> findAllServicios();

    @Query(value = """
                SELECT *
                FROM telesai_services_db.servicio s
                WHERE (s.idservicio = :id OR 0 = :id)
                AND (s.nombreservicio = :nombre OR :nombre is null)
                AND s.idestado != 1;
            """)
    Mono<ServicioData> getServicioByFilter(@Param("id") Integer idServicio, @Param("nombre") String nombreServicio );

}
