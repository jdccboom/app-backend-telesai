package com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryGatewayAdapter implements CloudinaryGateway {

    private final Cloudinary cloudinary;

    public CloudinaryGatewayAdapter(Environment env) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", env.getProperty("cloud_name"));
        config.put("api_key", env.getProperty("api_key"));
        config.put("api_secret", env.getProperty("api_secret"));
        this.cloudinary = new Cloudinary(config);
    }

    @Override
    public Mono<Map> save(FilePart image) {
        return convert(image)
                .flatMap(file -> {
                    // Crear el mapa de parámetros para la configuración de la subida
                    Map<String, Object> uploadOptions = new HashMap<>();
                    uploadOptions.put("folder", "telesaiEvidencias");//telesaiProyecto
                    uploadOptions.put("resource_type", "auto");
                    // Subir la imagen a Cloudinary
                    return Mono.fromCallable(() -> cloudinary.uploader().upload(file, uploadOptions));
                });
    }

    @Override
    public Mono<Map> savePdf(File file) {
        // Crear el mapa de parámetros para la configuración de la subida
        Map<String, Object> uploadOptions = new HashMap<>();
        uploadOptions.put("folder", "telesaiEvidencias");//telesaiProyecto
        uploadOptions.put("resource_type", "auto");
        // Subir la imagen a Cloudinary
        return Mono.fromCallable(() -> cloudinary.uploader().upload(file, uploadOptions));
    }

    @Override
    public Mono<Map> delete(String idImage) {
        return Mono.fromCallable(() ->
                cloudinary.uploader().destroy(idImage, ObjectUtils.emptyMap())
        ); // Llama al método de eliminación de Cloudinary
    }

    private Mono<File> convert(FilePart image) {
        return Mono.create(sink -> {
            try {
                File tempFile = File.createTempFile(image.filename(), null);
                image.transferTo(tempFile).subscribe(null, sink::error, () -> sink.success(tempFile));
            } catch (IOException e) {
                sink.error(e);
            }
        });
    }
}
