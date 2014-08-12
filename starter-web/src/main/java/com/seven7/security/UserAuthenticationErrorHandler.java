package com.seven7.security;

import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seven7.domain.entity.User;
import com.seven7.repository.UserRepository;

@Component
public class UserAuthenticationErrorHandler implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		Object principal = event.getAuthentication().getPrincipal();
		if (principal instanceof String) {
			User user = userRepository.findByUserName((String) principal);
			if (user != null) {
				if (user.getFailedLogins() == null) {
					user.setFailedLogins(1);
				} else {
					user.setFailedLogins(user.getFailedLogins() + 1);
				}

				if (user.getFailedLogins() > 10) {
					user.setLockedOut(DateTime.now().plusMinutes(10));
				}

			} else {
				LoggerFactory.getLogger(UserAuthenticationErrorHandler.class).warn("Unknown user login attempt: {}",principal);
			}
		}
	}
}