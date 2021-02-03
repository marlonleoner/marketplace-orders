package br.com.ufsm.pedidoapi.controller.form;

import java.util.LinkedList;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PedidoForm {

	@NotNull @NotEmpty @Email
	private String emailUsuario;
	
	@NotNull @NotEmpty
	private String senha;
	
	@NotNull @NotEmpty
	private LinkedList<String> nomeProdutos;
	
	@NotNull  @Positive
	private LinkedList<Integer> quantidadeProdutos;
	
}
