package com.example.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post
{
	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int postId;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="sender_id_fk")
	private UserProfile sender;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="receiver_id_fk")
	private UserProfile receiver;
	
	@Column(name="post_contents", nullable=false)
	private String contents;
	
	@Column(name="image_url")
	private String image_url;
	
	@Column(name="post_timestamp")
	private Date timestamp;

	public Post() {}

	public Post(UserProfile sender, UserProfile receiver, String contents, String image_url, Date timestamp) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.contents = contents;
		this.image_url = image_url;
		this.timestamp = timestamp;
	}

	public Post(int postId, UserProfile sender, UserProfile receiver, String contents, String image_url,
			Date timestamp) {
		super();
		this.postId = postId;
		this.sender = sender;
		this.receiver = receiver;
		this.contents = contents;
		this.image_url = image_url;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", sender=" + sender + ", receiver=" + receiver + ", contents=" + contents + ", image_url=" + image_url + ", timestamp=" + timestamp + "]";
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public UserProfile getSender() {
		return sender;
	}

	public void setSender(UserProfile sender) {
		this.sender = sender;
	}

	public UserProfile getReceiver() {
		return receiver;
	}

	public void setReceiver(UserProfile receiver) {
		this.receiver = receiver;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
