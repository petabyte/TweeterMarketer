package org.tweet.marketing;

import java.io.Serializable;

public class Credential implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6081710666134494820L;
	private String userId;
	private String userName;
	private AccessToken accessToken;
	private ConsumerToken consumerToken;

	public AccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	public ConsumerToken getConsumerToken() {
		return consumerToken;
	}

	public void setConsumerToken(ConsumerToken consumerToken) {
		this.consumerToken = consumerToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
