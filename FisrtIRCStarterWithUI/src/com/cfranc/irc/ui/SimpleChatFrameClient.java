package com.cfranc.irc.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

import com.cfranc.irc.client.IfSenderModel;

public class SimpleChatFrameClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Document documentModel;
	public static ListModel<String> listModel;
	public JList<String> list;
	IfSenderModel sender;
	private String senderName;

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblSender;
	private final ResourceAction sendAction = new SendAction();
	private final ResourceAction lockAction = new LockAction();

	private boolean isScrollLocked = true;

	private JPopupMenu popupMenu;
	private JTabbedPane onglet;
	private JSplitPane splitPane;

	ArrayList<String> tabOngletNames = new ArrayList<>();
	ArrayList<JTextPane> tabJTextPane = new ArrayList<>();
	ArrayList<JScrollPane> tabJScrollPane = new ArrayList<>();
	final static String nom1erOnglet = "Salon général";

	public JTextField getTextField() {
		return textField;
	}

	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}

	public void setSplitPane(JSplitPane splitPane) {
		this.splitPane = splitPane;
	}

	private void addTabOngletNames(String myOngletName, JTextPane myJTextPane, JScrollPane myJScrollPane) {
		if (!existeTabOngletName(myOngletName)) {
			tabOngletNames.add(myOngletName);
			tabJTextPane.add(myJTextPane);
			tabJScrollPane.add(myJScrollPane);
			syso_tabOngletNames("Après le add");
		}
	}

	public void syso_tabOngletNames(String message) {
		System.out.println(message + "__________Début");
		for (Iterator<String> it = tabOngletNames.iterator(); it.hasNext();) {
			System.out.println("currentOngletName=" + (String) it.next());
		}
		System.out.println(message + "__________Fin");
	}

	/**
	 * Suppression des onglets: Des éléments graphiques sur la fenêtre et dans
	 * les listes permettant de les parcourir
	 */
	private void removeTabOngletNames(String myOngletName) {
		int index = indiceTabOngletName(myOngletName);
		if (index != -1) {

			// Récupération et Suppression des éléments graphiques
			tabOngletNames.get(index);
			JTextPane myJTextPane = tabJTextPane.get(index);
			JScrollPane myJScrollPane = tabJScrollPane.get(index);

			myJScrollPane.remove(myJTextPane);
			onglet.remove(myJScrollPane);

			// Suppression dans la liste des onglets
			tabOngletNames.remove(index);
			tabJTextPane.remove(index);
			tabJScrollPane.remove(index);
		}
		syso_tabOngletNames("Après le remove");
	}

	/**
	 * 
	 * @param ongletName
	 *            : Nom de l'onglet à ajouter
	 * @param popupMenu
	 * 
	 */
	private void addOngletSplitPane(String ongletName, JPopupMenu popupMenu) {

		JTextPane textArea = new JTextPane((StyledDocument) documentModel);
		textArea.setEnabled(false);
		addPopup(textArea, popupMenu);

		JScrollPane scrollPaneText = new JScrollPane(textArea);
		scrollPaneText.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				if (isScrollLocked) {
					e.getAdjustable().setValue(e.getAdjustable().getMaximum());
				}
			}
		});

		onglet.addTab(ongletName, scrollPaneText);

		addTabOngletNames(ongletName, textArea, scrollPaneText);

	}

	/**
	 * Retourne l'indice de la liste tabOngletNames correspondant au nom de
	 * l'onglet :myOngletName
	 * 
	 * @param myOngletName
	 * @return
	 */
	private int indiceTabOngletName(String myOngletName) {
		int indice = -1;

		int i = -1;
		for (Iterator<String> it = tabOngletNames.iterator(); it.hasNext();) {
			i = i + 1;
			String currentOngletName = (String) it.next();
			System.out.println("indiceTabOngletName_myOngletName" + myOngletName + "|currentOngletName=" + currentOngletName + " i=" + i);

			if (myOngletName == currentOngletName) {
				indice = i;
				break;
			}
		}
		return indice;
	}

	/**
	 * Return vrai si myOngletName existe dans le tableau tabOngletNames
	 * 
	 * @param myOngletName
	 * @return
	 */
	private boolean existeTabOngletName(String myOngletName) {
		indiceTabOngletName(myOngletName);
		return (indiceTabOngletName(myOngletName) != -1);
	}

	/**
	 * Launch the application.
	 * 
	 * @throws BadLocationException
	 */
	public static void main(String[] args) throws BadLocationException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleChatFrameClient frame = new SimpleChatFrameClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Scanner sc = new Scanner(System.in);
		String line = ""; //$NON-NLS-1$
		while (!line.equals(".bye")) { //$NON-NLS-1$
			line = sc.nextLine();
		}
	}

	public static void sendMessage(String user, String line, Style styleBI, Style styleGP) {
		try {
			documentModel.insertString(documentModel.getLength(), user + " : ", styleBI); //$NON-NLS-1$
			documentModel.insertString(documentModel.getLength(), line + "\n", styleGP); //$NON-NLS-1$
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void sendMessage() {
		sender.setMsgToSend(textField.getText());
	}

	public SimpleChatFrameClient() {
		
		this(null, new DefaultListModel<String>(), SimpleChatClientApp.defaultDocumentModel());
	}

	/**
	 * Create the frame.
	 */
	public SimpleChatFrameClient(IfSenderModel sender, ListModel<String> clientListModel, Document documentModel) {
		this.sender = sender;
		this.documentModel = documentModel;
		this.listModel = clientListModel;
		setTitle(Messages.getString("SimpleChatFrameClient.4")); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu(Messages.getString("SimpleChatFrameClient.5")); //$NON-NLS-1$
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);

		JMenuItem mntmEnregistrerSous = new JMenuItem(Messages.getString("SimpleChatFrameClient.6")); //$NON-NLS-1$
		mnFile.add(mntmEnregistrerSous);

		JMenu mnOutils = new JMenu(Messages.getString("SimpleChatFrameClient.7")); //$NON-NLS-1$
		mnOutils.setMnemonic('O');
		menuBar.add(mnOutils);

		JMenuItem mntmEnvoyer = new JMenuItem(Messages.getString("SimpleChatFrameClient.8")); //$NON-NLS-1$
		mntmEnvoyer.setAction(sendAction);
		mnOutils.add(mntmEnvoyer);

		JSeparator separator = new JSeparator();
		mnOutils.add(separator);
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem(lockAction);
		mnOutils.add(chckbxmntmNewCheckItem);

		// US3 FRED 1
		JSeparator separator2 = new JSeparator();
		mnOutils.add(separator2);
		final JCheckBoxMenuItem chckbxAffichageBarreOutil = new JCheckBoxMenuItem("Barre de menu");
		mnOutils.add(chckbxAffichageBarreOutil);
		// US3 FRED 1 //

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// JSplitPane splitPane = new JSplitPane();
		splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		JList<String> list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			
			
			public void valueChanged(ListSelectionEvent e) {
				int iFirstSelectedElement = ((JList) e.getSource()).getSelectedIndex();
				if (iFirstSelectedElement >= 0 && iFirstSelectedElement < listModel.getSize()) {
					senderName = listModel.getElementAt(iFirstSelectedElement);
					getLblSender().setText(senderName);
					
					
					
				} else {
					getLblSender().setText("?"); //$NON-NLS-1$
				}
			}
		});
		
		list.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			    if (e.getClickCount() == 2) {
			        //code à exécuter
			    	syso_tabOngletNames("double clic");
			    	addOngletSplitPane(senderName, getPopupMenu());
			      }
				
			}
		});
		
		
		
		list.setMinimumSize(new Dimension(100, 0));
		splitPane.setLeftComponent(list);

		// JTextPane textArea = new JTextPane((StyledDocument)documentModel);
		// textArea.setEnabled(false);
		// JScrollPane scrollPaneText=new JScrollPane(textArea);

		// addPopup(textArea, popupMenu);
		popupMenu = new JPopupMenu();

		JCheckBoxMenuItem chckbxmntmLock = new JCheckBoxMenuItem(Messages.getString("SimpleChatFrameClient.10")); //$NON-NLS-1$
		chckbxmntmLock.setEnabled(isScrollLocked);
		// popupMenu.add(chckbxmntmLock);
		chckbxmntmLock.addActionListener(lockAction);

		// JScrollPane scrollPaneText=new JScrollPane(textArea);
		// scrollPaneText.getVerticalScrollBar().addAdjustmentListener(new
		// AdjustmentListener() {
		//
		// @Override
		// public void adjustmentValueChanged(AdjustmentEvent e) {
		// if(isScrollLocked){
		// e.getAdjustable().setValue(e.getAdjustable().getMaximum());
		// }
		// }
		// });

		// splitPane.setRightComponent(scrollPaneText);

		onglet = new JTabbedPane();
		addOngletSplitPane(nom1erOnglet, popupMenu);

		// --------------Pour Tester l'ajout des onglets ainsi que la
		// suppression
		// addOngletSplitPane("Salon Fred", popupMenu);
		// addOngletSplitPane("Salon Hubert", popupMenu);
		// removeTabOngletNames("Salon Fred");

		splitPane.setRightComponent(onglet);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel_1.add(panel);

		lblSender = new JLabel("?"); //$NON-NLS-1$
		lblSender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSender.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSender.setPreferredSize(new Dimension(100, 14));
		lblSender.setMinimumSize(new Dimension(100, 14));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), Messages.getString("SimpleChatFrameClient.12")); //$NON-NLS-1$
		textField.getActionMap().put(Messages.getString("SimpleChatFrameClient.13"), sendAction); //$NON-NLS-1$

		JButton btnSend = new JButton(sendAction);
		btnSend.setMnemonic(KeyEvent.VK_ENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup().addComponent(lblSender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(textField, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSend, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addGap(10)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(textField, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
										.addComponent(lblSender, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
										.addComponent(btnSend, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));
		panel.setLayout(gl_panel);

panel.setLayout(gl_panel);
		
		final JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton button = toolBar.add(sendAction);
		chckbxAffichageBarreOutil.setSelected(true);
		
		// US3 FRED 2
		chckbxAffichageBarreOutil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (chckbxAffichageBarreOutil.isSelected()){
					contentPane.add(toolBar);
					contentPane.repaint();

				} else {
					contentPane.remove(toolBar);
					contentPane.repaint();

				}
			}
		});
		// US3 FRED 2 //

	}

	public JLabel getLblSender() {
		return lblSender;
	}

	private abstract class ResourceAction extends AbstractAction {
		public ResourceAction() {
		}
	}

	private class SendAction extends ResourceAction {
		private Icon getIcon() {
			return new ImageIcon(SimpleChatFrameClient.class.getResource("send_16_16.jpg")); //$NON-NLS-1$
		}

		public SendAction() {
			putValue(NAME, Messages.getString("SimpleChatFrameClient.3")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, Messages.getString("SimpleChatFrameClient.2")); //$NON-NLS-1$
			putValue(SMALL_ICON, getIcon());
		}

		public void actionPerformed(ActionEvent e) {
			sendMessage();
			textField.setText("");
			
		}
	}

	private class LockAction extends ResourceAction {
		public LockAction() {
			putValue(NAME, Messages.getString("SimpleChatFrameClient.1")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, Messages.getString("SimpleChatFrameClient.0")); //$NON-NLS-1$
		}

		public void actionPerformed(ActionEvent e) {
			isScrollLocked = (!isScrollLocked);
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
