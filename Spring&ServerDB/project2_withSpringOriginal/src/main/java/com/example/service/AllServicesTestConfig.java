package com.example.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.dao.UserCredentialsDao;
import com.example.dao.UserCredentialsDaoImpl;

@Configuration
//@ComponentScan
public class AllServicesTestConfig {
	@Bean
	UserCredentialsDao getUcDao() {
		return  new UserCredentialsDaoImpl();
	}
	@Bean
	UCService getUcService() {
		return new UCServiceImpl();
	}
}
