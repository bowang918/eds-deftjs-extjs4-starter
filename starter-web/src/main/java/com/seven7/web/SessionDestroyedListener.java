package com.seven7.web;

<<<<<<< HEAD
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seven7.domain.entity.AccessLog;
import com.seven7.repository.AccessLogRepository;

@Component
public class SessionDestroyedListener implements
		ApplicationListener<SessionDestroyedEvent> {

	@Autowired
	private AccessLogRepository accessLogRepository;

	@Override
	@Transactional
	public void onApplicationEvent(SessionDestroyedEvent event) {
		AccessLog accessLog = accessLogRepository.findBySessionId(event.getId());
		accessLog.setLogOut(DateTime.now());
=======
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seven7.domain.entity.AccessLog;
import com.seven7.repository.AccessLogRepository;

@Component
public class SessionDestroyedListener implements ApplicationListener<SessionDestroyedEvent> {

	@Autowired
	private AccessLogRepository accessLogRepository;

	@Override
	@Transactional
	public void onApplicationEvent(SessionDestroyedEvent event) {
		AccessLog accessLog = accessLogRepository.findBySessionId(event.getId());
		accessLog.setLogOut(Calendar.getInstance());
>>>>>>> refs/remotes/origin/master
	}

}
