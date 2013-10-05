package org.tweet.marketing.test.repository;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tweet.marketing.ConsumerInformation;
import org.tweet.marketing.repository.TokenRepository;

import twitter4j.auth.AccessToken;

public class TokenRepositoryTest {
    
	private static final String CONSUMER_ACCESS_PROPERTIES = "resources/consumerAccess.properties";
	TokenRepository tokenRepository;
	Properties oldProperties;
	@Before
	public void setUp() throws IOException{
		FileInputStream in = new FileInputStream(CONSUMER_ACCESS_PROPERTIES);
		oldProperties = new Properties();
		oldProperties.load(in);
		in.close();
		tokenRepository = new TokenRepository();

	}
	
	@After
	public void tearDown() throws IOException{
		FileOutputStream out = new FileOutputStream(CONSUMER_ACCESS_PROPERTIES);
		oldProperties.store(out, "---No Comment---");
		out.close();
	}
	
	
	@Test
	public void testStoreAccessToken() throws IOException {
		AccessToken accessToken = new AccessToken("fakeToken", "fakeSecret");
		int mockUserId = 1234;
		tokenRepository.storeAccessToken(mockUserId, accessToken);
		AccessToken returnTokenAccess = tokenRepository.getAccessToken(mockUserId);
		assertTrue(accessToken.getToken().equalsIgnoreCase(returnTokenAccess.getToken()));
		assertTrue(accessToken.getTokenSecret().equalsIgnoreCase(returnTokenAccess.getTokenSecret()));	
	
	}

	@Test
	public void testStoreConsumerInformation() throws IOException {
		ConsumerInformation consumerInformation = new ConsumerInformation("fakeConsumerKey", "fakeConsumerSecret");
		String userName = "fakeUserName";
		tokenRepository.storeConsumerInformation(userName, consumerInformation);
		ConsumerInformation returnconsumerInformation = tokenRepository.getConsumerInfo(userName);
		assertTrue(consumerInformation.getConsumerKey().equalsIgnoreCase(returnconsumerInformation.getConsumerKey()));
		assertTrue(consumerInformation.getConsumerSecret().equalsIgnoreCase(returnconsumerInformation.getConsumerSecret()));	
	
	}

}
