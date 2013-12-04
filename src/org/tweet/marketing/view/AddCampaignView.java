package org.tweet.marketing.view;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.tweet.marketing.Campaign;
import org.tweet.marketing.view.controller.CampaignController;

import javax.swing.JScrollPane;

import java.awt.Font;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextArea;

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
		lblName.setBounds(240, 53, 76, 25);
		viewPanel.add(lblName);
		campaignName = new JTextField(20);
		campaignName.setBounds(334, 53, 295, 25);
		campaignName.setText("enter campaign name");
		viewPanel.add(campaignName);
		
		lblMessage = new JLabel("Message", SwingConstants.RIGHT);
		lblMessage.setBounds(240, 131, 76, 25);
		viewPanel.add(lblMessage);
		campaignMessage = new JTextField(20);
		campaignMessage.setBounds(334, 131, 295, 25);
		campaignMessage.setText("enter campaign message");
		viewPanel.add(campaignMessage);

		lblInterval = new JLabel("Interval", SwingConstants.RIGHT);
		lblInterval.setBounds(240, 211, 76, 25);
		viewPanel.add(lblInterval);
		campaignInterval = new JTextField(20);
		campaignInterval.setBounds(334, 209, 295, 25);
		campaignInterval.setText("enter interval in seconds as an integer");
		viewPanel.add(campaignInterval);
		
		submitButton = new JButton("Add this campaign");
		submitButton.setBounds(334, 287, 295, 25);
		submitButton.setActionCommand(submitButtonID);
		submitButton.addActionListener(this);
		viewPanel.add(submitButton);
		
		addUserButton = new JButton("Add a user to the campaign");
		addUserButton.setEnabled(false);
		addUserButton.setBounds(334, 365, 295, 25);
		addUserButton.setActionCommand(addUserButtonID);
		addUserButton.addActionListener(this);
		viewPanel.add(addUserButton);
	}
	
	JPanel getViewPanel() {
		return viewPanel;
	}

	private Campaign newCampaign = null;
	private AddUserView addUserView = null;
	
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getActionCommand());
		if (submitButtonID.equals(e.getActionCommand())) {
			submitButton.setEnabled(false);
			addUserButton.setEnabled(true);

			newCampaign = new Campaign();
			newCampaign.setName(campaignName.getText());
			newCampaign.setTweetText(campaignMessage.getText());
			newCampaign.setIntervalInSeconds(Integer.parseInt(campaignInterval.getText()));
			
			campaignName.setText("");
			campaignMessage.setText("");
			campaignInterval.setText("");
			
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