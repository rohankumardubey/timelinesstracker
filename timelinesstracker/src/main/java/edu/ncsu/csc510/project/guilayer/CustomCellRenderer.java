/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc510.project.guilayer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author svpendse1
 */

  public class CustomCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (isSelected) {
                return c;
            }

            if (value.toString().toLowerCase().contains("cancelled")) {
                c.setForeground(Color.BLACK);
                c.setBackground(Color.RED);
            } else if (value.toString().toLowerCase().contains("scheduled")) {
                c.setForeground(Color.WHITE);
                c.setBackground(Color.BLUE);
            } else if (value.toString().toLowerCase().contains("on-time")) {
                c.setForeground(Color.BLACK);
                c.setBackground(Color.GREEN);    
            } else if (value.toString().toLowerCase().contains("min")) {
                c.setForeground(Color.BLACK);
                c.setBackground(Color.YELLOW);
            } else if (value.toString().toLowerCase().contains("of")) {
                String parts[] = value.toString().split(" ");
                float rating = Float.parseFloat(parts[0]);
                if (rating < 2.0f) {
                    c.setForeground(Color.BLACK);
                    c.setBackground(Color.RED);
                } else if (rating < 4.0f) {
                    c.setForeground(Color.BLACK);
                    c.setBackground(Color.YELLOW);
                } else {
                    c.setForeground(Color.BLACK);
                    c.setBackground(Color.GREEN);
                }
            } else {
                c.setForeground(Color.BLACK);
                c.setBackground(Color.WHITE);
            }
            return c;
        }    
    }
