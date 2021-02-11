package br.com.ufsm.order.api.clients;

import java.util.HashMap;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ufsm.order.api.controller.form.DecreaseAmount;
import br.com.ufsm.order.api.controller.form.ProductRequest;
import br.com.ufsm.order.api.controller.form.ProductResponse;
import feign.Headers;

@FeignClient(name = "ProdutoClient", url = "http://localhost:8091/products")
public interface ProdutClient {

	@RequestMapping(value = "/available", method = RequestMethod.POST)
	@Headers("Content-Type: " + MediaType.APPLICATION_JSON_VALUE)
	List<ProductResponse> verify(@RequestBody HashMap<String, List<ProductRequest>> formProducts);

	@RequestMapping(value = "/{productId}/amount", method = RequestMethod.PUT)
	@Headers("Content-Type: " + MediaType.APPLICATION_JSON_VALUE)
	void decrement(@PathVariable("productId") Long productId, @RequestBody DecreaseAmount form);

}
