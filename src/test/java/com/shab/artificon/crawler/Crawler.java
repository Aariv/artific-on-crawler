package com.shab.artificon.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

	/**
	 * @param args
	 *            the command line arguments
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {

		Set<String> visitedUrls = new HashSet<>();
		String url = "https://www.kloudone.com/careers.html";
		crawl(url, visitedUrls);
	}

	private static void crawl(String url, Set<String> visited) throws IOException {
		if (url.isEmpty() || visited.contains(url)) {
			return;
		}
		print("Fetching %s...", url);
		visited.add(url);
		Document doc;
		try {
			doc = Jsoup.connect(url).timeout(10000).get();
		} catch (UnsupportedMimeTypeException e) {
			System.out.println("Unsupported Mime type. Aborting crawling for URL: " + url);
			return;
		} catch (MalformedURLException e) {
			System.out.println("Unsupported protocol for URL: " + url);
			return;
		} catch (HttpStatusException e) {
			System.out.println("Error (status=" + e.getStatusCode() + ") fetching URL: " + url);
			return;
		} catch (IOException e) {
			System.out.println("Timeout fetching URL: " + url);
			return;
		}

		Elements media = doc.select("[src]");
		Elements imports = doc.select("link[href]");
		Elements links1 = doc.select("a[href]");

		print("\nMedia: (%d)", media.size());
		for (Element src : media) {
			if (src.tagName().equals("img"))
				print(" * %s: <%s> %sx%s (%s)", src.tagName(), src.attr("abs:src"), src.attr("width"),
						src.attr("height"), trim(src.attr("alt"), 20));
			else
				print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
		}

		print("\nImports: (%d)", imports.size());
		for (Element link : imports) {
			print(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
		}
		print("\nLinks: (%d)", links1.size());
		for (Element link : links1) {
			print(" * a: <%s> (%s)", link.attr("abs:href"), trim(link.text(), 35));
		}

		for (Element link : links1) {
			String href = link.attr("abs:href");
			URL hrefURL = null;
			try {
				hrefURL = new URL(href);
			} catch (MalformedURLException e) {
				// nothing
			}
			if (hrefURL != null && hrefURL.getHost().equals(new URL(url).getHost())) {
				crawl(href, visited);
			}
		}
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	private static String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width - 1) + ".";
		else
			return s;
	}
}