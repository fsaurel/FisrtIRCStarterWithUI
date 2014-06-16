package com.cfranc.irc.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.activation.MailcapCommandMap;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.cfranc.irc.controller.NewUserController;

import java.awt.BorderLayout;
import java.sql.SQLException;

public class ConnectionPanel extends JDialog {

    private JTextField serverPortField;
    private JTextField serverField;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton btnNewButton = new JButton("Connexion");
    

	public JTextField getServerPortField() {
		return serverPortField;
	}



	public JTextField getServerField() {
		return serverField;
	}



	/**
	 * Create the panel.
	 */
	public ConnectionPanel() {
	
	
	    final JPanel connectionPanel = new JPanel(false);
		connectionPanel.setLayout(new BoxLayout(connectionPanel,
							BoxLayout.X_AXIS));
	    
	    GroupLayout groupLayout = new GroupLayout(connectionPanel);
	    groupLayout.setHorizontalGroup(
	    	groupLayout.createParallelGroup(Alignment.LEADING)
	    		.addGap(0, 0, Short.MAX_VALUE)
	    );
	    groupLayout.setVerticalGroup(
	    	groupLayout.createParallelGroup(Alignment.LEADING)
	    		.addGap(0, 213, Short.MAX_VALUE)
	    );
//	    setLayout(groupLayout);	 
	    getContentPane().add(connectionPanel, BorderLayout.WEST);
	    
	    JPanel panel = new JPanel();
	    getContentPane().add(panel, BorderLayout.NORTH);
	    JLabel userNameLabel = new JLabel("User name: ", JLabel.RIGHT);
	    panel.add(userNameLabel);
	    userNameField = new JTextField("UTILISATEUR");
	    panel.add(userNameField);
	    
	        JLabel passwordLabel = new JLabel("Password: ", JLabel.RIGHT);
	        panel.add(passwordLabel);
	        passwordField = new JPasswordField("PWD");
	        panel.add(passwordField);
	    
	    JPanel panel_1 = new JPanel();
	    getContentPane().add(panel_1, BorderLayout.SOUTH);
	    
	    JButton btnNewUser = new JButton("New User");
	    panel_1.add(btnNewUser);
	    
	    
	    panel_1.add(btnNewButton);
	    
	    JPanel panel_2 = new JPanel();
	    getContentPane().add(panel_2, BorderLayout.CENTER);
	    
	        JLabel serverLabel = new JLabel("Server name:", JLabel.RIGHT);
	        panel_2.add(serverLabel);
	        serverField = new JTextField("localhost");
	        panel_2.add(serverField);
	        
	            JLabel serverPortLabel = new JLabel("Port: ", JLabel.RIGHT);
	            panel_2.add(serverPortLabel);
	            serverPortField = new JTextField("4567");
	            panel_2.add(serverPortField);
	            
	            	JPanel fieldPanel = new JPanel(false);
	            	panel_2.add(fieldPanel);
	            	fieldPanel.setLayout(new GridLayout(0, 1));
	            	
	            		JPanel namePanel = new JPanel(false);
	            		panel_2.add(namePanel);
	            		namePanel.setLayout(new GridLayout(0, 1));
	    
	    
	    
	    btnNewUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NewUserIHM newuserihm = new NewUserIHM(ConnectionPanel.this);
				NewUserController newusercontroller = new NewUserController(newuserihm);
				newuserihm.setVisible(true);
			}
		});
	    setPreferredSize(new Dimension(300,200));
	}

	public JTextField getUserNameField() {
		return userNameField;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	
	public void addConnexionListener(ActionListener listenforconnexion){
		btnNewButton.addActionListener(listenforconnexion);
	}
	
}
