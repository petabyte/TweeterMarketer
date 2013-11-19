package org.tweet.marketing;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;

public class CampaignJob implements Job {
	private Campaign campaign;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			//For testing random String
			SecureRandom random = new SecureRandom();
	    	String randomString = new BigInteger(130, random).toString(32);
			StatusUpdate statusUpdate = new StatusUpdate(campaign.getTweetText() + randomString);
			//TODO Add image here
			//statusUpdate.setMedia(file);
			Status status = campaign.getTwitter().updateStatus(statusUpdate);
		} catch (TwitterException e) {
			throw new JobExecutionException(e.getMessage());
		}

	}


	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

}
