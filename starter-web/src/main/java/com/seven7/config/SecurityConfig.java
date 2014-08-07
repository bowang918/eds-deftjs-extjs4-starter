package com.seven7.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		//@formatter:off
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		//@formatter:on
	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends
			WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			//@formatter:off
			http.antMatcher("services/i18n*").headers().disable().authorizeRequests().anyRequest().permitAll();
			//@formatter:on
		}
	}

	@Configuration
	public static class FormLoginWebSecurityConfigurerAdapter extends
			WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			//@formatter:off
			http.headers().contentTypeOptions().xssProtection().cacheControl().httpStrictTransportSecurity().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN))
			.and().csrf().disable()
			.authorizeRequests().anyRequest().authenticated().and().formLogin();
			//@formatter:on
		}
	}
}
