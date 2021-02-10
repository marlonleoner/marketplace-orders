package br.com.ufsm.order.api.controller;

import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufsm.order.api.controller.dto.OrderDto;
import br.com.ufsm.order.api.controller.form.UpdateOrderForm;
import br.com.ufsm.order.api.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public List<OrderDto> find(@RequestParam(defaultValue = "") String email) {
		return OrderDto.converter(orderService.find(email));
	}
	
	/*
	@PostMapping
	@Transactional
	public ResponseEntity<OrderDto> cadastrar(@RequestBody @Valid OrderForm form, UriComponentsBuilder uriBuilder) throws MethodArgumentNotValidException, ParamInvalidoException {
		FormPedidoServices formServices = new FormPedidoServices();
		Order pedido = formServices.criar(form);
		pedidoRepository.save(pedido);
		
		URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getIdPedido()).toUri();
		return ResponseEntity.created(uri).body(new OrderDto(pedido));
	}
	*/
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDto> get(@PathVariable Long id) {
		return ResponseEntity.ok(new OrderDto(orderService.findById(id)));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<OrderDto> update(@PathVariable Long id, @RequestBody @Valid UpdateOrderForm form) {
		return ResponseEntity.ok(new OrderDto(orderService.update(id, form)));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		orderService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
