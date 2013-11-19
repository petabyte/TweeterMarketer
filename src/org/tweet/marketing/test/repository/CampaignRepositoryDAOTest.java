package org.tweet.marketing.test.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tweet.marketing.AccessToken;
import org.tweet.marketing.Campaign;
import org.tweet.marketing.ConsumerToken;
import org.tweet.marketing.Credential;
import org.tweet.marketing.Monitor;
import org.tweet.marketing.repository.CampaignRepositoryDAO;
import org.tweet.marketing.repository.CredentialRepositoryDAO;

public class CampaignRepositoryDAOTest {
    
	CampaignRepositoryDAO campaignRepository;
	Monitor monitor;
	Campaign campaign;
	AccessToken accessToken;
	ConsumerToken consumerToken;
	Credential credential;
	
	@Before
	public void setUp() throws Exception{
		campaignRepository = new CampaignRepositoryDAO();
		//Setup Monitor
		monitor = new Monitor();
		monitor.setHashtagToMonitor("#fakeHashTag");
		monitor.setIntervalInSeconds(4);
		
		//Setup Credential
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
		//Set Up Campaign
		campaign = new Campaign();
		campaign.setIntervalInSeconds(5);
		campaign.setName("fakeCampaign");
		campaign.setTweetText("This is the tweet");
		campaign.setMonitor(monitor);
		
		campaign.setCredential(credential);
	}
	
	@After
	public void tearDown() throws Exception{
		campaignRepository.deleteCampaign(campaign);		
	}
	
	@Test
	public void getListOfCampaign () throws Exception{
	
		Campaign campaign1 = new Campaign();
		campaign1.setIntervalInSeconds(5);
		campaign1.setName("fakeCampaign1");
		campaign1.setTweetText("This is the tweet");
		campaign1.setMonitor(monitor);
		
		Campaign campaign2 = new Campaign();
		campaign2.setIntervalInSeconds(5);
		campaign2.setName("fakeCampaign2");
		campaign2.setTweetText("This is the tweet");
		campaign2.setMonitor(monitor);
		
		
		int returnInt = 0;
		Campaign returnCampaign1 = campaignRepository.insertCampaign(campaign1);
		assertTrue(returnCampaign1.getId() > 0);
		Campaign returnCampaign2 = campaignRepository.insertCampaign(campaign2);
		assertTrue(returnCampaign2.getId() > 0);
		List<Campaign> campaignList = campaignRepository.getAllCampaign();
		assertTrue(campaignList.size() == 2);
		returnInt = campaignRepository.deleteCampaign(campaign1);
		assertTrue(returnInt > 0);
		returnInt = campaignRepository.deleteCampaign(campaign2);
		assertTrue(returnInt > 0);
		
	}
	
	@Test 
	public void insertTestCampaignForUI() throws Exception{
		CredentialRepositoryDAO tokenDao = new CredentialRepositoryDAO();
		String userId = "1938854540";
		Credential credential = tokenDao.getCredentialUsingUserId(userId);
		//Setup Monitor
		Monitor newMonitor = new Monitor();
		newMonitor.setHashtagToMonitor("#fakeHashTag");
		newMonitor.setIntervalInSeconds(4);
		
		Campaign newCampaign = new Campaign();
		newCampaign.setIntervalInSeconds(5);
		newCampaign.setName("fakeCampaign");
		newCampaign.setTweetText("This is the tweet Test!");
		newCampaign.setMonitor(newMonitor);
		newCampaign.setCredential(credential);
		
		tokenDao.deleteCredential(credential);
		campaignRepository.insertCampaign(newCampaign);
		
		
	}
	
	
	@Test
	public void testStoreCampaign() throws Exception{
		int returnInt = 0;
		Campaign returnCampaign = campaignRepository.insertCampaign(campaign);
		assertTrue(returnCampaign.getId() > 0);
		returnInt = campaignRepository.deleteCampaign(campaign);
		assertTrue(returnInt > 0);
	}
	
	
	@Test
	public void testGetCampaign() throws Exception {	
		int returnInt = 0;
		Campaign returnCampaign = campaignRepository.insertCampaign(campaign);
		assertTrue(returnCampaign.getId() > 0);
		assertTrue(returnCampaign.getHashtag().equalsIgnoreCase(monitor.getHashtagToMonitor()));
		assertTrue(returnCampaign.getCredential().getConsumerToken().getConsumerKey().equalsIgnoreCase(consumerToken.getConsumerKey()));
		assertTrue(returnCampaign.getCredential().getAccessToken().getAccessKey().equalsIgnoreCase(accessToken.getAccessKey()));
		returnInt = campaignRepository.deleteCampaign(campaign);
		assertTrue(returnInt > 0);
	}

	
}
