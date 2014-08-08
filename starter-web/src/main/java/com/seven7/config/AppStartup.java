package com.seven7.config;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.seven7.domain.entity.User;
import com.seven7.repository.UserRepository;

@Component
public class AppStartup implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		configLogging();

		if (userRepository.count() == 0) {
			User adminUser = new User();
			adminUser.setUserName("admin");
			adminUser.setEmail("test@test.ch");
			adminUser.setFirstName("admin");
			adminUser.setName("admin");
			adminUser.setLocale("en");
			adminUser.setPasswordHash(passwordEncoder.encode("admin"));
			adminUser.setEnabled(true);
			adminUser.setRole("ADMIN");
			userRepository.save(adminUser);
		}
	}

	private void configLogging() {
		Logger logger = (Logger) LogManager.getRootLogger();
		if (logger.getAppenders().containsKey("databaseAppender")) {
			// already configured
			return;
		}
	}

}
