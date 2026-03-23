package com.HiloNacional.Service;

import java.util.List;

import com.HiloNacional.model.Pedido;
import com.HiloNacional.model.Pedido.EstadoPedido;
import com.HiloNacional.Repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidosRepository pedidosRepository;

    @Autowired
    public PedidoService(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }

    public List<Pedido> getPedidos() {
        return pedidosRepository.findAll();
    }

    public Pedido getPedido(Long id) {
        return pedidosRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El pedido con el id [" + id + "] no existe"));
    }

    public List<Pedido> getPedidosPorComprador(Long compradorId) {
        return pedidosRepository.findByIdUsuarioCompradorIdUsuario(compradorId);
    }

    public List<Pedido> getPedidosPorEstado(EstadoPedido estado) {
        return pedidosRepository.findByEstado(estado);
    }

    public Pedido addPedido(Pedido pedido) {
        pedido.setEstado(EstadoPedido.PENDIENTE);
        return pedidosRepository.save(pedido);
    }

    public Pedido updateEstadoPedido(Long id, EstadoPedido nuevoEstado) {
        Pedido tmpPedido = pedidosRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El pedido con el id [" + id + "] no existe"));

        tmpPedido.setEstado(nuevoEstado);
        return pedidosRepository.save(tmpPedido);
    }

    public Pedido deletePedido(Long id) {
        Pedido tmpPedido = null;

        if (pedidosRepository.existsById(id)) {
            tmpPedido = pedidosRepository.findById(id).get();
            pedidosRepository.deleteById(id);
        }

        return tmpPedido;
    }

} // class PedidoService