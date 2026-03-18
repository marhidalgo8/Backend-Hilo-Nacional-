package com.HiloNacional.Controller;



import com.HiloNacional.model.DetallePedido;
import com.HiloNacional.Service.DetallePedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/HiloNacional/detalle-pedido/")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    @Autowired
    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public List<DetallePedido> getDetallesPedidos() {
        return detallePedidoService.mostrarTodo();
    }

    @GetMapping(path="{id}")
    public Optional<DetallePedido> getDetallePedido(@PathVariable("id") Integer id) {
        return detallePedidoService.buscarId(id);
    }

    @PostMapping
    public DetallePedido addDetallePedido(@RequestBody DetallePedido detallePedido) {
        return detallePedidoService.guardar(detallePedido);
    }

    @PutMapping(path="{id}")
    public DetallePedido updateDetallePedido(@PathVariable("id") Integer id, @RequestBody DetallePedido detallePedido) {
        detallePedido.setIdDetalle(id);
        return detallePedidoService.guardar(detallePedido);
    }

    @DeleteMapping(path="{id}")
    public void deleteDetallePedido(@PathVariable("id") Integer id) {
        detallePedidoService.eliminar(id);
    }
}
