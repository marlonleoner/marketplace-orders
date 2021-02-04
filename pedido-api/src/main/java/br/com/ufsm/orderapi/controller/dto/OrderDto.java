package br.com.ufsm.orderapi.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ufsm.orderapi.model.Order;
import br.com.ufsm.orderapi.model.Product;
import br.com.ufsm.orderapi.model.User;
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
