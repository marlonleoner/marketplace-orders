package br.com.ufsm.pedidoapi.controller.form;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AtualizacaoPedidoForm {

	@NotNull
	private Double precoTotal;
	
}
