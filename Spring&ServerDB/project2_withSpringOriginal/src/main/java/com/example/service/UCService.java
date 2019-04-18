package com.example.service;

import java.math.BigDecimal;

import com.example.model.UserProfile;

public interface UCService {
	boolean registerNewUser(String username, String password, String firstname, String lastname, String email);
	UserProfile login(String username, String password);
	boolean changePassword(String username, String oldPassword, String newPassword);
	BigDecimal logIn(String username, String password);
	boolean resetPassword(String username);
	boolean updateEmail(String username, String password, String newEmail);
}
