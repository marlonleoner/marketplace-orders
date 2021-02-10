package br.com.ufsm.order.api.clients;

import lombok.Data;

@Data
public class ProductResponse {

	private Long id;

	private Double price;

	private Integer amount;

	private Double total;
}
