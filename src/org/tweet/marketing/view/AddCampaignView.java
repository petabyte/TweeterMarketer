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

public class AddCampaignView implements ActionListener {
	
	private static String submitButtonID = "submitID";
	private JPanel viewPanel;
	private JTextField campaignIdText;
	private JTextField tweetIntervalText;
	private JTextField tweetText;
	private JTextField urlText;
	private JTextField hashtagText;
	private JTextField pathToImageText;
	
	public AddCampaignView () {
		viewPanel = new JPanel(new GridLayout(7,2,20,20));
		
		JLabel label = new JLabel("Campaign ID", SwingConstants.RIGHT);
		viewPanel.add(label);
		campaignIdText = new JTextField(20);
		campaignIdText.setText("enter ID here");
		campaignIdText.addActionListener(this);
		viewPanel.add(campaignIdText);
		
		label = new JLabel("Tweet Interval", SwingConstants.RIGHT);
		viewPanel.add(label);
		tweetIntervalText = new JTextField(20);
		tweetIntervalText.setText("type tweet interval here");
		tweetIntervalText.addActionListener(this);
		viewPanel.add(tweetIntervalText);
		
		label = new JLabel("Tweet Text", SwingConstants.RIGHT);
		viewPanel.add(label);
		tweetText = new JTextField(20);
		tweetText.setText("enter campaign message text here");
		tweetText.addActionListener(this);
		viewPanel.add(tweetText);

		label = new JLabel("URL", SwingConstants.RIGHT);
		viewPanel.add(label);
		urlText = new JTextField(20);
		urlText.setText("enter a URL link here");
		urlText.addActionListener(this);
		viewPanel.add(urlText);

		label = new JLabel("hashtag", SwingConstants.RIGHT);
		viewPanel.add(label);
		hashtagText = new JTextField(20);
		hashtagText.setText("type #hashtag here");
		hashtagText.addActionListener(this);
		viewPanel.add(hashtagText);

		label = new JLabel("path to image", SwingConstants.RIGHT);
		viewPanel.add(label);
		pathToImageText = new JTextField(20);
		pathToImageText.setText("type path to image here");
		pathToImageText.addActionListener(this);
		viewPanel.add(pathToImageText);		

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
			// grab the text from each text box and validate the input values
			// pass the input values to a Campaign factory that knows how to:
			//  - create a Campaign object
			//  - populate the Campaign object with input values
			//  - hand the object off to whatever caches Campaign objects (repository)
		}
	}

}