package br.desafio.pitang.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.desafio.pitang.utils.PasswordUtils;

@Entity
@NamedQuery(name = "Usuario.findByEmailAddress", query = "select u from Usuario u where u.email = ?1")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(value = { "password"},allowSetters = true)
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	private String firstName;

	private String lastName;

	@NotBlank(message = "Email é necessário")
	@Email()
	private String email;

	private String password;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "USUARIO_ID", referencedColumnName = "id")
	@Valid
	private List<Telefone> phones;

	private Date create_at;

	private Date last_login;


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public void encriptPassowrd() {
		setPassword(PasswordUtils.gerarBCrypt(password));
	}

	public boolean validarFields() {
		if (firstName == null || lastName == null || password == null)
			return false;

		return true;
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
}
