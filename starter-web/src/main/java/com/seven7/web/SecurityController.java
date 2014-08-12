package com.seven7.web;

<<<<<<< HEAD
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;

import com.seven7.domain.entity.AccessLog;
import com.seven7.domain.entity.User;
import com.seven7.repository.AccessLogRepository;
import com.seven7.repository.UserRepository;
import com.seven7.security.SecurityUserDetails;

@Service
public class SecurityController {
	@Autowired
	private AccessLogRepository accessLogRepository;

	@Autowired
	private UserRepository userRepository;

	@ExtDirectMethod
	@PreAuthorize("isAuthenticated()")
	@Transactional
	public User getLoggedOnUser(HttpServletRequest request, HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof SecurityUserDetails) {

			User user = userRepository.findOne(((SecurityUserDetails) principal).getUserDbId());

			AccessLog accessLog = new AccessLog();
			accessLog.setUserName(user.getUserName());
			accessLog.setSessionId(session.getId());
			accessLog.setLogIn(DateTime.now());
=======
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;

import com.seven7.domain.entity.AccessLog;
import com.seven7.domain.entity.User;
import com.seven7.repository.AccessLogRepository;
import com.seven7.repository.UserRepository;
import com.seven7.security.SecurityUserDetails;

@Service
public class SecurityController {
	@Autowired
	private AccessLogRepository accessLogRepository;

	@Autowired
	private UserRepository userRepository;

	@ExtDirectMethod
	@PreAuthorize("isAuthenticated()")
	@Transactional
	public User getLoggedOnUser(HttpServletRequest request, HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof SecurityUserDetails) {

			User user = userRepository.findOne(((SecurityUserDetails) principal).getUserDbId());

			AccessLog accessLog = new AccessLog();
			accessLog.setUserName(user.getUserName());
			accessLog.setSessionId(session.getId());
			accessLog.setLogIn(Calendar.getInstance());
>>>>>>> refs/remotes/origin/master
			accessLogRepository.save(accessLog);
			return user;

		}
		return null;
	}
}
