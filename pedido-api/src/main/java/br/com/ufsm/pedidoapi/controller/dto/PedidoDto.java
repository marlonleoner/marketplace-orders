package br.com.ufsm.pedidoapi.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ufsm.pedidoapi.modelo.Pedido;
import br.com.ufsm.pedidoapi.modelo.Produto;
import br.com.ufsm.pedidoapi.modelo.Usuario;
import lombok.Data;

@Data
public class PedidoDto {

	private Long id;
	private Double precoTotal;
	private Usuario usuario;
	private List<Produto> produtos;
	
	public PedidoDto(Pedido pedido) {
		this.id = pedido.getIdPedido();
		this.precoTotal = pedido.getPrecoTotal();
		this.usuario = pedido.getUsuario();
		this.produtos = pedido.getProdutos();
	}

	public static List<PedidoDto> converter(List<Pedido> pedidos) {
		return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
	}

}
