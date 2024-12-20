package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.bitacora.BitacoraDTO;
import com.test.proyectotelesai.domain.model.evidencia.EvidenciaDTO;
import com.test.proyectotelesai.domain.model.observacion.ObservacionDTO;
import com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud.InfoActaData;
import com.test.proyectotelesai.infrastructure.driven_adapters.service.pdf.PdfGetaway;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class PdfUseCase {

    private final PdfGetaway pdfGetaway;

    public File generatePdf(InfoActaData solicitudResult,
                            List<ObservacionDTO> observaciones,
                            List<BitacoraDTO> bitacoras,
                            EvidenciaDTO evidencia) throws IOException {
        return pdfGetaway.generatePdf(solicitudResult,observaciones,bitacoras,evidencia);
    }
}
