package br.com.ufsm.orderapi.clients;

import java.util.LinkedList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ufsm.orderapi.model.User;

@FeignClient(name="UsuarioClient", url="http://localhost:8899/usuarios")
public interface UserClient {

	public boolean auditable = true;

	@GetMapping(value = "/{email}")
    LinkedList<User> findAll(@PathVariable String email);
	
}
