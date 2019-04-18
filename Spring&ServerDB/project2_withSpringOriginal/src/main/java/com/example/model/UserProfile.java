package com.example.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="user_profile")
public class UserProfile
{
	@Id
	@Column(name="profile_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int profileId;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="birth_date")
	private Date birthdate;
	
	@Column(name="occupation")
	private String occupation;
	
	@Column(name="relationship_status")
	private String relationshipStatus;
	
	@Column(name="current_status")
	private String currentStatus;
	
	@Column(name="hobbies")
	private String hobbies;
	
	@Column(name="aboutMe")
	private String aboutMe;
	
	@Column(name="profile_image")
	private String profileImageUrl;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="sender")
	List <Post> myPosts;
	
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List <UserProfile> friendList;
	
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List <Post> likedPosts;
	
	public UserProfile() {}

	public UserProfile(String firstName, String lastName, String gender, Date birthdate, String occupation,
			String relationshipStatus, String currentStatus, String hobbies, String aboutMe, String profileImageUrl,
			List<Post> myPosts, List<UserProfile> friendList, List<Post> likedPosts) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthdate = birthdate;
		this.occupation = occupation;
		this.relationshipStatus = relationshipStatus;
		this.currentStatus = currentStatus;
		this.hobbies = hobbies;
		this.aboutMe = aboutMe;
		this.profileImageUrl = profileImageUrl;
		this.myPosts = myPosts;
		this.friendList = friendList;
		this.likedPosts = likedPosts;
	}

	public UserProfile(int profileId, String firstName, String lastName, String gender, Date birthdate,
			String occupation, String relationshipStatus, String currentStatus, String hobbies, String aboutMe,
			String profileImageUrl, List<Post> myPosts, List<UserProfile> friendList, List<Post> likedPosts) {
		super();
		this.profileId = profileId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthdate = birthdate;
		this.occupation = occupation;
		this.relationshipStatus = relationshipStatus;
		this.currentStatus = currentStatus;
		this.hobbies = hobbies;
		this.aboutMe = aboutMe;
		this.profileImageUrl = profileImageUrl;
		this.myPosts = myPosts;
		this.friendList = friendList;
		this.likedPosts = likedPosts;
	}

	@Override
	public String toString() {
		return "UserProfile [profileId=" + profileId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", birthdate=" + birthdate + ", occupation=" + occupation
				+ ", relationshipStatus=" + relationshipStatus + ", currentStatus=" + currentStatus + ", hobbies="
				+ hobbies + ", aboutMe=" + aboutMe + ", profileImageUrl=" + profileImageUrl + "]";
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public List<Post> getMyPosts() {
		return myPosts;
	}

	public void setMyPosts(List<Post> myPosts) {
		this.myPosts = myPosts;
	}

	public List<UserProfile> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<UserProfile> friendList) {
		this.friendList = friendList;
	}

	public List<Post> getLikedPosts() {
		return likedPosts;
	}

	public void setLikedPosts(List<Post> likedPosts) {
		this.likedPosts = likedPosts;
	}
}
