package br.com.ufsm.pedidoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufsm.pedidoapi.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
