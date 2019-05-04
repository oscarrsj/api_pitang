package br.desafio.pitang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.desafio.pitang.model.Usuario;
import br.desafio.pitang.repository.UsuarioRepository;
import br.desafio.pitang.security.exception.InvalidFieldsException;
import br.desafio.pitang.utils.PasswordUtils;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public Usuario findByEmailAddress(String emailAddress) {
		return usuarioRepository.findByEmailAddress(emailAddress);
	}
	
	
	
	public Usuario singup(Usuario usuario) {
		if(findByEmailAddress(usuario.getEmail()) != null) {
			throw new InvalidFieldsException("E-mail already exists");
		}
		
		if(!usuario.validarFields()) {
			throw new InvalidFieldsException("Missing fields");
		}
		
		usuario.encriptPassowrd();
		
		Usuario UsuarioSalvo = usuarioRepository.save(usuario);
		
	
		return UsuarioSalvo;
	}


	public Usuario singin(Usuario usuario) {
		Usuario usuarioPesquisado = findByEmailAddress(usuario.getEmail());
		
		if(usuarioPesquisado == null || !PasswordUtils.isPasswordValid(usuario.getPassword(), usuarioPesquisado.getPassword()) ) {
			throw new InvalidFieldsException("Invalid e-mail or password");
		}
		
		return usuarioPesquisado;
	}
}
