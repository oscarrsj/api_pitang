package br.desafio.pitang.model;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UsuarioMeDto extends UsuarioSingUpDto{
	
	private Date create_at;

	private Date last_login;

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}
	
	
	public Usuario convertToUsuario() {
		Usuario usuario = super.convertToUsuario();
		usuario.setCreate_at(create_at);
		usuario.setLast_login(this.last_login);
		
		return usuario;
	}
}
