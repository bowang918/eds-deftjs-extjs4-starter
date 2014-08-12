package com.seven7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seven7.domain.entity.AccessLog;

public interface AccessLogRepository extends JpaRepository<AccessLog, String> {

	AccessLog findBySessionId(String sessionId);
}
