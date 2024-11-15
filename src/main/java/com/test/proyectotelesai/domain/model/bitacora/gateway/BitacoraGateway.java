package com.test.proyectotelesai.domain.model.bitacora.gateway;

import com.test.proyectotelesai.domain.model.bitacora.BitacoraDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BitacoraGateway {
    Mono<BitacoraDTO> getBitacoraByIdSolicitud(Integer id);
    Flux<BitacoraDTO> getAllBitacoras();
    Mono<BitacoraDTO> saveBitacora(BitacoraDTO bitacora);
}
