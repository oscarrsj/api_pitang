package br.desafio.pitang.model;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UsuarioSingUpDto extends UsuarioCredencialDto{
	private String firstName;

	private String lastName;
	
	@Valid
	private List<Telefone> phones;
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Telefone> getPhones() {
		return phones;
	}

	public void setPhones(List<Telefone> phones) {
		this.phones = phones;
	}

	
	public Usuario convertToUsuario() {
		Usuario usuario = super.convertToUsuario();
		usuario.setFirstName(this.firstName);
		usuario.setLastName(this.lastName);
		usuario.setPhones(this.phones);
		
		return usuario;
	}
}
