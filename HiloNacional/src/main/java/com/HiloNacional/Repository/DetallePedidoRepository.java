package com.HiloNacional.Repository;


import com.HiloNacional.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
}
