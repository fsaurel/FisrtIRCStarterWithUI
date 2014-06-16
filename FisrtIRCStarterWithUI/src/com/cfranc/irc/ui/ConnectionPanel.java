package com.cfranc.irc.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.activation.MailcapCommandMap;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	        
	        JLabel lblChat = new JLabel();
			Icon image = new ImageIcon( "Z:/04_TP/FSAU/Chat.jpg");
			//newuserihm.jLabel6.setText(newuserihm.myfilechooser.getSelectedFile().toString());
			lblChat.setIcon( image );
	        
	        
	        
	        GroupLayout gl_panel = new GroupLayout(panel);
	        gl_panel.setHorizontalGroup(
	        	gl_panel.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addGap(34)
	        			.addComponent(lblChat)
	        			.addContainerGap(362, Short.MAX_VALUE))
	        );
	        gl_panel.setVerticalGroup(
	        	gl_panel.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(lblChat)
	        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        panel.setLayout(gl_panel);
	    
	    JPanel panel_1 = new JPanel();
	    getContentPane().add(panel_1, BorderLayout.SOUTH);
	    
	    JButton btnNewUser = new JButton("New User");
	    panel_1.add(btnNewUser);
	    
	    
	    panel_1.add(btnNewButton);
	    
	    JPanel panel_2 = new JPanel();
	    getContentPane().add(panel_2, BorderLayout.CENTER);
	    
	        JLabel serverLabel = new JLabel("Server name:", JLabel.RIGHT);
	        serverField = new JTextField("");
	        
	            JLabel serverPortLabel = new JLabel("Port: ", JLabel.RIGHT);
	            serverPortField = new JTextField("");
	            
	            	JPanel fieldPanel = new JPanel(false);
	            	fieldPanel.setLayout(new GridLayout(0, 1));
	            	
	            		JPanel namePanel = new JPanel(false);
	            		namePanel.setLayout(new GridLayout(0, 1));
	            		JLabel userNameLabel = new JLabel("User name: ", JLabel.RIGHT);
	            		userNameField = new JTextField("");
	            		passwordField = new JPasswordField("");
	            		
	            		    JLabel passwordLabel = new JLabel("Password: ", JLabel.RIGHT);
	            		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
	            		gl_panel_2.setHorizontalGroup(
	            			gl_panel_2.createParallelGroup(Alignment.LEADING)
	            				.addGroup(gl_panel_2.createSequentialGroup()
	            					.addGap(25)
	            					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
	            						.addGroup(gl_panel_2.createSequentialGroup()
	            							.addComponent(userNameLabel)
	            							.addPreferredGap(ComponentPlacement.RELATED)
	            							.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
	            							.addPreferredGap(ComponentPlacement.RELATED)
	            							.addComponent(fieldPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	            							.addGap(18)
	            							.addComponent(passwordLabel)
	            							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	            							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
	            							.addGap(19))
	            						.addGroup(gl_panel_2.createSequentialGroup()
	            							.addComponent(serverLabel)
	            							.addPreferredGap(ComponentPlacement.RELATED)
	            							.addComponent(serverField, 44, 44, 44)
	            							.addGap(18)
	            							.addComponent(serverPortLabel)
	            							.addPreferredGap(ComponentPlacement.RELATED)
	            							.addComponent(serverPortField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
	            							.addContainerGap(217, Short.MAX_VALUE))))
	            		);
	            		gl_panel_2.setVerticalGroup(
	            			gl_panel_2.createParallelGroup(Alignment.LEADING)
	            				.addGroup(gl_panel_2.createSequentialGroup()
	            					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
	            						.addGroup(gl_panel_2.createSequentialGroup()
	            							.addGap(15)
	            							.addComponent(fieldPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	            						.addGroup(gl_panel_2.createSequentialGroup()
	            							.addContainerGap()
	            							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
	            								.addComponent(userNameLabel)
	            								.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	            								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	            								.addComponent(passwordLabel))))
	            					.addGap(20)
	            					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
	            						.addComponent(serverLabel)
	            						.addComponent(serverField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	            						.addComponent(serverPortLabel)
	            						.addComponent(serverPortField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	            					.addContainerGap(13, Short.MAX_VALUE))
	            		);
	            		panel_2.setLayout(gl_panel_2);
	    
	    
	    
	    btnNewUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NewUserIHM newuserihm = new NewUserIHM(ConnectionPanel.this);
				NewUserController newusercontroller = new NewUserController(newuserihm);
				newuserihm.setVisible(true);
			}
		});
//	    setPreferredSize(new Dimension(300,200));
	    setBounds(100, 100, 600, 400);
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
