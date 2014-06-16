package com.cfranc.irc.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTree;

public class SimpleChatFrameServer extends JFrame{

	public StyledDocument model=null;
	public DefaultListModel<String> clientListModel=null;
	public DefaultMutableTreeNode racine	= null;
	
	public SimpleChatFrameServer(int port, StyledDocument model,  DefaultListModel<String> clientListModel, DefaultMutableTreeNode racine) {
		super("ISM - IRC Server Manager");
		this.model=model;
		this.clientListModel=clientListModel;
		this.racine=racine;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane(model);
		scrollPane.setViewportView(textPane);
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
				
			}
		});
		
		JScrollPane scrollPaneList = new JScrollPane();
		getContentPane().add(scrollPaneList, BorderLayout.WEST);
		

		JTree tree = new JTree(racine);
//		getContentPane().add(tree, BorderLayout.NORTH);
		
		
		
		final JLabel statusBar=new JLabel("");
		getContentPane().add(statusBar, BorderLayout.SOUTH);

		final JList<String> list = new JList<String>(clientListModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String clientSelected=list.getSelectedValue().toString();
				statusBar.setText(clientSelected);
				
				
				
			}
		});
		list.setMinimumSize(new Dimension(200,0));
		//scrollPaneList.setViewportView(list);
		scrollPaneList.setViewportView(tree);
	}	
}