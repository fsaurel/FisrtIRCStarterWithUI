package com.cfranc.irc.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;

import com.cfranc.irc.client.ClientToServerThread;
import com.cfranc.irc.server.ClientConnectThread;


import javax.swing.JButton;

import org.sqlite.JDBC;

import javax.swing.JPanel;

import java.awt.BorderLayout;

public class SimpleChatClientApp {

    static String[] ConnectOptionNames = { "Connect" };	
    static String   ConnectTitle = "Connection Information";
    Socket socketClientServer;
    int serverPort;
    String serverName;
    String clientName;
    String clientPwd;
    public static int connecok; 
    public ConnectionPanel connectionPanel=new ConnectionPanel();
    
	public  SimpleChatFrameClient frame;
	public StyledDocument documentModel=new DefaultStyledDocument();
	DefaultListModel<String> clientListModel=new DefaultListModel<String>();
	DefaultMutableTreeNode racine=new DefaultMutableTreeNode();
	
    public static final String BOLD_ITALIC = "BoldItalic";
    public static final String GRAY_PLAIN = "Gray";
        
	public static DefaultStyledDocument defaultDocumentModel() {
		DefaultStyledDocument res=new DefaultStyledDocument();
	    
	    Style styleDefault = (Style) res.getStyle(StyleContext.DEFAULT_STYLE);
	    
	    res.addStyle(BOLD_ITALIC, styleDefault);
	    Style styleBI = res.getStyle(BOLD_ITALIC);
	    StyleConstants.setBold(styleBI, true);
	    StyleConstants.setItalic(styleBI, true);
	    StyleConstants.setForeground(styleBI, Color.black);	    

	    res.addStyle(GRAY_PLAIN, styleDefault);
        Style styleGP = res.getStyle(GRAY_PLAIN);
        StyleConstants.setBold(styleGP, false);
        StyleConstants.setItalic(styleGP, false);
        StyleConstants.setForeground(styleGP, Color.lightGray);

		return res;
	}

	private static ClientToServerThread clientToServerThread;
			
	public SimpleChatClientApp(){
		

		
	}
	
	public void displayClient() {
		
		// Init GUI
		this.frame=new SimpleChatFrameClient(clientToServerThread, clientListModel, documentModel);
		this.frame.setTitle(this.frame.getTitle()+" : "+clientName+" connected to "+serverName+":"+serverPort);
		
		

		
		((JFrame)this.frame).setVisible(true);
		this.frame.addWindowListener(new WindowListener() {
					
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				quitApp(SimpleChatClientApp.this);
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	
	public void hideClient() {
		
		// Init GUI
		((JFrame)this.frame).setVisible(false);
	}
	

	
    void displayConnectionDialog() {
    	
    	
    	connectionPanel.setModal(true);
    	connectionPanel.addConnexionListener(new connexionButton());
    	//final JDialog dialog = new JDialog(connectionPanel,	"Connexion",true);   	
    	//    	dialog.set setContentPane(connectionPanel);

    	connectionPanel.setSize(new Dimension(600,400));
    	
    	JPanel panel = new JPanel();
    	connectionPanel.getContentPane().add(panel, BorderLayout.EAST);
    	
    	connectionPanel.serverField.setText("localhost");
    	connectionPanel.serverPortField.setText("4567");
    	
    	connectionPanel.setVisible(true);
    	

    	
    	

		

    	

    	
//		if (JOptionPane.showOptionDialog(null, connectionPanel, ConnectTitle,
//				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
//				null, ConnectOptionNames, ConnectOptionNames[0]) == 0) {
//			serverPort=Integer.parseInt(connectionPanel.getServerPortField().getText());
//			serverName=connectionPanel.getServerField().getText();
//			clientName=connectionPanel.getUserNameField().getText();
//			clientPwd=connectionPanel.getPasswordField().getText();
//		}
		
	}
    
    public void connectClient() {
		System.out.println("Establishing connection. Please wait ...");
		try {
			socketClientServer = new Socket(this.serverName, this.serverPort);
			// Start connection services
			clientToServerThread=new ClientToServerThread(documentModel, clientListModel, racine, socketClientServer,clientName, clientPwd);
			clientToServerThread.start();
			


			System.out.println("Connected: " + socketClientServer);
		} catch (UnknownHostException uhe) {
			System.out.println("Host unknown: " + uhe.getMessage());
		} catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
	}
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final SimpleChatClientApp app = new SimpleChatClientApp();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {

					app.displayConnectionDialog();
					System.out.println("app.displayConnectionDialog();");

					if (connecok == 2){

						app.connectClient();
						System.out.println("app.connectClient();");

						app.displayClient();
						System.out.println("app.displayClient();");
						

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

		Scanner sc=new Scanner(System.in);
		String line="";
		while(!line.equals(".bye")){
			line=sc.nextLine();			
		}
		
		quitApp(app);
	}

	private static void quitApp(final SimpleChatClientApp app) {
		try {
			app.clientToServerThread.quitServer();
			app.socketClientServer.close();
			app.hideClient();
			System.out.println("SimpleChatClientApp : fermée");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int verifLoginUtilisateur() throws SQLException{
		
		Connection connection = null;
		
		System.out.println(connectionPanel.getServerPortField().getText());

		
	    if (connectionPanel.getServerPortField().getText().equals("")){
	    	JOptionPane.showMessageDialog(connectionPanel, "Vous devez renseigner le port !", "Hey!", JOptionPane.ERROR_MESSAGE);
			connectionPanel.serverPortField.requestFocus();
			connectionPanel.serverPortField.setBackground(Color.LIGHT_GRAY);
	    	connecok = 0 ;
	    	return 0;
	    } else{
	    	serverPort=Integer.parseInt(connectionPanel.getServerPortField().getText());
	    	connectionPanel.serverPortField.setBackground(Color.white);
	    }
	    
	    if (connectionPanel.getServerField().getText().equals("")){
	    	JOptionPane.showMessageDialog(connectionPanel, "Vous devez renseigner le serveur !", "Hey!", JOptionPane.ERROR_MESSAGE);
			connectionPanel.serverField.requestFocus();
			connectionPanel.serverField.setBackground(Color.LIGHT_GRAY);
	    	connecok = 0 ;
	    	return 0;
	    } else{
	    	serverName=connectionPanel.getServerField().getText();
	    	connectionPanel.serverField.setBackground(Color.white);
	    }
	    
	    if (connectionPanel.getUserNameField().getText().equals("")){
	    	JOptionPane.showMessageDialog(connectionPanel, "Vous devez renseigner le User !", "Hey!", JOptionPane.ERROR_MESSAGE);
			connectionPanel.userNameField.requestFocus();
			connectionPanel.userNameField.setBackground(Color.LIGHT_GRAY);
	    	connecok = 0 ;
	    	return 0;
	    } else{
	    	clientName=connectionPanel.getUserNameField().getText();
	    	connectionPanel.userNameField.setBackground(Color.white);
	    }
	    
	    if (connectionPanel.getPasswordField().getText().equals("")){
	    	JOptionPane.showMessageDialog(connectionPanel, "Vous devez renseigner le Password !", "Hey!", JOptionPane.ERROR_MESSAGE);
			connectionPanel.passwordField.requestFocus();
			connectionPanel.passwordField.setBackground(Color.LIGHT_GRAY);
	    	connecok = 0 ;
	    	return 0;
	    } else{
	    	clientPwd=connectionPanel.getPasswordField().getText();
	    	connectionPanel.passwordField.setBackground(Color.white);
	    }
		


		if(JDBC.isValidURL("jdbc:sqlite:Z:/04_TP/FSAU/BDD/IRC.SQLITE")) {
			connection = DriverManager.getConnection("jdbc:sqlite:Z:/04_TP/FSAU/BDD/IRC.SQLITE");
		}
		
		System.out.println("la connexion est : " + connection.toString());
		Statement statement = connection.createStatement();
		String myrequete = "SELECT COUNT(*)  FROM USERS WHERE PSEUDO = '" + clientName + "' AND PASSWORD = '" + clientPwd + "'";
		System.out.println(myrequete);
		ResultSet rs = statement.executeQuery(myrequete);
		
		
		if (rs.getInt(1) == 1){
			System.out.println("Mot de passe OK");
			connecok = 2;
			connection.close();
			return 2;
			
		} else {
			System.out.println("Mot de passe KO");
			connecok = 1;
			connection.close();
			return 1 ;
		}
		
		
		
		
	}
	
	
	
	
	
	public class connexionButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			try {
				verifLoginUtilisateur();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (connecok == 2){
				connectionPanel.dispose();
			}else if (connecok == 1) {
				JOptionPane.showMessageDialog(connectionPanel, "Le mot de passe n'est pas correct", "alert", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	

	
	
}


