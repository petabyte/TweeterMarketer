package org.tweet.marketing.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import org.tweet.marketing.view.controller.CampaignController;

public class TweeterMarketerView implements ActionListener {
	private Container contentPane;

	private static String addCampaignID = "addCampaignView";
	private static String monitorCampaignID = "monitorCampaignView";
	private CampaignController campaignController;
	
	public static void main(String args[]) {
		new TweeterMarketerView();
	}
	
	JPanel subViews;
	
	public TweeterMarketerView() {
		
		JFrame frame = new JFrame("Tweeter Marketer");
		
		frame.setSize(800, 550); 
		
		campaignController = new CampaignController();
		JToolBar toolbar = buildToolBar();
		subViews = buildSubViews();

		contentPane = frame.getContentPane();
		contentPane.add(toolbar, BorderLayout.PAGE_START);
		contentPane.add(subViews, BorderLayout.CENTER);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension dim = kit.getScreenSize();
		int xPos = (dim.width/2) - (frame.getWidth()/2);
		int yPos = (dim.height/2) - (frame.getHeight()/2);
		frame.setLocation(xPos, yPos);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout) (subViews.getLayout());
		switch (e.getActionCommand()) {
		case "addCampaign":
			cl.show(subViews, addCampaignID);
			break;
		case "monitorCampaign":
			cl.show(subViews, monitorCampaignID);
			break;
		default:
			break;
		}
	}

	private JToolBar buildToolBar() {
		JButton addCampaignButton = new JButton("Add Campaign");
		addCampaignButton.setActionCommand("addCampaign");
		addCampaignButton.addActionListener(this);
		
		JButton monitorButton = new JButton("Monitor Campaign");
		monitorButton.setActionCommand("monitorCampaign");
		monitorButton.addActionListener(this);

		JToolBar toolbar = new JToolBar();
		toolbar.add(addCampaignButton);
		toolbar.add(monitorButton);
		
		toolbar.setAlignmentX(0);
		toolbar.setAlignmentY(0);
		
		return toolbar;
	}


	private JPanel buildAddCampaignView() {
		AddCampaignView addCampaignView = new AddCampaignView(campaignController);
		return addCampaignView.getViewPanel();
	}
	
	private JPanel buildMonitorCampaignView() {
		MonitorCampaignView monitorView = new MonitorCampaignView(campaignController);
		return monitorView.getViewPanel();
	}

	
	private JPanel buildSubViews() {
		JPanel subViews = new JPanel(new CardLayout(0,0));
		
		subViews.add(buildAddCampaignView(), addCampaignID);		
		subViews.add(buildMonitorCampaignView(), monitorCampaignID);
		
		return subViews;	
	}

}
