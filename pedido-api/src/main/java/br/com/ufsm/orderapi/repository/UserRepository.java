package br.com.ufsm.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufsm.orderapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
