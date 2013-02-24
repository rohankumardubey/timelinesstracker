/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StatusByAirport.java
 *
 * Created on Jan 27, 2013, 4:08:15 PM
 */
package edu.ncsu.csc510.project.guilayer;
import edu.ncsu.csc510.project.commlayer.AirportStatusComm;
import edu.ncsu.csc510.project.commlayer.FlightStatusComm;
import edu.ncsu.csc510.project.utillayer.DelayData;
import edu.ncsu.csc510.project.utillayer.ResourceFetcher;
import edu.ncsu.csc510.project.utillayer.WeatherData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
/**
 *
 * @author svpendse1
 */
public class FlightStatusByAirportPanel extends javax.swing.JPanel {

	/** Creates new form StatusByAirport */
	public FlightStatusByAirportPanel(javax.swing.JTabbedPane parentTab, FlightStatusByFlightPanel flightPanel) {
        this.parentTab = parentTab;
        this.flightPanel = flightPanel;
        initComponents();
		this.buttonGroup1.add(this.departureOption);
		this.buttonGroup1.add(this.arrivalOption);
		this.departureOption.setSelected(true);
		ResourceFetcher fetcher = new ResourceFetcher();
        weatherPanel = new WeatherPanel();
        delayPanel = new DelayPanel();

		try {
            LinkedList<String> airlinecodes = fetcher.getAirlineCodesAndNames();
            airlinecodes.addFirst("All Airlines");
			this.airportField.setModel(new DefaultComboBoxModel(fetcher.getAirportCodesAndNames().toArray()));
			this.airlineField.setModel(new DefaultComboBoxModel(airlinecodes.toArray()));
	        AutoCompleteDecorator.decorate(airportField);
			AutoCompleteDecorator.decorate(airlineField);
		} catch (Exception e) {
			e.printStackTrace();
		}
        this.removeAll();
        this.setLayout(new MigLayout("insets 10", "[]10[]", "[]10[]"));
		this.add(this.jPanel1, "center, span 2 1, wrap, w 100%");
        this.add(this.jScrollPane1, "center, span 2 1, wrap, w 100%");
        this.add(this.trackButton, "left, span 1 2, h 50%, w 50%");
        this.trackButton.setVisible(false);
        this.add(weatherPanel, "right, w 50%, wrap");
        listSelectionModel = this.jTable1.getSelectionModel();
        listSelectionModel.addListSelectionListener(new SharedListSelectionHandler(this));
        this.jTable1.setSelectionModel(listSelectionModel);
        this.jScrollPane1.setVisible(false);
        dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        DateFormat df[] = new DateFormat[1];
        df[0] = dateFormat; 
        this.jXDatePicker1.setFormats(df);
        this.jXDatePicker1.setDate(new Date());
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        timeField = new javax.swing.JComboBox();
        searchButton = new javax.swing.JButton();
        departureOption = new javax.swing.JRadioButton();
        arrivalOption = new javax.swing.JRadioButton();
        airportField = new javax.swing.JComboBox();
        airlineField = new javax.swing.JComboBox();
        busyLabel = new org.jdesktop.swingx.JXBusyLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        trackButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(640, 464));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Parameters"));

        jLabel1.setText("Airport");

        jLabel2.setText("Date");

        jLabel3.setText("Time Period");

        jLabel4.setText("Airline (Optional)");

        timeField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Current Time Period", "0000 - 0600", "0600 - 1200", "1200 - 1800", "1800 - 0000" }));

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        departureOption.setText("Departures");

        arrivalOption.setText("Arrivals");

        airportField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                airportFieldActionPerformed(evt);
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
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(airportField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 215, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(departureOption)
                                .add(44, 44, 44)
                                .add(arrivalOption)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(89, 89, 89)
                                .add(airlineField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 326, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(searchButton))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jXDatePicker1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(33, 33, 33)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel4)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(timeField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(37, 37, 37)
                                        .add(busyLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(100, 100, 100)
                        .add(jLabel1)
                        .add(168, 168, 168)
                        .add(jLabel2)
                        .add(123, 123, 123)
                        .add(jLabel3)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(jLabel2)
                            .add(jLabel3))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(airportField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jXDatePicker1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(timeField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(busyLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(searchButton)
                    .add(airlineField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(departureOption)
                    .add(arrivalOption))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        airportTableModel = new javax.swing.table.DefaultTableModel(null,
            new String [] {
                "Destination", "Flight", "On-time Rating", "Airline", "Sched. Departure",
                "Act. Departure", "Term Gate", "Status", "Equipment"
            }
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(airportTableModel
        );
        jScrollPane1.setViewportView(jTable1);

        trackButton.setText("Track this flight");
        trackButton.setEnabled(false);
        trackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trackButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(382, Short.MAX_VALUE)
                .add(trackButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(331, 331, 331))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(trackButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
    this.busyLabel.setEnabled(true);
    this.busyLabel.setBusy(true);
    this.busyLabel.setText("Working...");
    new Thread(new SearchByAirport(this)).start();
    // TODO add your handling code here:
    /*FlightStatusComm comm = new FlightStatusComm();
    String airportName = this.airportField.getSelectedItem().toString();
    String date = this.dateField.getText();
    String timePeriod = this.timeField.getSelectedItem().toString();
    String airlineName = this.airlineField.getSelectedItem().toString();
    System.out.println("Airline name = " + airlineName);
    String searchOption = null;
    if (this.departureOption.isSelected()) {
        searchOption = "departures";
    }  else {
        searchOption = "arrivals";
    }
    if (airlineName.equals("All Airlines")) {
        airlineName = "";
    }
    comm.queryFlightStatusByAirport(airportName, date, timePeriod, airlineName, searchOption, FlightStatusComm.SearchMethod.BY_AIRPORT);
    Vector<String[]> output= comm.getFlightsByAirportTableContent();
    while (this.airportTableModel.getRowCount() != 0) {
        this.airportTableModel.removeRow(0);
    }
    if (output != null) {
        for (int i =0 ; i < output.size(); i++) {
            this.airportTableModel.addRow(output.get(i));
        }
    } else {
        System.out.println("Error fetching flight status data for the airport " + airportName + " from source");
    }*/
}//GEN-LAST:event_searchButtonActionPerformed

	private void airportFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_airportFieldActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_airportFieldActionPerformed

    private void trackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trackButtonActionPerformed
        // TODO add your handling code here:
        String airline, flightNumber, date;

        int selectedRowIndex = this.jTable1.getSelectedRow();
        airline = this.jTable1.getValueAt(selectedRowIndex, 3).toString();
        flightNumber = this.jTable1.getValueAt(selectedRowIndex, 1).toString();
        date = dateFormat.format(this.jXDatePicker1.getDate());

        flightPanel.setAirlineField(airline);
        flightPanel.setFlightNumberField(flightNumber);
        flightPanel.setDateField(date);
        parentTab.setSelectedComponent(flightPanel);
        flightPanel.searchByFlight();
    }//GEN-LAST:event_trackButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox airlineField;
    private javax.swing.JComboBox airportField;
    private javax.swing.JRadioButton arrivalOption;
    private org.jdesktop.swingx.JXBusyLabel busyLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton departureOption;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.table.DefaultTableModel airportTableModel;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox timeField;
    private javax.swing.JButton trackButton;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JSplitPane splitter;
    private javax.swing.ListSelectionModel listSelectionModel;
    private FlightStatusByFlightPanel flightPanel;
    private javax.swing.JTabbedPane parentTab;
    private SimpleDateFormat dateFormat;
    private WeatherPanel weatherPanel;
    private DelayPanel delayPanel;
    
    
    class SharedListSelectionHandler implements ListSelectionListener {
        private FlightStatusByAirportPanel panel;
        public SharedListSelectionHandler(FlightStatusByAirportPanel panel) {
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
                        panel.trackButton.setEnabled(true);
                        break;
                    }
                }
            }
        }
    }

    class SearchByAirport implements Runnable {
        FlightStatusByAirportPanel panel;
        
        public SearchByAirport(FlightStatusByAirportPanel panel) {
            this.panel = panel;
        }

        public void run() {
            FlightStatusComm comm = new FlightStatusComm();
            AirportStatusComm acomm = new AirportStatusComm();
            
            acomm.queryWeatherStatusByAirport(panel.airportField.getSelectedItem().toString());
            WeatherData wdata = acomm.getWeatherTable();
            DelayData ddata = acomm.getDelaysTable();

            panel.weatherPanel.setConditions(wdata.conditions);
            panel.weatherPanel.setDewpoint(wdata.dewpoint);
            panel.weatherPanel.setTemperature(wdata.temperature);
            panel.weatherPanel.setVisibility(wdata.visibility);
            panel.weatherPanel.setWind(wdata.wind);
            panel.weatherPanel.setSky(wdata.skyconditions);
            panel.weatherPanel.setChange(ddata.change);
            panel.weatherPanel.setDelay(ddata.delay);
            panel.weatherPanel.setTrend(ddata.trend);
            panel.weatherPanel.setBorder(BorderFactory.createTitledBorder(panel.airportField.getSelectedItem().toString() + " Weather"));

            
            String airportName = panel.airportField.getSelectedItem().toString();
            String date = panel.dateFormat.format(panel.jXDatePicker1.getDate());//panel.dateField.getText();
            String timePeriod = panel.timeField.getSelectedItem().toString();
            String airlineName = panel.airlineField.getSelectedItem().toString();
            System.out.println("Airline name = " + airlineName);
            String searchOption = null;
            if (panel.departureOption.isSelected()) {
                searchOption = "departures";
            }  else {
                searchOption = "arrivals";
            }
            if (airlineName.equals("All Airlines")) {
                airlineName = "";
            }
            comm.queryFlightStatusByAirport(airportName, date, timePeriod, airlineName, searchOption, FlightStatusComm.SearchMethod.BY_AIRPORT);
            Vector<String[]> output= comm.getFlightsByAirportTableContent();
            
            if (output == null) {
                panel.busyLabel.setText("");
                panel.busyLabel.setBusy(false);
                panel.busyLabel.setEnabled(false);
                JOptionPane.showMessageDialog(new JFrame(), "No flights at the specified airport."); 
                return; 
            }
            
            while (panel.airportTableModel.getRowCount() != 0) {
                panel.airportTableModel.removeRow(0);
            }
            if (output != null) {
                for (int i =0 ; i < output.size(); i++) {
                    panel.airportTableModel.addRow(output.get(i));
                }
            } else {
                System.out.println("Error fetching flight status data for the airport " + airportName + " from source");
            }
            panel.busyLabel.setBusy(false);
            panel.busyLabel.setText("");
            panel.busyLabel.setEnabled(false);
            panel.jScrollPane1.setVisible(true);
            panel.trackButton.setVisible(true);
            panel.weatherPanel.setVisible(true);
        }
    }
}