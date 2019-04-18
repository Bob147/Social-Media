package com.example.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Post;
import com.example.model.UserProfile;
import com.example.service.UPService;

@Transactional
@Repository("postDao")
public class PostDaoImpl implements PostDao
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
	
	@Autowired
	private UPService upService;
	
	public SessionFactory getSesFact() {
		return sesFact;
	}

	public void setSesFact(SessionFactory sesFact) {
		this.sesFact = sesFact;
	}

	public UPService getUpService() {
		return upService;
	}

	public void setUpService(UPService upService) {
		this.upService = upService;
	}

	public PostDaoImpl() {}
	
	public PostDaoImpl(SessionFactory sesFact, UPService upService) {
		super();
		this.sesFact = sesFact;
		this.upService = upService;
	}

	public void insert(Post myPost)
	{
		sesFact.getCurrentSession().save(myPost);
	}
	
	public void update(Post myPost)
	{
		sesFact.getCurrentSession().update(myPost);
	}
	
	public Post selectById(int id)
	{
		return sesFact.getCurrentSession().get(Post.class, id);
	}
	
	public List<Post> selectUserPostsByUserId(int id)
	{
		UserProfile up = upService.getProfileById(id);
		List<Post> userPosts = sesFact.getCurrentSession().createQuery(
				"from Post where sender_id_fk='" + up.getProfileId() + "' order by post_timestamp, post_id ASC",Post.class).list();
		System.out.println(userPosts);
		return userPosts;
	}
	
	public List<Post> selectUserLikedPostsByUserId(int id)
	{
		
		List<Post> likedPosts = sesFact.getCurrentSession().get(UserProfile.class, id).getLikedPosts();
		System.out.println(likedPosts);
		return likedPosts;
	}
	
	public List<Post> selectAll()
	{
		List<Post> allPosts = sesFact.getCurrentSession().createQuery("from Post ORDER BY post_timestamp, post_id ASC",
				Post.class).list();
		System.out.println(allPosts);
		return allPosts;
	}

	@Override
	public void insertNewPost(Post post) {
		UserProfile senderProfile = upService.getProfileById(post.getSender().getProfileId());
		UserProfile receiverProfile = upService.getProfileById(post.getReceiver().getProfileId());
		//System.out.println(senderProfile);
		//System.out.println(receiverProfile);
		Post newPost = new Post(senderProfile, receiverProfile, post.getContents(), post.getImage_url(), new Date());
		System.out.println(newPost);
		
		senderProfile.getMyPosts().add(newPost);
		upService.updateProfile(senderProfile);
	}

	@Override
	public boolean checkLikedPost(int profileId, int postId) {
		List<Post> likedPosts = upService.getProfileById(profileId).getLikedPosts();
		
		for (int i = 0; i < likedPosts.size(); i++) {
			if (likedPosts.get(i).getPostId() == postId)
				return true;
		}
		
		return false;
	}

	@Override
	public void likePost(int profileId, int postId) {
		List<Post> likedPosts = upService.getProfileById(profileId).getLikedPosts();
		Post post = this.selectById(postId);
		
		if (!likedPosts.contains(post)) {
			likedPosts.add(post);
		}
	}
	
	public void unlikePost(int profileId, int postId) {
		List<Post> likedPosts = upService.getProfileById(profileId).getLikedPosts();
		Post post = this.selectById(postId);
		
		if (likedPosts.contains(post)) {
			likedPosts.remove(post);
		}
	}

	@Override
	public List<PostLikeCount> selectLikeCounts() {
		List<Object[]> results = sesFact.getCurrentSession().createSQLQuery(
				"select likedposts_post_id, count(*) from USER_PROFILE_POST Group by LIKEDPOSTS_POST_ID").list();
		String returnJson = "{";
		
		PostLikeCount obj;
		List<PostLikeCount> returnList = new ArrayList<>();
		
		for (int i = 0; i < results.size(); i++)
		{
			obj = new PostLikeCount();
			obj.setPostId(((BigDecimal) results.get(i)[0]).intValue());
			obj.setLikeCount(((BigDecimal) results.get(i)[1]).intValue());
			returnList.add(obj);
			
			returnJson = returnJson.concat("{\"postId\":\"" + ((BigDecimal) results.get(i)[0]).intValue() + "\",");
			if (i < results.size() - 1)
			{
				returnJson = returnJson.concat("\"likeCount\":\"" + ((BigDecimal) results.get(i)[1]).intValue() + "\"},");
			}
			else
			{
				returnJson = returnJson.concat("\"likeCount\":\"" + ((BigDecimal) results.get(i)[1]).intValue() + "\"}");
			}
		}
		returnJson = returnJson + "}";
		System.out.println(returnJson);
		
		return returnList;
	}
	
	public class PostLikeCount
	{
		int postId;
		int likeCount;
		
		public void setLikeCount(int count) {
			likeCount = count;
		}
		public void setPostId(int id) {
			postId = id;
		}
		public int getLikeCount() {
			return likeCount;
		}
		public int getPostId() {
			return postId;
		}
	}
}
