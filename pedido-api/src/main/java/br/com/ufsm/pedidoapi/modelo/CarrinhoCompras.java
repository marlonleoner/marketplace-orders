package br.com.ufsm.pedidoapi.modelo;

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
public class CarrinhoCompras {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_carrinho")
	private Long idCarrinho;
	
	@Column(name = "usuario")
	private Usuario usuario;
	
	@Column(name = "produtos")
	private List<Produto> produtos;
	
	public CarrinhoCompras(Usuario usuario, List<Produto> produtos) {
		this.usuario = usuario;
		this.produtos = produtos;
	}
	
	public void AddProduto(Produto p) {
		this.produtos.add(p);
	}
	
	public void RemoveProduto(Produto p) {
		this.produtos.remove(p);
	}
	
	//TODO verificar usuario e disponibilidades
	public Pedido FinalizarCompra() {
		return new Pedido(this.usuario, this.produtos);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CarrinhoCompras)) return false;
		CarrinhoCompras carinho = (CarrinhoCompras) o;
		return Objects.equals(this.idCarrinho, carinho.idCarrinho);
	}
	
}
