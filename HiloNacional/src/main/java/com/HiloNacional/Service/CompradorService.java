package com.HiloNacional.Service;

import com.HiloNacional.model.Comprador;
import com.HiloNacional.Repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompradorService {

    private final CompradorRepository compradorRepository;

    @Autowired
    public CompradorService(CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;
    }

    public List<Comprador> mostrarTodo() {
        return compradorRepository.findAll();
    }

    public Optional<Comprador> buscarId(Long id) {
        return compradorRepository.findById(id);
    }

    public Comprador guardar(Comprador comprador) {
        return compradorRepository.save(comprador);
    }

    public void eliminar(Long id) {
        compradorRepository.deleteById(id);
    }
}
