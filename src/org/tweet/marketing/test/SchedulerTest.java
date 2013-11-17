package org.tweet.marketing.test;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.quartz.SchedulerException;
import org.tweet.marketing.Campaign;
import org.tweet.marketing.Credential;
import org.tweet.marketing.Monitor;
import org.tweet.marketing.MonitorCallback;
import org.tweet.marketing.Scheduler;
import org.tweet.marketing.Scheduler.JobType;
import org.tweet.marketing.repository.CredentialRepositoryDAO;

import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SchedulerTest {
	Campaign campaign;
	Scheduler scheduler;
	Monitor monitor;
    @Before
	public void setUp() throws Exception{
		CredentialRepositoryDAO tokenDao = new CredentialRepositoryDAO();
		String userId = "1938854540";
		Credential credential = tokenDao.getCredentialUsingUserId(userId);
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
		
		monitor = new Monitor();
		monitor.setHashtagToMonitor("#fakeHashTag");
		monitor.setIntervalInSeconds(1);
		campaign.setMonitor(monitor);
		scheduler = new Scheduler();
    	
    }
    
    @After
    public void tearDown() throws SchedulerException{
    	scheduler.stopScheduler();
    }
	
    
    @Test
	public void testSubmitCampaignJob() throws SchedulerException, TwitterException, InterruptedException {
    	SecureRandom random = new SecureRandom();
    	String randomString = new BigInteger(130, random).toString(32);
		campaign.setTweetText("This is a test schedule job "+ randomString);
		boolean submitted = scheduler.submitCampaignJob(campaign);
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
 	public void testSubmitMonitorJob() throws SchedulerException, TwitterException, InterruptedException {
     	SecureRandom random = new SecureRandom();
     	String randomString = new BigInteger(130, random).toString(32);
 		campaign.setTweetText("This is a test schedule job "+ randomString);
 		monitor.setMonitorCallback(new MonitorCallback() {
			
			@Override
			public void executeCallback() {
				QueryResult queryResult  = monitor.getTwitterQueryResult();
				for (Status status : queryResult.getTweets()) {
			        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
			    }
				
			}
		});
 		boolean submittedCampaignJob = scheduler.submitCampaignJob(campaign);
 		boolean submittedMonitor = scheduler.submitMonitorJob(campaign);
 		//Sleep to so that the job will fire at least once
 		Thread.sleep(5000);
 		Status latestStatus = null;
 		if(submittedCampaignJob){
 			List<Status> statuses = campaign.getTwitter().getUserTimeline();
 			latestStatus = statuses.get(0);
 		}
 		assertTrue(latestStatus.getText().equalsIgnoreCase(campaign.getTweetText()));
 		
 	}
    
    @Test
	public void testStopCampaignJob() throws SchedulerException, TwitterException, InterruptedException {
    	SecureRandom random = new SecureRandom();
    	String randomString = new BigInteger(130, random).toString(32);
		campaign.setTweetText("This is a test schedule job "+ randomString);
		boolean submitted = scheduler.submitCampaignJob(campaign);
		//Sleep to so that the job will fire at least once
		Thread.sleep(5000);
		Status latestStatus = null;
		if(submitted){
			List<Status> statuses = campaign.getTwitter().getUserTimeline();
			latestStatus = statuses.get(0);
		}
		assertTrue(latestStatus.getText().equalsIgnoreCase(campaign.getTweetText()));
		boolean stopped = scheduler.stopJob(campaign, JobType.CAMPAIGN);
		assertTrue(stopped);
	}
    
    @Test
 	public void testStopMonitorJob() throws SchedulerException, TwitterException, InterruptedException {
     	SecureRandom random = new SecureRandom();
     	String randomString = new BigInteger(130, random).toString(32);
 		campaign.setTweetText("This is a test schedule job "+ randomString);
	    monitor.setMonitorCallback(new MonitorCallback() {
			
			@Override
			public void executeCallback() {
				QueryResult queryResult  = monitor.getTwitterQueryResult();
				for (Status status : queryResult.getTweets()) {
			        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
			    }
				
			}
		});
 		boolean submitted = scheduler.submitMonitorJob(campaign);
 		//Sleep to so that the job will fire at least once
 		Thread.sleep(5000);
 		if(submitted){
 			boolean stopped = scheduler.stopJob(campaign, JobType.MONITOR);
 	 		assertTrue(stopped);
 		}
 		
 	}
 	
	
	

}
