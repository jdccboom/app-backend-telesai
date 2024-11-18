package com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.Map;

public interface CloudinaryGateway {

    Mono<Map> save(FilePart image);

    Mono<Map> savePdf(File file);

    Mono<Map> delete(String idImage);
}