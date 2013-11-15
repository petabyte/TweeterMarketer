package org.tweet.marketing;

import java.io.Serializable;

import twitter4j.Twitter;

public class Campaign implements Serializable {
	/**
	 * comment
	 */
	private static final long serialVersionUID = 4024558909646088878L;
	private String tweetText;
	private Credential credential;
	private int intervalInSeconds;
	private Twitter twitter;
	private Monitor monitor;

	public String getTweetText() {
		return tweetText;
	}

	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public int getIntervalInSeconds() {
		return intervalInSeconds;
	}

	public void setIntervalInSeconds(int intervalInSeconds) {
		this.intervalInSeconds = intervalInSeconds;
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
}
