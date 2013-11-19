package org.tweet.marketing.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.tweet.marketing.AccessToken;
import org.tweet.marketing.Campaign;
import org.tweet.marketing.Monitor;

/**
 * 
 * @author georgesanchez
 *
 */
public interface CampaignRepositoryMapper {
	
	/**
	 * consumerAccessProperties 
	 */
     String GET_MONITOR = "SELECT * FROM MONITOR WHERE TW_CAMP_ID = #{campaignId}";
     String INSERT_MONITOR = "INSERT INTO MONITOR (TW_MON_HASHTAG, TW_MON_INTERVAL, TW_CAMP_ID) VALUES (#{hashtagToMonitor},#{intervalInSeconds},#{campaignId})";
     String DELETE_MONITOR = "DELETE FROM MONITOR WHERE TW_MON_ID =#{monitorId} AND TW_CAMP_ID = #{campaignId}";
     String GET_ALL_CAMPAIGN = "SELECT * FROM CAMPAIGN";
     String GET_CAMPAIGN = "SELECT * FROM CAMPAIGN WHERE TW_CAMP_ID = #{id}";
     String INSERT_CAMPAIGN = "INSERT INTO CAMPAIGN (TW_CAMP_NAME,TW_CAMP_TEXT, TW_CAMP_INTERVAL) VALUES (#{name}, #{tweetText},#{intervalInSeconds})";
     String DELETE_CAMPAIGN = "DELETE FROM CAMPAIGN WHERE TW_CAMP_ID = #{id}";

     /*
      *Get Monitor 
      */
     @Select(GET_MONITOR) 
     @Results(value = {
    		@Result(property="hashtagToMonitor", column="TW_MON_HASHTAG"),
     		@Result(property="monitorId", column="TW_MON_ID"),
     		@Result(property="intervalInSeconds", column="TW_MON_INTERVAL"),
     		@Result(property="campaignId", column="TW_CAMP_ID"),
     		})
 	public Monitor getMonitor(int campaignId) throws Exception;
     
     @Insert(INSERT_MONITOR)
     @Options(useGeneratedKeys=true, keyProperty="monitorId")
     public int insertMonitor(Monitor monitor) throws Exception;
     
     @Delete(DELETE_MONITOR)
     public int deleteMonitor(Monitor monitor) throws Exception;
     
     /*
      *Get Monitor 
      */
     @Select(GET_CAMPAIGN) 
     @Results(value = {
    		@Result(property="id", column="TW_CAMP_ID"), 
    		@Result(property="name", column="TW_CAMP_NAME"),
     		@Result(property="tweetText", column="TW_CAMP_TEXT"),
     		@Result(property="intervalInSeconds", column="TW_CAMP_INTERVAL"),
     		@Result(property="monitor", column="TW_CAMP_ID", javaType=Monitor.class,one=@One(select="getMonitor"))
     		})
   
 	public Campaign getCampaign(int id) throws Exception;
     
     /*
      *Get Monitor 
      */
     @Select(GET_ALL_CAMPAIGN) 
     @Results(value = {
    		@Result(property="id", column="TW_CAMP_ID"), 
    		@Result(property="name", column="TW_CAMP_NAME"),
     		@Result(property="tweetText", column="TW_CAMP_TEXT"),
     		@Result(property="intervalInSeconds", column="TW_CAMP_INTERVAL"),
     		@Result(property="monitor", column="TW_CAMP_ID", javaType=Monitor.class,one=@One(select="getMonitor"))
     		})
   
 	public List<Campaign> getAllCampaign() throws Exception;
     
     
     @Insert(INSERT_CAMPAIGN)
     @Options(useGeneratedKeys=true, keyProperty="id")
     public int insertCampaign(Campaign campaign) throws Exception;
     
     @Delete(DELETE_CAMPAIGN)
     public int deleteCampaign(Campaign campaign) throws Exception;
}
