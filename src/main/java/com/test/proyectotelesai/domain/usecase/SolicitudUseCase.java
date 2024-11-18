package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.rol.RolDTO;
import com.test.proyectotelesai.domain.model.solicitud.SolicitudDTO;
import com.test.proyectotelesai.domain.model.solicitud.gateway.SolicitudGateway;
import com.test.proyectotelesai.domain.model.solicitud.response.DispobilidadResult;
import com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud.InfoActaData;
import com.test.proyectotelesai.domain.model.solicitud.response.SolicitudResult;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequiredArgsConstructor
public class SolicitudUseCase {

    private final SolicitudGateway solicitudGateway;

    private final RolUseCase rolUseCase;

    private final UsuarioUseCase usuarioUseCase;

    public Mono<SolicitudDTO> getSolicitudById(Integer id) {
        return solicitudGateway.getSolicitudById(id);
    }

    public Flux<SolicitudDTO> getSolicitudAll() {
        return solicitudGateway.getAllSolicituds();
    }

    public Mono<SolicitudResult> getSolicitudInfoById(Integer id) {
        return solicitudGateway.getSolicitudInfoById(id);
    }

    public Mono<InfoActaData> getInfoActaData(Integer id) {
        return solicitudGateway.getInfoActaData(id);
    }

    public Flux<SolicitudResult> getSolicitudInfoByIdCliente(Integer id) {
        return solicitudGateway.getSolicitudInfoByIdCliente(id);
    }

    public Mono<SolicitudDTO> procesarSolicitud(SolicitudDTO solicitudDTO, Integer id){
        return rolUseCase.getRolsByUsuario(id)
                .map(RolDTO::getNombreRol)
                .collectList()
                .flatMap(listRol -> {
                    solicitudDTO.setIdCliente(id);
                    if(listRol.contains("Cliente"))
                        return asignarProgamacion(solicitudDTO);

                    return Mono.error(new Exception("No tiene permisos para este recurso"));
                });
    }

    private Mono<SolicitudDTO> asignarProgamacion (SolicitudDTO solicitudDTO){
        return solicitudGateway
                .verificarSolicitudesPorFecha(
                        solicitudDTO.getFechaRevision())
                .flatMap(result ->{
                    if (result){
                        solicitudDTO.setFechaSolicitud(LocalDate.now());
                        return asignarOperario(solicitudDTO);
                    }
                    return Mono.error(new Exception("Fecha no disponible"));
                });
    }

    private Mono<SolicitudDTO> asignarOperario(SolicitudDTO solicitudDTO){
        return usuarioUseCase.getUsuarioByRol(3)
                .switchIfEmpty(Mono.error(new Exception("No hay operarios disponibles")))
                .collectList()
                .flatMap(operarioList -> {
                    int op= (int)(Math.random()*operarioList.size());
                    solicitudDTO.setIdOperario(operarioList.get(op).getIdusuario());
                    solicitudDTO.setIdSupervisor(1);
                    solicitudDTO.setIdEstado(3);
                    return solicitudGateway.saveSolicitud(solicitudDTO);
                });

    }

    public Mono<SolicitudDTO> updateSolicitud(SolicitudDTO solicitud){
        return solicitudGateway.getSolicitudById(solicitud.getIdSolicitud())
                .switchIfEmpty(Mono.error(
                        new Exception("No existe el solicitud con id: " + solicitud.getIdSolicitud())))
                .flatMap(SolicitudDTO -> {
                        SolicitudDTO.setFechaRevision(solicitud.getFechaRevision());
                        SolicitudDTO.setDescripcion(solicitud.getDescripcion());
                        return solicitudGateway.saveSolicitud(SolicitudDTO);
                });
    }

    public Mono<SolicitudDTO> updateEstadoSolicitud(Integer id, Integer estado){
        return solicitudGateway.getSolicitudById(id)
                .switchIfEmpty(Mono.error(
                        new Exception("No existe el solicitud con id: " + id)))
                .flatMap(SolicitudDTO -> {
                    SolicitudDTO.setIdEstado(estado);
                    return solicitudGateway.saveSolicitud(SolicitudDTO);
                });
    }

    public Mono<String> deleteSolicitud(Integer id){
        return solicitudGateway.getSolicitudById(id)
                .switchIfEmpty(Mono.error(
                        new Exception("No existe el solicitud con id: " + id)))
                .flatMap(SolicitudDTO -> {
                    SolicitudDTO.setIdEstado(1);
                    return solicitudGateway.saveSolicitud(SolicitudDTO)
                            .thenReturn("Solicitud eliminada con exito");
                });
    }

    public Flux<DispobilidadResult> verificarDisponibilidad5Dias(){
        return solicitudGateway.verificarDisponibilidad5Dias();
    }

    public Mono<String> validarActa(Integer idSolicitud, Integer id){
        return rolUseCase.getRolsByUsuario(id)
                .map(RolDTO::getNombreRol)
                .collectList()
                .flatMap(listRol -> {
                    if(listRol.contains("Supervisor"))
                        return updateEstadoSolicitud(idSolicitud, 6).thenReturn("Estado actualizado");

                    return Mono.error(new Exception("No tiene permisos para este recurso"));
                });
    }
}
