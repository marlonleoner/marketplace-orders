package br.com.ufsm.order.api.controller.dto;

import br.com.ufsm.order.api.model.Cart;
import lombok.Getter;

@Getter
public class CartDto {

	private Long productId;

	private Integer amount;

	public CartDto(Cart cart) {
		this.productId = cart.getProductId();
		this.amount = cart.getAmount();
	}
}
