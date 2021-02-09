package br.com.ufsm.order.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufsm.order.api.model.Order;
import br.com.ufsm.order.api.model.User;


public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUser(User user);
	
}
