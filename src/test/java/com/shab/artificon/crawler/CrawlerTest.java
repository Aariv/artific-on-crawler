/**
 * 
 */
package com.shab.artificon.crawler;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author zentere
 *
 */
public class CrawlerTest {

	public static void main(String[] args) {
		String path = "https://www.ibm.com/products";
		int timeoutMillis = 10 * 1000;
		try {
		    URL url = new URL(path);
		    Document doc = Jsoup.parse(url, timeoutMillis);
		    Elements elements = doc.select("body").first().children();
		    for (Element el : elements)
		        System.out.println("element: "+el.text());
//		    Elements selections = doc.select("a");
//		    String format = "%-40s %s%n";
//		    for (Element element : selections) {
//		        System.out.printf(format, element.attr("href"), element.text());
//		    }

		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
}
