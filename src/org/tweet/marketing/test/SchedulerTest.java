package org.tweet.marketing.test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.quartz.SchedulerException;
import org.tweet.marketing.Campaign;
import org.tweet.marketing.Credential;
import org.tweet.marketing.Scheduler;
import org.tweet.marketing.repository.TokenRepositoryDAO;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SchedulerTest {
	Campaign campaign;
	Scheduler scheduler;
    @Before
	public void setUp() throws Exception{
		TokenRepositoryDAO tokenDao = new TokenRepositoryDAO();
		String userId = "1938854540";
		Credential credential = tokenDao.getCredential(userId);
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(credential.getConsumerToken().getConsumerKey())
		  .setOAuthConsumerSecret(credential.getConsumerToken().getConsumerSecret())
		  .setOAuthAccessToken(credential.getAccessToken().getAccessKey())
		  .setOAuthAccessTokenSecret(credential.getAccessToken().getAccessSecret());
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		campaign = new Campaign();
		campaign.setCredential(credential);
		campaign.setTwitter(twitter);
		campaign.setIntervalInSeconds(5);
		scheduler = new Scheduler();
    	
    }
    
    @After
    public void tearDown() throws SchedulerException{
    	scheduler.stopScheduler();
    }
	
    
    @Test
	public void testSubmitAJob() throws SchedulerException, TwitterException, InterruptedException {
    	SecureRandom random = new SecureRandom();
    	String randomString = new BigInteger(130, random).toString(32);
		campaign.setTweetText("This is a test schedule job "+ randomString);
		boolean submitted = scheduler.submitJob(campaign);
		//Sleep to so that the job will fire at least once
		Thread.sleep(5000);
		Status latestStatus = null;
		if(submitted){
			List<Status> statuses = campaign.getTwitter().getUserTimeline();
			latestStatus = statuses.get(0);
		}
		assertTrue(latestStatus.getText().equalsIgnoreCase(campaign.getTweetText()));
		
	}
    
    @Test
	public void testStopAJob() throws SchedulerException, TwitterException, InterruptedException {
    	SecureRandom random = new SecureRandom();
    	String randomString = new BigInteger(130, random).toString(32);
		campaign.setTweetText("This is a test schedule job "+ randomString);
		boolean submitted = scheduler.submitJob(campaign);
		//Sleep to so that the job will fire at least once
		Thread.sleep(5000);
		Status latestStatus = null;
		if(submitted){
			List<Status> statuses = campaign.getTwitter().getUserTimeline();
			latestStatus = statuses.get(0);
		}
		assertTrue(latestStatus.getText().equalsIgnoreCase(campaign.getTweetText()));
		boolean stopped = scheduler.stopJob(campaign);
		assertTrue(stopped);
	}
	
	

}
