package br.desafio.pitang.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Telefone {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore()
	private long id;
	
	@Pattern(regexp = "^9[0-9]{8}[:.,-]?$", message="Invalid number")
	private String number;
	
	@Pattern(regexp = "^[0-9]{2}[:.,-]?$", message="Invalid area_code")
	private String area_code;
	
	@Pattern(regexp = "^[+][0-9]{2}[:.,-]?$", message="Invalid Country Code")
	private String country_code;
	
	public Telefone() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Telefone(@Pattern(regexp = "^9[0-9]{8}[:.,-]?$", message = "Invalid number") String number,
			@Pattern(regexp = "^[0-9]{2}[:.,-]?$", message = "Invalid area_code") String area_code,
			@Pattern(regexp = "^[+][0-9]{2}[:.,-]?$", message = "Invalid Country Code") String country_code) {
		super();
		this.number = number;
		this.area_code = area_code;
		this.country_code = country_code;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
}
