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

public class RunCampaignView implements ActionListener {
	
	private static String submitButtonID = "submitID";
	private JPanel viewPanel;
	private JTextField campaignIdText;
	
	public RunCampaignView () {
		viewPanel = new JPanel(new GridLayout(2,2,50,50));
		
		JLabel label = new JLabel("Campaign ID", SwingConstants.RIGHT);
		viewPanel.add(label);
		campaignIdText = new JTextField(20);
		campaignIdText.setText("enter a campaign ID here");
		campaignIdText.addActionListener(this);
		viewPanel.add(campaignIdText);
		
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
			// check the state of the campaign and if not running start running the campaign
		}
	}

}