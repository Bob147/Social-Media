package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserProfileDao;
import com.example.dao.UserProfileDaoImpl;
import com.example.model.UserProfile;

@Service("upService")
public class UPServiceImpl implements UPService{

	@Autowired 
	UserProfileDao profileDao;
	
	public UPServiceImpl() {}
	
	public UPServiceImpl(UserProfileDaoImpl profileDao) {
		super();
		this.profileDao = profileDao;
	}

	public void setProfileDao(UserProfileDaoImpl profileDao) {
		this.profileDao = profileDao;
	}
	
	public UserProfile getProfileById(int id) {
		UserProfile up = profileDao.selectProfileById(id);
		return up;
	}
	public List<UserProfile> getFriendsListByUserId(int id)
	{
		List <UserProfile> fl = profileDao.selectFriendsListByUserId(id);
		return fl;
	}

	@Override
	public UserProfile updateProfile(UserProfile userProfile) {
		
		UserProfile profile = profileDao.updateProfile(userProfile);
		
		return profile;
	}

	@Override
	public List<UserProfile> getAllProfiles() {
		List<UserProfile> allProfiles = profileDao.selectAll();
		return allProfiles;
	}

	@Override
	public boolean addFriend(int userId, int friendId) {
		return profileDao.addOrRemoveFriend(userId, friendId, true);
	}
	
	@Override
	public boolean removeFriend(int userId, int friendId) {
		return profileDao.addOrRemoveFriend(userId, friendId, false);
	}
	
}
