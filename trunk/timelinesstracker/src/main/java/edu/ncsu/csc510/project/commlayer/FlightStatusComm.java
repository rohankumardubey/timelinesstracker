/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc510.project.commlayer;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.UnexpectedPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlScript;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import edu.ncsu.csc510.project.utillayer.FlightInformation;
import edu.ncsu.csc510.project.utillayer.FlightStatusData;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FlightStatusComm {

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
    
	public FlightStatusComm() {
        ismultipleFlight = false;
		try {
			currentPage = (HtmlPage) browser.getPage("http://www.flightstats.com/go/FlightStatus/flightStatusByFlight.do");
		}
		catch(Exception e) {
			System.out.println("Could not open browser window");
		}		
	}

    public void queryFlightStatusByAirport(String airportName, String date, 
                                           String timePeriod, String airlineName, 
                                           String searchOption,
                                           SearchMethod method) {
          try {
            if (method == SearchMethod.BY_AIRPORT) {					
		((HtmlTextInput) currentPage.getElementByName("airport")).setValueAttribute(airportName);	
		((HtmlTextInput) currentPage.getElementByName("airportQueryDate")).setValueAttribute(date);
		HtmlSelect select = (HtmlSelect) currentPage.getElementByName("airportQueryTime");
		HtmlOption option = select.getOptionByText(timePeriod);
		select.setSelectedAttribute(option, true);
                if (airlineName != null) {
    		  ((HtmlTextInput) currentPage.getElementByName("airlineToFilter")).setValueAttribute(airlineName);
                }
                if (searchOption.equals("departures")) {
  		  ((HtmlRadioButtonInput) currentPage.getElementById("widgetFlightStatusByAirportForm_airportQueryType0")).setChecked(true);
                } else {
  		  ((HtmlRadioButtonInput) currentPage.getElementById("widgetFlightStatusByAirportForm_airportQueryType1")).setChecked(true);
                }
		HtmlImageInput  button = (HtmlImageInput)currentPage.getElementById("widgetFlightStatusByAirportForm_0");			
		currentPage = (HtmlPage)button.click();
            }
          } catch (Exception e) {
            System.err.println("Could not query flight status by airport");
          }
	} 
    
    public boolean queryFlightStatusByFlight(String airlineName, 
                                             String flightNumber, String date, 
                                             SearchMethod method) {
		try {
			if (method == SearchMethod.BY_FLIGHT) {
				((HtmlTextInput) currentPage.getElementByName("airline")).setValueAttribute(airlineName);
				((HtmlTextInput) currentPage.getElementByName("flightNumber")).setValueAttribute(flightNumber);
				((HtmlTextInput) currentPage.getElementByName("departureDate")).setValueAttribute(date);
				
				HtmlImageInput  button = (HtmlImageInput)currentPage.getElementById("widgetFlightStatusByFlightForm_0");			
				currentPage = (HtmlPage)button.click();
                if (currentPage.asText().contains("This flight has multiple segments.")) {
                   ismultipleFlight = true; 
                }
                return ismultipleFlight;
		    } 		    
            return false;
        } catch(Exception e) {
            System.err.println("Coudl not query status by flight");
		}
        return false;
	}

    public void queryFlightStatusByRoute(String departureName, String arrivalName, 
                                         String date, SearchMethod method) {
        try {
            if (method == SearchMethod.BY_ROUTE) {
				((HtmlTextInput) currentPage.getElementByName("departure")).setValueAttribute(departureName);
				((HtmlTextInput) currentPage.getElementByName("arrival")).setValueAttribute(arrivalName);
				((HtmlTextInput) currentPage.getElementById("fsByRouteDepartureDateTextField")).setValueAttribute(date);
				HtmlImageInput  button = (HtmlImageInput)currentPage.getElementById("widgetFlightStatusByRouteForm_0");			
				currentPage = (HtmlPage)button.click();
				//System.out.println(currentPage.asText());
            }
        } catch (Exception e) {
            System.err.println("Could not query status by route");
        }
        
    }
    public Vector<String> getMultipleFlightOptions() {
        if (ismultipleFlight) {
            HtmlDivision multiFlightDivision = (HtmlDivision) currentPage.getFirstByXPath("//div[@class='uiComponentBody']");
            anchorList = (List<HtmlAnchor>)multiFlightDivision.getByXPath("//div[@class='uiComponentBody']//a");
            flightSegments = new Vector<String>();
            flightSegmentMap = new HashMap<String, String>();
            for (int i = 0; i < anchorList.size(); ++i) {
                flightSegments.add(anchorList.get(i).asText());
                flightSegmentMap.put(anchorList.get(i).asText(), "http://www.flightstats.com" + anchorList.get(i).getHrefAttribute());
            }
            return flightSegments;
        }   
        return null;
    }

    public void selectFlightSegmentPage(String segmentName) {
        String href = flightSegmentMap.get(segmentName);
        try {
            currentPage = (HtmlPage) browser.getPage(href);
        } catch (Exception e) {
            System.err.println("Unable to get flight segment");
        }
    }
    
	public FlightStatusData getFlightStatusTable() {	
		try {
            HtmlDivision division = (HtmlDivision) currentPage.getFirstByXPath("//div[@class='statusType']");
            HtmlTable table = null;
            table = (HtmlTable) currentPage.getFirstByXPath("//table[@class='statusDetailsTable']");
            if (table == null) {
                System.out.println("Flight status table not found");
                return null;
            }
            
            System.out.println("status table ");
            for (HtmlTableRow row : table.getRows()) {
				for (HtmlTableCell cell : row.getCells()) {
					System.out.print(cell.asText() + " ");
				}
                System.out.println();
			}
            //Sched. departure Sched. Arrival : Row 3 (index 2)            
            //Act. departure Est. Arrival : Row 5 (index 4)
            //Arrival Gate Baggage Claim : Row 7 (index 6)

            System.out.println("Building data");
            FlightStatusData data = null;

            if (table.getRowCount() == 7) {
                data = new FlightStatusData.FlightStatusBuilder().schedDeparture(table.getCellAt(2, 0).asText()).schedArrival(table.getCellAt(2, 1).asText()).actDeparture(table.getCellAt(4, 0).asText()).estArrival(table.getCellAt(4, 1).asText()).gate(table.getCellAt(6, 0).asText()).baggageClaim(table.getCellAt(6, 1).asText()).status(division.asText()).build();
            } else if (table.getRowCount() == 6) {
                String gate = table.getCellAt(5, 0).asText();
                String parts[] = gate.split(":");
                data = new FlightStatusData.FlightStatusBuilder().schedDeparture(table.getCellAt(2, 0).asText()).schedArrival(table.getCellAt(2, 1).asText()).actDeparture(table.getCellAt(4, 0).asText()).estArrival(table.getCellAt(4, 1).asText()).gate(parts[1]).status(division.asText()).build();
            } else {
                 data = new FlightStatusData.FlightStatusBuilder().schedDeparture(table.getCellAt(2, 0).asText()).schedArrival(table.getCellAt(2, 1).asText()).status(division.asText()).build();
            }
            System.out.println("Data built");
            
            return data;
			//return division.asXml() + table.asXml();
		} catch (Exception e) {
			System.err.println("Could not get flight status table");
            return null;
		}
	}
    
    public FlightInformation getFlightInformation() {
		try {
            HtmlTable table = null;
            table = (HtmlTable) currentPage.getFirstByXPath("//table[@class='contentTable']");
            if (table == null) {
                System.out.println("Flight Information table not found");
                return null;
            }

            for (HtmlTableRow row : table.getRows()) {
				for (HtmlTableCell cell : row.getCells()) {
					System.out.print(cell.asText() + " | ");
				}
                System.out.println();
			}
            
            //Route : Row 1, Column 3 (index 0, 2)
            //Duration : Row 2, Column 3 (index 1, 2)
            //Equipment : Row 3, Column 3 (index 2, 2)
            //On time rating : Row 5, Column 3 (index 4, 2)

            int onTimeIndex = 4;
           
            if (table.getCellAt(3, 0).asText().contains("On-Time Rating")) {
                onTimeIndex = 3;
            }
            
            FlightInformation information = new FlightInformation.FlightInformationBuilder().route(table.getCellAt(0, 2).asText()).duration(table.getCellAt(1, 2).asText()).equipment(table.getCellAt(2, 2).asText()).ontimeRating(table.getCellAt(onTimeIndex, 2).asText()).build();

            return information;
		    //return table.asXml();
        } catch (Exception e) {
			return null;
		}		
	}
        
	public Vector<String []> getFlightsByAirportTableContent() {
        try {
            DomNodeList<DomElement> list = currentPage.getElementsByTagName("table");

            //The 10th table corresponds to the flight statuses table
            HtmlTable table = (HtmlTable)list.get(10);
            Vector<String []> output = new Vector();
            //System.out.println("Row count in table = " + table.getRowCount());
            
            //The first two rows are the table headers. Avoid them!!
            for (int i = 2; i < table.getRowCount(); i++) {
                HtmlTableRow row = table.getRow(i);
                String[] currentRow = new String[9];
                int count = 0;
                for (int j = 0; j < row.getChildElementCount() - 1; j++) {
                    if (j == 2) {
                        String temp = row.getCell(j).asXml();
                        Pattern p = null;
                        Matcher m = null;
                        try {
                            p = Pattern.compile("-?\\d+px;");
                            m = p.matcher(temp);
                            while (m.find()) {
                                currentRow[count] = m.group();
                            }
                            float rating = 1.0f * 
                                           (Integer.parseInt(currentRow[count].substring(0, currentRow[count].length() - 3)) + 100) / 20;
                            currentRow[count] = "" + rating + " of 5";
                        } catch(Exception e) {
                            System.out.println("No match found");
                            currentRow[count] = "";
                        }
                    } else {
                        currentRow[count] = row.getCell(j).asText();
                        currentRow[count] = currentRow[count].replaceAll("\\^", "");
                        currentRow[count] = currentRow[count].replaceAll("~", "");
                    }
                    count++;
                }
                output.add(currentRow);
			}
            return output;
        } catch (Exception e) {
        }
        return null; 
    }

	public Vector<String []> getFlightsByRouteTableContent() {
        try {
            System.out.println(currentPage.asXml());
            DomNodeList<DomElement> list = currentPage.getElementsByTagName("table");

            //The 17th table corresponds to the flight statuses table
            HtmlTable table = (HtmlTable)list.get(16);
            Vector<String []> output = new Vector();
            //HtmlTable table = (HtmlTable) currentPage.getFirstByXPath("//table[@class='tableListingTable']");
            //Vector<String []> output = new Vector();
            System.out.println("Row count in table = " + table.getRowCount());
            
            //The first two rows are the tab6e headers. Avoid them!!
            for (int i = 2; i < table.getRowCount(); i++) {
                HtmlTableRow row = table.getRow(i);
                String[] currentRow = new String[11];
                int count = 0;
                for (int j = 0; j < row.getChildElementCount() - 1; j++) {
                    if (j == 1) {
                        String temp = row.getCell(j).asXml();
                        Pattern p = null;
                        Matcher m = null;
                        try {
                            p = Pattern.compile("-?\\d+px;");
                            m = p.matcher(temp);
                            while (m.find()) {
                                currentRow[count] = m.group();
                            }
                            float rating = 1.0f * 
                                           (Integer.parseInt(currentRow[count].substring(0, currentRow[count].length() - 3)) + 100) / 20;
                            currentRow[count] = "" + rating + " of 5";
                        } catch(Exception e) {
                            System.out.println("No match found");
                            currentRow[count] = "";
                        }
                    } else {
                        currentRow[count] = row.getCell(j).asText();
                        currentRow[count] = currentRow[count].replaceAll("\\^", "");
                        currentRow[count] = currentRow[count].replaceAll("~", "");
                    }
                    count++;
                }
                output.add(currentRow);
			}
            return output;
        } catch (Exception e) {
        }
        return null; 
    }

	public BufferedImage getQRCode() {
		BufferedImage code = null;
		try {
            HtmlImage image = (HtmlImage)currentPage.getFirstByXPath("//img[@alt='Scan with Mobile Phone']");
			//7th image tag refers to the SPARQ barcode image
			code = image.getImageReader().read(0);			
		} catch (Exception e) {
            System.err.println("Could not get QR code");
			return null;
		}
		return code;
	}
    
	private boolean calculateDistance(float position1[], float position2[]) {
		if (position1.length != 2 || position2.length != 2) {
			System.out.println("Input must be a two element array");
		}
		if (Math.sqrt(Math.pow(position1[0] - position2[0], 2) + Math.pow(position1[1] - position2[1], 2)) < kThreshold) {
			return true;
		}
		return false;
	}

    /*public void getMapNew(JLabel mapLabel) {
        MapFetcher fetcherThread = new MapFetcher(mapLabel);
        fetcherThread.running = true;
        new Thread(fetcherThread).start(); 
    }*/
    
	public BufferedImage getMap() {
		BufferedImage map = null;
		try {			
			DomNodeList<DomElement> list;
			//21st javascript to get departure, arrival and flight coordinates
			list = currentPage.getElementsByTagName("script");
            HtmlScript script = null;
            boolean found = false;
            for (int i = 0; i < list.size(); ++i) {
    			script = (HtmlScript)list.get(i);
                if (script.asXml().contains("var depAirport = new AirportForMap();")) {
                    found = true;
                    break;       
                }
            }
			//browser.setJavaScriptEnabled(true);
			//ScriptResult result = currentPage.executeJavaScript("return depAirport.airportCode;");			
			//System.out.println(result.getJavaScriptResult());
	
			String xmlContent = script.asXml();
			if (found) {
				System.out.println("This flight is currently active");			
				String[] lines = xmlContent.split(System.getProperty("line.separator"));
	
				String depAirportLat = "", depAirportLong = "";
				String arrAirportLat = "", arrAirportLong = "";
				String flightLat = "", flightLong = "";			
				//Departure airport data
				//for (int i = 4; i < 11; i++) {
				//	System.out.println(lines[i]);
				//}
		
				Pattern p = Pattern.compile("-?\\d+\\.?\\d*");
				Matcher m = p.matcher(lines[5]);
				while (m.find()) {
					depAirportLat = m.group();
				}
				m = p.matcher(lines[6]);
				while (m.find()) {
					depAirportLong = m.group();
				}
		
				//Arrival airport data
				//for (int i = 13; i< 20; i++) {
				//	System.out.println(lines[i]);
				//}
				
				m = p.matcher(lines[14]);
				while (m.find()) {
					arrAirportLat = m.group();
				}
				m = p.matcher(lines[15]);
				while (m.find()) {
					arrAirportLong = m.group();
				}
				
                //Flight data
				//Check if the flight co-ordinates are equal to the departure/arrival airport co-ordinates
				int flightLatIndex = 41, flightLongIndex = 42;
				for (int i = 23; i< 44; i++) {
					if (lines[i].contains("flight.lat")) {
						flightLatIndex = i;
					}
					if (lines[i].contains("flight.lng")) {
						flightLongIndex = i;
					}
					//System.out.println(i + " : " + lines[i]);
				}
                //System.out.println(flightLatIndex + " : " + flightLongIndex);
				m = p.matcher(lines[flightLatIndex]);
				while (m.find()) {
					flightLat = m.group();
				}
				m = p.matcher(lines[flightLongIndex]);
				while (m.find()) {
					flightLong = m.group();
				}
			
				//!TODO : add code later on to check the distance from the departure and arrival airports. If distance < kThreshold, the flight has either not departed, or has arrived.
				
				//Flight is at the departure or arrival airport
				if (lines[flightLatIndex].contains("flight.lat = depAirport.lat;")) {
					System.out.println("Flight is at the departure airport");
					flightLat = depAirportLat;
					flightLong = depAirportLong;
				} else if (lines[flightLatIndex].contains("flight.lat = arrAirport.lat;")) {
					flightLat = arrAirportLat;
					flightLong = arrAirportLong;
					System.out.println("Flight has arrived");
				}
				
				System.out.println(depAirportLat + " " + depAirportLong + " " +
						   arrAirportLat + " " + arrAirportLong + " " +
						   flightLat + " " + flightLong);

				//Sample URL for google static maps.
                //http://maps.googleapis.com/maps/api/staticmap?center=33.73,-119.58&size=600x300&maptype=roadmap%20&markers=color:blue%7Clabel:S%7C32.89,-97.04&markers=color:green%7Clabel:F%7Csize:small%7C33.73,-119.58&markers=color:red%7Ccolor:red%7Clabel:D%7C21.325,%20-157.921&sensor=false				     
				String centerLat = "" + (Float.parseFloat(depAirportLat) + Float.parseFloat(arrAirportLat))/2;
				String centerLong = "" + (Float.parseFloat(depAirportLong) + Float.parseFloat(arrAirportLong))/2;
				
				String mapUrl = "http://maps.googleapis.com/maps/api/staticmap?center=" 
                                + centerLat + "," + centerLong 
                                + "&size=600x225&maptype=roadmap%20&markers=color:blue|label:S|" 
                                + depAirportLat + "," + depAirportLong + "&markers=color:green|label:F|size:small|" 
                                + flightLat + "," + flightLong + "&markers=color:red|color:red|label:D|" 
                                + arrAirportLat + "," + arrAirportLong + "&sensor=false";
                
				UnexpectedPage page = browser.getPage(mapUrl);
				InputStream inputStream = page.getInputStream();
				map = (BufferedImage)ImageIO.read(inputStream);
			} else if (currentPage.asXml().contains("This flight has multiple segments")){
                System.out.println("This flight has multiple segments");
                
            } else {
				System.out.println("This flight is currently inactive");
				String arrAirportLat = "", arrAirportLong = "";
				String[] lines = xmlContent.split(System.getProperty("line.separator"));

                /*for (int i = 0; i < lines.length; i++) {
					System.out.println(i + ": " + lines[i]);
				}*/

				//The 5th and 6th lines contain the destination airport latitude and 
				//longitude
				Pattern p = Pattern.compile("-?\\d+\\.?\\d*");
				Matcher m = p.matcher(lines[4]);
				while (m.find()) {
					arrAirportLat = m.group();					
				}

				m = p.matcher(lines[5]);
				while (m.find()) {
					arrAirportLong = m.group();
				}

				String mapUrl = "http://maps.googleapis.com/maps/api/staticmap?center=" 
                                + arrAirportLat + "," + arrAirportLong 
                                + "&zoom=12&size=600x225&maptype=roadmap%20&markers=color:red|color:red|label:D|" 
                                + arrAirportLat + "," + arrAirportLong + "&sensor=false";

				UnexpectedPage page = browser.getPage(mapUrl);
				InputStream inputStream = page.getInputStream();
				map = (BufferedImage)ImageIO.read(inputStream);

			}
		} catch (Exception e) {
            System.out.println("In catch block");
            DomNodeList<DomElement> list = currentPage.getElementsByTagName("script");
            HtmlScript script = null;
            boolean found = false;
            for (int i = 0; i < list.size(); ++i) {
    	       script = (HtmlScript)list.get(i);
               if (script.asXml().contains("targetedAirport")) {
                  found = true;
                  break;       
               }
            } 
            script = (HtmlScript)list.get(8);
            if (found && currentPage.asXml().contains("Landed")) {
                System.out.println(script.asXml());
			    String lines[] = script.asXml().split(System.getProperty("line.separator"));
                String targetAirport = null, targetCity = null;
                Pattern p = Pattern.compile("\"[\\w\\s/]+\"");
                //Target airport
                Matcher m = p.matcher(lines[3]);
                while (m.find()) {
                    targetAirport = m.group();
                }
                targetAirport = targetAirport.substring(1, targetAirport.length()); 
                //The target city
                m = p.matcher(lines[5]);
                while (m.find()) {
                    targetCity = m.group();
                }
                targetCity = targetCity.substring(1, targetCity.length());
                String mapUrl = "http://maps.googleapis.com/maps/api/staticmap?center=" 
                                + targetAirport + "," + targetCity + "&zoom=12&size=600x225&maptype=roadmap&sensor=true"; 
                try {
                    UnexpectedPage page = browser.getPage(mapUrl);
	    			InputStream inputStream = page.getInputStream();
		    		map = (BufferedImage)ImageIO.read(inputStream);
                } catch (Exception ex) {
                    System.err.println("Could not get static Maps");
                    ex.printStackTrace();
                }
            } else if (found && currentPage.asXml().contains("Scheduled")) {
               String lines[] = script.asXml().split(System.getProperty("line.separator"));
                String sourceAirport = null, sourceCity = null;
                Pattern p = Pattern.compile("\"[\\w\\s]+\"");
                //Target airport
                Matcher m = p.matcher(lines[6]);
                while (m.find()) {
                    sourceAirport = m.group();
                }
                sourceAirport = sourceAirport.substring(1, sourceAirport.length()); 
                //The target city
                m = p.matcher(lines[8]);
                while (m.find()) {
                    sourceCity = m.group();
                }
                sourceCity = sourceCity.substring(1, sourceCity.length());
                String mapUrl = "http://maps.googleapis.com/maps/api/staticmap?center=" 
                                + sourceAirport + "," + sourceCity + "&zoom=12&size=600x225&maptype=roadmap&sensor=true"; 
                try {
                    UnexpectedPage page = browser.getPage(mapUrl);
	    			InputStream inputStream = page.getInputStream();
		    		map = (BufferedImage)ImageIO.read(inputStream);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
		}
		return map;
	}
	
	public void test() {
		try {
			DomNodeList<DomElement> list = currentPage.getElementsByTagName("table");
			
			//7th table on the flight status results page is the <Airline Code + flight number> flight status table
			HtmlTable table = (HtmlTable)list.get(6);			
			for (HtmlTableRow row : table.getRows()) {
				for (HtmlTableCell cell : row.getCells()) {
					System.out.println(" Cell : " + cell.asText());
				}
			}
			
			//12th table on the flight status results page is the <Airline Code + flight number> information table
			HtmlTable table2 = (HtmlTable)list.get(11);
			for (HtmlTableRow row : table2.getRows()) {
				for (HtmlTableCell cell : row.getCells()) {
					System.out.println(" Cell : " + cell.asText());
				}
			}										
			
			//6th image tag refers to the SPARQ barcode image
			list = currentPage.getElementsByTagName("img");
			HtmlImage image = (HtmlImage)list.get(6);						
			
			
			//21st javascript to get departure, arrival and flight coordinates
			list = currentPage.getElementsByTagName("script");
			HtmlScript script = (HtmlScript)list.get(21);			
			//browser.setJavaScriptEnabled(true);
			//ScriptResult result = currentPage.executeJavaScript("return depAirport.airportCode;");			
			//System.out.println(result.getJavaScriptResult());
			
			String xmlContent = script.asXml();
			if (xmlContent.contains("depAirport")) {
				System.out.println("This flight is currently active");			
			//System.out.println(xmlContent);
			String[] lines = xmlContent.split(System.getProperty("line.separator"));
			
			//Departure airport data
			for (int i = 4; i < 11; i++) {
				System.out.println(lines[i]);
			}
			
			//Arrival airport data
			for (int i = 13; i< 20; i++) {
				System.out.println(lines[i]);
			}
			
			//Flight data
			//Check if the flight co-ordinates are equal to the departure/arrival airport co-ordinates
			for (int i = 23; i< 27; i++) {
				System.out.println(lines[i]);
			}
			
			
			//http://maps.googleapis.com/maps/api/staticmap?center=33.73,-119.58&size=600x300&maptype=roadmap%20&markers=color:blue%7Clabel:S%7C32.89,-97.04&markers=color:green%7Clabel:F%7Csize:small%7C33.73,-119.58&markers=color:red%7Ccolor:red%7Clabel:D%7C21.325,%20-157.921&sensor=false			
			String mapUrl = "http://maps.googleapis.com/maps/api/staticmap?center=33.73,-119.58&size=600x225&maptype=roadmap%20&markers=color:blue|label:S|32.89,-97.04&markers=color:green|label:F|size:small|33.73,-119.58&markers=color:red|color:red|label:D|21.325, -157.921&sensor=false";
			UnexpectedPage page = browser.getPage(mapUrl);
			InputStream inputStream = page.getInputStream();
			BufferedImage map = (BufferedImage)ImageIO.read(inputStream);

			} else {
				System.out.println("This flight is currently inactive");
			}
		}
		catch(Exception e) {
			e.printStackTrace();		
		}
	}
}
