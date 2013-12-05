package org.tweet.marketing.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import org.tweet.marketing.Campaign;
import org.tweet.marketing.MonitorCallback;
import org.tweet.marketing.view.controller.CampaignController;

import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MonitorCampaignView implements ActionListener {

	private JPanel viewPanel;
	private CampaignController campaignController;
	private Campaign campaign;
	private JTextArea monitorTextArea;
	private JComboBox campaignIdsComboBox;

	public MonitorCampaignView(CampaignController controller) {
		try {
			initialize(controller);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	JPanel getViewPanel() {
		return viewPanel;
	}

	private void initialize(CampaignController controller) throws Exception {
		campaignController = controller;
	
		viewPanel = new JPanel();
		viewPanel.setBounds(100, 100, 800, 497);
		viewPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Monitor Tweets:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(216, 6, 102, 16);
		viewPanel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(218, 28, 576, 463);
		viewPanel.add(scrollPane);
		
		monitorTextArea = new JTextArea();
		monitorTextArea.setFont(new Font("Dialog", Font.PLAIN, 12));
		monitorTextArea.setEditable(false);
		scrollPane.setViewportView(monitorTextArea);
		
		JLabel lblNewLabel_1 = new JLabel("Pick a campaign id:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1.setBounds(21, 62, 146, 26);
		viewPanel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Start Monitoring");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(viewPanel, "Starting to monitor the campaign...");
				try {
					campaignController.submitMonitoringJob(campaign);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(21, 329, 146, 29);
		viewPanel.add(btnNewButton);
		
		JButton btnStopMonitoring = new JButton("Stop Monitoring");
		btnStopMonitoring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(viewPanel, "No longer monitoring the campaign...");
				try {
					campaignController.stopMonitoringJob(campaign);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStopMonitoring.setBounds(21, 369, 146, 29);
		viewPanel.add(btnStopMonitoring);
		
		JButton btnStartTweeting = new JButton("Start Tweeting");
		btnStartTweeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(viewPanel, "Starting the campaign...");
				try {
					campaignController.submitCampaignJob(campaign);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStartTweeting.setBounds(21, 252, 146, 29);
		viewPanel.add(btnStartTweeting);
		
		JButton btnStopTweeting = new JButton("Stop Tweeting");
		btnStopTweeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(viewPanel, "Stopping the campaign...");				
				try {
					campaignController.stopCampaignJob(campaign);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStopTweeting.setBounds(21, 292, 146, 26);
		viewPanel.add(btnStopTweeting);
		
		campaignIdsComboBox = new JComboBox();
		campaignIdsComboBox.setBounds(21, 96, 146, 29);
		updateCampaignList();
		campaignIdsComboBox.addActionListener(this);
		viewPanel.add(campaignIdsComboBox);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String campaignIdString = (String) campaignIdsComboBox.getSelectedItem();
		if(campaignIdString != null) try {
			int campaignId = Integer.parseInt(campaignIdString);
			campaign = campaignController.getCampaign(campaignId);
			
			if(campaign.getCredential() != null) {
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
							monitorTextArea.append( status.getCreatedAt() + ": @" + status.getUser().getScreenName() + ":" + status.getText() + "\n");
					    }
						
						
					}
				});
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	public void updateCampaignList() {
		List<Campaign> campaigns = null;
		try {
			campaigns = campaignController.getListOfCampaigns();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Iterator<Campaign> itr = campaigns.iterator();
		campaignIdsComboBox.removeAllItems();
		while(itr.hasNext()) {
			campaignIdsComboBox.addItem(String.valueOf(itr.next().getId()));
		}
	}
}
