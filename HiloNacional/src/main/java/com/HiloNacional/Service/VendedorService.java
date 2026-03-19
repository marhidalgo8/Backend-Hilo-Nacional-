package com.HiloNacional.Service;

import com.HiloNacional.model.Vendedor;
import com.HiloNacional.Repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    @Autowired
    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public List<Vendedor> mostrarTodo() {
        return vendedorRepository.findAll();
    }

    public Optional<Vendedor> buscarId(Integer id) {
        return vendedorRepository.findById(id);
    }

    public Vendedor guardar(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public void eliminar(Integer id) {
        vendedorRepository.deleteById(id);
    }
}
