package br.com.ufsm.order.api.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CART_ID")
	@TableGenerator(name = "CART_ID", table = "GENERATOR_TABLE", pkColumnName = "CART_KEY", valueColumnName = "CART_KEY_NEXT", pkColumnValue = "cart", allocationSize = 1)
	@Column(name = "id")
	@Getter
	private Long id;

	@Column(name = "user_id")
	@Getter
	private Long userId;

	@Column(name = "product_id")
	@Getter
	private Long productId;

	@Column(name = "amount")
	@Getter
	@Setter
	private Integer amount;

	@Column(name = "created_at")
	@Getter
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(name = "updated_at")
	@Getter
	@Setter
	private LocalDateTime updatedAt = LocalDateTime.now();

	public Cart(Long userId, Long productId, Integer amount) {
		this.userId = userId;
		this.productId = productId;
		this.amount = amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Cart))
			return false;
		Cart carinho = (Cart) o;
		return Objects.equals(this.id, carinho.id);
	}

	@Override
	public String toString() {
		return " > [Carrinho] Produto: " + this.productId + " // Quantidade: " + this.amount;
	}

}
