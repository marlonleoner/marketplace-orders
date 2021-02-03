package br.com.ufsm.pedidoapi.modelo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Pedido {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Long idPedido;
	
	@Column(name = "usuario")
	private Usuario usuario;
	
	@Column(name = "produtos")
	private List<Produto> produtos;
	
	@Column(name = "preco_total")
	private Double precoTotal;
	
	@Column(name = "data_pedido")
	private LocalDateTime dataPedido = LocalDateTime.now();
	
	
	public Pedido(Usuario usuario, List<Produto> produtos) {
		this.usuario = usuario;
		this.produtos = produtos;
		this.precoTotal = precoPedido();
	}
	
	private Double precoPedido() {
		Double precoPedido = 0.0;
		for(int i=0; i<produtos.size(); i++) {
			precoPedido += produtos.get(i).getPreco() * produtos.get(i).getQuantidade();
		}
		return precoPedido;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pedido)) return false;
		Pedido pedido = (Pedido) o;
		return Objects.equals(this.idPedido, pedido.idPedido);
	}

}
