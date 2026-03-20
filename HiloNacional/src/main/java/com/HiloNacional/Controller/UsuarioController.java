package com.HiloNacional.Controller;

import com.HiloNacional.Service.UsuarioService;
import com.HiloNacional.model.Usuario;

import com.HiloNacional.Dto.ClaveDto;
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

    @GetMapping(path="{usuarioId}") // http://localhost:8080/store/productos/{productoId}
	public Usuario getUsuario(@PathVariable("usuarioId") Long id) {
		return usuarioService.getUsuario(id);
	}// getUsuario
    
    @PostMapping
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return usuarioService.addUsuario(usuario);
    }

    @PutMapping(path="{usuarioId}") // http://localhost:8080/store/productos/{productoId}
	public Usuario updateUsuario(@PathVariable("usuarioId") Long id,
			@RequestBody(required=true) ClaveDto claveDto) {
		
		return usuarioService.updateUsuario(id, claveDto);
		
	}// updateUsuario

    @DeleteMapping(path="{usuarioId}") // http://localhost:8080/store/productos/{productoId}
	public Usuario deleteUsuario(@PathVariable("usuarioId") Long id) {
		return usuarioService.deleteUsuario(id);
	}// deleteUsuario
}