package br.desafio.pitang.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordUtilsTest {
	private static final String SENHA = "123456";

	@Test
	public void testSenhaNula() throws Exception {
		assertNull(PasswordUtils.gerarBCrypt(null));
	}
	
	@Test
	public void testGerarHashSenha() throws Exception {
		String hash = PasswordUtils.gerarBCrypt(SENHA);
		
		assertTrue(PasswordUtils.isPasswordValid(SENHA, hash));
	}
}
