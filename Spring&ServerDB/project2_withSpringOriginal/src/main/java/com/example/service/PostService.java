package com.example.service;

import java.util.List;

import com.example.dao.PostDaoImpl.PostLikeCount;
import com.example.model.Post;

public interface PostService {

	public List<Post> getUserPostsByUserId(int id);
	public List<Post> getAllPosts();
	public List<Post> getUserLikedPostsByUserId(int id);
	public void submitNewPost(Post post);
	public boolean checkLikedpost(int profileId, int postId);
	public void likePost(int profileId, int postId);
	public void unlikePost(int profileId, int postId);
	public List<PostLikeCount> getLikeCounts();
	
}
