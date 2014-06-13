package com.cfranc.irc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.JDBC;

import com.cfranc.irc.ui.NewUserIHM;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class NewUserController {


	public NewUserIHM newuserihm;
	public Connection connection;


	public NewUserController(NewUserIHM newuserihm) {

		this.connection = connection;
		this.newuserihm = newuserihm;
		this.newuserihm.addValiderListener(new validerButton());
		this.newuserihm.addAnnulerListener(new annulerButton());
		this.newuserihm.addParcourirListener(new parcourirButton());

	}



	public class validerButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Action sur le bouton valider");

			try {
				connecteBase("jdbc:sqlite:Z:/04_TP/FSAU/BDD/IRC.SQLITE");
				System.out.println("la connexion est : " + connection.toString());
				Statement statement = connection.createStatement();
				String myrequete = "INSERT INTO USERS (PSEUDO, NOM, PRENOM, PASSWORD, IMAGEAVATAR) VALUES ('" + newuserihm.jTextField1.getText() + "', '" + newuserihm.jTextField2.getText() + "', '" + newuserihm.jTextField3.getText() + "', '" + newuserihm.jTextField4.getText() + "', '" + newuserihm.myfilechooser.getSelectedFile().toString() + "')";
				System.out.println(myrequete);
				statement.executeUpdate(myrequete);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}




	public class annulerButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Action sur le bouton annuler");
			newuserihm.dispose();
		}
	}

	public class parcourirButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			newuserihm.myfilechooser.setCurrentDirectory(new File("c:/"));
			newuserihm.myfilechooser.showOpenDialog(null);
			//newuserihm.jTextField4.setText(newuserihm.myfilechooser.getSelectedFile().toString());
			Icon image = new ImageIcon( newuserihm.myfilechooser.getSelectedFile().toString());
			//newuserihm.jLabel6.setText(newuserihm.myfilechooser.getSelectedFile().toString());
			newuserihm.jLabel6.setIcon( image );


		}
	}





	public static void main(String[] args) {



		NewUserIHM myihm = new NewUserIHM();
		myihm.jLabel4.setText("Password : ");
		NewUserController mytestihm = new NewUserController(myihm);
		myihm.setVisible(true);

	}



	public void connecteBase(String cheminbdd) throws SQLException {
		if(JDBC.isValidURL(cheminbdd)) {
			connection = DriverManager.getConnection(cheminbdd);
		}



	}









}
