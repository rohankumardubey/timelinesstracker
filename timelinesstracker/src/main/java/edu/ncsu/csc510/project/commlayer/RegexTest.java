/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc510.project.commlayer;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author svpendse1
 */
public class RegexTest {

    static void test() {
        HashMap<Integer, String> rfcCodeMap = new HashMap<Integer, String>();
        WebClient browser;
        browser = new WebClient(BrowserVersion.FIREFOX_3_6);
        browser.setJavaScriptEnabled(false);
        try {
            HtmlPage currentPage = (HtmlPage) browser.getPage("http://www.rfc-editor.org/rfcxx00.html");
        
            DomNodeList<DomElement> list = currentPage.getElementsByTagName("table");
            
            HtmlTable rfcTable = (HtmlTable)list.get(1);
         
            System.out.println (rfcTable.getRowCount());
            for (int i = 2; i < rfcTable.getRowCount(); ++i) {
                HtmlTableRow row = rfcTable.getRow(i);
                if (! row.getCell(2).asText().equals("xxx")) {
                    int number = Integer.parseInt(row.getCell(2).asText().replaceAll("\\*", ""));
                    String name = row.getCell(1).asText();
                    System.out.println(row.getCell(2).asText().replaceAll("\\*", "") + " " + row.getCell(1).asText());
                    rfcCodeMap.put(new Integer(number), name);
                }
            }
            FileOutputStream fos = new FileOutputStream(new File("/Users/svpendse1/Desktop/metadata.dat"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(rfcCodeMap);
            oos.flush();
            fos.close();
            oos.close();
        } catch (Exception e) {
           e.printStackTrace(); 
        }
               
    }
	public static void main(String[] args) {
        test();
		/*String test = "depAirport.lat = 97.2351432;";
		Pattern p = Pattern.compile("\\d+\\.?\\d*");
		Matcher m = p.matcher(test);
		while (m.find()) {
			System.out.println("Regex found : " + m.group());
		}*/
	}
	
}
