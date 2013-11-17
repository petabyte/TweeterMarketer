package org.tweet.marketing.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.tweet.marketing.ConsumerToken;
import org.tweet.marketing.Credential;
import org.tweet.marketing.repository.CredentialRepositoryDAO;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TweetMarketing {
	private static final String TWEETER_PROMO = "Tweeter_Promo";

	public static void main(String args[]) throws Exception {
		// The factory instance is re-useable and thread safe.
		Twitter twitter = TwitterFactory.getSingleton();
		CredentialRepositoryDAO credentialRepository = new CredentialRepositoryDAO();
		ConsumerToken consumerToken = credentialRepository
				.getConsumerToken(TWEETER_PROMO);
		twitter.setOAuthConsumer(consumerToken.getConsumerKey(),
				consumerToken.getConsumerSecret());
		RequestToken requestToken = twitter.getOAuthRequestToken();
		AccessToken accessToken = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (null == accessToken) {
			System.out
					.println("Open the following URL and grant access to your account:");
			System.out.println(requestToken.getAuthorizationURL());
			System.out
					.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
			String pin = br.readLine();
			try {
				if (pin.length() > 0) {
					accessToken = twitter
							.getOAuthAccessToken(requestToken, pin);
				} else {
					accessToken = twitter.getOAuthAccessToken();
				}
			} catch (TwitterException te) {
				if (401 == te.getStatusCode()) {
					System.out.println("Unable to get the access token.");
				} else {
					te.printStackTrace();
				}
			}
		}
		// persist to the accessToken for future reference.
		org.tweet.marketing.AccessToken accessTokenTweet = new org.tweet.marketing.AccessToken();
		String userId = Long.toString(accessToken.getUserId());
		Credential credential = new Credential();
		credential.setConsumerToken(consumerToken);
		credential.setUserId(userId);
		credential.setUserName(TWEETER_PROMO);
		accessTokenTweet.setUserId(userId);
		accessTokenTweet.setAccessKey(accessToken.getToken());
		accessTokenTweet.setAccessSecret(accessToken.getTokenSecret());
		credential.setAccessToken(accessTokenTweet);
		credentialRepository.insertCredential(credential);
		Status status = twitter.updateStatus(args[0]);
		System.out.println("Successfully updated the status to ["
				+ status.getText() + "].");
		System.exit(0);
	}

}
