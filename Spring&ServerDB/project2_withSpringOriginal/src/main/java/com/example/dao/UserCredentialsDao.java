package com.example.dao;

import java.math.BigDecimal;
import java.util.List;

import com.example.model.UserCredentials;

public interface UserCredentialsDao {

	//implemented in DAO 
	public BigDecimal signIn(String username, String password);

	/*
	 * parameter(s): username, password, and email are all inputs from user sign up
	 * webpage. we need to add the new user to table if username does not exist in
	 * the DB. the initial userProfile should be null.
	 * 
	 * return : case1:  username exist, return "username exist"
	 *          case2:  fail to insert to table in db, return "system failure, please try again later"
	 *          case3:  succeed,  return "new user create"
	 */
	String signUp(String userName, String password, String email);
	
	/*
	 * parameter(s): the old password without hashing that user input in website
	 * - hash the InputOldPassword
	 * -check if it matches the user's hashedpassword in DB if not return 1stReturn 
	 * -change the hashed password in DB if fail to change return 2ndReturn
	 * -if succeed return 3rdReturn
	 * return: 
	 * 1stReturn: "input password incorrect"
	 * 2ndReturn: "cannot change your password for now"
	 * 3rdReturn: "password changing successful"
	 */
	String changePassword(int userId, String InputOldPassword);
	
	

	UserCredentials selectCredentialsByUsername(String username);
	
	String selectHashedPassword(String username, String password);

	void insert(UserCredentials c1);

	void update(UserCredentials creds);
	
	public UserCredentials selectById(int id);
	
	public List<UserCredentials> selectAll();
}
