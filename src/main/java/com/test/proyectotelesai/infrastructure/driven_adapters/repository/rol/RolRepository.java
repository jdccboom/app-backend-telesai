package com.test.proyectotelesai.infrastructure.driven_adapters.repository.rol;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface RolRepository extends ReactiveCrudRepository<RolData, Integer>, ReactiveQueryByExampleExecutor<RolData> {
    @Query("""
            SELECT r.nombrerol
            FROM telesai_services_db.usuariorol ur
            JOIN telesai_services_db.rol r
            ON ur.idrol = r.idrol
            WHERE ur.idusuario = :idUsuario
            """)
    Flux<RolData> getRolByIdUsuario(@Param("idUsuario") int idUsuario);

    Mono<RolData> findByIdRol(@Param("id") int idRol);


}