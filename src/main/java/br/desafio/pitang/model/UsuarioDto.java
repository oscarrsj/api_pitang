package br.desafio.pitang.model;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UsuarioDto {
	private String firstName;

	private String lastName;
	
	@NotBlank(message = "Email é necessário")
	@Email()
	private String email;
	
	private String password;
	
	@Valid
	private List<Telefone> phones;
	
	private Date create_at;

	private Date last_login;

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

	public List<Telefone> getPhones() {
		return phones;
	}

	public void setPhones(List<Telefone> phones) {
		this.phones = phones;
	}

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
		Usuario usuario = new Usuario();
		usuario.setFirstName(this.firstName);
		usuario.setLastName(this.lastName);
		usuario.setEmail(this.email);
		usuario.setPassword(this.password);
		usuario.setPhones(this.phones);
		usuario.setCreate_at(create_at);
		usuario.setLast_login(this.last_login);
		
		return usuario;
	}
}
