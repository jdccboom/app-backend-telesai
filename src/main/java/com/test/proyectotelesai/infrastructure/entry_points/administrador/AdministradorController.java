package com.test.proyectotelesai.infrastructure.entry_points.administrador;


import com.test.proyectotelesai.domain.model.usuario.UsuarioDTO;
import com.test.proyectotelesai.domain.usecase.UsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/moderator")
public class AdministradorController {

    private final UsuarioUseCase usuarioUseCase;

    @GetMapping("/allUsuarios")
    public ResponseEntity<Object> getUsuarioAll() {
        return ResponseEntity.ok().body(usuarioUseCase.getUsuarioAll());
    }

    @GetMapping("/getUsuario/{id}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(usuarioUseCase.getUsuarioById(id));
    }

    @PostMapping("/usuario/save")
    public ResponseEntity<Object> saveUsuario(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok().body(usuarioUseCase.saveUsuario(usuario));
    }

    @PostMapping("/usuario/update")
    public ResponseEntity<Object> updateUpdate(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok().body(usuarioUseCase.updateUsuario(usuario));
    }

    @DeleteMapping("/usuario/delete/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(usuarioUseCase.deleteUsuario(id));
    }
}
