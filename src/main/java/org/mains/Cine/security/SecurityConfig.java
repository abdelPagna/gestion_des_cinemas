package org.mains.Cine.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	  @Bean 
	  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception { 
		  return httpSecurity 
			  .csrf(AbstractHttpConfigurer::disable)
			  .cors(Customizer.withDefaults()) 
			  .headers(httpSecurityHeadersConfigurer ->	  httpSecurityHeadersConfigurer
			  .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) 
			  .authorizeHttpRequests(ar -> ar.requestMatchers("/api/**", "/swagger-ui/**", "/swagger-ui.html", "/v3/**").permitAll())
			  .authorizeHttpRequests( ar -> ar.anyRequest().authenticated()) 
			  .build(); 
		  }
	 
}
