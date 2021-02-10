package br.com.ufsm.order.api.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ORDER_ID")
	@TableGenerator(name = "ORDER_ID", table = "GENERATOR_TABLE", pkColumnName = "ORDER_KEY", valueColumnName = "ORDER_KEY_NEXT", pkColumnValue = "order", allocationSize = 1)
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

	@Column(name = "total_price")
	@Getter
	@Setter
	private Double totalPrice;

	@Column(name = "created_at")
	@Getter
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(name = "updated_at")
	@Getter
	@Setter
	private LocalDateTime updatedAt = LocalDateTime.now();

	public Order(Long userId, Long productId, Integer amount) {
		
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Order))
			return false;
		Order order = (Order) o;
		return Objects.equals(this.id, order.id);
	}

}
