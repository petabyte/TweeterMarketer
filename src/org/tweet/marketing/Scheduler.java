package org.tweet.marketing;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Class that will schedule campaigns to run
 * 
 * @author georgesanchez
 * 
 */
public class Scheduler {
	private static final String TWEET_GROUP = "TweetGroup1";
	private static final String CAMPAIGN = "campaign";
	SchedulerFactory schFactory = new StdSchedulerFactory();

	public boolean submitJob(Campaign campaign) throws SchedulerException {
		boolean returnBoolean = false;
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put(CAMPAIGN, campaign);
		JobDetail tweetJob = JobBuilder
				.newJob(CampaignJob.class)
				.withIdentity(campaign.getCredential().getUserId(), TWEET_GROUP)
				.usingJobData(jobDataMap).build();
		Trigger tweetTrigger = TriggerBuilder
				.newTrigger()
				.withSchedule(
						SimpleScheduleBuilder
								.simpleSchedule()
								.withIntervalInSeconds(
										campaign.getIntervalInSeconds())
								.repeatForever()).build();
		org.quartz.Scheduler scheduler = schFactory.getScheduler();
		if (!scheduler.isStarted()) {
			scheduler.start();
		}
		Date dateSchedule = scheduler.scheduleJob(tweetJob, tweetTrigger);
		if (dateSchedule != null) {
			returnBoolean = true;
		}
		return returnBoolean;
	}

	public boolean stopJob(Campaign campaign) throws SchedulerException {
		boolean returnBoolean = false;
		org.quartz.Scheduler scheduler = schFactory.getScheduler();
		JobKey campaignJobKey = new JobKey(
				campaign.getCredential().getUserId(), TWEET_GROUP);
		if (scheduler != null && scheduler.checkExists(campaignJobKey)) {
			scheduler.deleteJob(campaignJobKey);
			returnBoolean = true;
		}
		return returnBoolean;
	}
	
	public boolean stopScheduler() throws SchedulerException{
		boolean returnBoolean = false;
		org.quartz.Scheduler scheduler = schFactory.getScheduler();
		if(scheduler.isStarted()){
			scheduler.shutdown(true);
			returnBoolean = true;
		}
		return returnBoolean;
	}
}
