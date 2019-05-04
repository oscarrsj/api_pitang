package br.desafio.pitang.resource;

import java.io.Serializable;

import br.desafio.pitang.model.Usuario;

public class AuthenticationRequest implements Serializable {
	private String email;
	private String password;

	public Usuario convertToUsuario() {

		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setPassword(password);


		return usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
