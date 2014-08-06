package com.seven7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.seven7.domain.jpa.User;

public interface UserRepository extends JpaRepository<User, String>,
		QueryDslPredicateExecutor<User> {

	public User findById(String id);
}
