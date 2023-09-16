package com.security.apps.filters;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.apps.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private TokenService tokenService;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, TokenService tokenService) {
		this.authenticationManager=authenticationManager;
		setFilterProcessesUrl("/api/auth/token");
		this.tokenService=tokenService;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		UsernamePasswordAuthenticationToken u=new UsernamePasswordAuthenticationToken(username,password);
		return authenticationManager.authenticate(u);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String jwtAccessToken=tokenService.generateToken(authResult);
		response.setHeader("Authorization", jwtAccessToken);
	}
}
