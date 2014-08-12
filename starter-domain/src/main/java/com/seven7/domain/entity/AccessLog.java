package com.seven7.domain.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import ch.rasc.extclassgenerator.Model;
import ch.rasc.extclassgenerator.ModelField;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Model(value = "App.model.AccessLog", readMethod = "accessLogService.read", paging = true)
public class AccessLog {

	@Id
	@GenericGenerator(strategy = "uuid", name = "generator")
	@GeneratedValue(generator = "generator")
	@Column(name = "id")
	@JsonIgnore
	private String id;

	@Size(max = 100)
	@JsonIgnore
	private String sessionId;

	@Size(max = 255)
	private String userName;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar logIn;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar logOut;

	@ModelField
	@Transient
	private String duration;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Calendar getLogIn() {
		return logIn;
	}

	public void setLogIn(Calendar logIn) {
		this.logIn = logIn;
	}

	public Calendar getLogOut() {
		return logOut;
	}

	public void setLogOut(Calendar logOut) {
		this.logOut = logOut;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
