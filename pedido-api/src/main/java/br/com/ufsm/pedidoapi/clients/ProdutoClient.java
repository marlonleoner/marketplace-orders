package br.com.ufsm.pedidoapi.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ufsm.pedidoapi.modelo.Produto;

@FeignClient(name="ProdutoClient", url="http://localhost:8889/produtos")
public interface ProdutoClient {

	public boolean auditable = true;

	@GetMapping(value = "/{nome}")
    Produto findOne(@PathVariable String nome);
	
	@PutMapping(value = "/atualizardisponibilidade/{id}")
	void atualizar(@PathVariable Long id, @RequestBody Integer disponibilidade);
	
}
