package org.tweet.marketing.test;

import org.junit.Before;
import org.junit.Test;

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
	public void setUp(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("jtUPig5EFBlFdV0kVCANLQ")
		  .setOAuthConsumerSecret("4zvp69Rq42z3go2YipxU1aySYtpTN0ralvnUEBZWI")
		  .setOAuthAccessToken("321402005-EdF998r662mhOPbaSIi2nWCKNXboCKJm5L7wLyUf")
		  .setOAuthAccessTokenSecret("qwb9ggeBurRUJyxQ7IuvNIlboHZvCi85qkelFdKh3o");
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
		
	}
	
	@Test
	public void testTweet() throws TwitterException{

	    Status status = twitter.updateStatus("I am in SEIS635 right now!");
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}
	
	@Test
	public void testSearchTweet() throws TwitterException{
		   Query query = new Query("source:petabyteflop This rocks!");
		    QueryResult result = twitter.search(query);
		    for (Status status : result.getTweets()) {
		        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
		    }
	}
	

}
