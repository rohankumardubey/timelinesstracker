/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc510.project.guilayer;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author svpendse1
 */
public class SharedListSelectionHandler implements ListSelectionListener {
        private FlightStatusPanel panel;
        public SharedListSelectionHandler(FlightStatusPanel panel) {
            this.panel = panel;
        }
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (lsm.isSelectionEmpty()) {
            } else {
                int minIndex= lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        panel.getTrackButton().setEnabled(true);
                        break;
                    }
                }
            }
        }
    }