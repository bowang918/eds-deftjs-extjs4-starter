package com.seven7.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import ch.rasc.extclassgenerator.Model;
import ch.rasc.extclassgenerator.ModelField;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Model
public class User {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@ModelField(convert = "null")
	private String id;

	@NotEmpty(message = "{user_missing_username}")
	@Size(max = 100)
	@Column(unique = true)
	private String userName;

	@Size(max = 255)
	private String name;

	@Size(max = 255)
	private String firstName;

	@Email(message = "{user_missing_email}")
	@Size(max = 255)
	@NotEmpty(message = "{user_missing_email}")
	@Column(unique = true)
	private String email;

	private String role;

	@Size(max = 255)
	@JsonIgnore
	private String passwordHash;

	@Transient
	private String passwordNew;

	@Transient
	private String passwordNewConfirm;

	@Transient
	private String oldPassword;

	@Size(max = 8)
	private String locale;

	private boolean enabled;

	@JsonIgnore
	private Integer failedLogins;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	private DateTime lockedOut;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Integer getFailedLogins() {
		return failedLogins;
	}

	public void setFailedLogins(Integer failedLogins) {
		this.failedLogins = failedLogins;
	}

	public DateTime getLockedOut() {
		return lockedOut;
	}

	public void setLockedOut(DateTime lockedOut) {
		this.lockedOut = lockedOut;
	}

	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

	public String getPasswordNewConfirm() {
		return passwordNewConfirm;
	}

	public void setPasswordNewConfirm(String passwordNewConfirm) {
		this.passwordNewConfirm = passwordNewConfirm;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
