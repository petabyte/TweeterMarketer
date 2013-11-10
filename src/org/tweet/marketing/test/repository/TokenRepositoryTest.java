package org.tweet.marketing.test.repository;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tweet.marketing.AccessToken;
import org.tweet.marketing.ConsumerToken;
import org.tweet.marketing.Credential;
import org.tweet.marketing.repository.TokenRepositoryDAO;

public class TokenRepositoryTest {
    
	TokenRepositoryDAO tokenRepository;
	AccessToken accessToken;
	ConsumerToken consumerToken;
	Credential credential;
	AccessToken accessToken1;
	ConsumerToken consumerToken1;
	Credential credential1;
	@Before
	public void setUp() throws Exception{
		tokenRepository = new TokenRepositoryDAO();
		accessToken = new AccessToken();
		accessToken.setUserId("testUserId");
		accessToken.setAccessKey("fakeAccessKey");
		accessToken.setAccessSecret("fakeAccessSecret");
		consumerToken = new ConsumerToken();
		consumerToken.setUserName("fakeUserName");
		consumerToken.setConsumerKey("fakeConsumerKey");
		consumerToken.setConsumerSecret("fakeConsumerSecret");
		credential = new Credential();
		credential.setUserId("testUserId");
		credential.setUserName("fakeUserName");
		accessToken1 = new AccessToken();
		accessToken1.setUserId("testUserId1");
		accessToken1.setAccessKey("fakeAccessKey");
		accessToken1.setAccessSecret("fakeAccessSecret");
		consumerToken1 = new ConsumerToken();
		consumerToken1.setUserName("fakeUserName1");
		consumerToken1.setConsumerKey("fakeConsumerKey");
		consumerToken1.setConsumerSecret("fakeConsumerSecret");
		credential1 = new Credential();
		credential1.setUserId("testUserId1");
		credential1.setUserName("fakeUserName1");
		

	}
	
	@After
	public void tearDown() throws Exception{
		tokenRepository.deleteConsumerToken(consumerToken);
		tokenRepository.deleteAccessToken(accessToken);
		tokenRepository.deleteCredential(credential);
		tokenRepository.deleteConsumerToken(consumerToken1);
		tokenRepository.deleteAccessToken(accessToken1);
		tokenRepository.deleteCredential(credential1);
		
	}
	
	
	@Test
	public void testStoreConsumerToken() throws Exception {	
		int returnInt = 0;
		returnInt = tokenRepository.insertConsumerToken(consumerToken);
		ConsumerToken tokenRetrieve = tokenRepository.getConsumerToken(consumerToken.getUserName());
		assertTrue(tokenRetrieve.getUserName().equalsIgnoreCase(consumerToken.getUserName()));		
		returnInt = tokenRepository.deleteConsumerToken(consumerToken);
		assertTrue(returnInt == 1);
	}

	@Test
	public void testStoreAccessToken() throws Exception {
		int returnInt = 0;
		returnInt = tokenRepository.insertAccessToken(accessToken);
		returnInt = tokenRepository.deleteAccessToken(accessToken);
		assertTrue(returnInt == 1);	
	
	}
	
	@Test
	public void testStoreCredential() throws Exception {
		int returnInt = 0;
		returnInt = tokenRepository.insertCredential(credential);
		returnInt = tokenRepository.deleteCredential(credential);
		assertTrue(returnInt == 1);	
	
	}
	
	@Test
	public void getCredential() throws Exception {
		int returnInt = 0;
		returnInt = tokenRepository.insertConsumerToken(consumerToken1);
		returnInt = tokenRepository.insertAccessToken(accessToken1);
		returnInt = tokenRepository.insertCredential(credential1);
		assertTrue(returnInt == 1);	
		Credential credential = tokenRepository.getCredential("testUserId1");
		assertTrue(credential.getUserId().equalsIgnoreCase("testUserId1"));
		assertTrue(credential.getUserName().equalsIgnoreCase("fakeUserName1"));
		assertTrue(credential.getConsumerToken().getConsumerKey().equalsIgnoreCase("fakeConsumerKey"));
		assertTrue(credential.getConsumerToken().getConsumerSecret().equalsIgnoreCase("fakeConsumerSecret"));
		assertTrue(credential.getAccessToken().getAccessKey().equalsIgnoreCase("fakeAccessKey"));
		assertTrue(credential.getAccessToken().getAccessSecret().equalsIgnoreCase("fakeAccessSecret"));
		returnInt = tokenRepository.deleteConsumerToken(consumerToken1);
		returnInt = tokenRepository.deleteAccessToken(accessToken1);
		returnInt = tokenRepository.deleteCredential(credential1);
		assertTrue(returnInt == 1);	
	}
	
}
