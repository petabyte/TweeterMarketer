package org.tweet.marketing.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class MonitorCampaignView implements ActionListener {
	
	private static String submitButtonID = "submitID";
	private JPanel viewPanel;
	private JTextField campaignIdText;
	private JTextField monitorInfoText;
	
	public MonitorCampaignView () {
		viewPanel = new JPanel(new GridLayout(3,2,30,30));
		
		JLabel label = new JLabel("Campaign ID", SwingConstants.RIGHT);
		viewPanel.add(label);
		campaignIdText = new JTextField(20);
		campaignIdText.setText("enter ID here");
		campaignIdText.addActionListener(this);
		viewPanel.add(campaignIdText);
		
		label = new JLabel("", SwingConstants.RIGHT);
		viewPanel.add(label);
		monitorInfoText = new JTextField(20);
		monitorInfoText.setText("monitoring statistics will go here");
		monitorInfoText.addActionListener(this);
		viewPanel.add(monitorInfoText);		

		label = new JLabel("", SwingConstants.RIGHT);
		viewPanel.add(label);
		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand(submitButtonID);
		submitButton.addActionListener(this);
		viewPanel.add(submitButton);		
	}
	
	JPanel getViewPanel() {
		return viewPanel;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if (submitButtonID.equals(e.getActionCommand())) {
			Toolkit.getDefaultToolkit().beep();
			// grab the campaign ID text and validate it (make sure a corresponding campaign exists)
			// check the state of the campaign
			// if the campaign is not running then notify the user
			// if the campaign is running then start presenting statistics:
			//  - get events from domain 
			//  - fetch info from events or domain objects
			//  - enter info into display area
		}
	}

}