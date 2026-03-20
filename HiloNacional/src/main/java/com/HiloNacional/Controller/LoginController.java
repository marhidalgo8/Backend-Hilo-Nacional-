package com.HiloNacional.Controller;

import java.util.Calendar;
import java.util.Date;

import com.HiloNacional.Dto.TokenAcceso;
import com.HiloNacional.model.Usuario;
import com.HiloNacional.Service.UsuarioService;
import com.HiloNacional.config.JwtFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.ServletException;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping(path="/store/login") // Endpoint base para login
public class LoginController {

    private final UsuarioService usuarioService;
    
    // Inyección de dependencia del servicio de usuario
    @Autowired
    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    // Endpoint POST para autenticar usuario
    @PostMapping
    public TokenAcceso loginUsuario(@RequestBody Usuario usuario ) throws ServletException {
        
        // Validar si el usuario existe y la contraseña es correcta
        if(usuarioService.validarUsuario(usuario)) {

            // Generar token JWT con el email del usuario
            String token = generarToken(usuario.getEmail());

            // Regresar el token en el DTO
            return new TokenAcceso(token);
        }
        
        // Lanzar error si las credenciales son incorrectas
        throw new ServletException("El correo o contraseña son incorrectos. [" + usuario.getEmail() + "].");
    }
    
    // Método para generar un token JWT
    private String generarToken(String email) {

        // Crear una instancia de calendario para definir expiración
        Calendar calendar = Calendar.getInstance();

        // El token será válido por 24 horas (solo para pruebas)
        calendar.add(Calendar.HOUR, 24);
        
        // Construcción del token JWT
        return Jwts.builder()
                .subject(email) // Identificador del usuario
                .claim("role", "user") // Rol del usuario (puede cambiarse dinámicamente)
                .issuedAt(new Date()) // Fecha de creación
                .expiration(calendar.getTime()) // Fecha de expiración
                .signWith(JwtFilter.getSigninKey()) // Firma del token con clave secreta
                .compact(); // Generar token final en formato String
    }// generar token
    
}