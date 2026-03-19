package com.HiloNacional.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
public class Pedido {

    public enum EstadoPedido {
        PENDIENTE, ENVIADO, ENTREGADO, CANCELADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_usuario_comprador", nullable = false)
    private Usuario idUsuarioComprador;

    @Column(name = "fecha_pedido")
    private LocalDateTime fechaPedido;

    @Column(name = "total")
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoPedido estado;

    @Column(name = "direccion_envio", columnDefinition = "TEXT")
    private String direccionEnvio;

    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    public Usuario getIdUsuarioComprador() { return idUsuarioComprador; }
    public void setIdUsuarioComprador(Usuario idUsuarioComprador) { this.idUsuarioComprador = idUsuarioComprador; }

    public LocalDateTime getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(LocalDateTime fechaPedido) { this.fechaPedido = fechaPedido; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }

    public String getDireccionEnvio() { return direccionEnvio; }
    public void setDireccionEnvio(String direccionEnvio) { this.direccionEnvio = direccionEnvio; }

    public Usuario getComprador() { return idUsuarioComprador; }
    public void setComprador(Usuario comprador) { this.idUsuarioComprador = comprador; }
}
