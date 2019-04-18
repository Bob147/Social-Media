package com.example.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.UserCredentials;

@Transactional
@Repository("credentialDao")
public class UserCredentialsDaoImpl implements UserCredentialsDao
{
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal signIn(String username, String password) {
		List<Object> userCreds = sesFact.getCurrentSession()
				.createSQLQuery("select USER_PROFILE_FK from User_Credentials where username='" + username + 
						"' AND Hashed_password =(SELECT GET_HASHED_PASSWORD('"
					+ username + "', '" + password + "') FROM DUAL)").list();
	System.out.println(userCreds);
	if (userCreds==null||userCreds.size()==0)
		return BigDecimal.ZERO;
	
	else
		return (BigDecimal) userCreds.get(0);
	
}
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	private SessionFactory sesFact;
	
	public UserCredentialsDaoImpl() {}

	public UserCredentialsDaoImpl(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}

	public SessionFactory getSesFact() {
		return sesFact;
	}

	public void setSesFact(SessionFactory sesFact) {
		this.sesFact = sesFact;
	}

	
	public void insert(UserCredentials myCred)
	{
		sesFact.getCurrentSession().save(myCred);
	}
	
	public void update(UserCredentials myCred)
	{
		sesFact.getCurrentSession().update(myCred);
	}
	
	public UserCredentials selectById(int id)
	{
		return sesFact.getCurrentSession().get(UserCredentials.class, id);
	}
	
	public List<UserCredentials> selectAll()
	{
		return sesFact.getCurrentSession().createQuery("from UserCredentials",UserCredentials.class).list();
	}
	
	@Override
	public UserCredentials selectCredentialsByUsername(String username)
	{
		List<UserCredentials> creds = sesFact.getCurrentSession().
				createQuery("from UserCredentials where username='" + username + "'", 
						UserCredentials.class).list();
		
		if (creds.size()==0)
		{
			return null;
		}
		else
		{
			return creds.get(0);
		}
	}
	
	@Override
	public String selectHashedPassword(String username, String password)
	{
		List<Object> hashPass = sesFact.getCurrentSession()
					.createSQLQuery("SELECT GET_HASHED_PASSWORD('"
						+ username + "', '" + password + "') FROM DUAL").list();
		
		if (hashPass.size()==0)
		{
			return null;
		}
		else
		{
			return (String) hashPass.get(0);
		}
	}
	

	////// TO DO
	//////	|
	//////	V
	


	@Override
	public String signUp(String userName, String password, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changePassword(int userId, String InputOldPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
