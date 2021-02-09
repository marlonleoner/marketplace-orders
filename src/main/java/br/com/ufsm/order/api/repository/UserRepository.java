package br.com.ufsm.order.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufsm.order.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
