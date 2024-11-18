package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.evidencia.EvidenciaDTO;
import com.test.proyectotelesai.domain.model.evidencia.gateway.EvidenciaGateway;
import com.test.proyectotelesai.domain.model.solicitud.SolicitudDTO;
import com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary.FileParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequiredArgsConstructor
public class EvidenciaUseCase {

    private final EvidenciaGateway evidenciaGateway;

    private final ClodinaryUseCase clodinaryUseCase;

    public Mono<EvidenciaDTO> getEvidenciaByTipo(String tipo, Integer id) {
        return evidenciaGateway.getEvidenciaByTipo(tipo,id)
                .switchIfEmpty(Mono.error(new Exception("No se encontro el tipo de evidencia: "+tipo)))
                .onErrorResume(error ->
                        Mono.error(new Exception(error.getMessage())));
    }

    public Mono<EvidenciaDTO> getEvidenciaById(Integer id) {
        return evidenciaGateway.getEvidenciaById(id)
                .switchIfEmpty(Mono.error(new Exception("No se encontro la evidencia con id: "+id)))
                .onErrorResume(error ->
                        Mono.error(new Exception(error.getMessage())));
    }

    public Mono<EvidenciaDTO> save(EvidenciaDTO evidenciaDTO) {
        return evidenciaGateway.saveEvidencia(evidenciaDTO)
                .onErrorResume(error ->
                        Mono.error(new Exception("Error al guardar la evidencia")));
    }

    public Mono<EvidenciaDTO> save(FilePart image,Integer idSolicitud) {
        return clodinaryUseCase.save(image)
                .flatMap(map -> {
                    String url = (String) map.get("secure_url");
                    return this.save(EvidenciaDTO.builder()
                                    .urlEvidencia(url)
                                    .tipo("Firma")
                                    .idEstado(2)
                                    .idSolicitud(idSolicitud)
                                    .build());
                }).onErrorResume(error ->
                        Mono.error(new Exception(error.getMessage())));
    }

    public Mono<String> delete(Integer id) {
        return evidenciaGateway.
                getEvidenciaById(id)
                .switchIfEmpty(Mono.error(new Exception("No existe evidencia con este id: "+id)))
                .flatMap(evidenciaDTO -> {
                    evidenciaDTO.setIdEstado(1);
                    return evidenciaGateway.saveEvidencia(evidenciaDTO)
                            .thenReturn("Evidencia eliminada correctamente");
                        }
                ).onErrorResume(error ->
                        Mono.error(new Exception(error.getMessage())));
    }
}
