package br.com.ufsm.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufsm.orderapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
