package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.model.UserProfile;

@Transactional
@Repository("profileDao")
public class UserProfileDaoImpl implements UserProfileDao
{
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	@Autowired
	private SessionFactory sesFact;
	
	public UserProfileDaoImpl() {}
	
	public void insert(UserProfile myProfile)
	{
		sesFact.getCurrentSession().save(myProfile);
	}
	
	public void update(UserProfile myProfile)
	{
		sesFact.getCurrentSession().update(myProfile);
	}
	
	public UserProfile selectProfileById(int id)
	{
		return sesFact.getCurrentSession().get(UserProfile.class, id);
	}
	
	public List<UserProfile> selectFriendsListByUserId(int id)
	{
		List<UserProfile> fl = sesFact.getCurrentSession().get(UserProfile.class, id).getFriendList();
		System.out.println(fl);
		return fl;
	}
	
	public List<UserProfile> selectAll()
	{
		return sesFact.getCurrentSession().createQuery("from UserProfile",UserProfile.class).list();
	}
	
	@Override
	public UserProfile updateProfile(UserProfile userProfile)
	{
		UserProfile updatedProfile = this.selectProfileById(userProfile.getProfileId());
		updatedProfile.setFirstName(userProfile.getFirstName());
		updatedProfile.setLastName(userProfile.getLastName());
		updatedProfile.setGender(userProfile.getGender());
		updatedProfile.setBirthdate(userProfile.getBirthdate());
		updatedProfile.setOccupation(userProfile.getOccupation());
		updatedProfile.setRelationshipStatus(userProfile.getRelationshipStatus());
		updatedProfile.setCurrentStatus(userProfile.getCurrentStatus());
		updatedProfile.setHobbies(userProfile.getHobbies());
		updatedProfile.setAboutMe(userProfile.getAboutMe());
		updatedProfile.setProfileImageUrl(userProfile.getProfileImageUrl());
		
		this.update(updatedProfile);
		return updatedProfile;
	}
	
	@Override
	public boolean addOrRemoveFriend(int hostProfileId, int friendProfileId, boolean addFriend) {
		UserProfile user = this.selectProfileById(hostProfileId);
		UserProfile friend = this.selectProfileById(friendProfileId);
		
		if(addFriend && !user.getFriendList().contains(friend))
		{
			user.getFriendList().add(friend);
			return true;
		}
		else if (!addFriend && user.getFriendList().contains(friend))
		{
			user.getFriendList().remove(friend);
			return true;
		}
		return false;
	}
}
