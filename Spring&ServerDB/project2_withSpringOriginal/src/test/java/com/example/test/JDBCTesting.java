package com.example.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.example.model.UserProfile;
import com.example.service.AllServicesTestConfig;
import com.example.service.UCService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AllServicesTestConfig.class },loader = 
AnnotationConfigContextLoader.class )
public class JDBCTesting {
	
	    @Autowired
  private UCService ucService;


  
  static UserProfile ellen
  =new UserProfile( "Yingdi", "Zheng", null, null, null, null, null, null, null, null, null, null, null);;
  

  @Test
  public void testLogin() {
      //in our DB, our user tUser1 has lastname = "User 1"
      assertEquals("User 1",ucService.login("tUser1", "password").getLastName());
  }
  
  @Test
  public void testSinUp() {
      ucService.registerNewUser("yzheng1", "121", "Yingdi", "Zheng", "123@gmail.com");
      assertEquals("User 1",ucService.login("yzheng1", "121"));
  }
  
  @Test
  public void testChangePassword() {
      ucService.changePassword("yzheng1", "121", "newpass");
      assertEquals("User 1",ucService.login("yzheng1", "newpass"));
  }
  



}
