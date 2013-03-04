/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc510.project.commlayer;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.util.logging.Level;

/**
 *
 * @author svpendse1
 */
public class CommTester {
    
    HtmlPage currentPage;
    static WebClient browser;
    static {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        browser = new WebClient(BrowserVersion.FIREFOX_3_6);
        browser.getOptions().setJavaScriptEnabled(true);
    }

    public CommTester() {
        try {
            currentPage = (HtmlPage) browser.getPage("https://www.flightstats.com/go/Login/login.do");

            //WebRequestSettings requestSettings = new WebRequestSettings(new URL(""), HttpMethod.POST);
            HtmlButton button = (HtmlButton) currentPage.createElement("button"); 
            button.setAttribute("type", "submit");
            DomNodeList<DomElement> list = currentPage.getElementsByTagName("form");
            HtmlForm form = (HtmlForm) list.get(0);
            form.getInputByName("username").setValueAttribute("timelinesstracker");
            form.getInputByName("arbitraryName").setValueAttribute("csc510ncsu");
            form.appendChild(button);

            currentPage = button.click();

            System.out.println(currentPage.asXml());
            /*((HtmlTextInput) currentPage.getElementByName("username")).setValueAttribute("timelinesstracker");
            ((HtmlPasswordInput) currentPage.getElementById("loginPassword")).setValueAttribute("csc510ncsu");
            DomNodeList<DomElement> list = currentPage.getElementsByTagName("table");

            for (int i = 0; i < list.size(); ++i) {
                System.out.println("Table : " + i);
                System.out.println(list.get(i).asXml()); 
            }
            HtmlTable table = (HtmlTable)list.get(1);
            System.out.println("Before click");
            currentPage = table.click();
            System.out.println(currentPage.asXml());*/
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(currentPage.asXml());
    }
   
    public static void main(String[] args) {
        CommTester test = new CommTester();
    }
}
