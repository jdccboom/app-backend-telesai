package com.test.proyectotelesai.infrastructure.driven_adapters.repository.usuario;


import com.test.proyectotelesai.domain.model.usuario.UsuarioDTO;
import com.test.proyectotelesai.domain.model.usuario.response.InfoUsuarioCrud;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioRepository extends
        ReactiveCrudRepository<UsuarioData, Integer>, ReactiveQueryByExampleExecutor<UsuarioData> {


    Mono<UsuarioData> findByEmail(String email);

    @Query("""
            SELECT *
            FROM telesai_services_db.usuariorol ur
            JOIN telesai_services_db.usuario u
            ON ur.idusuario = u.idusuario
            WHERE ur.idrol = :rol
            AND u.idestado != 1
            """)
    Flux<UsuarioData> findByRol(@Param("rol") Integer rol);

    @Query("""
            SELECT *
            FROM telesai_services_db.usuario u
            JOIN telesai_services_db.usuariorol ur on u.idusuario = ur.idusuario
            JOIN telesai_services_db.rol r on ur.idrol = r.idrol
            WHERE u.idestado != 1;
            """)
    Flux<InfoUsuarioCrud> getUsuarioAll();

    @Query("""
            SELECT *
            FROM telesai_services_db.usuario u
            JOIN telesai_services_db.usuariorol ur on u.idusuario = ur.idusuario
            JOIN telesai_services_db.rol r on ur.idrol = r.idrol
            WHERE u.idestado != 1
            AND u.idusuario = :id;
            """)
    Mono<InfoUsuarioCrud> findByUsuarioId(@Param("id") Integer id);
}
