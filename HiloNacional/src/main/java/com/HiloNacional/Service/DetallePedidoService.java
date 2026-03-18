package com.HiloNacional.Service;


import com.HiloNacional.model.DetallePedido;
import com.HiloNacional.Repository.DetallePedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;

    @Autowired
    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    public List<DetallePedido> mostrarTodo() {
        return detallePedidoRepository.findAll();
    }

    public Optional<DetallePedido> buscarId(Integer id) {
        return detallePedidoRepository.findById(id);
    }

    public DetallePedido guardar(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    public void eliminar(Integer id) {
        detallePedidoRepository.deleteById(id);
    }
}

