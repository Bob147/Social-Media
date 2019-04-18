package com.example.dao;

import java.util.List;

import com.example.model.UserProfile;

public interface UserProfileDao {
	
	/**
	 * parameter(s): the profileIds of both currentUser and the 'friend' you want to add or remove.
	 * 				 boolean: isFriend is true when the two user are friends currently, false otherwise.
	 *
	 * purpose: add friend to host's friend list if isFriend is false
	 *          or
	 *          remove friend from host's friend list if isFriend is true
	 * return: true if process successfully
	 *         false otherwise.
	 */
	public boolean addOrRemoveFriend(int hostProfileId, int friendProfileId, boolean isFriend);
	
	public void insert(UserProfile myProfile);
	
	public void update(UserProfile myProfile);
	
	public UserProfile selectProfileById(int id);
	
	public List<UserProfile> selectAll();

	public List<UserProfile> selectFriendsListByUserId(int id);

	public UserProfile updateProfile(UserProfile userProfile);
	
}
