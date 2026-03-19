package com.HiloNacional.Repository;

import java.util.Optional;

import com.HiloNacional.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<Producto, Long>{
	Optional<Producto> findByNombre(String nombre);
}
