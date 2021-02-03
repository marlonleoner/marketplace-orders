package br.com.ufsm.pedidoapi.clients;

import java.util.LinkedList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ufsm.pedidoapi.modelo.Usuario;

@FeignClient(name="UsuarioClient", url="http://localhost:8899/usuarios")
public interface UsuarioClient {

	public boolean auditable = true;

	@GetMapping(value = "/{email}")
    LinkedList<Usuario> findAll(@PathVariable String email);
	
}
