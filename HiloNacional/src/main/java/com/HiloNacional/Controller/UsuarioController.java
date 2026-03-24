package com.HiloNacional.Controller;

import com.HiloNacional.Service.UsuarioService;
import com.HiloNacional.config.JwtFilter;
import com.HiloNacional.model.Usuario;
import com.HiloNacional.Dto.ClaveDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/HiloNacional/usuarios/")
@CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public Usuario getUsuarioActual(HttpServletRequest request) {
        String email = getEmailFromToken(request);
        return usuarioService.getUsuarioByEmail(email);
    }

    @GetMapping(path = "{usuarioId}")
    public Usuario getUsuario(@PathVariable("usuarioId") Long id, HttpServletRequest request) {
        String email = getEmailFromToken(request);
        Usuario usuarioActual = usuarioService.getUsuarioByEmail(email);

        if (!usuarioActual.getIdUsuario().equals(id)) {
            throw new IllegalArgumentException("No tienes permiso para ver este usuario");
        }

        return usuarioService.getUsuario(id);
    }
    @PostMapping
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return usuarioService.addUsuario(usuario);
    }

    @PutMapping(path = "{usuarioId}")
    public Usuario updateUsuario(@PathVariable("usuarioId") Long id,
            @RequestBody(required = true) ClaveDto claveDto,
            HttpServletRequest request) {

        String email = getEmailFromToken(request);
        Usuario usuarioActual = usuarioService.getUsuarioByEmail(email);

        if (!usuarioActual.getIdUsuario().equals(id)) {
            throw new IllegalArgumentException("No tienes permiso para modificar este usuario");
        }

        return usuarioService.updateUsuario(id, claveDto);
    }


    @DeleteMapping(path = "{usuarioId}")
    public Usuario deleteUsuario(@PathVariable("usuarioId") Long id,
            HttpServletRequest request) {

        String email = getEmailFromToken(request);
        Usuario usuarioActual = usuarioService.getUsuarioByEmail(email);

        if (!usuarioActual.getIdUsuario().equals(id)) {
            throw new IllegalArgumentException("No tienes permiso para eliminar este usuario");
        }

        return usuarioService.deleteUsuario(id);
    }


    private String getEmailFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Claims claims = Jwts.parser()
                .verifyWith(JwtFilter.getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

}