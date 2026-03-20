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
    public Optional<DetallePedido> getDetallePedido(@PathVariable("id") Long id) {  // Integer → Long
        return detallePedidoService.buscarId(id);  // __buscarId__ → buscarId
    }

    @PostMapping
    public DetallePedido addDetallePedido(@RequestBody DetallePedido detallePedido) {
        return detallePedidoService.guardar(detallePedido);
    }

    @PutMapping(path="{id}")
    public DetallePedido updateDetallePedido(@PathVariable("id") Long id, @RequestBody DetallePedido detallePedido) {  // Integer → Long
        detallePedido.setIdDetalle(id);  // __setIdDetalle__ → setIdDetalle
        return detallePedidoService.guardar(detallePedido);
    }

    @DeleteMapping(path="{id}")
    public void deleteDetallePedido(@PathVariable("id") Long id) {  // Integer → Long
        detallePedidoService.eliminar(id);  // __eliminar__ → eliminar
    }
}