package com.HiloNacional.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {
	@Bean
	public SecurityFilterChain configuracion( HttpSecurity http) throws Exception {
		
		return http
				.csrf( (csrf) -> csrf.disable() )
				.authorizeHttpRequests( (auth) -> auth.anyRequest().permitAll() )
				.httpBasic( withDefaults() ).build();
		
	}// configuracion
	
	@Bean
	public PasswordEncoder cifrar() {
		return new BCryptPasswordEncoder();
	}// cifrar
}

