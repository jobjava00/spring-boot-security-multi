package com.github.jobjava00.domain.account;

import javax.persistence.*;
import java.util.Date;

/**
 * @author jobjava00
 */
@Entity
public class Account {
	@Id @GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String role;

	@Temporal(TemporalType.TIMESTAMP)
	private Date joined;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getJoined() {
		return joined;
	}

	public void setJoined(Date joined) {
		this.joined = joined;
	}

	public boolean isAdmin(){
		return "ROLE_ADMIN".equals(this.getRole()) ? true : false;
	}
}
