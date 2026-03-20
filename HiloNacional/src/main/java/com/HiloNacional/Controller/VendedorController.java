package com.HiloNacional.Controller;

import com.HiloNacional.model.Vendedor;
import com.HiloNacional.Service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/HiloNacional/vendedor/")
public class VendedorController {

    private final VendedorService vendedorService;

    @Autowired
    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping
    public List<Vendedor> getVendedores() {
        return vendedorService.mostrarTodo();
    }

    @GetMapping(path="{id}")
    public Optional<Vendedor> getVendedor(@PathVariable("id") Long id) {
        return vendedorService.buscarId(id);
    }

    @PostMapping
    public Vendedor addVendedor(@RequestBody Vendedor vendedor) {
        return vendedorService.guardar(vendedor);
    }

    @PutMapping(path="{id}")
    public Vendedor updateVendedor(@PathVariable("id") Long id, @RequestBody Vendedor vendedor) {
        vendedor.setIdUsuario(id);
        return vendedorService.guardar(vendedor);
    }

    @DeleteMapping(path="{id}")
    public void deleteVendedor(@PathVariable("id") Long id) {
        vendedorService.eliminar(id);
    }
}
