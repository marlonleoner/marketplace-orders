package br.com.ufsm.order.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufsm.order.api.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUserId(Long id);

	Optional<Order> findByIdAndUserId(Long id, Long user_id);

}
