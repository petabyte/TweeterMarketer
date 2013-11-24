package org.tweet.marketing.view.controller;

import org.tweet.marketing.Campaign;
import org.tweet.marketing.ConsumerToken;
import org.tweet.marketing.Credential;
import org.tweet.marketing.repository.CampaignRepositoryDAO;
import org.tweet.marketing.repository.CredentialRepositoryDAO;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class AddUserController {
	private CredentialRepositoryDAO credentialRepository = new CredentialRepositoryDAO();
	private CampaignRepositoryDAO campaignRepository = new CampaignRepositoryDAO();
	private static final String TWEETER_PROMO = "Tweeter_Promo";
	private ConsumerToken consumerToken;
	private Twitter twitter;
	
	
	public AddUserController() throws Exception{
		consumerToken = credentialRepository.getConsumerToken(TWEETER_PROMO);
		configureTwitterInstance();
	}
	public Campaign getCampaign (int campaignId) throws Exception{
		return campaignRepository.getCampaign(campaignId);
	}
	
	public RequestToken getRequestToken() throws Exception{
		return twitter.getOAuthRequestToken();
	}
	
	public void authorizeApp(int campaignId, String pin, String userName, RequestToken requestToken) throws Exception{
		if(pin != null){
				 AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pin);	
				if(accessToken != null){
					org.tweet.marketing.AccessToken accessTokenTweet = new org.tweet.marketing.AccessToken();
					String userId = Long.toString(accessToken.getUserId());
					Credential credential = new Credential();
					credential.setConsumerToken(consumerToken);
					credential.setUserId(userId);
					credential.setUserName(userName);	
					credential.setCampaignId(campaignId);
					accessTokenTweet.setUserName(userName);
					accessTokenTweet.setUserId(userId);
					accessTokenTweet.setAccessKey(accessToken.getToken());
					accessTokenTweet.setAccessSecret(accessToken.getTokenSecret());
					credential.setAccessToken(accessTokenTweet);
					credentialRepository.deleteCredential(credential);
					credentialRepository.insertCredential(credential);
				}

		}
	
	}
	
	public void configureTwitterInstance(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(consumerToken.getConsumerKey())
		  .setOAuthConsumerSecret(consumerToken.getConsumerSecret())
		  .setUseSSL(true);
		   TwitterFactory tf = new TwitterFactory(cb.build());
		   twitter = tf.getInstance();
	}
}
