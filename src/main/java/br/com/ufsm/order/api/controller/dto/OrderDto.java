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

	private UserDto user;

	public OrderDto(Order pedido) {
		this.id = pedido.getId();
		this.totalPrice = pedido.getTotalPrice();
	}

	public static List<OrderDto> convert(List<Order> orders) {
		return orders.stream().map(OrderDto::new).collect(Collectors.toList());
	}

}
