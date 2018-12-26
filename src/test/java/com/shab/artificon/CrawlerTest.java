package com.shab.artificon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerTest {

    public static final int sleepInterval = 100; // interval between getting pages for webserver friendliness :)

    List<String> allowedUrls;
    List<String> seedUrls;
    List<String> foundAndVisitedUrls;
    List<String> foundLinks;
    List<String> deadLinks;

    public CrawlerTest() {
        allowedUrls = new ArrayList<String>();
        seedUrls = new ArrayList<String>();
        foundAndVisitedUrls = new ArrayList<String>();
        deadLinks = new ArrayList<String>();
        foundLinks = new ArrayList<String>();

        allowedUrls.add("http://en.wikipedia.org/");
        seedUrls.add("http://en.wikipedia.org/");

        foundLinks.addAll(seedUrls);
    }

    public void run() {
        for(int i = 0; i < foundLinks.size(); i++) {

            String pageToCrawl = foundLinks.get(i);
            log("Crawling " + pageToCrawl);
            crawl(pageToCrawl);
        }

        log("\n\nreporting found urls:");
        for(String s : foundAndVisitedUrls) {
            log(s);
        }
    }

    private Document getDoc(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            return doc;
        } catch (IOException e) {
            System.out.println("Adding URL: " + url + " to list of dead links");
            deadLinks.add(url);
        }

        return null;
    }

    private String[] getLinksFromDocument(Document doc) {
        Elements elements = doc.select("a");
        String[] links = new String[elements.size()];

        Iterator i = elements.iterator();
        int b = 0;

        while(i.hasNext()) {
            links[b] = ((Element) i.next()).attr("abs:href");
            b++;
        }

        return links;
    }

    private void crawl(String url) {
        if(!urlAllowed(url)) return;

        Document page = getDoc(url);

        String[] links = getLinksFromDocument(page);

        for(String l : links) {
            if(urlAllowed(l)) {
                if(!foundLinks.contains(l)) {
                    foundLinks.add(l);
                }
            }
        }
        foundLinks.remove(url);
        foundAndVisitedUrls.add(url);
    }

    private boolean urlAllowed(String url) {

        boolean allowed = false;

        //stay within certain domains
        for(String u : allowedUrls) {
             if(url.startsWith(u)) allowed = true;
        }

        //make sure we havent already found that the links is dead
        for(String u : deadLinks) {
            if(url.equals(u)) allowed = false;
        }

        //make sure we havent already visited it
        for(String u : foundAndVisitedUrls) {
            if(url.equals(u)) allowed = false;
        }

        return allowed;

    }

    private void log(String item) {
        System.out.println(item);
    }

    public static void main(String args[]) {
        CrawlerTest damn = new CrawlerTest();
        damn.run();
    }
}