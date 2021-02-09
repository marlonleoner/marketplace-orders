package br.com.ufsm.order.api.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ufsm.order.api.model.Order;
import br.com.ufsm.order.api.model.Product;
import br.com.ufsm.order.api.model.User;
import lombok.Data;

@Data
public class OrderDto {

	private Long id;
	private Double totalPrice;
	private User user;
	private List<Product> products;
	
	public OrderDto(Order pedido) {
		this.id = pedido.getIdOrder();
		this.totalPrice = pedido.getTotalPrice();
		this.user = pedido.getUser();
		this.products = pedido.getProducts();
	}

	public static List<OrderDto> converter(List<Order> pedidos) {
		return pedidos.stream().map(OrderDto::new).collect(Collectors.toList());
	}

}