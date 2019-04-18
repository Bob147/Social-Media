package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.UserProfile;
import com.example.service.UCService;
import com.example.service.UCServiceImpl;


@CrossOrigin//(origins ="http://localhost:4200")
@Controller
public class UCController {

	@Autowired
	UCService ucService;

	public UCController() {}
	
	public UCController(UCServiceImpl ucService) {
		super();
		this.ucService = ucService;
	}

	public void setUcService(UCServiceImpl ucService) {
		this.ucService = ucService;
	}
	
	
	@RequestMapping("/login.do")
	public @ResponseBody UserProfile login(@RequestParam("username")String username,@RequestParam("password") String password) {
		return ucService.login(username, password);
	}
	
	@RequestMapping("/signup.do")
	public @ResponseBody Boolean signUp(@RequestParam("username") String username,@RequestParam("password") String password,
			@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname,
			@RequestParam("email") String email) {
		return ucService.registerNewUser(username,password,firstname,lastname,email);
	}
	
	@RequestMapping("/changePassword.do")
	public @ResponseBody Boolean changePassword(@RequestParam("username") String username, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword)
	{
		return ucService.changePassword(username, oldPassword, newPassword);
	}
	
	@RequestMapping("/resetPassword.do")
	public @ResponseBody Boolean changePassword(@RequestParam("username") String username)
	{
		return ucService.resetPassword(username);
	}
	
	@RequestMapping("/updateEmail.do")
	public @ResponseBody Boolean updateEmail(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("newEmail") String newEmail)
	{
		return ucService.updateEmail(username, password, newEmail);
	}
	
}
