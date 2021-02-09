package br.com.ufsm.order.api.controller.form;

import java.util.LinkedList;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderForm {

	@NotNull @NotEmpty @Email
	private String email;
	
	@NotNull @NotEmpty
	private String password;
	
	@NotNull @NotEmpty
	private LinkedList<String> nameProducts;
	
	@NotNull  @Positive
	private LinkedList<Integer> quantityProducts;
	
}
