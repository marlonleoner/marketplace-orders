package br.com.ufsm.orderapi.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;
import br.com.ufsm.orderapi.controller.dto.OrderDto;
import br.com.ufsm.orderapi.controller.form.UpdateOrderForm;
import br.com.ufsm.orderapi.model.Order;
import br.com.ufsm.orderapi.model.User;
import br.com.ufsm.orderapi.repository.OrderRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

	@Autowired
	private OrderRepository pedidoRepository;
	
	@GetMapping
	public List<OrderDto> lista(User usuario) {
		if (usuario == null) {
			List<Order> pedidos = pedidoRepository.findAll();
			return OrderDto.converter(pedidos);
		} else {
			List<Order> pedidos = pedidoRepository.findByUser(usuario);
			return OrderDto.converter(pedidos);
		}
		
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
	public ResponseEntity<OrderDto> detalhar(@PathVariable Long id) {
		Optional<Order> pedido = pedidoRepository.findById(id);
		if (pedido.isPresent()) {
			return ResponseEntity.ok(new OrderDto(pedido.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<OrderDto> atualizar(@PathVariable Long id, @RequestBody @Valid UpdateOrderForm form) {
		Optional<Order> optional = pedidoRepository.findById(id);
		if (optional.isPresent()) {
			Order p = optional.get();
			p.setTotalPrice(form.getTotalPrice());
			pedidoRepository.save(p);
			return ResponseEntity.ok(new OrderDto(p));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Order> optional = pedidoRepository.findById(id);
		if (optional.isPresent()) {
			pedidoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
