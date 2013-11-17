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

public class TweeterMarketerView implements ActionListener {
	private Container contentPane;
	
	private static String addViewID = "addCampaignView";
	private static String editViewID = "editCampaignView";
	private static String removeViewID = "removeCampaignView";
	private static String runViewID = "runCampaignView";
	private static String monitorViewID = "monitorCampaignView";
	private static String stopViewID = "stopCampaignView";
	
	public static void main(String args[]) {
		// high level builder stuff can go here:
		//  - construct a domain controller
		//  - construct views and connect to controller

		new TweeterMarketerView();
	}
	
	JPanel subViews;
	
	public TweeterMarketerView() {
		
		JFrame frame = new JFrame("Tweeter Marketer");
		frame.setSize(800, 550); 
		
		contentPane = frame.getContentPane();
		
		JToolBar toolbar = buildToolBar();
		subViews = buildSubViews();

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
		System.out.println(e.getActionCommand());
		Toolkit.getDefaultToolkit().beep();

		CardLayout cl = (CardLayout) (subViews.getLayout());
		switch (e.getActionCommand()) {
		case "add":
			cl.show(subViews, addViewID);
			break;
		case "edit":
			cl.show(subViews, editViewID);
			break;
		case "remove":
			cl.show(subViews, removeViewID);
			break;
		case "run":
			cl.show(subViews, runViewID);
			break;
		case "monitor":
			cl.show(subViews, monitorViewID);
			break;
		case "stop":
			cl.show(subViews, stopViewID);
			break;
		default:
			break;
		}
	}

	private JToolBar buildToolBar() {
		JButton addButton = new JButton("Add a Campaign");
		addButton.setActionCommand("add");
		addButton.addActionListener(this);

		JButton editButton = new JButton("TBD: Edit Campaign");
		editButton.setActionCommand("edit");
		editButton.addActionListener(this);

		JButton removeButton = new JButton("TBD: Remove Campaign");
		removeButton.setActionCommand("remove");
		removeButton.addActionListener(this);
		
		JButton runButton = new JButton("Run a Campaign");
		runButton.setActionCommand("run");
		runButton.addActionListener(this);
		
		JButton monitorButton = new JButton("Monitor a Campaign");
		monitorButton.setActionCommand("monitor");
		monitorButton.addActionListener(this);

		JButton stopButton = new JButton("TBD: Stop Campaign");
		stopButton.setActionCommand("stop");
		stopButton.addActionListener(this);

		JToolBar toolbar = new JToolBar();
		toolbar.add(addButton);
		toolbar.add(editButton);
		toolbar.add(removeButton);
		toolbar.add(runButton);
		toolbar.add(monitorButton);
		toolbar.add(stopButton);
		
		toolbar.setAlignmentX(0);
		toolbar.setAlignmentY(0);
		
		return toolbar;
	}

	private JPanel buildAddView() {
		AddCampaignView addView = new AddCampaignView();
		return addView.getViewPanel();
	}

	private JPanel buildRunView() {
		RunCampaignView runView = new RunCampaignView();
		return runView.getViewPanel();
	}
	
	private JPanel buildMonitorView() {
		MonitorCampaignView monitorView = new MonitorCampaignView();
		return monitorView.getViewPanel();
	}

	
	private JPanel buildSubViews() {
		JPanel subViews = new JPanel(new CardLayout());
		
		subViews.add(buildAddView(), addViewID);
		subViews.add(buildRunView(), runViewID);		
		subViews.add(buildMonitorView(), monitorViewID);

		// the rest can go away if we don't want them:
		
		JPanel editCard = new JPanel();
		JButton buttonView = new JButton("edit campaign view placeholder");
		editCard.add(buttonView);
		subViews.add(editCard, editViewID);		
		
		JPanel removeCard = new JPanel();
		buttonView = new JButton("remove campaign view placeholder");
		removeCard.add(buttonView);
		subViews.add(removeCard, removeViewID);
		
		JPanel stopCard = new JPanel();
		buttonView = new JButton("stop campaign view placeholder");
		stopCard.add(buttonView);
		subViews.add(stopCard, stopViewID);
		
		return subViews;	
	}

}
