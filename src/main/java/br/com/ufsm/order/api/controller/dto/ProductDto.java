package br.com.ufsm.order.api.controller.dto;

import br.com.ufsm.order.api.model.ItemOrder;
import lombok.Data;

@Data
public class ProductDto {

	private Long productId;

	private Double productPrice;

	private Integer amount;

	private Double totalPrice;

	public ProductDto(ItemOrder item) {
		this.productId = item.getProductId();
		this.productPrice = item.getProductPrice();
		this.amount = item.getAmount();
		this.totalPrice = item.getTotalPrice();
	}
}
