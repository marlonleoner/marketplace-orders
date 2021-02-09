package br.com.ufsm.order.api.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
public class User {
	
	@Id
	@Column(name = "id_user")
	@Getter
	@Setter
	private Long idUser;
	
	public User(Long id) {
		this.idUser = id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return Objects.equals(this.idUser, user.idUser);
	}

}
