package br.com.ufsm.pedidoapi.modelo;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter @NoArgsConstructor
public class Usuario {
	
	@Id
	@Column(name = "id_usuario")
	private Long idUsuario;
	
	public Usuario(Long id) {
		this.idUsuario = id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Usuario)) return false;
		Usuario usuario = (Usuario) o;
		return Objects.equals(this.idUsuario, usuario.idUsuario);
	}

}
