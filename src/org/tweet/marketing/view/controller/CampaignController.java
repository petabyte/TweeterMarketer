package org.tweet.marketing.view.controller;

import java.util.List;

import org.tweet.marketing.Campaign;
import org.tweet.marketing.Scheduler;
import org.tweet.marketing.Scheduler.JobType;
import org.tweet.marketing.repository.CampaignRepositoryDAO;

public class CampaignController {
   private CampaignRepositoryDAO campaignRepository;
   private Scheduler scheduler;
   public CampaignController(){
	   campaignRepository = new CampaignRepositoryDAO();
	   scheduler = new Scheduler();
   }
   
   public List<Campaign> getListOfCampaigns() throws Exception{
	   return campaignRepository.getAllCampaign();
   }
   
   public Campaign getCampaign(int id) throws Exception{
	   return campaignRepository.getCampaign(id);
   }
   
   public void submitMonitoringJob (Campaign campaign) throws Exception{
	   scheduler.submitMonitorJob(campaign);
   }
   
   public void submitCampaignJob (Campaign campaign) throws Exception{
	   scheduler.submitCampaignJob(campaign);
   }
   
   public void stopMonitoringJob (Campaign campaign) throws Exception{
	   scheduler.stopJob(campaign, JobType.MONITOR);
   }
   
   public void stopCampaignJob (Campaign campaign) throws Exception{
	   scheduler.stopJob(campaign, JobType.CAMPAIGN);
   }

	public Campaign addCampaign(Campaign campaign) throws Exception {
		return campaignRepository.insertCampaign(campaign);
	}
}
