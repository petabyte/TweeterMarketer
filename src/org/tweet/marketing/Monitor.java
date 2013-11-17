package org.tweet.marketing;

import twitter4j.QueryResult;

public class Monitor {
	private int campaignId;
	private int monitorId;
	private String hashtagToMonitor;
	private QueryResult twitterQueryResult;
	private int intervalInSeconds;

	public QueryResult getTwitterQueryResult() {
		return twitterQueryResult;
	}

	public void setTwitterQueryResult(QueryResult twitterQueryResult) {
		this.twitterQueryResult = twitterQueryResult;
	}

	public int getIntervalInSeconds() {
		return intervalInSeconds;
	}

	public void setIntervalInSeconds(int intervalInSeconds) {
		this.intervalInSeconds = intervalInSeconds;
	}

	public String getHashtagToMonitor() {
		return hashtagToMonitor;
	}

	public void setHashtagToMonitor(String hashtagToMonitor) {
		this.hashtagToMonitor = hashtagToMonitor;
	}

	public int getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(int monitorId) {
		this.monitorId = monitorId;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
}
