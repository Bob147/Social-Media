package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_credentials")
public class UserCredentials
{
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="hashed_password")
	private String hashedPassword;
	
	@Column(name="email", nullable=false)
	private String eMail;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_profile_fk", unique=true)
	private UserProfile profile;
	
	public UserCredentials() {}

	public UserCredentials(String username, String password, String hashedPassword, String eMail, UserProfile profile) {
		super();
		this.username = username;
		this.password = password;
		this.hashedPassword = hashedPassword;
		this.eMail = eMail;
		this.profile = profile;
	}

	public UserCredentials(int userId, String username, String password, String hashedPassword, String eMail, UserProfile profile) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.hashedPassword = hashedPassword;
		this.eMail = eMail;
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "UserCredentials [userId=" + userId + ", username=" + username + ", password=" + password
				+ ", hashedPassword=" + hashedPassword + ", eMail=" + eMail + ", profile=" + profile + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
}
