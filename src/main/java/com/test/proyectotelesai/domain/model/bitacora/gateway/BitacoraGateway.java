package com.test.proyectotelesai.domain.model.bitacora.gateway;

import com.test.proyectotelesai.domain.model.bitacora.BitacoraDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BitacoraGateway {
    Flux<BitacoraDTO> getBitacoraByIdSolicitud(Integer id, Integer idsolicitud);
    Flux<BitacoraDTO> getAllBitacoras();
    Mono<BitacoraDTO> saveBitacora(BitacoraDTO bitacora);
}
