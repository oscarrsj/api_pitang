package br.desafio.pitang.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.desafio.pitang.exception.InvalidFieldsException;
import br.desafio.pitang.exception.InvalidTokenException;
import br.desafio.pitang.model.Usuario;
import br.desafio.pitang.repository.UsuarioRepository;
import br.desafio.pitang.utils.PasswordUtils;
/**
 * 
 * @author oscar
 *Implementação do Service
 */
@Service
public class UsuarioServiceImp implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	/**
	 * Método interno para pesqwuisar o usuário por email.
	 * @param emailAddress
	 * @return
	 */
	private Usuario findByEmailAddress(String emailAddress) {
		return usuarioRepository.findByEmailAddress(emailAddress);
	}
	
	
	/**
	 * Método que valida e salva um usuário
	 * @param usuario
	 * @return
	 */
	public Usuario singup(Usuario usuario) {
		if(findByEmailAddress(usuario.getEmail()) != null) {
			throw new InvalidFieldsException("E-mail already exists");
		}
		
		if(!usuario.validarFields()) {
			throw new InvalidFieldsException("Missing fields");
		}
		
		usuario.encriptPassowrd();
		
		Date dataCadastro = new Date();
		usuario.setCreate_at(dataCadastro);
		usuario.setLast_login(dataCadastro);
		
		Usuario UsuarioSalvo = usuarioRepository.save(usuario);
		
	
		return UsuarioSalvo;
	}

	/**
	 * Método que obtém um usuário a partir das credenciais Usuario e Senha
	 * @param usuario
	 * @return
	 */
	public Usuario singin(Usuario usuario) {
		Usuario usuarioPesquisado = findByEmailAddress(usuario.getEmail());
		
		if(usuarioPesquisado == null || !PasswordUtils.isPasswordValid(usuario.getPassword(), usuarioPesquisado.getPassword()) ) {
			throw new InvalidFieldsException("Invalid e-mail or password");
		}
		
		usuarioPesquisado.setLast_login(new Date());
		usuarioRepository.save(usuarioPesquisado);
		
		return usuarioPesquisado;
	}


	/**
	 * Método que Obtém usuário pelo e-mail.
	 * @param email
	 * @return
	 */
	public Usuario me(String email) {
		Usuario usuario = findByEmailAddress(email);
		if(usuario == null)
			throw new InvalidTokenException("Unauthorized - Invalid Session");
		return usuario;
	}
}
