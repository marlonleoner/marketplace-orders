package br.com.ufsm.order.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufsm.order.api.clients.ProdutClient;
import br.com.ufsm.order.api.controller.form.ItemCart;
import br.com.ufsm.order.api.exceptions.ObjectNotFoundException;
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

		List<ItemCart> items = verifyCart(user);
		for (ItemCart i : items) {
			Order order = new Order();

			orderRepository.save(order);
		}

		return null;
	}

	private List<ItemCart> verifyCart(User user) {
		HashMap<String, List<ItemCart>> map = new HashMap<String, List<ItemCart>>();

		List<ItemCart> items = new ArrayList<>();
		cartService.findByUser(user.getId()).forEach(c -> items.add(new ItemCart(c.getProductId(), c.getAmount())));

		map.put("products", items);

		produtClient.verify(map);

		return items;
	}

}
