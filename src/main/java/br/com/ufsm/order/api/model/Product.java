package br.com.ufsm.order.api.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
public class Product {
	
	@Id
	@Column(name = "id_produto")
	@Getter
	private Long idProduct;
	
	@Column(name = "preco_produto")
	@Getter
	@Setter
	private Double price;
	
	@Column(name = "quantidade")
	@Getter
	@Setter
	private Integer quantity;
	
	public Product(Long id, Double preco, Integer quantidade) {
		this.idProduct = id;
		this.price = preco;
		this.quantity = quantidade;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product)) return false;
		Product product = (Product) o;
		return Objects.equals(this.idProduct, product.idProduct);
	}

}
