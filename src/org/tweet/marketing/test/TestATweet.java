package org.tweet.marketing.test;

import org.junit.Before;
import org.junit.Test;
import org.tweet.marketing.Credential;
import org.tweet.marketing.repository.CredentialRepositoryDAO;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TestATweet {

	private Twitter twitter = null;
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
		twitter = tf.getInstance();
		
	}
	
	@Test
	public void testTweet() throws TwitterException{

		for (int i = 0;i < 50; i++){
			Status status = twitter.updateStatus("This is my tweet SEIS635 " + i + " #thisisatweet");
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
		}
	    
	    
	}
	
	@Test
	public void testSearchTweet() throws TwitterException{
		   Query query = new Query("#thisisatweet");
		    QueryResult result = twitter.search(query);
		    for (Status status : result.getTweets()) {
		        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
		    }
	}
	

}
