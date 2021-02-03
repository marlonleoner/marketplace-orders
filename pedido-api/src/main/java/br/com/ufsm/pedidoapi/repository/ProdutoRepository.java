package br.com.ufsm.pedidoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufsm.pedidoapi.modelo.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
