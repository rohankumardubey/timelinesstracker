/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DelayPanel.java
 *
 * Created on Feb 16, 2013, 7:25:12 PM
 */
package edu.ncsu.csc510.project.guilayer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author svpendse1
 */
public class DelayPanel extends javax.swing.JPanel {
    JLabel delayLabel, trendLabel;
    JLabel changeLabel;
        
    /** Creates new form DelayPanel */
    public DelayPanel() {
        initComponents();
        this.setLayout(new MigLayout("fillx, insets", "[]10[]", "[]10[]"));
        this.add(delayLabel = new JLabel("Delay Index : "), "left, wrap");
        this.add(trendLabel = new JLabel("Trend : "), "left, wrap");
        this.add(changeLabel = new JLabel("Change : "), "left, wrap");
        this.setBorder(BorderFactory.createTitledBorder("Delay"));
        this.setVisible(false);
    }

    public void setDelay(String delay) {
        delayLabel.setText("<html><b>Delay Index : </b>" + delay + "</html>");
    }

    public void setTrend(String trend) {
        trendLabel.setText("<html><b>Trend : </b>" + trend + "</html>");
    }

    public void setChange(String change) {
        changeLabel.setText("<html><b>Change : </b>" + change + "</html>");
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
