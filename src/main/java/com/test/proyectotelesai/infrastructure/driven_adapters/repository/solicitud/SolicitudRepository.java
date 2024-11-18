package com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud;

import com.test.proyectotelesai.domain.model.solicitud.response.DispobilidadResult;
import com.test.proyectotelesai.domain.model.solicitud.response.SolicitudResult;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


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
        u.idusuario AS idCliente,
        u.nombreCompleto AS Cliente,
        ub.nombreUbicacion AS Ubicacion,
        sol.fechaSolicitud AS Fecha_Solicitud,
        ser.nombreServicio AS Servicio,
        op.nombreCompleto AS Operario,
        sup.nombreCompleto AS Supervisor,
        sol.descripcion AS Descripcion,
        est.nombreestado AS Estado
        
    FROM telesai_services_db.solicitud sol
             JOIN telesai_services_db.Usuario u ON sol.idCliente = u.idUsuario
             JOIN telesai_services_db.Ubicacion ub ON sol.idUbicacion = ub.idUbicacion
             JOIN telesai_services_db.Servicio ser ON sol.idServicio = ser.idServicio
             JOIN telesai_services_db.Usuario op ON sol.idOperario = op.idUsuario
             JOIN telesai_services_db.Usuario sup ON sol.idSupervisor = sup.idUsuario
             JOIN telesai_services_db.estado est ON sol.idEstado = est.idEstado
    WHERE sol.idSolicitud = :solicitudId
    LIMIT 1;
""")
    Mono<SolicitudResult> getSolicitudInfoById(@Param("solicitudId") Integer solicitudId);

    @Query("""
    SELECT
        sol.idsolicitud,
        u.nombreCompleto AS Cliente,
        u.email AS Email,
        ub.nombreUbicacion AS Ubicacion,
        sol.fechaSolicitud AS Fecha_Solicitud,
        ser.nombreServicio AS Servicio,
        op.nombreCompleto AS Operario,
        sup.nombreCompleto AS Supervisor,
        sol.descripcion AS Descripcion,
        est.nombreestado AS Estado
        
    FROM telesai_services_db.solicitud sol
             JOIN telesai_services_db.Usuario u ON sol.idCliente = u.idUsuario
             JOIN telesai_services_db.Ubicacion ub ON sol.idUbicacion = ub.idUbicacion
             JOIN telesai_services_db.Servicio ser ON sol.idServicio = ser.idServicio
             JOIN telesai_services_db.Usuario op ON sol.idOperario = op.idUsuario
             JOIN telesai_services_db.Usuario sup ON sol.idSupervisor = sup.idUsuario
             JOIN telesai_services_db.estado est ON sol.idEstado = est.idEstado
    WHERE sol.idSolicitud = :solicitudId
    LIMIT 1;
""")
    Mono<InfoActaData> getInfoActaById( @Param("solicitudId") Integer solicitudId);

    @Query("""
    SELECT
        sol.idsolicitud,
        u.idusuario AS idCliente,
        u.nombreCompleto AS Cliente,
        ub.nombreUbicacion AS Ubicacion,
        sol.fechaSolicitud AS Fecha_Solicitud,
        ser.nombreServicio AS Servicio,
        op.nombreCompleto AS Operario,
        sup.nombreCompleto AS Supervisor,
        sol.descripcion AS Descripcion,
        est.nombreestado AS Estado
    FROM telesai_services_db.solicitud sol
             JOIN telesai_services_db.Usuario u ON sol.idCliente = u.idUsuario
             JOIN telesai_services_db.Ubicacion ub ON sol.idUbicacion = ub.idUbicacion
             JOIN telesai_services_db.Servicio ser ON sol.idServicio = ser.idServicio
             JOIN telesai_services_db.Usuario op ON sol.idOperario = op.idUsuario
             JOIN telesai_services_db.Usuario sup ON sol.idSupervisor = sup.idUsuario
             JOIN telesai_services_db.estado est ON sol.idEstado = est.idEstado
    WHERE (:id IS null OR sol.idCliente = :id OR sol.idOperario = :id OR sol.idSupervisor = :id);
""")
    Flux<SolicitudResult> getSolicitudInfoByIdCliente( @Param("id") Integer id);

    @Query("""
        SELECT COUNT(*) < 2
        FROM telesai_services_db.solicitud
        WHERE fecharevision = :date
    """
    )
    Mono<Boolean> verificarSolicitudesPorFecha(@Param("date") LocalDate date);

    @Query("""
        WITH dias AS (
            SELECT CURRENT_DATE + i AS fecha
            FROM generate_series(0, 4) AS i
        )
        SELECT
            d.fecha,
            CASE
                WHEN (SELECT COUNT(*) FROM telesai_services_db.solicitud s WHERE s.fecharevision = d.fecha) <= 1 THEN true
                ELSE false
                END AS disponible
        FROM dias d;
    """)
    Flux<DispobilidadResult> verificarDisponibilidad5Dias();
}
