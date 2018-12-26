package com.shab.artificon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Demo of a simple web crawler, that:
 * <ul>
 * <li>fetches the content for a given start URL;</li>
 * <li>extracts the links from the content;</li>
 * <li>goes on to crawl the extracted links (back to step 1);</li>
 * <li>stops after 1000 found URLs.</li>
 * </ul>
 * 
 * @author Ivan Palianytsia <a href=
 *         "mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public class Crawler {

	/**
	 * Crawls the web starting from the given initial URL.
	 * 
	 * @param start
	 *            The URL to start from.
	 * 
	 * @param limit
	 *            The maximum number of URLs to discover. Crawler stops when this
	 *            number is reached.
	 * 
	 * @return List of the URLs in the order they were discovered while crawling.
	 */
	public static List<URL> crawl(URL start, int limit) {
		List<URL> urls = new ArrayList<URL>(limit);
		urls.add(start);

		// We maintain this set to be exact copy of the URLs list in order to use it for
		// faster checks on whether or not we have such an URL already in the
		// list. Thus we are sacrificing memory in the name of performance.
		Set<URL> urlsCopy = new HashSet<URL>(urls);

		int i = 0;
		while (urls.size() < limit && i < urls.size()) {
			URL currentUrl = urls.get(i);
			for (URL url : extractLinks(currentUrl)) {
				if (urlsCopy.add(url)) {
					urls.add(url);
					if (urls.size() == limit) {
						break;
					}
				}
			}
			i++;
		}
		return urls;
	}

	/**
	 * Demonstrates the work of the crawler.
	 * 
	 * @param args
	 *            TODO: pass start and limit arguments from the command line.
	 */
	public static void main(String[] args) {
		try {
			URL initial = new URL("https://www.kloudone.com/careers.html");
			int limit = 1000;

			long start = System.currentTimeMillis();
			List<URL> discovered = Crawler.crawl(initial, limit);
			long finish = System.currentTimeMillis();
			System.out.println("Crawler discovered " + discovered.size() + " urls in " + (finish - start) + " ms.");

			System.out.println("Showing first 10 results: ");
			int i = 1;
			Iterator<URL> iterator = discovered.iterator();
			while (iterator.hasNext() && i <= 10) {
				System.out.println(i + ") " + iterator.next());
				i++;
			}
		} catch (MalformedURLException e) {
			System.err.println("The URL to start crawling with is invalid.");
		}
	}

	/**
	 * Extracts all the links contained by the web page at the given URL.
	 * 
	 * @param url
	 *            URL of the web page to extract the links from.
	 * 
	 * @return Set of links in the order they occur on the web page. The set might
	 *         be empty if a page a the given URL contains no links or an error
	 *         occurred while fetching its content.
	 */
	private static LinkedHashSet<URL> extractLinks(URL url) {
		LinkedHashSet<URL> links = new LinkedHashSet<URL>();
		Pattern p = Pattern.compile("href=\"((http://|https://|www).*?)\"", Pattern.DOTALL);
		Matcher m = p.matcher(fetchContent(url));

		while (m.find()) {
			String linkStr = normalizeUrlStr(m.group(1));
			try {
				URL link = new URL(linkStr);
				links.add(link);
			} catch (MalformedURLException e) {
				System.err.println("Page at " + url + " has a link to invalid URL : " + linkStr + ".");
			}
		}
		return links;
	}

	/**
	 * Fetches content for a given URL.
	 * 
	 * <p>
	 * If URL can't be accessed or properly fetched returns all content that was
	 * successfully fetched before an error has occurred, which means an empty
	 * string will be returned if the error occurred just at the beginning of the
	 * fetch process.
	 * </p>
	 * 
	 * @param url
	 *            URL to fetch content for.
	 * 
	 * @return Fetched content.
	 */
	private static String fetchContent(URL url) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				stringBuilder.append(inputLine);
			}
			in.close();
		} catch (IOException e) {
			System.err.println("An error occured while atempt to fetch content from " + url);
		}
		System.out.println("Content:" + stringBuilder.toString());
		return stringBuilder.toString();
	}

	/**
	 * Normalizes string representation of the URL, so it becomes easier to
	 * transform it to URL object or reject duplicates.
	 * 
	 * @param urlStr
	 *            String representation of the URL to normalize.
	 * 
	 * @return Normalized string.
	 */
	private static String normalizeUrlStr(String urlStr) {
		if (!urlStr.startsWith("http")) {
			urlStr = "http://" + urlStr;
		}
		if (urlStr.endsWith("/")) {
			urlStr = urlStr.substring(0, urlStr.length() - 1);
		}
		if (urlStr.contains("#")) {
			urlStr = urlStr.substring(0, urlStr.indexOf("#"));
		}
		return urlStr;
	}

}