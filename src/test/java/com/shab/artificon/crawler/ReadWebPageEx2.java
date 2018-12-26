package com.shab.artificon.crawler;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ReadWebPageEx2 {

	public static void main(String[] args) throws IOException {

		String webPage = "https://delldriverscenter.blogspot.com/2018/06/dell-1320c-driver-windows-10-windows-7.html";

		String html = Jsoup.connect(webPage).get().html();

		//System.out.println(html);
		
		System.out.println("========================");
		String text = Jsoup.connect(webPage).get().text();

		System.out.println(text);
		
	}
}