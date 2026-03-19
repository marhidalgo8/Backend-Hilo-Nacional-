package com.HiloNacional.Service;

import com.HiloNacional.Repository.UsuarioRepository;
import com.HiloNacional.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuario(Integer id) {
        return usuarioRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con id [" + id + "] no existe"));
    }

    public Optional<Usuario> getUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario addUsuario(Usuario usuario) {
        if (usuario.getFechaRegistro() == null) {
            usuario.setFechaRegistro(LocalDateTime.now());
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Integer id, Usuario datos) {
        Usuario usuario = getUsuario(id);
        if (datos.getNombre() != null)   usuario.setNombre(datos.getNombre());
        if (datos.getEmail() != null)    usuario.setEmail(datos.getEmail());
        if (datos.getTelefono() != null) usuario.setTelefono(datos.getTelefono());
        if (datos.getPassword() != null) usuario.setPassword(datos.getPassword());
        return usuarioRepository.save(usuario);
    }

    public Usuario deleteUsuario(Integer id) {
        Usuario usuario = getUsuario(id);
        usuarioRepository.deleteById(id);
        return usuario;
    }
}