package br.desafio.pitang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.desafio.pitang.model.Usuario;
/**
 * 
 * @author oscar
 *
 *Repositorio do Usuario extendendo a implementação do JpaRepository
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmailAddress(String emailAddress);

}
