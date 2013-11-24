package org.tweet.marketing.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.tweet.marketing.Campaign;
import org.tweet.marketing.MonitorCallback;
import org.tweet.marketing.view.controller.CampaignController;

import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweeterMarketerApp {

	private JFrame frame;
	private CampaignController campaignController;
	private Campaign campaign;
	private AddUserView addUserView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TweeterMarketerApp window = new TweeterMarketerApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TweeterMarketerApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		campaignController = new CampaignController();
	
	
		frame = new JFrame();
		frame.setBounds(100, 100, 633, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Monitor Tweet:");
		lblNewLabel.setBounds(216, 6, 102, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(218, 28, 409, 370);
		frame.getContentPane().add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		try {
			campaign = campaignController.getCampaign(campaignController.getListOfCampaigns().get(0).getId());
			if(campaign.getCredential() != null){
				ConfigurationBuilder cb = new ConfigurationBuilder();
				cb.setDebugEnabled(true)
				  .setOAuthConsumerKey(campaign.getCredential().getConsumerToken().getConsumerKey())
				  .setOAuthConsumerSecret(campaign.getCredential().getConsumerToken().getConsumerSecret())
				  .setOAuthAccessToken(campaign.getCredential().getAccessToken().getAccessKey())
				  .setOAuthAccessTokenSecret(campaign.getCredential().getAccessToken().getAccessSecret());
				   TwitterFactory tf = new TwitterFactory(cb.build());
				   Twitter twitter = tf.getInstance();
				   campaign.setTwitter(twitter);
				   campaign.getMonitor().setMonitorCallback(new MonitorCallback() {
					
					@Override
					public void executeCallback() {
						QueryResult queryResult  = campaign.getMonitor().getTwitterQueryResult();
						for (Status status : queryResult.getTweets()) {
							textArea.append( status.getCreatedAt() + ": @" + status.getUser().getScreenName() + ":" + status.getText() + "\n");
					    }
						
						
					}
				});
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JButton btnNewButton = new JButton("Start Monitoring");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					campaignController.submitMonitoringJob(campaign);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(21, 307, 146, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnStopMonitoring = new JButton("Stop Monitoring");
		btnStopMonitoring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					campaignController.stopMonitoringJob(campaign);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStopMonitoring.setBounds(21, 348, 146, 29);
		frame.getContentPane().add(btnStopMonitoring);
		
		JButton btnStartTweeting = new JButton("Start Tweeting");
		btnStartTweeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					campaignController.submitCampaignJob(campaign);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStartTweeting.setBounds(21, 228, 146, 29);
		frame.getContentPane().add(btnStartTweeting);
		
		JButton btnStopTweeting = new JButton("Stop Tweeting");
		btnStopTweeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					campaignController.stopCampaignJob(campaign);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStopTweeting.setBounds(21, 269, 146, 26);
		frame.getContentPane().add(btnStopTweeting);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addUserView = new AddUserView(17);
					addUserView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					addUserView.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton_1.setBounds(21, 179, 146, 29);
		frame.getContentPane().add(btnNewButton_1);
	
		
	
	}
}
