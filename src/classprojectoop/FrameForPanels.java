/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package classprojectoop;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Andrei Re.
 * @author Samantha
 */
public class FrameForPanels extends javax.swing.JFrame {
    NewsFeedFrame nff;
    MainPageImage mpi = new MainPageImage ();
    private Object loggedInUserName;
    
     private String getCurrentUsername() {
        // Implementation of getCurrentUsername...
        return "ExampleUsername";
    }
    
    
    /**
     * Creates new form FrameForPanels
     */
    public FrameForPanels() {
        try{
            this.nff = new NewsFeedFrame();
            initComponents();
            // Set the location relative to the center of the screen
            setLocationRelativeTo(null);
            this.setVisible(true);
            // Initially, show only the Sign In button
            hideAllButtonsExcept(inOutBtn);
            desktopPanel.add(mpi).setVisible(true);
            try{
                mpi.setSelected(true);
            }
            catch(java.beans.PropertyVetoException e){
                System.out.println(e);
            }
            desktopPanel.repaint();
        }
        catch(SQLException ex){
            Logger.getLogger(FrameForPanels.class.getName()).log(Level.SEVERE, null,ex);
        }
        
    }

        // Method to hide all buttons except a specified one
    private void hideAllButtonsExcept(javax.swing.JButton buttonToShow) {
        // Hide all buttons
        profilesBtn.setVisible(false);
        messagesBtn.setVisible(false);
        newsFeedBtn.setVisible(false);
        groupsBtn.setVisible(false);
        inOutBtn.setVisible(true);
        exitBtn.setVisible(true);

        // Show the specified button
        buttonToShow.setVisible(true);
    }
// Method to show appropriate buttons based on the user's login status
    private void showAppropriateButtons(boolean isLoggedIn) {
        if (loggedInUserName != null) {
            // Show buttons for logged-in user
            profilesBtn.setVisible(true);
            messagesBtn.setVisible(true);
            newsFeedBtn.setVisible(true);
            groupsBtn.setVisible(false);
            exitBtn.setVisible(true);
            inOutBtn.setVisible(false); // Hide Sign In button
        } else {
            // Show buttons for a user who is not logged in
            inOutBtn.setVisible(true);
            profilesBtn.setVisible(true);
            messagesBtn.setVisible(true);
            newsFeedBtn.setVisible(false);
            groupsBtn.setVisible(false);
            exitBtn.setVisible(true);
        }
    }

  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPanel = new javax.swing.JDesktopPane();
        profilesBtn = new javax.swing.JButton();
        messagesBtn = new javax.swing.JButton();
        newsFeedBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        groupsBtn = new javax.swing.JButton();
        inOutBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktopPanel.setBackground(new java.awt.Color(255, 255, 0));
        desktopPanel.setLayout(new java.awt.BorderLayout());

        profilesBtn.setText("Profiles");
        profilesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profilesBtnActionPerformed(evt);
            }
        });

        messagesBtn.setText("Messages");
        messagesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messagesBtnActionPerformed(evt);
            }
        });

        newsFeedBtn.setText("NewsFeed");
        newsFeedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newsFeedBtnActionPerformed(evt);
            }
        });

        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        groupsBtn.setText("Groups");
        groupsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupsBtnActionPerformed(evt);
            }
        });

        inOutBtn.setText("Sign In");
        inOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inOutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(exitBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(groupsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(profilesBtn)
                    .addComponent(messagesBtn)
                    .addComponent(newsFeedBtn)
                    .addComponent(inOutBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {exitBtn, groupsBtn, inOutBtn, messagesBtn, newsFeedBtn, profilesBtn});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPanel)
            .addGroup(layout.createSequentialGroup()
                .addComponent(profilesBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messagesBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newsFeedBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(groupsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addComponent(inOutBtn)
                .addGap(18, 18, 18)
                .addComponent(exitBtn)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
   // TODO add your handling code here:
    System.exit(0);
    }

    private void profilesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profilesBtnActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_profilesBtnActionPerformed

    private void messagesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messagesBtnActionPerformed
        // TODO add your handling code here:
       

    }//GEN-LAST:event_messagesBtnActionPerformed

    private void newsFeedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newsFeedBtnActionPerformed
        // TODO add your handling code here:
        if (desktopPanel.getSelectedFrame() != nff) {   // if the user pressed the button while it was open it would throw an exception
            // so i added a check to make sure the current frame wasnt selected

            //  frame is selected and then remove it from the desktop
            desktopPanel.removeAll();
            // add the newsfeed to the desktopPanel
            desktopPanel.add(nff).setVisible(true);
            try {
                nff.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                System.out.println(e);
            }
            desktopPanel.repaint();
            desktopPanel.revalidate();
        }
    }//GEN-LAST:event_newsFeedBtnActionPerformed

    private void groupsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupsBtnActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_groupsBtnActionPerformed

    private void inOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inOutBtnActionPerformed
     
    
      try {
            FrontMainPage fmp = new FrontMainPage();
            fmp.setVisible(true);
            this.setVisible(false);
            boolean isLoggedIn = true; // Set login logic
            showAppropriateButtons(isLoggedIn);
            mpi.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            System.out.println(e);
        }
       
        
    

    }//GEN-LAST:event_inOutBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameForPanels.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameForPanels.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameForPanels.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameForPanels.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameForPanels().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPanel;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton groupsBtn;
    private javax.swing.JButton inOutBtn;
    private javax.swing.JButton messagesBtn;
    private javax.swing.JButton newsFeedBtn;
    private javax.swing.JButton profilesBtn;
    // End of variables declaration//GEN-END:variables
}
