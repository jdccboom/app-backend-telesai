package com.test.proyectotelesai.infrastructure.driven_adapters.service.pdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.test.proyectotelesai.domain.model.bitacora.BitacoraDTO;
import com.test.proyectotelesai.domain.model.evidencia.EvidenciaDTO;
import com.test.proyectotelesai.domain.model.observacion.ObservacionDTO;
import com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud.InfoActaData;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PdfGatewayAdapter implements PdfGetaway {

    @Override
    public File generatePdf(InfoActaData solicitudResult,
                            List<ObservacionDTO> observaciones,
                            List<BitacoraDTO> bitacoras,
                            EvidenciaDTO evidencia) throws IOException {

        // Crear un archivo temporal para el PDF
        Path tempFilePath = Files.createTempFile("acta_revision_", ".pdf");
        File tempFile = tempFilePath.toFile();

        // Crear el documento PDF
        try (PdfWriter writer = new PdfWriter(tempFile);
             Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer))) {

            // Encabezado
            document.add(new Paragraph("ACTA DE REVISIÓN DEL SERVICIO")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(16));

            // Información de la solicitud
            document.add(new Paragraph(
                    "\nCliente: " + solicitudResult.getCliente() +
                            "\nUbicación: " + solicitudResult.getUbicacion() +
                            "\nServicio: " + solicitudResult.getServicio() +
                            "\nFecha de la Solicitud: " + solicitudResult.getFechaSolicitud() +
                            "\nDescripción: " + solicitudResult.getDescripcion() +
                            "\nOperario: " + solicitudResult.getOperario() +
                            "\nSupervisor: " + solicitudResult.getSupervisor())
                    .setFontSize(12));

            // Observaciones
            document.add(new Paragraph("\nOBSERVACIONES:")
                    .setBold()
                    .setFontSize(14));
            for (ObservacionDTO observacion : observaciones) {
                document.add(new Paragraph("- "+observacion.getDescripcion()).setFontSize(12));
            }

            // Bitácora de acciones
            document.add(new Paragraph("\nBITÁCORA DE ACCIONES:")
                    .setBold()
                    .setFontSize(14));

            for (BitacoraDTO bitacora : bitacoras) {
                document.add(new Paragraph(bitacora.getFechaAccion() +
                                           " - " +bitacora.getAccion()+ ": "+
                                           bitacora.getDetalle()).setFontSize(12));
            }

            document.add(new Paragraph("\nFirma del Cliente:")
                    .setFontSize(12));
            // Descargar y agregar la imagen de la firma desde Cloudinary
            if (evidencia.getUrlEvidencia() != null && !evidencia.getUrlEvidencia().isEmpty()) {
                Image image = new Image(ImageDataFactory.create(evidencia.getUrlEvidencia()));
                image.setWidth(200);  // Ajustar el tamaño de la imagen
                image.setHeight(100); // Ajustar el tamaño de la imagen
                document.add(image);  // Agregar la imagen al documento
            }
            // Firma
            document.add(new Paragraph("Fecha: " + LocalDateTime.now())
                    .setFontSize(12));



        }

        // Devolver el archivo temporal generado
        return tempFile;
    }
}
