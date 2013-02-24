/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc510.project.guilayer;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author svpendse1
 */
public class MigLayoutTest extends JFrame {
	JPanel panel1, panel2;
	JButton button1, button2, button3, button4;

    public void weatherLayoutTest() {
        this.setLayout(new MigLayout("fillx, insets", "[]10[]", "[]20[]"));
		panel1 = new JPanel();
        panel1.setLayout(new MigLayout("fillx, insets", "[]10[]", "[]20[]"));
	
        panel1.add(new JLabel("Current Conditions: "), "left");
        panel1.add(new JLabel("Temperature: "), "right, wrap");
        panel1.add(new JLabel("Dewpoint: "), "left");
        panel1.add(new JLabel("Visibility: "), "right, wrap");
        panel1.add(new JLabel("Wind: "), "left");
        panel1.add(new JLabel("Sky Conditions: "), "right, wrap");
        panel1.setBorder(BorderFactory.createTitledBorder("panel 1"));
        /*panel1.add(new JLabel("Airport"), "center");
		panel1.add(new JLabel("Date"), "center");
		panel1.add(new JLabel("Timer Period"), "center");
		panel1.add(new JLabel("Airline (Optional)"), "center, wrap");
		panel1.add(new JTextField(10), "center, width :150:" );
		panel1.add(new JTextField(10), "center, width :150:");
		panel1.add(new JTextField(10), "center, width :150:" );
		panel1.add(new JTextField(10), "center, width :150:, wrap");
		panel1.add(new JRadioButton("Departure"), "");
		panel1.add(new JRadioButton("Arrival"), "");
		panel1.add(new JButton("Search"), "span 2 1, center");*/
		this.add(panel1, "w 50%, left");

        panel2 = new JPanel();
        panel2.setLayout(new MigLayout("fillx, insets", "[]10[]", "[]20[]"));
	
        panel2.add(new JLabel("Delay Index: "), "left, wrap");
        panel2.add(new JLabel("Trend: "), "left, wrap");
        panel2.add(new JLabel("Change: "), "left, wrap");
        panel2.setBorder(BorderFactory.createTitledBorder("panel 2"));
        this.add(panel2, "w 50%, right");
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//panel.setLayout(new MigLayout("insets 10", "[grow][grow][grow]", "[grow][grow]"));

    }
    public void flightLayoutTest() {
        this.setLayout(new MigLayout("fillx, insets", "[]10[]", "[]20[]"));
		panel1 = new JPanel();
        panel1.setLayout(new MigLayout("fillx, insets", "[]10[]", "[]20[]"));
	
        panel1.add(new JLabel("<html><b>Sched. Departure</b> <br> Sat Feb 16 3:41 PM</html>" ), "left");
        panel1.add(new JLabel("Sched. Arrival : "), "right, wrap");
        panel1.add(new JLabel("Act. Departure : "), "left");
        panel1.add(new JLabel("Est. Arrival : "), "right, wrap");
        panel1.add(new JLabel("Arrival Gate : "), "left");
        panel1.add(new JLabel("Baggage Claim : "), "right, wrap");
 
        /*panel1.add(new JLabel("Airport"), "center");
		panel1.add(new JLabel("Date"), "center");
		panel1.add(new JLabel("Timer Period"), "center");
		panel1.add(new JLabel("Airline (Optional)"), "center, wrap");
		panel1.add(new JTextField(10), "center, width :150:" );
		panel1.add(new JTextField(10), "center, width :150:");
		panel1.add(new JTextField(10), "center, width :150:" );
		panel1.add(new JTextField(10), "center, width :150:, wrap");
		panel1.add(new JRadioButton("Departure"), "");
		panel1.add(new JRadioButton("Arrival"), "");
		panel1.add(new JButton("Search"), "span 2 1, center");*/
		this.add(panel1, "w 50%, left");

        panel2 = new JPanel();
        panel2.setLayout(new MigLayout("fillx, insets", "[]10[]", "[]20[]"));
	
        panel2.add(new JLabel("Status"), "center, wrap");
        panel2.add(new JLabel("Route : "), "left, wrap");
        panel2.add(new JLabel("Duration : "), "left, wrap");
        panel2.add(new JLabel("Equipment : "), "left, wrap");
        panel2.add(new JLabel("On-time Rating : "), "left, wrap");

        this.add(panel2, "w 50%, right");
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//panel.setLayout(new MigLayout("insets 10", "[grow][grow][grow]", "[grow][grow]"));


    }
	public MigLayoutTest() {
        weatherLayoutTest();  	
    }
	public static void main(String[] args) {
		MigLayoutTest test = new MigLayoutTest();
	}	
}
