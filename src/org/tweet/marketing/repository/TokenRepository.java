package org.tweet.marketing.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.tweet.marketing.ConsumerToken;

import twitter4j.auth.AccessToken;
/**
 * 
 * @author georgesanchez
 *
 */
public class TokenRepository {
	private static final String CONSUMER_ACCESS_PROPERTIES = "resources/consumerAccess.properties";
	private static final String CONSUMER_SECRET = "_consumerSecret";
	private static final String CONSUMER_KEY = "_consumerKey";
	private static final String TOKEN_SECRET = "_TokenSecret";
	private static final String TOKEN = "_Token";
	/**
	 * consumerAccessProperties 
	 */
	private Properties consumerAccessProperties = new Properties();
    /**
     * constructor to load the properties
     * @throws IOException
     */
	public TokenRepository() throws IOException {
		FileInputStream in = new FileInputStream(CONSUMER_ACCESS_PROPERTIES);
		consumerAccessProperties.load(in);
		in.close();

	}
    /*
     *Store the AccessToken in the properties file 
     */
	public void storeAccessToken(long userId, AccessToken accessToken) throws IOException {
		consumerAccessProperties.setProperty(userId + TOKEN,
				accessToken.getToken());
		consumerAccessProperties.setProperty(userId + TOKEN_SECRET,
				accessToken.getTokenSecret());
		saveProperties();
		// store accessToken.getToken()
		// store accessToken.getTokenSecret()
	}
    /**
     * retrieve the accesstoken using user id
     * @param userId comes from Twitter Credentials
     * @return
     */
	public AccessToken getAccessToken(long userId) {
		String token = consumerAccessProperties.getProperty(userId + TOKEN);
		String tokenSecret = consumerAccessProperties.getProperty(userId
				+ TOKEN_SECRET);
		AccessToken returnAccessToken = new AccessToken(token, tokenSecret);
		return returnAccessToken;
	}
    /**
     * 
     * @return
     */
	public ConsumerToken getConsumerToken (String userName) {
		String consumerKey = consumerAccessProperties.getProperty(userName + CONSUMER_KEY);
		String consumerSecret = consumerAccessProperties.getProperty(userName
				+ CONSUMER_SECRET);
		ConsumerToken returnConsumerInfo = new ConsumerToken(consumerKey, consumerSecret);
		return returnConsumerInfo;
	}
    /**
     * 
     * @param consumerToken
     * @throws IOException 
     */
	public void storeConsumerToken(String userName, ConsumerToken consumerToken) throws IOException {
		consumerAccessProperties.setProperty(userName + CONSUMER_KEY,
				consumerToken.getConsumerKey());
		consumerAccessProperties.setProperty(userName + CONSUMER_SECRET,
				consumerToken.getConsumerSecret());
        saveProperties();
	}
	
	private void saveProperties() throws IOException{
		FileOutputStream out = new FileOutputStream(CONSUMER_ACCESS_PROPERTIES);
		consumerAccessProperties.store(out, "---No Comment---");
		out.close();
	}

}
