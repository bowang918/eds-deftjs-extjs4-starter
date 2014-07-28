package com.seven7.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.seven7.config", "com.seven7.service", "com.seven7.dao" })
public class ComponentConfig {

}
