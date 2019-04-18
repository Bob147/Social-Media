package com.example.service;

import java.math.BigDecimal;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserCredentialsDao;
import com.example.dao.UserCredentialsDaoImpl;
import com.example.model.UserCredentials;
import com.example.model.UserProfile;

@Service("ucService")
public class UCServiceImpl implements UCService
{
	@Autowired
	private UserCredentialsDao credentialDao;
	@Autowired
	private UPService upService;
	 
	public UCServiceImpl(UserCredentialsDaoImpl credentialDao, UPService upService) {
		super();
		this.credentialDao = credentialDao;
		this.upService = upService;
	}

	public UserCredentialsDao getCredentialDao() {
		return credentialDao;
	}

	public void setCredentialDao(UserCredentialsDao credentialDao) {
		this.credentialDao = credentialDao;
	}
	

	public UPService getUpService() {
		return upService;
	}

	public void setUpService(UPService upService) {
		this.upService = upService;
	}

	public UCServiceImpl() {
		super();
	}

	public boolean registerNewUser(String username, String password, String firstname, String lastname, String email)
	{
		UserCredentials creds = credentialDao.selectCredentialsByUsername(username);
		
		if (creds == null)
		{
			UserProfile profile = new UserProfile();
			creds = new UserCredentials();
			
			profile.setFirstName(firstname);
			profile.setLastName(lastname);
			creds.setUsername(username);
			creds.setHashedPassword(credentialDao.selectHashedPassword(username, password));
			creds.setPassword(password);
			creds.seteMail(email);
			creds.setProfile(profile);
			
			credentialDao.insert(creds);
			
			return true;
		}
		else
		{
			return false;
		}
	}

	//ellen's version
	public BigDecimal logIn(String username, String password) {
		BigDecimal ucId  = credentialDao.signIn(username, password);
		return ucId;
	}
	public UserProfile login(String username, String password)
	{
		UserCredentials creds = credentialDao.selectCredentialsByUsername(username);
		String hashedPassword = credentialDao.selectHashedPassword(username, password);
		
		if (creds != null && creds.getHashedPassword().equals(hashedPassword))
		{
			UserProfile userProf=upService.getProfileById(creds.getProfile().getProfileId());
			System.out.println(userProf);
			return userProf;
		}
		else
		{
			return null;
		}	
	}
	
	public boolean changePassword(String username, String oldPassword, String newPassword)
	{
		UserCredentials creds = credentialDao.selectCredentialsByUsername(username);
		String hashedPassword = credentialDao.selectHashedPassword(username, oldPassword);
		
		if (creds.getHashedPassword().equals(hashedPassword))
		{
			hashedPassword = credentialDao.selectHashedPassword(username, newPassword);
			creds.setPassword(newPassword);
			creds.setHashedPassword(hashedPassword);
			
			credentialDao.update(creds);
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean resetPassword(String username)
	{
		UserCredentials creds = credentialDao.selectCredentialsByUsername(username);
		String hashedPassword = credentialDao.selectHashedPassword(username, "tempPassword");
		
		if (creds != null)
		{
			creds.setPassword("tempPassword");
			creds.setHashedPassword(hashedPassword);
			
			credentialDao.update(creds);
			
			// EMAIL FUNCTIONALITY
			String msg = "The password for your Abowlt account has been reset.\n\nAccount Username: " + creds.getUsername() +
					"\nTemp Password: tempPassword\n\nPlease log into your account and update your password as soon as possible.\n\n"
					+ "Sincerely, Andrew Stone\nPassword Administrator, Abowlt, LLC";
			
			return this.sendEmail("andrew.stone.abowlt", "Pass123!", creds.geteMail(), "Abowlt Password Reset", msg);
		}
		else
		{			
			return false;
		}		
	}
	
	public boolean sendEmail(String from,String password,String to,String sub,String msg)
	{  
        //Get properties object    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");
        
        //get Session   
        Session session = Session.getDefaultInstance(props,
        	new javax.mail.Authenticator() {
        		protected PasswordAuthentication getPasswordAuthentication() {
        			return new PasswordAuthentication(from,password);
        		}
    		}
        );
        
        //compose message    
        try {
        	MimeMessage message = new MimeMessage(session);    
        	message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
        	message.setSubject(sub);    
        	message.setText(msg);    
        	//send message  
        	Transport.send(message);    
        	System.out.println("message sent successfully");
        	return true;
        } catch (MessagingException e) {
        	e.printStackTrace();
        	return false;
        }   
    }

	@Override
	public boolean updateEmail(String username, String password, String newEmail) {
		UserCredentials creds = credentialDao.selectCredentialsByUsername(username);
		String hashedPassword = credentialDao.selectHashedPassword(username, password);
		
		if (creds.getHashedPassword().equals(hashedPassword))
		{
			creds.seteMail(newEmail);
			credentialDao.update(creds);
			return true;
		}
		else
		{
			return false;
		}
	}
}
