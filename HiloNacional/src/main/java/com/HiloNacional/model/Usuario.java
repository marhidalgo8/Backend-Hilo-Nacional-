package com.HiloNacional.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    public Integer getIdUsuario() { 
    	return idUsuario; 
    	}
    public void setIdUsuario(Integer idUsuario) { 
    	this.idUsuario = idUsuario; 
    	}

    public String getEmail() { 
    	return email; 
    	}
    public void setEmail(String email) { 
    	this.email = email; 
    	}

    public String getPassword() { 
    	return password; 
    	}
    public void setPassword(String password) { 
    	this.password = password; 
    	}

    public String getNombre() { 
    	return nombre; 
    	}
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    	}

    public String getTelefono() { 
    	return telefono; 
    	}
    public void setTelefono(String telefono) { 
    	this.telefono = telefono; 
    	}

    public LocalDateTime getFechaRegistro() { 
    	return fechaRegistro; 
    	}
    public void setFechaRegistro(LocalDateTime fechaRegistro) { 
    	this.fechaRegistro = fechaRegistro; 
    	}
}