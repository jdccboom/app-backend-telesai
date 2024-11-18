package com.test.proyectotelesai.infrastructure.entry_points.autenticacion;

import com.test.proyectotelesai.domain.model.usuario.request.LoginParams;
import com.test.proyectotelesai.domain.usecase.AutenticacionUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AutenticacionController {

    @Autowired
    private final AutenticacionUseCase autenticacionUseCase; ;

    @PostMapping("/login")
    public ResponseEntity<Object> loginClient(@Valid @RequestBody
                                              LoginParams loginParams) throws Exception {
        return ResponseEntity.ok().body(autenticacionUseCase.login(loginParams));
    }


}
