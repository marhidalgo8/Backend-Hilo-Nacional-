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
	}// constructor
	
	public List<Usuario> getUsuarios() {
		return usuariosRepository.findAll();
	}// getUsuarios
	

	public Usuario getUsuario(Long id) {
		
		return usuariosRepository
				.findById(id)
				.orElseThrow( () -> new IllegalArgumentException("El usuario con el id [" + id + "] no existe") );
	}// getUsuario
	
	
	public Usuario deleteUsuario(Long id) {
		Usuario tmpUsuario = null;
		
		if(usuariosRepository.existsById(id)) {
			tmpUsuario = usuariosRepository.findById(id).get();
			usuariosRepository.deleteById(id);
		}// if
		
		return tmpUsuario;
		
	}// deleteUsuario
	
	public Usuario addUsuario(Usuario usuario) {
		Optional<Usuario> usr = usuariosRepository.findByEmail( usuario.getEmail() );
		
		if(usr.isEmpty()) {
			usuario.setClave(encoder.encode( usuario.getClave() ) );
			usuariosRepository.save(usuario);
		} else {
			usuario = null;
		}// else
		
		return usuario;
		
	}// addUsuario
	
	public Usuario updateUsuario(Long id, ClaveDto claveDto) {
		Usuario tmpUsuario = usuariosRepository.findById(id).get();
		
		//tmpUsuario.getClave().equals( claveDto.getClave()
		if( encoder.matches(claveDto.getClave(), tmpUsuario.getClave()) ) {
			tmpUsuario.setClave( encoder.encode( claveDto.getNclave() ) );
			return usuariosRepository.save(tmpUsuario);
		} else {
			tmpUsuario = null;
		}// else
		
		return tmpUsuario;
	}// updateUsuario

	public boolean validarUsuario(Usuario usuario) {
		Optional<Usuario> usr = usuariosRepository.findByEmail( usuario.getEmail() );
		Usuario tmpUsuario = null;
		
		if(usr.isPresent()) {
			tmpUsuario = usr.get();
			
			if( encoder.matches(usuario.getClave(), tmpUsuario.getClave() )) {
				return true;
				
			}//if
			
		}//if
		
		return false;
	}//validarUsuario
	
}	// class UsuarioService
