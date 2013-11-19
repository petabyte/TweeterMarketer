package org.tweet.marketing;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class MonitorJob implements Job {

	private Campaign monitor;
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		if(monitor.getMonitor() != null && monitor.getTwitter() != null){
			try {
			 Monitor monitorCampaign = monitor.getMonitor();
			 Twitter twitter = monitor.getTwitter();
			 Query query = new Query(monitorCampaign.getHashtagToMonitor());
			 query.setSinceId(monitorCampaign.getMaxId());
		     QueryResult result = twitter.search(query);
		     monitorCampaign.setMaxId(result.getMaxId());
		     monitorCampaign.setTwitterQueryResult(result);
		     monitorCampaign.getMonitorCallback().executeCallback();		     
			} catch (TwitterException e) {
				throw new JobExecutionException(e);
			}
			
		}
	
	}
	public Campaign getMonitor() {
		return monitor;
	}
	public void setMonitor(Campaign monitor) {
		this.monitor = monitor;
	}
  
}
