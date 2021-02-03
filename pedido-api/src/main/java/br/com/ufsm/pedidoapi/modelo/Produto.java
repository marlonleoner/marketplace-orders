package br.com.ufsm.pedidoapi.modelo;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter @NoArgsConstructor
public class Produto {
	
	@Id
	@Column(name = "id_produto")
	private Long idProduto;
	
	@Column(name = "preco_produto")
	private Double preco;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	public Produto(Long id, Double preco, Integer quantidade) {
		this.idProduto = id;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Produto)) return false;
		Produto Produto = (Produto) o;
		return Objects.equals(this.idProduto, Produto.idProduto);
	}

}
