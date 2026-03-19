package com.HiloNacional.Controller;

import java.util.List;

import com.HiloNacional.model.Pedido;
import com.HiloNacional.model.Pedido.EstadoPedido;
import com.HiloNacional.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/HiloNacional/pedidos/")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    } // constructor



    @GetMapping
    public List<Pedido> getPedidos() {
        return pedidoService.getPedidos();
    } // getPedidos


  
    @GetMapping(path = "{pedidoId}")
    public Pedido getPedido(@PathVariable("pedidoId") Long id) {
        return pedidoService.getPedido(id);
    } // getPedido



    @GetMapping(path = "comprador/{compradorId}")
    public List<Pedido> getPedidosPorComprador(@PathVariable("compradorId") Long compradorId) {
        return pedidoService.getPedidosPorComprador(compradorId);
    } // getPedidosPorComprador


  
    @GetMapping(path = "estado")
    public List<Pedido> getPedidosPorEstado(
            @RequestParam(value = "valor") EstadoPedido estado) {
        return pedidoService.getPedidosPorEstado(estado);
    } // getPedidosPorEstado


 
    @PostMapping
    public Pedido addPedido(@RequestBody Pedido pedido) {
        return pedidoService.addPedido(pedido);
    } // addPedido


  
    @PutMapping(path = "{pedidoId}")
    public Pedido updateEstadoPedido(@PathVariable("pedidoId") Long id,
            @RequestParam(value = "estado") EstadoPedido nuevoEstado) {
        return pedidoService.updateEstadoPedido(id, nuevoEstado);
    } // updateEstadoPedido


  
    @DeleteMapping(path = "{pedidoId}")
    public Pedido deletePedido(@PathVariable("pedidoId") Long id) {
        return pedidoService.deletePedido(id);
    } // deletePedido

} // class PedidoController