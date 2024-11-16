package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.bitacora.BitacoraDTO;
import com.test.proyectotelesai.domain.model.evidencia.EvidenciaDTO;
import com.test.proyectotelesai.domain.model.observacion.ObservacionDTO;
import com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud.SolicitudResult;
import com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary.FileParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ActaUseCase {

    private final SolicitudUseCase solicitudUseCase;
    private final ObservacionUseCase observacionUseCase;
    private final BitacoraUseCase bitacoraUseCase;
    private final EvidenciaUseCase evidenciaUseCase;
    private final PdfUseCase pdfUseCase;
    private final ClodinaryUseCase clodinaryUseCase;

    // Método principal que coordina el flujo
    public Mono<FileParams> generarActa(Integer idSolicitud) {
        return obtenerInformacionSolicitud(idSolicitud)
                .flatMap(this::procesarSolicitud);
    }

    // Procesa la información de la solicitud
    private Mono<FileParams> procesarSolicitud(SolicitudResult infoSolicitud) {
        return obtenerObservaciones(infoSolicitud.getIdSolicitud())
                .zipWith(obtenerBitacoras(infoSolicitud.getIdSolicitud()))
                .flatMap(tuple -> {
                    List<ObservacionDTO> observaciones = tuple.getT1();
                    List<BitacoraDTO> bitacoras = tuple.getT2();
                    return obtenerEvidencia(infoSolicitud.getIdSolicitud())
                            .flatMap(evidenciaDTO -> generarPdf(infoSolicitud, observaciones, bitacoras, evidenciaDTO));
                })
                .flatMap(this::guardarEnCloudinary)
                .flatMap(this::crearEvidencia);
    }

    // Obtiene la información de la solicitud
    private Mono<SolicitudResult> obtenerInformacionSolicitud(Integer idSolicitud) {
        return solicitudUseCase.getSolicitudInfoById(idSolicitud);
    }

    // Obtiene las observaciones relacionadas con la solicitud
    private Mono<List<ObservacionDTO>> obtenerObservaciones(Integer idSolicitud) {
        return observacionUseCase.getObservacionByIdSolictud(idSolicitud).collectList();
    }

    // Obtiene las bitácoras relacionadas con la solicitud
    private Mono<List<BitacoraDTO>> obtenerBitacoras(Integer idSolicitud) {
        return bitacoraUseCase.getBitacoraByIdSolicitud(idSolicitud).collectList();
    }

    // Obtiene la evidencia relacionada con la solicitud (en este caso, la firma)
    private Mono<EvidenciaDTO> obtenerEvidencia(Integer idSolicitud) {
        return evidenciaUseCase.getEvidenciaByTipo("Firma", idSolicitud);
    }

    // Genera el archivo PDF con la información obtenida
    private Mono<File> generarPdf(SolicitudResult infoSolicitud, List<ObservacionDTO> observaciones,
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
    private Mono<Map> guardarEnCloudinary(File pdfFile) {
        return clodinaryUseCase.savePdf(pdfFile)
                .doFinally(signalType -> {
                    if (pdfFile.exists()) {
                        //noinspection ResultOfMethodCallIgnored
                        pdfFile.delete();
                    }
                })
                .onErrorMap(error -> new RuntimeException("Error al guardar el archivo en Cloudinary", error));
    }

    // Convierte un EvidenciaDTO a FileParams
    private Mono<FileParams> mapToFileParams(EvidenciaDTO evidenciaDTO) {
        if (evidenciaDTO.getUrlEvidencia() == null) {
            return Mono.error(new IllegalArgumentException("Datos incompletos para construir FileParams"));
        }
        return Mono.just(FileParams.builder()
                .url(evidenciaDTO.getUrlEvidencia())
                .id("") // Aquí se puede ajustar si el `id` debe venir de otra fuente
                .build());
    }

    // Crea la evidencia a partir de los datos obtenidos de Cloudinary
    private Mono<FileParams> crearEvidencia(Map<String, Object> datos) {
        String url = (String) datos.get("secure_url");
        return evidenciaUseCase.save(EvidenciaDTO.builder()
                        .urlEvidencia(url)
                        .tipo("Acta")
                        .build())
                .flatMap(this::mapToFileParams);
    }
}
