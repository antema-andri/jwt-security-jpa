package com.security.apps.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.security.apps.encrypter.Encrypter;

import lombok.AllArgsConstructor;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TokenService {
    private final JwtEncoder encoder;
    private final Encrypter encrypter;

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> !authority.startsWith("ROLE"))
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        var encoderParameters =  getEncoderPrameters(claims);
        return this.encoder.encode(encoderParameters).getTokenValue();
    }

    public JwtEncoderParameters getEncoderPrameters(JwtClaimsSet claims) {
        JwtEncoderParameters encoderParameters = null;
        if(encrypter.getClass().getSimpleName().equals("SymetricEncrypter")){
            encoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), claims);
        }
        else{
            encoderParameters = JwtEncoderParameters.from(claims);
        }
        return encoderParameters;
    }
    
    public boolean isValidateToken(String token) throws ParseException {
    	boolean isValid=validateSignature(token);
    	return isValid;
    }
    
    public boolean validateSignature(String token) throws ParseException {
    	boolean isValidSignature;
    	String algoConfig;
    	SignedJWT signedJWT=SignedJWT.parse(token);
    	String algoToken=signedJWT.getHeader().getAlgorithm().getName();
    			
    	System.out.println("ALGO_TOKEN: "+algoToken);
    	if(encrypter.getClass().getSimpleName().equals("SymetricEncrypter")){
    		algoConfig=MacAlgorithm.HS512.getName();
    		isValidSignature=algoToken.equals(algoConfig);
        }
        else{
        	algoConfig="RS256";
        	isValidSignature=algoToken.equals(algoConfig);
        }
    	System.out.println("ALGO_CONFIG: "+algoConfig);
    	return isValidSignature;
    }
}
