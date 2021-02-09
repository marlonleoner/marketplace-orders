package br.com.ufsm.order.api.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Cart {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_carrinho")
	@Getter
	private Long idCarrinho;
	
	@Column(name = "usuario")
	@Getter
	private User usuario;
	
	@Column(name = "produtos")
	@Getter
	private List<Product> produtos;
	
	public Cart(User usuario, List<Product> produtos) {
		this.usuario = usuario;
		this.produtos = produtos;
	}
	
	public void AddProduto(Product p) {
		this.produtos.add(p);
	}
	
	public void RemoveProduto(Product p) {
		this.produtos.remove(p);
	}
	
	//TODO verificar usuario e disponibilidades
	public Order FinalizarCompra() {
		return new Order(this.usuario, this.produtos);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Cart)) return false;
		Cart carinho = (Cart) o;
		return Objects.equals(this.idCarrinho, carinho.idCarrinho);
	}
	
}
