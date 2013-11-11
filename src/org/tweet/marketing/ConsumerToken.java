package org.tweet.marketing;

import java.io.Serializable;

public class ConsumerToken implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5530153697223565842L;
	private String userName;
	private String consumerKey;
	private String consumerSecret;

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
