package com.yagobbosa.forum.config.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yagobbosa.forum.config.security.service.TokenService;
import com.yagobbosa.forum.model.User;
import com.yagobbosa.forum.repository.UserRepository;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	private UserRepository userRepository;

	public AuthenticationTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = retrieveToken(request);

		boolean itsValid = tokenService.isValidToken(token);

		if (itsValid)
			authenticateClient(token);

		filterChain.doFilter(request, response);
	}

	private void authenticateClient(String token) {
		Long userId = tokenService.getUserId(token);

		User user = userRepository.findById(userId).get();

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}

	private String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer "))
			return null;

		return token.substring(7, token.length());
	}

}
