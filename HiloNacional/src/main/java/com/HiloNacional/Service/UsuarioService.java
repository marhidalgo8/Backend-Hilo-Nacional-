package com.HiloNacional.Service;

import com.HiloNacional.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.HiloNacional.Dto.ClaveDto;
import com.HiloNacional.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuariosRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public List<Usuario> getUsuarios() {
        return usuariosRepository.findAll();
    }

    public Usuario getUsuario(Long id) {
        return usuariosRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el id [" + id + "] no existe"));
    }

    // ✅ Nuevo método — buscar por email (extraído del token)
    public Usuario getUsuarioByEmail(String email) {
        return usuariosRepository
                .findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el email [" + email + "] no existe"));
    }

    public Usuario deleteUsuario(Long id) {
        Usuario tmpUsuario = null;

        if (usuariosRepository.existsById(id)) {
            tmpUsuario = usuariosRepository.findById(id).get();
            usuariosRepository.deleteById(id);
        }

        return tmpUsuario;
    }

    public Usuario addUsuario(Usuario usuario) {
        Optional<Usuario> usr = usuariosRepository.findByEmail(usuario.getEmail());

        if (usr.isEmpty()) {
            usuario.setClave(encoder.encode(usuario.getClave()));
            return usuariosRepository.save(usuario);
        } else {
            usuario = null;
        }

        return usuario;
    }

    public Usuario updateUsuario(Long id, ClaveDto claveDto) {
        Usuario tmpUsuario = usuariosRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el id [" + id + "] no existe"));

        if (encoder.matches(claveDto.getClave(), tmpUsuario.getClave())) {
            tmpUsuario.setClave(encoder.encode(claveDto.getNclave()));
            return usuariosRepository.save(tmpUsuario);
        } else {
            return null;
        }
    }

    public boolean validarUsuario(Usuario usuario) {
        Optional<Usuario> usr = usuariosRepository.findByEmail(usuario.getEmail());

        if (usr.isPresent()) {
            Usuario tmpUsuario = usr.get();
            if (encoder.matches(usuario.getClave(), tmpUsuario.getClave())) {
                return true;
            }
        }

        return false;
    }

}