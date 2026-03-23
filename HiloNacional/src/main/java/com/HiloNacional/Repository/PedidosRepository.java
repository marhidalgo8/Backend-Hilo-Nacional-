package com.HiloNacional.Repository;

import com.HiloNacional.model.Pedido;
import com.HiloNacional.model.Pedido.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidosRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByIdUsuarioCompradorIdUsuario(Long compradorId); // ✅ Integer → Long

    List<Pedido> findByEstado(EstadoPedido estado);


}