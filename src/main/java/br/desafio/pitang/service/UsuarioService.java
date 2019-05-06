package br.desafio.pitang.service;

import br.desafio.pitang.model.Usuario;
/**
 * 
 * @author oscar
 *
 * Service para acessar os recursos do Usuário.
 */

public interface UsuarioService {
	
	
	public Usuario singup(Usuario usuario);

	public Usuario singin(Usuario usuario) ;

	public Usuario me(String email);
}
