package com.yagobbosa.forum.config.security.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.yagobbosa.forum.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private String expiration;

	@Value("${forum.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		User logged = (User) authentication.getPrincipal();
		Date date = new Date();
		Date expirationDate = new Date(date.getTime() + Long.parseLong(expiration));

		return Jwts.builder().setIssuer("forum API").setSubject(logged.getId().toString()).setIssuedAt(date)
				.setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
