package br.desafio.pitang.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UsuarioCredencialDto {
	@NotBlank(message = "Email é necessário")
	@Email()
	private String email;
	
	private String password;

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
	
	public Usuario convertToUsuario() {
		Usuario usuario = new Usuario();
		usuario.setEmail(this.email);
		usuario.setPassword(password);
		
		return  usuario;
	}
}
