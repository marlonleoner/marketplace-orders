package br.com.ufsm.order.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufsm.order.api.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findByUserId(Long id);

	Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);

	void deleteByUserId(Long userId);

	void deleteByUserIdAndProductId(Long userId, Long productId);

}
