package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.bitacora.BitacoraDTO;
import com.test.proyectotelesai.domain.model.evidencia.EvidenciaDTO;
import com.test.proyectotelesai.domain.model.observacion.ObservacionDTO;
import com.test.proyectotelesai.domain.model.solicitud.SolicitudDTO;
import com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud.InfoActaData;
import com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary.FileParams;
import com.test.proyectotelesai.infrastructure.driven_adapters.service.mail.EmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ActaUseCase {

    private final SolicitudUseCase solicitudUseCase;
    private final ObservacionUseCase observacionUseCase;
    private final BitacoraUseCase bitacoraUseCase;
    private final EvidenciaUseCase evidenciaUseCase;
    private final PdfUseCase pdfUseCase;
    private final ClodinaryUseCase clodinaryUseCase;
    private final MailUseCase mailUseCase;

    // Método principal que coordina el flujo
    public Mono<FileParams> generarActa(Integer idSolicitud) {
        return obtenerInformacionSolicitud(idSolicitud)
                .flatMap(solicitudResult -> {
                    if (Objects.equals(solicitudResult.getEstado(), "Validada")) {
                        return procesarSolicitud(solicitudResult)
                                .flatMap(fileParams -> enviarCorreo(solicitudResult,fileParams.getUrl()) // Cambiar a idUsuario
                                        .thenReturn(fileParams));  // Continuar con el flujo
                    }
                    return Mono.error(new RuntimeException("La solicitud no está Validada"));
                });
    }

    // Procesa la información de la solicitud
    private Mono<FileParams> procesarSolicitud(InfoActaData infoSolicitud) {
        return obtenerObservaciones(infoSolicitud.getIdSolicitud())
                .zipWith(obtenerBitacoras(infoSolicitud.getIdSolicitud()))
                .flatMap(tuple -> {
                    List<ObservacionDTO> observaciones = tuple.getT1();
                    List<BitacoraDTO> bitacoras = tuple.getT2();
                    return obtenerEvidencia(infoSolicitud.getIdSolicitud())
                            .flatMap(evidenciaDTO -> generarPdf(infoSolicitud, observaciones, bitacoras, evidenciaDTO));
                })
                .flatMap(this::guardarEnCloudinary)
                .flatMap(fileParams -> crearEvidencia(fileParams, infoSolicitud.getIdSolicitud()));
    }

    // Obtiene la información de la solicitud
    private Mono<InfoActaData> obtenerInformacionSolicitud(Integer idSolicitud) {
        return solicitudUseCase.getInfoActaData(idSolicitud);
    }

    // Obtiene las observaciones relacionadas con la solicitud
    private Mono<List<ObservacionDTO>> obtenerObservaciones(Integer idSolicitud) {
        return observacionUseCase.getObservacionByIdSolictud(0, idSolicitud).collectList();
    }

    // Obtiene las bitácoras relacionadas con la solicitud
    private Mono<List<BitacoraDTO>> obtenerBitacoras(Integer idSolicitud) {
        return bitacoraUseCase.getBitacoraByIdSolicitud(0, idSolicitud).collectList();
    }

    // Obtiene la evidencia relacionada con la solicitud (en este caso, la firma)
    private Mono<EvidenciaDTO> obtenerEvidencia(Integer idSolicitud) {
        return evidenciaUseCase.getEvidenciaByTipo("Firma", idSolicitud);
    }

    // Genera el archivo PDF con la información obtenida
    private Mono<File> generarPdf(InfoActaData infoSolicitud, List<ObservacionDTO> observaciones,
                                  List<BitacoraDTO> bitacoras, EvidenciaDTO evidenciaDTO) {
        try {
            File pdfFile = pdfUseCase.generatePdf(infoSolicitud, observaciones, bitacoras, evidenciaDTO);
            return Mono.just(pdfFile).doOnNext(file ->
                    System.out.println("PDF generado exitosamente: " + file.getAbsolutePath())
            );
        } catch (IOException e) {
            return Mono.error(new RuntimeException("Error al generar el PDF", e));
        }
    }

    // Guarda el archivo PDF en Cloudinary
    private Mono<FileParams> guardarEnCloudinary(File pdfFile) {
        return clodinaryUseCase.savePdf(pdfFile)
                .map(datos -> {
                    String url = (String) datos.get("secure_url");
                    String publicId = (String) datos.get("public_id");
                    return FileParams.builder()
                            .url(url)
                            .id(publicId)
                            .build();
                })
                .doFinally(signalType -> {
                    if (pdfFile.exists()) //noinspection SingleStatementInBlock
                    {
                        pdfFile.delete(); // Eliminar archivo temporal después de subirlo
                    }
                })
                .onErrorMap(error -> new RuntimeException("Error al guardar el archivo en Cloudinary", error));
    }

    // Crea la evidencia a partir de los datos obtenidos de Cloudinary
    private Mono<FileParams> crearEvidencia(FileParams fileParams, Integer idSolicitud) {
        return evidenciaUseCase.save(EvidenciaDTO.builder()
                        .urlEvidencia(fileParams.getUrl())
                        .tipo("Acta")
                        .idSolicitud(idSolicitud)
                        .idEstado(2)
                        .build())
                .flatMap(evidencia -> actualizarEstadoSolicitud(idSolicitud)
                        .thenReturn(fileParams));
    }

    // Actualiza el estado de la solicitud
    private Mono<SolicitudDTO> actualizarEstadoSolicitud(Integer idSolicitud) {
        return solicitudUseCase.updateEstadoSolicitud(idSolicitud, 5);
    }

    // Método que envía el correo al usuario
    private Mono<Void> enviarCorreo(InfoActaData info, String url) {  // Cambiar a idUsuario
        info.setEstado("Finalizada");
        try {
            mailUseCase.sendMail(
                    EmailDto.builder()
                            .destinatario(info.getEmail())
                            .asunto("Notificación de Acta de Servicio Prestado")
                            .body("""
                                   <div class="header">
                                      <h1>Acta de Servicio Prestado</h1>
                                   </div>
                                   <div class="content">
                                       <p>Estimado/a <strong>%s</strong>,</p>
                                       <p>Se ha generado el acta correspondiente al servicio solicitado. A continuación, se detallan los datos de la solicitud:</p>
                                            <table>
                                                 <tr>
                                                      <td><strong>Cliente:</strong></td>
                                                      <td>%s</td>
                                                 </tr>
                                                 <tr>
                                                      <td><strong>Ubicación:</strong></td>
                                                      <td>%s</td>
                                                 </tr>
                                                 <tr>
                                                      <td><strong>Servicio:</strong></td>
                                                      <td>%s</td>
                                                 </tr>
                                                 <tr>
                                                       <td><strong>Operario:</strong></td>
                                                       <td>%s</td>
                                                 </tr>
                                                 <tr>
                                                       <td><strong>Supervisor:</strong></td>
                                                       <td>%s</td>
                                                 </tr>
                                                 <tr>
                                                       <td><strong>Fecha de Solicitud:</strong></td>
                                                       <td>%s</td>
                                                 </tr>
                                                 <tr>
                                                       <td><strong>Descripción:</strong></td>
                                                       <td>%s</td>
                                                 </tr>
                                                 <tr>
                                                       <td><strong>Estado:</strong></td>
                                                       <td>%s</td>
                                                 </tr>
                                            </table>
                                       <p>Para acceder al acta en formato PDF, por favor haga clic en el siguiente enlace:</p>
                                       <a href="%s" class="button">Ver Acta en PDF</a>
                                   </div>
                                   """.formatted(
                                            info.getCliente(),
                                            info.getCliente(),
                                            info.getUbicacion(),
                                            info.getServicio(),
                                            info.getOperario(),
                                            info.getSupervisor(),
                                            info.getFechaSolicitud(),
                                            info.getDescripcion(),
                                            info.getEstado(),
                                            url
                                    )
                            )
                            .build());
        } catch (Exception e) {
            return Mono.error(new RuntimeException("Error al enviar correo", e));
        }
        return Mono.empty();
    }
}
