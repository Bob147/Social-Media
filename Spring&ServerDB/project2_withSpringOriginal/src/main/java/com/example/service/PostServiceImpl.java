package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PostDao;
import com.example.dao.PostDaoImpl;
import com.example.dao.PostDaoImpl.PostLikeCount;
import com.example.model.Post;

@Service("postService")
public class PostServiceImpl implements PostService{

	@Autowired
	PostDao postDao;
	
	public PostServiceImpl() {}
	
	public PostServiceImpl(PostDaoImpl postDao) {
		super();
		this.postDao = postDao;
	}

	public PostDao getPostDao() {
		return postDao;
	}

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}

	public List<Post> getUserPostsByUserId(int id)
	{
		return postDao.selectUserPostsByUserId(id);
	}
	
	public List<Post> getUserLikedPostsByUserId(int id)
	{
		return postDao.selectUserLikedPostsByUserId(id);
	}

	public List<Post> getAllPosts()
	{
		return postDao.selectAll();
	}

	@Override
	public void submitNewPost(Post post) {
		postDao.insertNewPost(post);
	}

	@Override
	public boolean checkLikedpost(int profileId, int postId) {
		return postDao.checkLikedPost(profileId, postId);
	}

	@Override
	public void likePost(int profileId, int postId) {
		postDao.likePost(profileId, postId);
		
	}

	@Override
	public void unlikePost(int profileId, int postId) {
		postDao.unlikePost(profileId, postId);
	}

	@Override
	public List<PostLikeCount> getLikeCounts() {
		return postDao.selectLikeCounts();
	}
	
}
