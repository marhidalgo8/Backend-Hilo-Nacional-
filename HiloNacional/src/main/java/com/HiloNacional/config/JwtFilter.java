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
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authHeader = httpServletRequest.getHeader("Authorization");
        String method = httpServletRequest.getMethod();
        String URI = httpServletRequest.getRequestURI();

        // ✅ Rutas públicas — no necesitan token
        boolean esGetProductos = method.equals("GET") && URI.contains("/HiloNacional/productos/");
        boolean esPostUsuarios = method.equals("POST") && URI.contains("/HiloNacional/usuarios/");
        boolean esGet = method.equals("GET"); // Allow all GET requests for frontend

        if (!esGetProductos && !esPostUsuarios && !esGet) {

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("1. Invalid Token");
            }

            String token = authHeader.substring(7);

            try {
                Claims claims = Jwts.parser()
                        .verifyWith(getSigninKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
                System.out.println(claims.getSubject());
            } catch (SignatureException | MalformedJwtException | ExpiredJwtException e) {
                throw new ServletException("2. Invalid Token");
            }

        }

        chain.doFilter(request, response);

    }

}