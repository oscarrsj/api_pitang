package br.desafio.pitang.config.jwt;

import java.util.Date;

import org.springframework.http.HttpStatus;

import br.desafio.pitang.exception.InvalidTokenException;
import br.desafio.pitang.exception.ResourceUnAuthorizedException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtil {
	// EXPIRATION_TIME = 10 dias
	public static final long EXPIRATION_TIME = 860_000_000;
	public static final String SECRET = "MySecret";
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";

	public static String getToken(String username) {
		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	public static String resolvedToken(String token) {
		if(token == null || !token.startsWith(TokenUtil.TOKEN_PREFIX))
			throw new ResourceUnAuthorizedException("Unauthorized");
		token = token.substring(7);
		validarToken(token) ;
		
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
				.getSubject();
	}

	private static boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new InvalidTokenException("Unauthorized - Invalid Session");
		}
	}
	
}
