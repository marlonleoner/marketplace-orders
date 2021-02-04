package br.com.ufsm.orderapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufsm.orderapi.model.Order;
import br.com.ufsm.orderapi.model.User;


public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUser(User user);
	
}
