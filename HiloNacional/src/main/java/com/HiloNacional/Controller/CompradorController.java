package com.HiloNacional.Controller;

import com.HiloNacional.model.Comprador;
import com.HiloNacional.Service.CompradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/HiloNacional/comprador/")
public class CompradorController {

    private final CompradorService compradorService;

    @Autowired
    public CompradorController(CompradorService compradorService) {
        this.compradorService = compradorService;
    }

    @GetMapping
    public List<Comprador> getCompradores() {
        return compradorService.mostrarTodo();
    }

    @GetMapping(path="{id}")
    public Optional<Comprador> getComprador(@PathVariable("id") Integer id) {
        return compradorService.buscarId(id);
    }

    @PostMapping
    public Comprador addComprador(@RequestBody Comprador comprador) {
        return compradorService.guardar(comprador);
    }

    @PutMapping(path="{id}")
    public Comprador updateComprador(@PathVariable("id") Integer id, @RequestBody Comprador comprador) {
        comprador.setIdUsuario(id);
        return compradorService.guardar(comprador);
    }

    @DeleteMapping(path="{id}")
    public void deleteComprador(@PathVariable("id") Integer id) {
        compradorService.eliminar(id);
    }
}
