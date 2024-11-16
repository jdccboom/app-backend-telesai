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
            @Param("idCliente") Integer idCliente);

    @Query("""
    SELECT
        sol.idsolicitud,
        u.nombreCompleto AS Cliente,
        ub.nombreUbicacion AS Ubicacion,
        sol.fechaSolicitud AS Fecha_Solicitud,
        ser.nombreServicio AS Servicio,
        op.nombreCompleto AS Operario,
        sup.nombreCompleto AS Supervisor,
        sol.descripcion AS Descripcion
    FROM telesai_services_db.solicitud sol
             JOIN telesai_services_db.Usuario u ON sol.idCliente = u.idUsuario
             JOIN telesai_services_db.Ubicacion ub ON sol.idUbicacion = ub.idUbicacion
             JOIN telesai_services_db.Servicio ser ON sol.idServicio = ser.idServicio
             JOIN telesai_services_db.Usuario op ON sol.idOperario = op.idUsuario
             JOIN telesai_services_db.Usuario sup ON sol.idSupervisor = sup.idUsuario
    WHERE sol.idSolicitud = :solicitudId
    LIMIT 1;
""")
    Mono<SolicitudResult> getSolicitudInfoById( @Param("solicitudId") Integer solicitudId);

    @Query("""
    SELECT
        sol.idsolicitud,
        u.nombreCompleto AS Cliente,
        ub.nombreUbicacion AS Ubicacion,
        sol.fechaSolicitud AS Fecha_Solicitud,
        ser.nombreServicio AS Servicio,
        op.nombreCompleto AS Operario,
        sup.nombreCompleto AS Supervisor,
        sol.descripcion AS Descripcion
    FROM telesai_services_db.solicitud sol
             JOIN telesai_services_db.Usuario u ON sol.idCliente = u.idUsuario
             JOIN telesai_services_db.Ubicacion ub ON sol.idUbicacion = ub.idUbicacion
             JOIN telesai_services_db.Servicio ser ON sol.idServicio = ser.idServicio
             JOIN telesai_services_db.Usuario op ON sol.idOperario = op.idUsuario
             JOIN telesai_services_db.Usuario sup ON sol.idSupervisor = sup.idUsuario
    WHERE (:id IS null OR sol.idCliente = :id OR sol.idOperario = :id OR sol.idSupervisor = :id);
""")
    Flux<SolicitudResult> getSolicitudInfoByIdCliente( @Param("id") Integer id);

}
