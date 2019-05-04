package br.desafio.pitang.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.desafio.pitang.model.Telefone;
import br.desafio.pitang.model.Usuario;
import br.desafio.pitang.security.exception.InvalidFieldsException;
import br.desafio.pitang.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioResourceTest {

	

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UsuarioService usuarioService;

	private static final String URL_SINGUP = "/singup";
	private static final Long ID_USUARIO = 1l;
	private static final String _EMAIL = "exemplo@gmail.com";
	

	@Test
	@WithMockUser
	public void testSingUp() throws Exception {
		Usuario usuario = obterDadosUsuario();
		BDDMockito.given(this.usuarioService.singup(Mockito.any(Usuario.class))).willReturn(usuario);

		mvc.perform(MockMvcRequestBuilders.post(URL_SINGUP)
				.content(obterJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.token").isNotEmpty());
	}
	
	
	@Test
	@WithMockUser
	public void testSingUpInsucessoEmailJaExistente() throws Exception {
		BDDMockito.given(this.usuarioService.singup(Mockito.any(Usuario.class))).willThrow(new InvalidFieldsException("E-mail already exists"));

	     mvc.perform(MockMvcRequestBuilders.post(URL_SINGUP)
			.content(obterJsonRequisicaoPost())
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isConflict())
			.andExpect(jsonPath("$.message").value("E-mail already exists"));
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

	private String obterJsonRequisicaoPost() throws JsonProcessingException {
		Usuario usuario = new Usuario();
		usuario.setId(null);
		usuario.setEmail(_EMAIL);
		usuario.setFirstName("exemplo");
		usuario.setLastName("Test");
		usuario.setPassword("123");
		usuario.setPhones(new ArrayList<>());
		usuario.getPhones().add(new Telefone("991911515", "81", "+55"));
		usuario.getPhones().add(new Telefone("982868486", "81", "+55"));
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(usuario);
	}

}