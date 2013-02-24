/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc510.project.commlayer;

import java.util.logging.Level;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import edu.ncsu.csc510.project.utillayer.DelayData;
import edu.ncsu.csc510.project.utillayer.WeatherData;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


public class AirportStatusComm {

	HtmlPage currentPage;
    boolean ismultipleFlight;
    HashMap<String, String> flightSegmentMap;
    Vector<String> flightSegments;
    List<HtmlAnchor> anchorList;
    //MapFetcher fetcherThread;
	public static enum SearchMethod {
		BY_FLIGHT,
		BY_AIRPORT,
		BY_ROUTE
	}
	private static final float kThreshold = 0.5f;	
	static WebClient browser;
	static {
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		browser = new WebClient(BrowserVersion.FIREFOX_3_6);
		browser.setJavaScriptEnabled(false);
	}
    
	public AirportStatusComm() {
        ismultipleFlight = false;
		try {
			currentPage = (HtmlPage) browser.getPage("http://www.flightstats.com/go/Airport/weather.do");
		}
		catch(Exception e) {
			System.out.println("Could not open browser window");
			e.printStackTrace();		
		}		
	}

    public void queryWeatherStatusByAirport(String airportName) {
        try {
    	    ((HtmlTextInput) currentPage.getElementByName("airport")).setValueAttribute(airportName);	
            HtmlImageInput  button = (HtmlImageInput)currentPage.getElementById("airportWeatherForm_0");			
		    currentPage = (HtmlPage)button.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    public WeatherData getWeatherTable() {
        List<DomElement> tableList = currentPage.getElementsByTagName("table");
        
        //Airport weather table : 12th table
        HtmlTable table = (HtmlTable)tableList.get(11);
        System.out.println(table.asText());

        HtmlTable windTable = (HtmlTable)tableList.get(9);
       
        /*for (HtmlTableRow row : table.getRows()) {
				for (HtmlTableCell cell : row.getCells()) {
					System.out.print(cell.asText() + " | ");
				}
                System.out.println();
		}*/

        WeatherData data = new WeatherData.WeatherBuilder().conditions(table.getCellAt(0, 1).asText()).temperature(table.getCellAt(1, 1).asText()).dewpoint(table.getCellAt(2, 1).asText()).visibility(windTable.getCellAt(2, 1).asText()).wind(windTable.getCellAt(3, 1).asText().replaceAll("\n", "")).skyconditions(windTable.getCellAt(4, 1).asText().replaceAll("\n", "")).build();
        
        return data;
    }
   
    public DelayData getDelaysTable() {
        List<DomElement> tableList = currentPage.getElementsByTagName("table");

        //Airport delay table : 14th table
        HtmlTable table = (HtmlTable)tableList.get(13);
        /*for (HtmlTableRow row : table.getRows()) {
				for (HtmlTableCell cell : row.getCells()) {
					System.out.print(cell.asText() + " | ");
				}
                System.out.println();
		}*/

        DelayData data = new DelayData.DelayBuilder().delay(table.getCellAt(0, 1).asText()).trend(table.getCellAt(1, 1).asText()).change(table.getCellAt(2, 1).asText()).build();
        
        return data; 
    }
    /*public String getDelayTable() {
        
    }*/

    public static void main(String[] args) {
        AirportStatusComm comm = new AirportStatusComm();
        comm.queryWeatherStatusByAirport("Boston, MA, US (BOS)");
        comm.getWeatherTable();
        System.out.println("\n\n");
        //comm.getDelaysTable();
    }
}

