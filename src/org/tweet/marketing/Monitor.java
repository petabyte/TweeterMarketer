package org.tweet.marketing;

import twitter4j.QueryResult;

public class Monitor {
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
}
