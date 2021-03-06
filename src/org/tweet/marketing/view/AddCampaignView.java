package org.tweet.marketing.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.tweet.marketing.Campaign;
import org.tweet.marketing.Monitor;
import org.tweet.marketing.view.controller.CampaignController;

public class AddCampaignView implements ActionListener {
	

	private JPanel viewPanel;
	private JTextField campaignName;
	private JTextField campaignMessage;
	private JTextField campaignInterval;
	private JLabel lblName;
	private JLabel lblMessage;
	private JLabel lblInterval;
	
	private static String submitButtonID = "submitID";
	private JButton submitButton;
	private static String addUserButtonID = "addUserID";
	private JButton addUserButton;
	
	CampaignController campaignController;
	
	public AddCampaignView (CampaignController controller) {
		campaignController = controller;
		
		viewPanel = new JPanel();
		viewPanel.setLayout(null);
		
		lblName = new JLabel("Name", SwingConstants.RIGHT);
		lblName.setBounds(240, 42, 76, 25);
		viewPanel.add(lblName);
		campaignName = new JTextField(20);
		campaignName.setBounds(334, 42, 295, 25);
		campaignName.setText("enter campaign name");
		viewPanel.add(campaignName);
		
		lblMessage = new JLabel("Message", SwingConstants.RIGHT);
		lblMessage.setBounds(240, 109, 76, 25);
		viewPanel.add(lblMessage);
		campaignMessage = new JTextField(20);
		campaignMessage.setBounds(334, 109, 295, 25);
		campaignMessage.setText("enter campaign message");
		viewPanel.add(campaignMessage);

		lblInterval = new JLabel("Interval", SwingConstants.RIGHT);
		lblInterval.setBounds(240, 176, 76, 25);
		viewPanel.add(lblInterval);
		campaignInterval = new JTextField(20);
		campaignInterval.setBounds(336, 176, 295, 25);
		campaignInterval.setText("enter interval in seconds as an integer");
		viewPanel.add(campaignInterval);
		
		submitButton = new JButton("Add this campaign");
		submitButton.setBounds(334, 305, 295, 25);
		submitButton.setActionCommand(submitButtonID);
		submitButton.addActionListener(this);
		viewPanel.add(submitButton);
		
		addUserButton = new JButton("Add a user to the campaign");
		addUserButton.setEnabled(false);
		addUserButton.setBounds(334, 372, 295, 25);
		addUserButton.setActionCommand(addUserButtonID);
		addUserButton.addActionListener(this);
		viewPanel.add(addUserButton);
		
		lblHashtag = new JLabel("#hashtag");
		lblHashtag.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHashtag.setBounds(240, 241, 76, 25);
		viewPanel.add(lblHashtag);
		
		hashtagTextField = new JTextField();
		hashtagTextField.setText("enter #hashtag");
		hashtagTextField.setBounds(336, 243, 295, 25);
		viewPanel.add(hashtagTextField);
		hashtagTextField.setColumns(10);
	}
	
	JPanel getViewPanel() {
		return viewPanel;
	}

	private Campaign newCampaign = null;
	private AddUserView addUserView = null;
	private JLabel lblHashtag;
	private JTextField hashtagTextField;
	
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getActionCommand());
		if (submitButtonID.equals(e.getActionCommand())) {
			submitButton.setEnabled(false);
			addUserButton.setEnabled(true);

			newCampaign = new Campaign();
			newCampaign.setName(campaignName.getText());
			newCampaign.setTweetText(campaignMessage.getText());
			newCampaign.setIntervalInSeconds(Integer.parseInt(campaignInterval.getText()));
			
			Monitor monitor = new Monitor();
			monitor.setHashtagToMonitor(hashtagTextField.getText());
			int monitorInterval = newCampaign.getIntervalInSeconds() - 1 < 0 ? 1 : newCampaign.getIntervalInSeconds() - 1;  
			monitor.setIntervalInSeconds(monitorInterval);
			newCampaign.setMonitor(monitor);			
			
			campaignName.setText("");
			campaignMessage.setText("");
			campaignInterval.setText("");
			hashtagTextField.setText("");
			
			try {
				newCampaign = campaignController.addCampaign(newCampaign);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if (addUserButtonID.equals(e.getActionCommand())) {
			addUserButton.setEnabled(false);
			try {
				addUserView = new AddUserView(newCampaign.getId());
				addUserView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				addUserView.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}		
		
	}
}