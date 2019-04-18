package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.PostDaoImpl.PostLikeCount;
import com.example.model.Post;
import com.example.service.PostService;

@CrossOrigin//(origins ="http://localhost:4200")
@Controller
public class PostController {

	@Autowired 
	PostService postService;
	
	@RequestMapping("/getAllPosts.do")
	public @ResponseBody List<Post> getAllPosts() {
		return postService.getAllPosts();
	}
	
	@RequestMapping("/getUserPosts.do")
	public @ResponseBody List<Post> getUserPostsById(@RequestParam("id") int id) {
		return postService.getUserPostsByUserId(id);
	}
	
	@RequestMapping("/getUserLikedPosts.do")
	public @ResponseBody List<Post> getUserLikedPostsById(@RequestParam("id") int id) {
		return postService.getUserLikedPostsByUserId(id);
	}
	
	@RequestMapping("/submitNewPost.do")
	public @ResponseBody void submitNewPost(@RequestBody Post post) {
		postService.submitNewPost(post);
	}
	
	@RequestMapping("/checkLikedPost.do")
	public @ResponseBody boolean checkLikedPost(@RequestParam("profileId") int profileId, @RequestParam("postId") int postId) {
		return postService.checkLikedpost(profileId, postId);
	}
	
	@RequestMapping("/likePost.do")
	public @ResponseBody void likePost(@RequestParam("profileId") int profileId, @RequestParam("postId") int postId) {
		postService.likePost(profileId, postId);
	}
	
	@RequestMapping("/unlikePost.do")
	public @ResponseBody void unlikePost(@RequestParam("profileId") int profileId, @RequestParam("postId") int postId) {
		postService.unlikePost(profileId, postId);
	}
	
	@RequestMapping(value="/getLikeCounts.do", produces="application/json")
	public @ResponseBody List<PostLikeCount> getLikeCounts() {
		return postService.getLikeCounts();
	}
}
