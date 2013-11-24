package org.tweet.marketing.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.tweet.marketing.AccessToken;
import org.tweet.marketing.ConsumerToken;
import org.tweet.marketing.Credential;

/**
 * 
 * @author georgesanchez
 *
 */
public interface CredentialRepositoryMapper {
	/**
	 * consumerAccessProperties 
	 */
     String GET_ACCESS_TOKEN = "SELECT * FROM ACCESS_TOKEN WHERE TW_USER_ID = #{userId}";
     String INSERT_ACCESS_TOKEN = "INSERT INTO ACCESS_TOKEN (TW_USER_ID, TW_ACCESS_KEY, TW_ACCESS_SECRET, TW_USER_NAME) VALUES (#{userId},#{accessKey},#{accessSecret},#{userName})";
     String DELETE_ACCESS_TOKEN = "DELETE FROM ACCESS_TOKEN WHERE TW_USER_ID = #{userId}";
     String GET_CONSUMER_TOKEN ="SELECT * FROM CONSUMER_TOKEN WHERE TW_USER_NAME = 'Tweeter_Promo'";
     String INSERT_CONSUMER_TOKEN = "INSERT INTO CONSUMER_TOKEN (TW_USER_NAME, TW_CONSUMER_KEY, TW_CONSUMER_SECRET) VALUES (#{userName}, #{consumerKey},#{consumerSecret})";
     String DELETE_CONSUMER_TOKEN = "DELETE FROM CONSUMER_TOKEN WHERE TW_USER_NAME = #{userName}";
     

    /*
     *Store the AccessToken in the properties file 
     */
    @Select("SELECT TW_USER_ID, TW_USER_NAME FROM CREDENTIAL WHERE TW_CAMP_ID = #{campaignId}") 
    @Results(value = {
    		@Result(property="userId", column="TW_USER_ID"),
    		@Result(property="userName", column="TW_USER_NAME"),
    		@Result(property="accessToken", column="TW_USER_ID", javaType=AccessToken.class,one=@One(select="getAccessToken")),
    		@Result(property="consumerToken", column="TW_USER_NAME", javaType=ConsumerToken.class,one=@One(select="getConsumerToken"))
    		})
	public Credential getCredential(int campaignId) throws Exception;
    
    /*
     *Store the AccessToken in the properties file 
     */
    @Select("SELECT TW_USER_ID, TW_USER_NAME FROM CREDENTIAL WHERE TW_USER_ID = #{userId}") 
    @Results(value = {
    		@Result(property="userId", column="TW_USER_ID"),
    		@Result(property="userName", column="TW_USER_NAME"),
    		@Result(property="accessToken", column="TW_USER_ID", javaType=AccessToken.class,one=@One(select="getAccessToken")),
    		@Result(property="consumerToken", column="TW_USER_NAME", javaType=ConsumerToken.class,one=@One(select="getConsumerToken"))
    		})
	public Credential getCredentialUsingUserId(String userId) throws Exception;
    
    @Insert("INSERT INTO CREDENTIAL (TW_USER_ID, TW_USER_NAME, TW_CAMP_ID) VALUES (#{userId}, #{userName},#{campaignId})")
    public int insertCredential(Credential credential) throws Exception;
    
    @Delete("DELETE FROM CREDENTIAL WHERE TW_CAMP_ID = #{campaignId}")
    public int deleteCredential(Credential credential) throws Exception;
    /**
     * 
     * @param userName
     * @return
     */
    @Select(GET_CONSUMER_TOKEN)
    @Results(value={
    		@Result(property="userName", column="TW_USER_NAME"),
    		@Result(property="consumerKey", column="TW_CONSUMER_KEY"),
    		@Result(property="consumerSecret", column="TW_CONSUMER_SECRET"),
    		})
	public ConsumerToken getConsumerToken(String userName);
    
    @Insert(INSERT_CONSUMER_TOKEN)
    public int insertConsumerToken(ConsumerToken consumerToken);
    
    @Delete(DELETE_CONSUMER_TOKEN)
    public int deleteConsumerToken(ConsumerToken consumerToken);
    
    /**
     * 
     * @param userId
     * @return
     */
    @Select(GET_ACCESS_TOKEN)
    @Results(value={
    		@Result(property="userId", column="TW_USER_ID"),
    		@Result(property="accessKey", column="TW_ACCESS_KEY"),
    		@Result(property="accessSecret", column="TW_ACCESS_SECRET"),
    		@Result(property="userName", column="TW_USER_NAME")
    		})
	public AccessToken getAccessToken(String userId);
    
    @Insert(INSERT_ACCESS_TOKEN)
    public int insertAccessToken(AccessToken accessToken);
    
    @Delete(DELETE_ACCESS_TOKEN)
    public int deleteAccessToken(AccessToken accessToken);
   
}
