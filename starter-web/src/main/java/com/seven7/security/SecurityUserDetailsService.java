package com.seven7.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.seven7.domain.entity.User;
import com.seven7.repository.UserRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user != null) {
			return new SecurityUserDetails(user);
		}
		throw new UsernameNotFoundException(username);
	}
}
