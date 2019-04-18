package com.example.dao;

import java.util.List;

import com.example.dao.PostDaoImpl.PostLikeCount;
import com.example.model.Post;

public interface PostDao {
	public void insert(Post myPost);
	
	public void update(Post myPost);
	
	public Post selectById(int id);
	
	public List<Post> selectAll();
	
	public List<Post> selectUserPostsByUserId(int id);
	
	public List<Post> selectUserLikedPostsByUserId(int id);

	public void insertNewPost(Post post);

	public boolean checkLikedPost(int profileId, int postId);

	public void likePost(int profileId, int postId);

	public void unlikePost(int profileId, int postId);

	public List<PostLikeCount> selectLikeCounts();

}
