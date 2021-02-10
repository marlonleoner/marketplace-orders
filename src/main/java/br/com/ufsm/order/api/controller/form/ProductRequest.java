package br.com.ufsm.order.api.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {

	private Long id;

	private Integer amount;
}
