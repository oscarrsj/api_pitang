package br.desafio.pitang.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.desafio.pitang.model.Telefone;
import br.desafio.pitang.model.Usuario;
import br.desafio.pitang.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioRepositoryTest {

	private static final String SENHA = "123";
	private static final String EMAIL = "usuarioTest@gmail.com";
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Before
	public void setUp() {
		usuarioRepository.save(obterDadosUsuario());
	}

	@After
	public final void tearDown() {
		this.usuarioRepository.deleteAll();
	}

	@Test
	public void testFindByEmailAddress() {
		Usuario usuario = usuarioRepository.findByEmailAddress(EMAIL);
		assertNotNull(usuario);
		assertEquals(EMAIL, usuario.getEmail());
		assertNotNull(usuario.getId());
		assertFalse(usuario.getPhones().isEmpty());
	}

	@Test
	public void testSaveSucesso() {
		Usuario usuario = usuarioRepository.save(obterDadosUsuario());
		assertNotNull(usuario.getId());
	}
	

	private Usuario obterDadosUsuario() {
		Usuario usuario = new Usuario();
		usuario.setEmail(EMAIL);
		usuario.setFirstName("Usuario");
		usuario.setLastName("Teste");
		usuario.setPassword(SENHA);
		usuario.encriptPassowrd();

		List<Telefone> phones = new ArrayList<>();

		phones.add(new Telefone("991917878", "81", "+55"));
		phones.add(new Telefone("991917879", "81", "+55"));
		phones.add(new Telefone("991917880", "81", "+55"));
		usuario.setPhones(phones);

		return usuario;
	}

}
