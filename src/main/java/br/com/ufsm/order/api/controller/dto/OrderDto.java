package br.com.ufsm.order.api.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ufsm.order.api.model.Order;
import lombok.Data;

@Data
public class OrderDto {

	private Long id;

	private List<ProductDto> products;

	private Double totalPrice;

	private Long user;

	public OrderDto(Order order) {
		this.id = order.getId();
		this.totalPrice = order.getTotalPrice();
		this.products = order.getProducts().stream().map(ProductDto::new).collect(Collectors.toList());
		this.user = order.getUserId();
	}

	public static List<OrderDto> convert(List<Order> orders) {
		return orders.stream().map(OrderDto::new).collect(Collectors.toList());
	}

}
