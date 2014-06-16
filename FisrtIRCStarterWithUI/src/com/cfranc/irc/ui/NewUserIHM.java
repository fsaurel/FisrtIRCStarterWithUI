package com.cfranc.irc.ui;

import java.awt.Dialog;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;



public class NewUserIHM extends JDialog {

    /**
     * Creates new form NewJFrame
     */
	
                 
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField3;
    public javax.swing.JTextField jTextField4;
    public JButton btnParcourir;
    public JFileChooser myfilechooser = new JFileChooser();

	
	
    public NewUserIHM(Dialog owner) {
    	super(owner, true);
        initComponents();
    }
    
    public NewUserIHM() {
    	super();
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pseudo : ");

        //jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nom : ");

        //jTextField2.setText("jTextField2");

        jLabel3.setText("Prénom : ");

        //jTextField3.setText("jTextField3");

        jLabel4.setText("Password : ");

       // jTextField4.setText("jTextField4");

        jLabel5.setText("Avatar : ");

        
        
        
        
        
        
        
        
       
        

        jButton1.setText("Valider");
        
//        jButton1.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButton1ActionPerformed(evt);
//            }
//        });

        jButton2.setText("Annuler");
        
        btnParcourir = new JButton("Parcourir...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(65)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel5)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jLabel6)
        					.addGap(18)
        					.addComponent(btnParcourir))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel1)
        						.addComponent(jLabel2))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(jTextField2, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
        						.addComponent(jTextField1, Alignment.TRAILING)))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel3)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel4)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jButton1)
        							.addGap(54)
        							.addComponent(jButton2))
        						.addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))))
        			.addContainerGap(141, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(49)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1)
        				.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(27)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel2)
        				.addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(27)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel3)
        				.addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(30)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel4)
        						.addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(31)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel5)
        						.addComponent(jLabel6)))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(82)
        					.addComponent(btnParcourir)))
        			.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jButton1)
        				.addComponent(jButton2))
        			.addGap(42))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	
    	if (jTextField1.getText().equals("")){
    		System.out.println("Test T1");
    		
    	}
	
    }                                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(NewUserIHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(NewUserIHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(NewUserIHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(NewUserIHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new NewUserIHM().setVisible(true);
//            }
//        });
//    }
    
    
	public void addValiderListener(ActionListener listenforvalider){
		jButton1.addActionListener(listenforvalider);
	}

	public void addAnnulerListener(ActionListener listenforannuler){
		jButton2.addActionListener(listenforannuler);
	}
	
	public void addParcourirListener(ActionListener listenforparcourir){
		btnParcourir.addActionListener(listenforparcourir);
	}
	
                 
}
