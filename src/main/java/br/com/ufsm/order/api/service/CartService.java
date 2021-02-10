package br.com.ufsm.order.api.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufsm.order.api.clients.ProdutClient;
import br.com.ufsm.order.api.controller.form.ItemCart;
import br.com.ufsm.order.api.model.Cart;
import br.com.ufsm.order.api.model.User;
import br.com.ufsm.order.api.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProdutClient produtClient;

	@Autowired
	private AuthService authService;

	public List<Cart> findByUser(Long id) {
		return cartRepository.findByUserId(id);
	}

	public List<Cart> get() {
		User user = authService.getAuthenticatedUser();

		return cartRepository.findByUserId(user.getId());
	}

	public Cart createOrUpdate(ItemCart form) {
		User user = authService.getAuthenticatedUser();

		verifyDisponibility(form);

		Optional<Cart> cartExists = cartRepository.findByUserIdAndProductId(user.getId(), form.getId());
		Cart cart = null;
		if (cartExists.isPresent()) {
			cart = cartExists.get();

			cart.setAmount(form.getAmount());
			cart.setUpdatedAt(LocalDateTime.now());
		} else {
			cart = new Cart(user.getId(), form.getId(), form.getAmount());
		}

		cartRepository.save(cart);

		return cart;
	}

	private void verifyDisponibility(ItemCart form) {
		HashMap<String, List<ItemCart>> map = new HashMap<String, List<ItemCart>>();
		map.put("products", Arrays.asList(form));
		produtClient.verify(map);
	}

	public void clear() {
		User user = authService.getAuthenticatedUser();

		cartRepository.deleteByUserId(user.getId());
	}

	public void remove(Long id) {
		User user = authService.getAuthenticatedUser();

		cartRepository.deleteByUserIdAndProductId(user.getId(), id);
	}

}
