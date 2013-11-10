package org.tweet.marketing.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.tweet.marketing.AccessToken;
import org.tweet.marketing.ConsumerToken;
import org.tweet.marketing.Credential;

public class TokenRepositoryDAO {
	private SqlSessionFactory sqlSessionFactory;

	public TokenRepositoryDAO() {
		sqlSessionFactory = ConnectionFactory.getSession();
	}
    /**
     * 
     * @param userId
     * @return
     * @throws Exception
     */
	public Credential getCredential(String userId) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			TokenRepositoryMapper mapper = session
					.getMapper(TokenRepositoryMapper.class);
			Credential credential = mapper.getCredential(userId);
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

			TokenRepositoryMapper mapper = session
					.getMapper(TokenRepositoryMapper.class);
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

			TokenRepositoryMapper mapper = session
					.getMapper(TokenRepositoryMapper.class);
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
		SqlSession session = sqlSessionFactory.openSession();
		try {

			TokenRepositoryMapper mapper = session
					.getMapper(TokenRepositoryMapper.class);
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

			TokenRepositoryMapper mapper = session
					.getMapper(TokenRepositoryMapper.class);
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

			TokenRepositoryMapper mapper = session
					.getMapper(TokenRepositoryMapper.class);
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

			TokenRepositoryMapper mapper = session
					.getMapper(TokenRepositoryMapper.class);
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

			TokenRepositoryMapper mapper = session
					.getMapper(TokenRepositoryMapper.class);
			int returnInt = mapper.deleteAccessToken(accessToken);
			return returnInt;
		} finally {
			session.close();
		}
	}
}
