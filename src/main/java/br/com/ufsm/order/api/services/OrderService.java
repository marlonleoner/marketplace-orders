package br.com.ufsm.order.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ufsm.order.api.controller.form.UpdateOrderForm;
import br.com.ufsm.order.api.exceptions.ObjectNotFoundException;
import br.com.ufsm.order.api.model.Order;
import br.com.ufsm.order.api.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Order> find(String email) {
		List<Order> orders = orderRepository.findAll();
		if(email != null) {
			//TODO verificar user pelo email
		}
				
		return orders;
	}
	
	public Order findById(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		if (!order.isPresent()) {
			throw new ObjectNotFoundException("Pedido não encontrado.");
		}

		return order.get();
	}

	public Order update(Long id, @Valid UpdateOrderForm form) {
		Optional<Order> orderExists = orderRepository.findById(id);
		if (!orderExists.isPresent()) {
			throw new ObjectNotFoundException("Pedido não encontrado.");
		}

		Order order = orderExists.get();
		order.setTotalPrice(form.getTotalPrice());
		order.setUpdatedAt(LocalDateTime.now());

		return order;
	}

	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Order> productExists = orderRepository.findById(id);
		if (!productExists.isPresent()) {
			throw new ObjectNotFoundException("Pedido não encontrado.");
		}

		orderRepository.deleteById(id);

		return ResponseEntity.ok().build();
	}
	
}
