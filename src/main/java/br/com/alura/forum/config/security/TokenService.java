package br.com.alura.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}") // injetar parametros que estão no apllication.properties
	private String expiration;

	@Value("${forum.jwt.secret}") // injetar parametros que estão no apllication.properties
	private String secret;

	public String gerarToken(Authentication authentication) {

		Usuario logado = (Usuario) authentication.getPrincipal(); // metodo que recupera usuario que está logado
		Date hoje = new Date();
		@SuppressWarnings("deprecation")
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		return Jwts.builder() // metodo que seta informações para construir o token
				.setIssuer("API dp forum da Alura")// identificação de quem fez a geração desse token
				.setSubject(logado.getId().toString()) // usuario autenticado a quem eses token pertence
				.setIssuedAt(hoje) // data de geração do token
				.setExpiration(dataExpiracao) // data de expiração do token
				.signWith(SignatureAlgorithm.HS256, secret).compact();

	}

}
