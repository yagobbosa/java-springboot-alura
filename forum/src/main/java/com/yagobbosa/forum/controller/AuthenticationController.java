package com.yagobbosa.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yagobbosa.forum.config.security.service.TokenService;
import com.yagobbosa.forum.controller.dto.TokenDto;
import com.yagobbosa.forum.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
@Profile(value = { "prod", "test" })
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> authentication(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken loginData = form.toConvert();

		try {
			Authentication authentication = authenticationManager.authenticate(loginData);

			String token = tokenService.generateToken(authentication);

			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}

}
