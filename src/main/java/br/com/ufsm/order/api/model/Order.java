package br.com.ufsm.order.api.model;

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
@NoArgsConstructor
public class Order {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order")
	@Getter
	private Long idOrder;
	
	@Column(name = "user")
	@Getter
	@Setter
	private User user;
	
	@Column(name = "products")
	@Getter
	@Setter
	private List<Product> products;
	
	@Column(name = "total_price")
	@Getter
	@Setter
	private Double totalPrice;
	
	@Column(name = "created_at")
	@Getter
	private LocalDateTime createdAt = LocalDateTime.now();
	
	
	public Order(User user, List<Product> products) {
		this.user = user;
		this.products = products;
		this.totalPrice = orderPrice();
	}
	
	private Double orderPrice() {
		Double orderPrice = 0.0;
		for(int i=0; i<products.size(); i++) {
			orderPrice += products.get(i).getPrice() * products.get(i).getQuantity();
		}
		return orderPrice;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Order)) return false;
		Order order = (Order) o;
		return Objects.equals(this.idOrder, order.idOrder);
	}

}
