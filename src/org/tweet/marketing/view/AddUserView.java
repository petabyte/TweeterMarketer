package org.tweet.marketing.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.tweet.marketing.Campaign;
import org.tweet.marketing.view.controller.AddUserController;

import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

import com.sun.javafx.application.PlatformImpl;

public class AddUserView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4574419824003832993L;
	private JPanel contentPane;
	private JTextField campaignPINNumber;
	private JTextField campaignUserName;
	private JFXPanel fxPanel;
	private Stage stage;  
	private WebView browser;
	private WebEngine webEngine;
	private String userName;
	private String pinNumber;
	private String url;
	private AddUserController addUserController;
	private Campaign campaign;
	private RequestToken requestToken;

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public AddUserView(final int campaignId) throws Exception {
		Platform.setImplicitExit(false);
		this.addUserController = new AddUserController();
	    this.campaign = addUserController.getCampaign(campaignId);
	    
	    
	
		setBounds(100, 100, 1085, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	    
		
		campaignPINNumber = new JTextField();
		campaignPINNumber.setBounds(24, 375, 182, 28);
		contentPane.add(campaignPINNumber);
		campaignPINNumber.setColumns(10);
		
		campaignUserName = new JTextField();
		if(campaign != null && campaign.getCredential() != null){
			campaignUserName.setText(campaign.getCredential().getAccessToken().getUserName());
		}
		campaignUserName.setBounds(24, 237, 288, 28);
		contentPane.add(campaignUserName);
		campaignUserName.setColumns(10);
		
		final JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(24, 196, 109, 16);
		contentPane.add(lblUserName);
		
		JLabel lblPin = new JLabel("PIN:");
		lblPin.setBounds(24, 347, 152, 16);
		contentPane.add(lblPin);
		
		JButton btnGetAuthorizationButton = new JButton("Get Authorization Pin");
		btnGetAuthorizationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {
					userName = campaignUserName.getText();
					requestToken = addUserController.getRequestToken();
					url = requestToken.getAuthorizationURL();
					createScene();
				} catch (TwitterException te) {
					if (401 == te.getStatusCode()) {
						addUserController.configureTwitterInstance();
						errorDialog("Unable to get the authorization request token. \n"
								+ "Please try again.");
					}
				}catch (Exception ex){
					errorDialog(ex.getMessage());
				}
			}
		});
		btnGetAuthorizationButton.setBounds(24, 277, 182, 29);
		contentPane.add(btnGetAuthorizationButton);
		
		JButton btnAuthorize = new JButton("Authorize");
		btnAuthorize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {
					pinNumber = campaignPINNumber.getText();
					addUserController.authorizeApp(campaignId, pinNumber, userName, requestToken);
					dispose();
				} catch (TwitterException te) {
					if (401 == te.getStatusCode()) {
						addUserController.configureTwitterInstance();
						errorDialog("Unable to process authorization. \n"
								+ "Please try 'Get Authorization Pin' again.");
					}
				}catch (Exception ex){
					errorDialog(ex.getMessage());
				}
			}
		});
		btnAuthorize.setBounds(24, 414, 182, 29);
		contentPane.add(btnAuthorize);
		
		JLabel lblNewLabel = new JLabel("<html>\n Instructions for Adding a User:<br />\n 1. Enter twitter user name to use, then click 'Get Authorization Pin' <br />\n 2. A PIN number will be shown at the left panel from twitter.com <br/>\n     (You may need to login )<br />\n 3. Enter the PIN then click 'Authorize' <br />\n</html>\n");
		lblNewLabel.setBounds(24, 6, 269, 178);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(340, 6, 739, 512);
		contentPane.add(scrollPane);
		fxPanel = new JFXPanel();
		scrollPane.setViewportView(fxPanel);
		
	
	}
	
	private void errorDialog(String message){
		JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);   
		JDialog dialog = optionPane.createDialog("Failure");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
	
	private void createScene(){
		PlatformImpl.startup(new Runnable(){

			@Override
			public void run() {
				stage = new Stage();
				stage.setTitle("Authorization Pin");
				stage.setResizable(false);
				Group root = new Group();
				Scene scene = new Scene(root,739,512);
				stage.setScene(scene);  
	                 
	                // Set up the embedded browser:
	                browser = new WebView();
	                webEngine = browser.getEngine();
	                webEngine.load(url);
	              
	                
	                ObservableList<Node> children = root.getChildren();
	                children.add(browser);                     
	                 
	                fxPanel.setScene(scene);
				
			}
		});
	}
}
