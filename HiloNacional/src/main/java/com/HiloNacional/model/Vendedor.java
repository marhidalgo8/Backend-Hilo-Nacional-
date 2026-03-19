package com.HiloNacional.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vendedor")
public class Vendedor {

    @Id
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "rfc", length = 13)
    private String rfc;

    @Column(name = "cuenta_bancaria", length = 50)
    private String cuentaBancaria;

    @Column(name = "calificacion_promedio", precision = 3, scale = 2)
    private BigDecimal calificacionPromedio;

    @Column(name = "activo")
    private Boolean activo;

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc; }

    public String getCuentaBancaria() { return cuentaBancaria; }
    public void setCuentaBancaria(String cuentaBancaria) { this.cuentaBancaria = cuentaBancaria; }

    public BigDecimal getCalificacionPromedio() { return calificacionPromedio; }
    public void setCalificacionPromedio(BigDecimal calificacionPromedio) { this.calificacionPromedio = calificacionPromedio; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
