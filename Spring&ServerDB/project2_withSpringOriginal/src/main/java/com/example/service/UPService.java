package com.example.service;

import java.util.List;

import com.example.model.UserProfile;

public interface UPService {
	public UserProfile getProfileById(int id);
	public List<UserProfile> getFriendsListByUserId(int id);
	public UserProfile updateProfile(UserProfile userProfile);
	public List<UserProfile> getAllProfiles();
	public boolean addFriend(int userId, int friendId);
	public boolean removeFriend(int userId, int friendId);
}
