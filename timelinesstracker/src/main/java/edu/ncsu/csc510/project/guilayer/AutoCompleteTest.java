/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc510.project.guilayer;

import edu.ncsu.csc510.project.utillayer.ResourceFetcher;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author edw
 */
public class AutoCompleteTest extends JFrame implements ActionListener {

    private JButton button = new JButton("tombol");
    private JComboBox comboComplete = new JComboBox(new Object[]{"Hello","My","Name","Saurabh"});
    private JTextField textComplete = new JTextField(30);
    private List<String> strings = new ArrayList<String>();

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
        }
    }

    public AutoCompleteTest() {

        try {
	    ResourceFetcher fetcher = new ResourceFetcher();
	    strings = fetcher.getAirlineCodesAndNames();
	    Collections.sort(strings);

	    comboComplete = new JComboBox(strings.toArray());
            // change true to false to enable string restriction
            comboComplete.setEditable(true);
            comboComplete.setModel(new javax.swing.DefaultComboBoxModel(strings.toArray()));

            AutoCompleteDecorator.decorate(comboComplete);
            // change true to false to disable string restriction
            AutoCompleteDecorator.decorate(textComplete, strings, true);

            Container con = getContentPane();
            con.setLayout(new FlowLayout());
            con.add(comboComplete);
            con.add(textComplete);
            con.add(button);

            button.addActionListener(this);
            comboComplete.addActionListener(this);
        } catch (Exception ex) {
        }
    }
    
    public static void main(String[] args) {
        AutoCompleteTest autoCompletionTextField = new AutoCompleteTest();
        autoCompletionTextField.setVisible(true);
        autoCompletionTextField.pack();
        autoCompletionTextField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
