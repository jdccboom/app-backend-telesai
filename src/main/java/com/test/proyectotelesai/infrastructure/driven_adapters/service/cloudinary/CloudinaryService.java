package com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CloudinaryService {

    Mono<Map> save(FilePart image);

    Mono<Map> delete(String idImage);
}