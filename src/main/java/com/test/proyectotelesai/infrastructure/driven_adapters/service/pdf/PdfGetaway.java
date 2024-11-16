package com.test.proyectotelesai.infrastructure.driven_adapters.service.pdf;

import com.test.proyectotelesai.domain.model.bitacora.BitacoraDTO;
import com.test.proyectotelesai.domain.model.evidencia.EvidenciaDTO;
import com.test.proyectotelesai.domain.model.observacion.ObservacionDTO;
import com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud.SolicitudResult;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface PdfGetaway {
    File generatePdf(SolicitudResult solicitudResult,
                     List<ObservacionDTO> observaciones,
                     List<BitacoraDTO> bitacoras,
                     EvidenciaDTO evidencia) throws IOException;
}
