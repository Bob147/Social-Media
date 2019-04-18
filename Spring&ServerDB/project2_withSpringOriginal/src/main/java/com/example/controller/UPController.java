package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.UserProfile;
import com.example.service.UPService;

@CrossOrigin//(origins ="http://localhost:4200")
@Controller
public class UPController {

	@Autowired
	UPService upService;
	
	@RequestMapping("/getAllProfiles.do")
	public @ResponseBody List<UserProfile> getAllProfiles() {
		return upService.getAllProfiles();
	}
	
	
	@RequestMapping("/getProfileById.do")
	public @ResponseBody UserProfile getProfileById(@RequestParam("id") int id) {
		return upService.getProfileById(id);
	}
	
	@RequestMapping("/getFriendsListById.do")
	public @ResponseBody List<UserProfile> getFriendsListById(@RequestParam("id") int id) {
		return upService.getFriendsListByUserId(id);
	}
	
	@RequestMapping("/updateProfile.do")
	public @ResponseBody UserProfile updateProfileById(@RequestBody UserProfile userProfile)
	{			
		UserProfile profile = upService.updateProfile(userProfile);
		return profile;
		
	}
	
	@RequestMapping("addFriend.do")
	public @ResponseBody Boolean addFriend(@RequestParam("userId") int userId, @RequestParam("friendId") int friendId)
	{
		return upService.addFriend(userId, friendId);
	}
	
	@RequestMapping("removeFriend.do")
	public @ResponseBody Boolean removeFriend(@RequestParam("userId") int userId, @RequestParam("friendId") int friendId)
	{
		return upService.removeFriend(userId, friendId);
	}
}
