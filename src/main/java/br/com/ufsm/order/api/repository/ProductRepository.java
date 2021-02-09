package br.com.ufsm.order.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufsm.order.api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
