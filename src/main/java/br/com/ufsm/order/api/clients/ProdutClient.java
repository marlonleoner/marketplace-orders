package br.com.ufsm.order.api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ufsm.order.api.model.Product;

@FeignClient(name="ProdutoClient", url="http://localhost:8889/produtos")
public interface ProdutClient {

	public boolean auditable = true;

	@GetMapping(value = "/{name}")
    Product findOne(@PathVariable String name);
	
	@PutMapping(value = "/updatedisponibility/{id}")
	void atualizar(@PathVariable Long id, @RequestBody Integer disponibility);
	
}
