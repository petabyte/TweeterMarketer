package org.tweet.marketing.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.tweet.marketing.Campaign;
import org.tweet.marketing.Credential;
import org.tweet.marketing.Monitor;

public class CampaignRepositoryDAO {
	private SqlSessionFactory sqlSessionFactory;
	private CredentialRepositoryDAO credentialRepository;

	public CampaignRepositoryDAO() {
		sqlSessionFactory = ConnectionFactory.getSession();
		credentialRepository = new CredentialRepositoryDAO();
	}
    /**
     * 
     * @param campaign
     * @return
     * @throws Exception
     */
	public Campaign getCampaign(int id) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CampaignRepositoryMapper mapper = session
					.getMapper(CampaignRepositoryMapper.class);
			Campaign returnCampaign = mapper.getCampaign(id);
			Credential credential = credentialRepository.getCredential(id);
			if (credential != null){
				returnCampaign.setCredential(credential);
			}
			return returnCampaign;
		} finally {
			session.close();
		}
	}
	
	  /**
     * 
     * @param
     * @return
     * @throws Exception
     */
	public List<Campaign> getAllCampaign() throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CampaignRepositoryMapper mapper = session
					.getMapper(CampaignRepositoryMapper.class);
			List<Campaign> returnCampaigns = mapper.getAllCampaign();
			return returnCampaigns;
		} finally {
			session.close();
		}
	}
	
    /**
     * 
     * @param campaign
     * @return
     * @throws Exception
     */
	public Campaign insertCampaign(Campaign campaign) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CampaignRepositoryMapper mapper = session .getMapper(CampaignRepositoryMapper.class);
			int returnInt = mapper.insertCampaign(campaign);
			if(campaign.getMonitor() != null && returnInt > 0){
				campaign.getMonitor().setCampaignId(campaign.getId());
				mapper.insertMonitor(campaign.getMonitor());
			}
			if(campaign.getCredential() != null && returnInt > 0){
				campaign.getCredential().setCampaignId(campaign.getId());
				credentialRepository.insertCredential(campaign.getCredential());
			}
			return campaign;
		} finally {
			session.close();
		}
	}
	
    /**
     * 
     * @param campaign
     * @return
     * @throws Exception
     */
	public int deleteCampaign(Campaign campaign) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CampaignRepositoryMapper mapper = session.getMapper(CampaignRepositoryMapper.class);
			
			if(campaign.getMonitor() != null){
				mapper.deleteMonitor(campaign.getMonitor());
			}
			if(campaign.getCredential() != null){
				credentialRepository.deleteCredential(campaign.getCredential());
			}
			int returnInt = mapper.deleteCampaign(campaign);
			return returnInt;
		} finally {
			session.close();
		}
	}
	
    /**
     * 
     * @param campaignId
     * @return
     * @throws Exception
     */
	public Monitor getMonitor(int campaignId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			CampaignRepositoryMapper mapper = session
					.getMapper(CampaignRepositoryMapper.class);
			Monitor monitor = mapper.getMonitor(campaignId);
			return monitor;
		} finally {
			session.close();
		}
	}
    /**
     * 
     * @param monitor
     * @return
     * @throws Exception
     */
	public int insertMonitor(Monitor monitor) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CampaignRepositoryMapper mapper = session
					.getMapper(CampaignRepositoryMapper.class);
			int returnInt = mapper.insertMonitor(monitor);
			return returnInt;
		} finally {
			session.close();
		}
	}
	
    /**
     * 
     * @param credential
     * @return
     * @throws Exception
     */
	public int deleteMonitor(Monitor monitor) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CampaignRepositoryMapper mapper = session
					.getMapper(CampaignRepositoryMapper.class);
			int returnInt = mapper.deleteMonitor(monitor);
			return returnInt;
		} finally {
			session.close();
		}
	}
	
	
	
}
