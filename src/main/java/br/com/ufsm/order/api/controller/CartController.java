package br.com.ufsm.order.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufsm.order.api.controller.dto.CartDto;
import br.com.ufsm.order.api.controller.form.ItemCart;
import br.com.ufsm.order.api.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping
	public List<CartDto> get() {
		return cartService.get().stream().map(CartDto::new).collect(Collectors.toList());
	}

	@PostMapping("/update")
	@Transactional
	public CartDto createOrUpdate(@RequestBody ItemCart form) {
		return new CartDto(cartService.createOrUpdate(form));
	}

	@PostMapping("/clear")
	@Transactional
	public ResponseEntity<?> clear() {
		cartService.clear();
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/remove/{id}")
	@Transactional
	public ResponseEntity<?> remove(@PathVariable Long id) {
		cartService.remove(id);

		return ResponseEntity.ok().build();
	}

}
