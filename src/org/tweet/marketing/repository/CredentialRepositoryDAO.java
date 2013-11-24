package org.tweet.marketing.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.tweet.marketing.AccessToken;
import org.tweet.marketing.ConsumerToken;
import org.tweet.marketing.Credential;

public class CredentialRepositoryDAO {
	private SqlSessionFactory sqlSessionFactory;

	public CredentialRepositoryDAO() {
		sqlSessionFactory = ConnectionFactory.getSession();
	}
    /**
     * 
     * @param userId
     * @return
     * @throws Exception
     */
	public Credential getCredential(int campaignId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CredentialRepositoryMapper mapper = session
					.getMapper(CredentialRepositoryMapper.class);
			Credential credential = mapper.getCredential(campaignId);
			return credential;
		} finally {
			session.close();
		}
	}
	
    /**
     * 
     * @param userId
     * @return
     * @throws Exception
     */
	public Credential getCredentialUsingUserId(String userId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CredentialRepositoryMapper mapper = session
					.getMapper(CredentialRepositoryMapper.class);
			Credential credential = mapper.getCredentialUsingUserId(userId);
			return credential;
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
	public int insertCredential(Credential credential) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CredentialRepositoryMapper mapper = session
					.getMapper(CredentialRepositoryMapper.class);
//			if(credential.getConsumerToken() != null){
//				mapper.insertConsumerToken(credential.getConsumerToken());
//			}
			if(credential.getAccessToken() != null){
				mapper.insertAccessToken(credential.getAccessToken());
			}
			int returnInt = mapper.insertCredential(credential);
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
	public int deleteCredential(Credential credential) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CredentialRepositoryMapper mapper = session
					.getMapper(CredentialRepositoryMapper.class);
//			if(credential.getConsumerToken() != null){
//				mapper.deleteConsumerToken(credential.getConsumerToken());
//			}
			if(credential.getAccessToken() != null){
				mapper.deleteAccessToken(credential.getAccessToken());
			}
			int returnInt = mapper.deleteCredential(credential);
			return returnInt;
		} finally {
			session.close();
		}
	}
	
	   /**
     * 
     * @param consumerToken
     * @return
     * @throws Exception
     */
	public ConsumerToken getConsumerToken(String userName) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CredentialRepositoryMapper mapper = session
					.getMapper(CredentialRepositoryMapper.class);
			ConsumerToken consumerToken = mapper.getConsumerToken(userName);
			return consumerToken;
		} finally {
			session.close();
		}
	}
	
	
    /**
     * 
     * @param consumerToken
     * @return
     * @throws Exception
     */
	public int insertConsumerToken(ConsumerToken consumerToken) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CredentialRepositoryMapper mapper = session
					.getMapper(CredentialRepositoryMapper.class);
			int returnInt = mapper.insertConsumerToken(consumerToken);
			return returnInt;
		} finally {
			session.close();
		}
	}
	
	/**
	 * 
	 * @param consumerToken
	 * @return
	 * @throws Exception
	 */
	public int deleteConsumerToken(ConsumerToken consumerToken) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CredentialRepositoryMapper mapper = session
					.getMapper(CredentialRepositoryMapper.class);
			int returnInt = mapper.deleteConsumerToken(consumerToken);
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
	public int insertAccessToken(AccessToken accessToken) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CredentialRepositoryMapper mapper = session
					.getMapper(CredentialRepositoryMapper.class);
			int returnInt = mapper.insertAccessToken(accessToken);
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
	public int deleteAccessToken(AccessToken accessToken) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {

			CredentialRepositoryMapper mapper = session
					.getMapper(CredentialRepositoryMapper.class);
			int returnInt = mapper.deleteAccessToken(accessToken);
			return returnInt;
		} finally {
			session.close();
		}
	}
}
