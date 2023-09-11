package com.security.apps.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.apps.filters.JwtAuthenticationFilter;
import com.security.apps.filters.JwtAuthorizationFilter;
import com.security.apps.service.TokenService;

public class SecurCustomDsl extends AbstractHttpConfigurer<SecurCustomDsl, HttpSecurity> {
	private final TokenService tokenService;
	
	public SecurCustomDsl(TokenService tokenService) {
		this.tokenService=tokenService;
	}
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilter(new JwtAuthenticationFilter(authenticationManager, tokenService));
        http.addFilterBefore(new JwtAuthorizationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
    }

}

