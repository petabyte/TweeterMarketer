package org.tweet.marketing;

import java.io.Serializable;

public class AccessToken implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9212288257793262747L;
	private String userId;
	private String accessKey;
	private String accessSecret;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getAccessSecret() {
		return accessSecret;
	}

	public void setAccessSecret(String accessSecret) {
		this.accessSecret = accessSecret;
	}

}
