package br.com.ufsm.order.api.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "order_id")
	@Getter
	private List<ItemOrder> products;

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

	public Order(Long userId, List<ItemOrder> products) {
		this.userId = userId;
		this.products = products;
		this.totalPrice = 0.0;
	}

	public void calculateTotalPrice() {
		for (ItemOrder io : products) {
			this.totalPrice += io.getTotalPrice();
		}
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

	@Override
	public String toString() {
		String str = "Order [" + this.id + "]\n";
		for (ItemOrder i : products) {
			str += "\tItem[" + i.getProductId() + "] Amount: " + i.getAmount() + " // TotalPrice: " + i.getTotalPrice()
					+ "\n";
		}
		str += "TotalPrice: " + this.totalPrice;

		return str;
	}

}
