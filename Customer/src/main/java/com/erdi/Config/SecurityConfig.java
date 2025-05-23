	package com.erdi.Config;

	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.authentication.AuthenticationManager;
	import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.http.SessionCreationPolicy;
	import org.springframework.security.web.SecurityFilterChain;

	@Configuration
	public class SecurityConfig {

		@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration authconfig) throws Exception{
			return authconfig.getAuthenticationManager();
		}

		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
			http
					.sessionManagement(session ->
							session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.csrf(csrf -> csrf.disable())
					.authorizeHttpRequests(auth -> auth
							.requestMatchers("/auth/**").permitAll()
							.anyRequest().authenticated());

			return http.build();
		}
	}
