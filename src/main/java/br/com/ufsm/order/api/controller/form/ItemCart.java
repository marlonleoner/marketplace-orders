package br.com.ufsm.order.api.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemCart {

	private Long id;

	private Integer amount;
}
