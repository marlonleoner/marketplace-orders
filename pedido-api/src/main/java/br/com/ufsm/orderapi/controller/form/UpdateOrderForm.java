package br.com.ufsm.orderapi.controller.form;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateOrderForm {

	@NotNull
	private Double totalPrice;
	
}
