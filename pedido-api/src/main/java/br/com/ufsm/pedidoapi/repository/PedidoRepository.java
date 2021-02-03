package br.com.ufsm.pedidoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufsm.pedidoapi.modelo.Pedido;
import br.com.ufsm.pedidoapi.modelo.Usuario;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findByUsuario(Usuario usuario);
	
}
