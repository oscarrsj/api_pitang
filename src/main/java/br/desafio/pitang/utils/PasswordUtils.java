package br.desafio.pitang.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	private static BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
	/**
	 * Gera um hash utilizando o BCrypt.
	 * 
	 * @param senha
	 * @return String
	 */
	public static String gerarBCrypt(String senha) {
		if (senha == null) {
			return senha;
		}

		return bCryptEncoder.encode(senha);
	}
	
	
	public static Boolean isPasswordValid(String senha,  String senhaEncriptada) {
		if(senha !=null && senhaEncriptada != null && bCryptEncoder.matches(senha, senhaEncriptada))
			return true;
		
		return false;
	}
}
