package org.sixth.sixseminar.common.config;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

import org.sixth.sixseminar.security.CustomAccessDeniedHandler;
import org.sixth.sixseminar.security.CustomJwtAuthenticationEntryPoint;
import org.sixth.sixseminar.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig{



	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final CustomJwtAuthenticationEntryPoint customJwtAuthenticationEntryPoint;
	private final CustomAccessDeniedHandler customAccessDeniedHandler;


	private static final String[] AUTH_WHITELIST = {
		"api/users/sign-up",
		"api/users/sign-in",
		"api/v2/**"
	};


	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.csrf().disable()
			.formLogin().disable()
			.httpBasic().disable()
			.sessionManagement()
			.sessionCreationPolicy(STATELESS)
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(customJwtAuthenticationEntryPoint)
			.accessDeniedHandler(customAccessDeniedHandler)
			.and()
			.authorizeHttpRequests()
			.requestMatchers(AUTH_WHITELIST).permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() { //cors에러를 해결하기 위한 메서드
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedOriginPatterns("*")
					.allowedMethods("*");
			}
		};
	}

}
