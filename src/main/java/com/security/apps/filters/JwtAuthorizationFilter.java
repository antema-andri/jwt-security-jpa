package com.security.apps.filters;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.security.apps.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends OncePerRequestFilter{
	private TokenService tokenService;
	
	public JwtAuthorizationFilter(TokenService tokenService) {
		this.tokenService=tokenService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("**** JwtAuthorizationFilter ****");
		String authorizationToken=request.getHeader("Authorization");
		if(authorizationToken!=null && authorizationToken.equals("Authorization")) {
			try {
				tokenService.isValidateToken(authorizationToken);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		filterChain.doFilter(request, response);
	}

}
