package com.HiloNacional.Controller;

import com.HiloNacional.Service.UsuarioService;
import com.HiloNacional.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/HiloNacional/usuarios/")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @GetMapping(path = "{usuarioId}")
    public Usuario getUsuario(@PathVariable("usuarioId") Integer id) {
        return usuarioService.getUsuario(id);
    }

    @PostMapping
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return usuarioService.addUsuario(usuario);
    }

    @PutMapping(path = "{usuarioId}")
    public Usuario updateUsuario(@PathVariable("usuarioId") Integer id,
                                  @RequestBody Usuario datos) {
        return usuarioService.updateUsuario(id, datos);
    }

    @DeleteMapping(path = "{usuarioId}")
    public Usuario deleteUsuario(@PathVariable("usuarioId") Integer id) {
        return usuarioService.deleteUsuario(id);
    }
}