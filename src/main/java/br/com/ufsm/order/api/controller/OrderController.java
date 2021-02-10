package br.com.ufsm.order.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufsm.order.api.controller.dto.OrderDto;
import br.com.ufsm.order.api.service.OrderService;

@RestController
@RequestMapping("/pedidos")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<OrderDto> findAll() {
		return OrderDto.convert(orderService.findAll());
	}

	@GetMapping("/{id}")
	public OrderDto get(@PathVariable Long id) {
		return new OrderDto(orderService.findById(id));
	}

	@PostMapping("/finalizar")
	public ResponseEntity<OrderDto> create() {
		return ResponseEntity.ok(new OrderDto(orderService.create()));
	}

}
