package org.tweet.marketing.test.repository;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tweet.marketing.AccessToken;
import org.tweet.marketing.ConsumerToken;
import org.tweet.marketing.Credential;
import org.tweet.marketing.repository.CredentialRepositoryDAO;

public class CredentialRepositoryTest {
    
	CredentialRepositoryDAO credentialRepository;
	AccessToken accessToken;
	ConsumerToken consumerToken;
	Credential credential;
	AccessToken accessToken1;
	ConsumerToken consumerToken1;
	Credential credential1;
	@Before
	public void setUp() throws Exception{
		credentialRepository = new CredentialRepositoryDAO();
		accessToken = new AccessToken();
		accessToken.setUserId("testUserId");
		accessToken.setAccessKey("fakeAccessKey");
		accessToken.setAccessSecret("fakeAccessSecret");
		consumerToken = new ConsumerToken();
		consumerToken.setUserName("fakeUserName");
		consumerToken.setConsumerKey("fakeConsumerKey");
		consumerToken.setConsumerSecret("fakeConsumerSecret");
		credential = new Credential();
		credential.setCampaignId(1);
		credential.setUserId("testUserId");
		credential.setUserName("fakeUserName");
		credential.setConsumerToken(consumerToken);
		credential.setAccessToken(accessToken);
		
		accessToken1 = new AccessToken();
		accessToken1.setUserId("testUserId1");
		accessToken1.setAccessKey("fakeAccessKey");
		accessToken1.setAccessSecret("fakeAccessSecret");
		consumerToken1 = new ConsumerToken();
		consumerToken1.setUserName("fakeUserName1");
		consumerToken1.setConsumerKey("fakeConsumerKey");
		consumerToken1.setConsumerSecret("fakeConsumerSecret");
		credential1 = new Credential();
		credential1.setCampaignId(2);
		credential1.setUserId("testUserId1");
		credential1.setUserName("fakeUserName1");
		credential1.setConsumerToken(consumerToken1);
		credential1.setAccessToken(accessToken1);
		

	}
	
	@After
	public void tearDown() throws Exception{
		credentialRepository.deleteConsumerToken(consumerToken);
		credentialRepository.deleteAccessToken(accessToken);
		credentialRepository.deleteCredential(credential);
		credentialRepository.deleteConsumerToken(consumerToken1);
		credentialRepository.deleteAccessToken(accessToken1);
		credentialRepository.deleteCredential(credential1);
		
	}
	
	
	@Test
	public void testStoreConsumerToken() throws Exception {	
		int returnInt = 0;
		returnInt = credentialRepository.insertConsumerToken(consumerToken);
		ConsumerToken tokenRetrieve = credentialRepository.getConsumerToken(consumerToken.getUserName());
		assertTrue(tokenRetrieve.getUserName().equalsIgnoreCase(consumerToken.getUserName()));		
		returnInt = credentialRepository.deleteConsumerToken(consumerToken);
		assertTrue(returnInt == 1);
	}

	@Test
	public void testStoreAccessToken() throws Exception {
		int returnInt = 0;
		returnInt = credentialRepository.insertAccessToken(accessToken);
		returnInt = credentialRepository.deleteAccessToken(accessToken);
		assertTrue(returnInt == 1);	
	
	}
	
	@Test
	public void testStoreCredential() throws Exception {
		int returnInt = 0;
		returnInt = credentialRepository.insertCredential(credential);
		returnInt = credentialRepository.deleteCredential(credential);
		assertTrue(returnInt == 1);	
	
	}
	
	@Test
	public void getCredential() throws Exception {
		int returnInt = 0;
		returnInt = credentialRepository.insertCredential(credential1);
		assertTrue(returnInt == 1);	
		Credential credential = credentialRepository.getCredential(2);
		assertTrue(credential.getUserId().equalsIgnoreCase("testUserId1"));
		assertTrue(credential.getUserName().equalsIgnoreCase("fakeUserName1"));
		assertTrue(credential.getConsumerToken().getConsumerKey().equalsIgnoreCase("fakeConsumerKey"));
		assertTrue(credential.getConsumerToken().getConsumerSecret().equalsIgnoreCase("fakeConsumerSecret"));
		assertTrue(credential.getAccessToken().getAccessKey().equalsIgnoreCase("fakeAccessKey"));
		assertTrue(credential.getAccessToken().getAccessSecret().equalsIgnoreCase("fakeAccessSecret"));
		returnInt = credentialRepository.deleteConsumerToken(consumerToken1);
		returnInt = credentialRepository.deleteAccessToken(accessToken1);
		returnInt = credentialRepository.deleteCredential(credential1);
		assertTrue(returnInt == 1);	
	}
	
}
