package br.com.ufsm.order.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import br.com.ufsm.order.api.controller.form.ProductResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "orders_products")
public class ItemOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "IO_ID")
	@TableGenerator(name = "IO_ID", table = "GENERATOR_TABLE", pkColumnName = "IO_KEY", valueColumnName = "IO_KEY_NEXT", pkColumnValue = "IO", allocationSize = 1)
	@Column(name = "id")
	@Getter
	private Long id;

	@Column(name = "products_id")
	@Getter
	private Long productId;

	@Column(name = "product_price")
	@Getter
	private Double productPrice;

	@Column(name = "amount")
	@Getter
	private Integer amount;

	@Column(name = "total_price")
	@Getter
	private Double totalPrice;

	public ItemOrder(Long productId, Double productPrice, Integer amount) {
		this.productId = productId;
		this.productPrice = productPrice;
		this.amount = amount;
		this.totalPrice = productPrice * amount;
	}

	public ItemOrder(ProductResponse response) {
		this.productId = response.getId();
		this.productPrice = response.getPrice();
		this.amount = response.getAmount();
		this.totalPrice = productPrice * amount;
	}

}
