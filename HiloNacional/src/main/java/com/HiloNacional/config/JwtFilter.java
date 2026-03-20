package com.HiloNacional.config;

import java.io.IOException;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class JwtFilter extends GenericFilterBean {

    public static final String secret =
        Base64.getEncoder().encodeToString("DicenQuelaVidaSeTratagDeIntentarloHastaConseguirloPeroNadieIIIEsConscienteDeLoCansadoQueEsEso".getBytes());

    public static SecretKey getSigninKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }// getSigninKey

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	HttpServletRequest httpServletRequest = (HttpServletRequest) request; // convertimos request a HttpServletRequest
		String authHeader = httpServletRequest.getHeader("Authorization"); // obtenemos el header del token
		String method = httpServletRequest.getMethod(); // obtenemos el método usado (ej. GET, POST)
		String URI = httpServletRequest.getRequestURI(); // obtenemos la ruta de los endpoints (ej. /api/products/)
		
		
		if( // Filtra las solicitudes y los endpoints
		( method.equals("POST") ) && ( ! URI.contains("/store/usuarios/") )
		|| ( method.equals("GET") ) && ( ! URI.contains("/store/products/") )
		|| ( method.equals("PUT") )
		|| ( method.equals("DELETE") )
		) {
			
			if( (authHeader == null) || !( authHeader.startsWith("Bearer ")) ) { // verifica: 1.Que el header no sea nulo, 2. Que el header inicie con "Bearer "
				throw new ServletException("1. Invalid Token");
			}// if
			
			String token = authHeader.substring(7); // Extraemos el token
			
			try { // intenta crear el Token
				Claims claims = Jwts.parser()
						.verifyWith(getSigninKey())
						.build()
						.parseSignedClaims(token)
						.getPayload();
				System.out.println(claims.getSubject());
			} catch ( SignatureException | MalformedJwtException | ExpiredJwtException e ) { // atrapa una de las siguientes excepciones
				throw new ServletException("2. Invalid Token");
			}// catch
			
		}// métodos
		

		chain.doFilter(request, response); // permite pasar la solicitud. Devuelve una respuesta (try/catch)

    }// doFilter

}// class JwtFilter

