package br.com.ufsm.order.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufsm.order.api.clients.ProdutClient;
import br.com.ufsm.order.api.controller.form.ProductRequest;
import br.com.ufsm.order.api.controller.form.ProductResponse;
import br.com.ufsm.order.api.exceptions.ObjectNotFoundException;
import br.com.ufsm.order.api.model.ItemOrder;
import br.com.ufsm.order.api.model.Order;
import br.com.ufsm.order.api.model.User;
import br.com.ufsm.order.api.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartService cartService;

	@Autowired
	private AuthService authService;

	@Autowired
	private ProdutClient produtClient;

	public List<Order> findAll() {
		User user = authService.getAuthenticatedUser();

		return orderRepository.findByUserId(user.getId());
	}

	public Order findById(Long id) {
		User user = authService.getAuthenticatedUser();

		Optional<Order> order = orderRepository.findByIdAndUserId(id, user.getId());
		if (!order.isPresent()) {
			throw new ObjectNotFoundException("Pedido não encontrado.");
		}

		return order.get();
	}

	public Order create() {
		User user = authService.getAuthenticatedUser();

		List<ItemOrder> products = verifyCart(user).stream().map(ItemOrder::new).collect(Collectors.toList());

		Order order = new Order(user.getId(), products);
		order.calculateTotalPrice();

		orderRepository.save(order);

		cartService.clear();

		return order;
	}

	private List<ProductResponse> verifyCart(User user) {
		HashMap<String, List<ProductRequest>> map = new HashMap<String, List<ProductRequest>>();

		List<ProductRequest> items = new ArrayList<>();
		cartService.findByUser(user.getId())
				.forEach(c -> items.add(new ProductRequest(c.getProductId(), c.getAmount())));

		map.put("products", items);

		return produtClient.verify(map);
	}

}
