package com.seven7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = { "com.seven7.dao" })
// @EnableJpaRepositories
public class DataConfig {
	@Bean
	public ClassPathResource randomdata() {
		return new ClassPathResource("/randomdata.csv.compressed");
	}
}
