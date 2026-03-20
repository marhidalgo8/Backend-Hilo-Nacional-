package com.HiloNacional.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comprador")
public class Comprador {

    @Id
    @Column(name = "id_usuario")
    private Long idUsuario;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "direccion_envio", columnDefinition = "TEXT")
    private String direccionEnvio;

    @Column(name = "cp", length = 10)
    private String cp;

    @Column(name = "puntos_fidelidad")
    private Long puntosFidelidad;

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long id) {
        this.idUsuario = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Long getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public void setPuntosFidelidad(Long puntosFidelidad) {
        this.puntosFidelidad = puntosFidelidad;
    }
}
