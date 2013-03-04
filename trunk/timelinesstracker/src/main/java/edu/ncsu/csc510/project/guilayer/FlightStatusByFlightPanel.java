/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StatusByFlightPanel.java
 *
 * Created on Jan 27, 2013, 4:08:05 PM
 */
package edu.ncsu.csc510.project.guilayer;
import edu.ncsu.csc510.project.commlayer.FlightStatusComm;
import edu.ncsu.csc510.project.utillayer.FlightInformation;
import edu.ncsu.csc510.project.utillayer.FlightStatusData;
import edu.ncsu.csc510.project.utillayer.ResourceFetcher;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
/**
 *
 * @author svpendse1
 */
public class FlightStatusByFlightPanel extends FlightStatusPanel {

	/** Creates new form StatusByFlightPanel */
	public FlightStatusByFlightPanel() {
		initComponents();
		this.removeAll();
        statusPanel = new FlightStatusPanel();
        infoPanel = new FlightInformationPanel();
		this.setLayout(new MigLayout("insets 10", "[]10[]", "[]10[]"));
		this.add(this.jPanel1, "center, span 2 1, wrap, w 100%");
        this.add(statusPanel, "left, w 50%, height 220:220:220");
        this.add(infoPanel, "right, w 50%, height 220:220:220, wrap");
        
//		this.add(this.jScrollPane1, "left, w 50%, height 200:200:200");
//		this.add(this.jScrollPane3, "right, w 50%, height 200:200:200, wrap");

        
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new MigLayout("insets 10", "[]30[]", ""));
		labelPanel.add(this.qrcodeLabel, "left, width 200:200:200, height 225:300:300");
		labelPanel.add(this.mapLabel, "right, width 600:600:600, height 225:300:300");
		this.add(labelPanel, "span 2 1, w 100%");
		this.flightStatusPane.setContentType("text/html");
		this.flightInfoPane.setContentType("text/html");
        this.jScrollPane1.setViewportView(flightStatusPane);
        this.jScrollPane3.setViewportView(flightInfoPane);

		ResourceFetcher fetcher = new ResourceFetcher();
		try {
			this.airlineField.setModel(new DefaultComboBoxModel(fetcher.getAirlineCodesAndNames().toArray()));
	        	 AutoCompleteDecorator.decorate(airlineField);
		} catch (Exception e) {
			e.printStackTrace();
		}

        dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        DateFormat df[] = new DateFormat[1];
        df[0] = dateFormat; 
        this.jXDatePicker1.setFormats(df);
        this.jXDatePicker1.setDate(new Date());
	}

    public void setAirlineField(String airline) {
        this.airlineField.setSelectedItem(airline);   
    }
    public void setFlightNumberField(String flightNumber) {
        this.flightNumberField.setText(flightNumber); 
    }
    public void setDateField(String date) {
        try {
            this.jXDatePicker1.setDate(dateFormat.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mapLabel = new javax.swing.JLabel();
        qrcodeLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        flightNumberField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        airlineField = new javax.swing.JComboBox();
        busyLabel = new org.jdesktop.swingx.JXBusyLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        flightInfoPane = new javax.swing.JEditorPane();
        jScrollPane3 = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        flightStatusPane = new javax.swing.JEditorPane();

        setPreferredSize(new java.awt.Dimension(640, 464));

        mapLabel.setMaximumSize(new java.awt.Dimension(461, 131));

        qrcodeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Parameters"));

        jLabel2.setText("Flight Number");

        jLabel1.setText("Airline Name");

        flightNumberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flightNumberFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Date");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        busyLabel.setEnabled(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(69, 69, 69)
                        .add(jLabel1))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(airlineField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 241, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 112, Short.MAX_VALUE)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(flightNumberField)
                            .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(83, 83, 83)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(8, 8, 8)
                                .add(jXDatePicker1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(54, 54, 54)
                                .add(jButton1))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(67, 67, 67)
                                .add(jLabel3)))
                        .addContainerGap(144, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(busyLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(730, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(airlineField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(flightNumberField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jXDatePicker1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(busyLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        flightInfoPane.setEditable(false);
        flightInfoPane.setMaximumSize(new java.awt.Dimension(100, 16));
        jScrollPane1.setViewportView(flightInfoPane);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        flightStatusPane.setEditable(false);
        flightStatusPane.setMaximumSize(new java.awt.Dimension(100, 16));
        jScrollPane3.setViewportView(flightStatusPane);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(layout.createSequentialGroup()
                                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 321, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 323, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createSequentialGroup()
                        .add(37, 37, 37)
                        .add(qrcodeLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 159, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(27, 27, 27)
                        .add(mapLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane1)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                .add(18, 30, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(qrcodeLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .add(mapLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

		private void flightNumberFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flightNumberFieldActionPerformed
		}

        public void searchByFlight() {
            jButton1ActionPerformed(null);
        }
// TODO add your handling code here:}//GEN-LAST:event_flightNumberFieldActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    this.busyLabel.setEnabled(true);
    this.busyLabel.setBusy(true);
    this.busyLabel.setText("Working...");
    new Thread(new SearchByFlightThread(this)).start();
}//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox airlineField;
    private org.jdesktop.swingx.JXBusyLabel busyLabel;
    private javax.swing.JEditorPane flightInfoPane;
    private javax.swing.JTextField flightNumberField;
    private javax.swing.JEditorPane flightStatusPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JLabel mapLabel;
    private javax.swing.JLabel qrcodeLabel;
    // End of variables declaration//GEN-END:variables
    private SimpleDateFormat dateFormat;
    private FlightStatusPanel statusPanel;
    private FlightInformationPanel infoPanel;

    public JButton getTrackButton() {
        return jButton1;
    }

    class SearchByFlightThread implements Runnable {
        FlightStatusByFlightPanel panel;
        
        public SearchByFlightThread(FlightStatusByFlightPanel panel) {
            this.panel = panel;
        }
        
        public void run() {
            FlightStatusComm comm = new FlightStatusComm();
        	String flightName = panel.airlineField.getSelectedItem().toString();
        	String flightNumber = panel.flightNumberField.getText();
        	String dateField = panel.dateFormat.format(panel.jXDatePicker1.getDate());//panel.dateField.getText();
        	boolean ismultipleFlight = comm.queryFlightStatusByFlight(flightName, flightNumber, dateField, FlightStatusComm.SearchMethod.BY_FLIGHT);

            if (ismultipleFlight) {
                Vector<String> flightSegments = comm.getMultipleFlightOptions();
                String s = (String)JOptionPane.showInputDialog(new JFrame(), "This flight has multiple segments. \n" 
                                                                + "Please select a segment from the list: ",
                                                               "Multiple Flights Alert", 
                                                               JOptionPane.QUESTION_MESSAGE,
                                                               null, flightSegments.toArray(),
                                                               flightSegments.get(0));
                System.out.println("Selected : " + s);
                comm.selectFlightSegmentPage(s);
            }
            
            FlightStatusData data = comm.getFlightStatusTable();
            if (data == null) {
                panel.busyLabel.setText("");
                panel.busyLabel.setBusy(false);
                panel.busyLabel.setEnabled(false);
                JOptionPane.showMessageDialog(new JFrame(), "No flight status available for the specified flight.");
                return;  
            }
            //panel.statusPanel.setStatus(data.status);
            panel.statusPanel.setSchedDeparture(data.schedDeparture);
            panel.statusPanel.setSchedArrival(data.schedArrival);
            panel.statusPanel.setActDeparture(data.actDeparture);
            panel.statusPanel.setEstArrival(data.estArrival);
            panel.statusPanel.setGate(data.gate);
            panel.statusPanel.setBaggageClaim(data.baggageClaim);
            panel.statusPanel.setVisible(true);
   	       //panel.flightStatusPane.setText(comm.getFlightStatusTable());

            FlightInformation information = comm.getFlightInformation();

            if (information == null) {
                panel.busyLabel.setText("");
                panel.busyLabel.setBusy(false);
                panel.busyLabel.setEnabled(false);
                JOptionPane.showMessageDialog(new JFrame(), "No flight information available for the specified flight.");
                return; 
            }
            
            panel.infoPanel.setStatus(data.status);
            panel.infoPanel.setRoute(information.route);
            panel.infoPanel.setDuration(information.duration);
            panel.infoPanel.setEquipment(information.equipment);
            panel.infoPanel.setOnTime(information.ontimeRating);
           	panel.infoPanel.setVisible(true);
            //panel.flightInfoPane.setText(comm.getFlightInformation());
            
            panel.qrcodeLabel.setIcon(new ImageIcon(comm.getQRCode()));
            panel.mapLabel.setIcon(new ImageIcon(comm.getMap()));
            //comm.getMapNew(panel.mapLabel);
            panel.busyLabel.setBusy(false);
            panel.busyLabel.setText("");
            panel.busyLabel.setEnabled(false);
        }
    }

    class FlightStatusPanel extends JPanel {

        JLabel statusLabel;
        JLabel schedDepartureLabel, schedArrivalLabel;
        JLabel actDepartureLabel, estArrivalLabel;
        JLabel gateLabel, baggageClaimLabel;
        
        public FlightStatusPanel() {
            this.setLayout(new MigLayout("fillx, insets", "[]10[]", "[]20[]"));
            //this.add(statusLabel = new JLabel("Status : "), "center, span 2 1, wrap");
            this.add(schedDepartureLabel = new JLabel("Sched. Departure : "), "left");
            this.add(schedArrivalLabel = new JLabel("Sched. Arrival : "), "left, wrap");
            this.add(actDepartureLabel = new JLabel("Act. Departure : "), "left");
            this.add(estArrivalLabel = new JLabel("Est. Arrival : "), "left, wrap");
            this.add(gateLabel = new JLabel("Gate : "), "left");
            this.add(baggageClaimLabel = new JLabel("Baggage Claim : "), "left, wrap");
            this.setBorder(BorderFactory.createTitledBorder("Flight Status"));
            this.setVisible(false);
        }

        public void setStatus(String status) {
            if (status == null) {
                status = "N/A";
            }
            statusLabel.setText("<html><b>Status : </b>" + status.replaceAll("\n", " ") + "</html>");
        }
        public void setSchedDeparture(String schedDeparture) {
            if (schedDeparture == null) {
                schedDeparture = "N/A";
            }
            schedDepartureLabel.setText(convertToMultiLine("<b>Scheduled Departure</b>\n" + schedDeparture));
        }

        public void setSchedArrival(String schedArrival) {
            if (schedArrival == null) {
                schedArrival = "N/A";
            }
            schedArrivalLabel.setText(convertToMultiLine("<b>Scheduled Arrival</b>\n" + schedArrival));
        }

        public void setActDeparture(String actDeparture) {
            if (actDeparture == null) {
                actDeparture = "N/A";
            }
            actDepartureLabel.setText(convertToMultiLine("<b>Actual Departure</b>\n" + actDeparture));
        }

        public void setEstArrival(String estArrival) {
            if (estArrival == null) {
                estArrival = "N/A";
            }
            estArrivalLabel.setText(convertToMultiLine("<b>Estimated Arrival</b>\n" + estArrival));
        }
        
        public void setGate(String gate) {
            if (gate == null) {
                gate = "N/A";
            }
            gateLabel.setText(convertToMultiLine("<b>Gate</b>\n" + gate));
        }
        
        public void setBaggageClaim(String baggageClaim) {
            if (baggageClaim == null) {
                baggageClaim = "N/A";
            }
            baggageClaimLabel.setText(convertToMultiLine("<b>Baggage Claim</b>\n" + baggageClaim));
        }
        
        public String convertToMultiLine(String input) {
            return "<html>" + input.replaceAll("\n","<br>") + "</html>";
        }
    }

    class FlightInformationPanel extends JPanel {

        JLabel statusLabel, routeLabel;
        JLabel durationLabel, equipmentLabel;
        JLabel onTimeLabel;
        
        public FlightInformationPanel() {
            this.setLayout(new MigLayout("fillx, insets", "[]10[]", "[]12[]"));
            this.add(statusLabel = new JLabel("Status :"), "center, span 2 1, wrap");
            this.add(routeLabel = new JLabel("Route : "), "left, wrap");
            this.add(durationLabel = new JLabel("Duration : "), "left, wrap");
            this.add(equipmentLabel = new JLabel("Equipment : "), "left, wrap");
            this.add(onTimeLabel = new JLabel("On-time Rating : "), "left, wrap");
            this.setBorder(BorderFactory.createTitledBorder("Flight Information"));
            this.setVisible(false);
        }
        
        public void setStatus(String status) {

            statusLabel.setText("<html><b>" + status.replaceAll("\n", " ") +"</b></html>");          
            Border raisedbevel, loweredbevel, compound;
            raisedbevel = BorderFactory.createRaisedBevelBorder();
            loweredbevel = BorderFactory.createLoweredBevelBorder();
            compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
            statusLabel.setBorder(compound);
            statusLabel.setOpaque(true);
            
            if (status.contains("Canceled")) {
                statusLabel.setBackground(Color.RED);
                statusLabel.setForeground(Color.WHITE);
            } else if (status.contains("Scheduled")) {
                statusLabel.setBackground(Color.BLUE);
                statusLabel.setForeground(Color.WHITE);
            } else if (status.contains("Delayed")) {
                statusLabel.setBackground(Color.YELLOW);
                statusLabel.setForeground(Color.BLACK);
            } else if (status.contains("On-time")) {
                statusLabel.setBackground(Color.GREEN);
                statusLabel.setForeground(Color.BLACK);
            }
        }
        
        public void setRoute(String route) {
            if (route == null) {
                route = "N/A";
            }
            routeLabel.setText("<html><b>Route : </b>" + route.replaceAll("\n", " ") + "</html>");
        }

        public void setDuration(String duration) {
            if (duration == null) {
                duration = "N/A";
            }
            durationLabel.setText("<html><b>Duration : </b>" + duration + "</html>");
        }

        public void setEquipment(String equipment) {
            if (equipment == null) {
                equipment = "N/A";
            }
            equipmentLabel.setText("<html><b>Equipment : </b>" + equipment + "</html>");       
        }

        public void setOnTime(String onTime) {
            
            Pattern p = Pattern.compile("-?\\d+\\.?\\d* of 5");
            Matcher m = p.matcher(onTime);
            String output = null;
            while (m.find()) {
                output = m.group();
            }
            System.out.println("Match : " + output);
            if (output == null) {
                output = "N/A";
            }
            onTimeLabel.setText("<html><b>On Time Rating : </b>" + output + "</html>");
        }
    }
}
