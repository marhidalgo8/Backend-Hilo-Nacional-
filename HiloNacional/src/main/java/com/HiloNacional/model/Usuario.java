package com.HiloNacional.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "clave", nullable = false, length = 255)
    private String clave;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @CreationTimestamp                                       
    @Column(name = "fecha_registro", updatable = false) 
    private LocalDateTime fechaRegistro;

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", email=" + email + ", clave=" + clave + ", nombre=" + nombre
                + ", telefono=" + telefono + ", fechaRegistro=" + fechaRegistro + "]";
    }

}

