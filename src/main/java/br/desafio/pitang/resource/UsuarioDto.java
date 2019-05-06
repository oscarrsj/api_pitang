package br.desafio.pitang.resource;

import java.util.Date;
import java.util.List;

import br.desafio.pitang.model.Telefone;
import br.desafio.pitang.model.Usuario;

public class UsuarioDto {
	private String firstName;

	private String lastName;
	
	private String email;
	
	private List<Telefone> phones;
	
	private Date created_at;
	
	private Date last_Login;
	
	private String password;
	
	public UsuarioDto() {
	}
	
	
	public UsuarioDto(String firstName, String lastName, String email, List<Telefone> phones, Date created_at,
			Date last_Login) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phones = phones;
		this.created_at = created_at;
		this.last_Login = last_Login;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public void setPhones(List<Telefone> phones) {
		this.phones = phones;
	}




	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}




	public void setLast_Login(Date last_Login) {
		this.last_Login = last_Login;
	}




	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public List<Telefone> getPhones() {
		return phones;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public Date getLast_Login() {
		return last_Login;
	}

	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
}
