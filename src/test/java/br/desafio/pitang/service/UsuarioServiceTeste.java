package br.desafio.pitang.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.desafio.pitang.model.Telefone;
import br.desafio.pitang.model.Usuario;
import br.desafio.pitang.repository.UsuarioRepository;
import br.desafio.pitang.security.exception.InvalidFieldsException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTeste {

	private static final Long ID_USUARIO = 1l;
	private static final String _EMAIL = "exemplo@gmail.com";

	@MockBean
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Test(expected = InvalidFieldsException.class)
	public void testSingUpInsucessoEmailJaExistente() {
		BDDMockito.given(this.usuarioRepository.findByEmailAddress(Mockito.anyString())).willReturn(new Usuario());
		usuarioService.singup(obterDadosUsuario());
	}
	
	
	@Test(expected = InvalidFieldsException.class)
	public void testSingUpInsucessoCamposAusentes() {
		BDDMockito.given(this.usuarioRepository.findByEmailAddress(Mockito.anyString())).willReturn(null);
		Usuario usuario = obterDadosUsuario();
		usuario.setFirstName(null);
		usuarioService.singup(usuario);
		
	}

	
	@Test()
	public void testSingUpSucesso() {
		BDDMockito.given(this.usuarioRepository.findByEmailAddress(Mockito.anyString())).willReturn(null);
		BDDMockito.given(this.usuarioRepository.save(Mockito.anyObject())).willReturn(obterDadosUsuario());
		Usuario usuario = obterDadosUsuario();
		usuario.setId(null);
		Usuario novoUsuario = usuarioService.singup(usuario);
		
		assertEquals(novoUsuario.getId(), ID_USUARIO);
		
		
	}

	
	private Usuario obterDadosUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(ID_USUARIO);
		usuario.setEmail(_EMAIL);
		usuario.setFirstName("exemplo");
		usuario.setLastName("Test");
		usuario.setPassword("123");
		usuario.setPhones(new ArrayList<>());
		usuario.getPhones().add(new Telefone("991911515", "81", "+55"));
		usuario.getPhones().add(new Telefone("982868486", "81", "+55"));
		return usuario;
	}
}
