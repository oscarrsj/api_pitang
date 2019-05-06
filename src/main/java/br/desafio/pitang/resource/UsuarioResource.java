package br.desafio.pitang.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.desafio.pitang.jwt.TokenUtil;
import br.desafio.pitang.model.Usuario;
import br.desafio.pitang.response.Response;
import br.desafio.pitang.security.exception.InvalidFieldsException;
import br.desafio.pitang.security.exception.ResourceUnAuthorizedException;
import br.desafio.pitang.service.UsuarioService;




@RestController
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;

	
	
	@PostMapping("/singup")
	public ResponseEntity<Object>singup(@RequestBody @Valid Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.singup(usuario);
		Response<Usuario> response  = new Response<>();
		response.setToken(TokenUtil.getToken(usuarioSalvo.getEmail()));
		return ResponseEntity.ok(response );
	} 
	
	@PostMapping("/singin")
	public ResponseEntity<Object> singup(@RequestBody  AuthenticationRequest usuarioRequest) {
	    ValidarFildsAuthentication(usuarioRequest);
	    
	    Usuario usuarioSalvo =  usuarioService.singin(usuarioRequest.convertToUsuario());
	    
	    Response<Usuario> response  = new Response<>();
		response.setToken(TokenUtil.getToken(usuarioSalvo.getEmail()));
	    
		return ResponseEntity.ok(response);
	}

	private void ValidarFildsAuthentication(AuthenticationRequest usuarioRequest) {
		if(usuarioRequest == null || usuarioRequest.getEmail() == null || usuarioRequest.getPassword() == null)
	  		   throw new InvalidFieldsException("Missing Fields");
	} 
	
	@GetMapping(path = "/me")
    public ResponseEntity<Object> me(@RequestHeader(required = false) String Authorization) {
		String email = TokenUtil.resolvedToken(Authorization);
		
		Usuario usuario = usuarioService.me(email);
		
		
        return ResponseEntity.ok(usuario);
    }


	
	
}
