/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc510.project.utillayer;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
/**
 *
 * @author svpendse1
 */
public class ResourceFetcher {
	public LinkedList<String> getAirportCodesAndNames() throws Exception {
		LinkedList<String> outputList = new LinkedList<String>();
		InputStream in = getClass().getClassLoader().getResourceAsStream("airportcodes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbFactory.newDocumentBuilder();
		Document doc = builder.parse(in);

		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("airport");
		for (int i = 0; i < nList.getLength(); ++i) {
			Node nNode = nList.item(i);
			Element eElement = (Element)nNode;
            String airport = eElement.getTextContent().trim();
            int index = airport.indexOf(" ");
            String airportCode = airport.substring(0, index);
            String airportName = airport.substring(index + 1, airport.length());
			outputList.add(airportName.trim() + " " + airportCode);
		}
		return outputList;
	}
	public LinkedList<String> getAirlineCodesAndNames() throws Exception {
		LinkedList<String> outputList = new LinkedList<String>();
		InputStream in = getClass().getClassLoader().getResourceAsStream("airlinecodes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbFactory.newDocumentBuilder();
		Document doc = builder.parse(in);

		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("airline");
		for (int i = 0; i < nList.getLength(); ++i) {
            Node nNode = nList.item(i);
			Element eElement = (Element)nNode;
            String airline = eElement.getTextContent().trim();
            int index = airline.indexOf(" ");
            String airlineCode = airline.substring(0, index);
            String airlineName = airline.substring(index + 1, airline.length());
			outputList.add(airlineName.trim() + " " + airlineCode);
		}
		return outputList;
	}

	public void getAirlineCodesFromWeb(String outputFilePath) throws Exception {
		//ResourceFetcher parser = new ResourceFetcher();
		//parser.getAirportCodes();

		HtmlPage currentPage;
		WebClient browser;
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		browser = new WebClient(BrowserVersion.FIREFOX_3_6);
		browser.setJavaScriptEnabled(false);
		
		currentPage = (HtmlPage) browser.getPage("http://en.wikipedia.org/wiki/Airline_codes-All");
		DomNodeList<DomElement> list = currentPage.getElementsByTagName("table");			
		String[][] xmlTags = new String[3][3];
		xmlTags[0][0] = "<code>";
		xmlTags[0][1] = "</code>";
		xmlTags[1][0] = "<name>";
		xmlTags[1][1] = "</name>";
		xmlTags[2][0] = "<country>";
		xmlTags[2][1] = "</country>";
	
		//Change the path of the output file as appropriate
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(outputFilePath)), "UTF-8"));
		writer.println("<?xml version=\"1.0\"?>");
		writer.println("<airlines>");
		//The 3rd table is the flights data table
		HtmlTable table = (HtmlTable)list.get(2);			
		int count = 0;
		for (HtmlTableRow row : table.getRows()) {
			writer.println("<airline>");
			List<HtmlTableCell> rowCells = row.getCells();
			writer.print("<code>");
			writer.print(rowCells.get(1).asText());
			writer.println("</code>");
			
			writer.print("<name>");
			writer.print(rowCells.get(2).asText());
			writer.println("</name>");

			writer.print("<country>");
			writer.print(rowCells.get(4).asText());
			writer.println("</country>");
			writer.println("</airline>");
			count++;

			if (count % 50 == 0) {
				System.out.format("%.3f completed%n", 100.0 * count / table.getRowCount());
			}
		}	
		writer.println("</airlines>");
		writer.close();
	}

	public static void main(String[] args) throws Exception {
		ResourceFetcher fetcher = new ResourceFetcher();
		//fetcher.getAirlineCodesFromWeb("/Users/svpendse1/Desktop/airlinecodes.xml");
		fetcher.getAirlineCodesAndNames();
		fetcher.getAirportCodesAndNames();
	}
}