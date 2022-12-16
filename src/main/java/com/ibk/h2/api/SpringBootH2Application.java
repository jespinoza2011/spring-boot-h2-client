package com.ibk.h2.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringBootH2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootH2Application.class, args);
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Configuration
	public class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers(HttpMethod.GET).permitAll()
					.antMatchers(HttpMethod.POST).permitAll().anyRequest().authenticated()
					.antMatchers(HttpMethod.PUT).permitAll().anyRequest().permitAll()
					.antMatchers(HttpMethod.DELETE).permitAll().anyRequest().permitAll();
			http.csrf().disable();
		}
	}
}