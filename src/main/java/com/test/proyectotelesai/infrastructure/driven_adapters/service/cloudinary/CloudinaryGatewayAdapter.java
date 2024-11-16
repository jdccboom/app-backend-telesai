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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                    Map<String, Object> uploadOptions = new HashMap<>();
                    uploadOptions.put("folder", "telesaiEvidencias");
                    uploadOptions.put("resource_type", "auto");
                    return Mono.fromCallable(() -> cloudinary.uploader().upload(file, uploadOptions));
                });
    }

    @Override
    public Mono<Map> savePdf(File file) {

        Map<String, Object> uploadOptions = new HashMap<>();
        uploadOptions.put("folder", "telesaiEvidencias");
        uploadOptions.put("resource_type", "auto");

        return Mono.fromCallable(() -> cloudinary.uploader().upload(file, uploadOptions));
    }

    @Override
    public Mono<Map> delete(String url) {
        return Mono.fromCallable(() ->
                cloudinary.uploader().destroy(extractIdFromUrl(url), ObjectUtils.emptyMap())
        );
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

    private String extractIdFromUrl(String url) {

        String regex = "/image/upload/v\\d+/([^/]+(?:/[^/]+)*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1).replaceAll("\\.[a-zA-Z0-9]+$", "");
        }

        throw new IllegalArgumentException("URL inválida, no se pudo extraer el ID: " + url);
    }




}
