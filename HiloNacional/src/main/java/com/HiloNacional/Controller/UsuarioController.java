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
import java.util.List;

@RestController
@RequestMapping(path = "/HiloNacional/usuarios/")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // ✅ GET — solo devuelve el usuario dueño del token
    @GetMapping
    public Usuario getUsuarioActual(HttpServletRequest request) {
        String email = getEmailFromToken(request);
        return usuarioService.getUsuarioByEmail(email);
    }

    // ✅ GET por ID — solo permite ver su propio perfil
    @GetMapping(path = "{usuarioId}")
    public Usuario getUsuario(@PathVariable("usuarioId") Long id, HttpServletRequest request) {
        String email = getEmailFromToken(request);
        Usuario usuarioActual = usuarioService.getUsuarioByEmail(email);

        if (!usuarioActual.getIdUsuario().equals(id)) {
            throw new IllegalArgumentException("No tienes permiso para ver este usuario");
        }

        return usuarioService.getUsuario(id);
    }

    // POST — público, no necesita token (registro)
    @PostMapping
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return usuarioService.addUsuario(usuario);
    }

    // ✅ PUT — solo puede modificar su propio perfil
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

    // ✅ DELETE — solo puede eliminar su propio perfil
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

    // ✅ Método helper — extrae el email del token JWT
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