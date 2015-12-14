package com.phicdy.niconicofilterranking.rss;

import android.content.Context;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RssParser {

	private static final String LOG_TAG = "RssParser";

	public RssParser(Context context) {
	}

	public void parseRssXml(final String baseUrl) {
		new Thread() {
			@Override
			public void run() {
				try {
					final URL url = new URL(baseUrl);
					if (!"http".equalsIgnoreCase(url.getProtocol())
							&& !"https".equalsIgnoreCase(url.getProtocol())) {
						return;
					}
					Document document = Jsoup.connect(baseUrl).get();
					if (!document.getElementsByTag("rdf").isEmpty() || !document.getElementsByTag("rdf:rdf").isEmpty()) {
						// RSS 1.0
						Elements links = document.getElementsByTag("link");
						String siteUrl = null;
						for (Element element : links) {
							if (element.parent().tag().toString().equals("channel")) {
								siteUrl = element.text();
								break;
							}
						}
						if (siteUrl == null || siteUrl.equals("")) {
							siteUrl = url.getProtocol() + "://" + url.getHost();
						}
						String title = document.title();
					}else if (!document.getElementsByTag("rss").isEmpty()) {
						// RSS 2.0
						Elements links = document.getElementsByTag("link");
						String siteUrl = null;
						for (Element element : links) {
							if (element.parent().tag().toString().equals("channel")) {
								siteUrl = element.text();
								break;
							}
						}
						if (siteUrl == null || siteUrl.equals("")) {
							siteUrl = url.getProtocol() + "://" + url.getHost();
						}
						String title = document.title();
					}else if (!document.getElementsByTag("feed").isEmpty()) {
						// ATOM:
						//<?xml version="1.0" encoding="utf-8"?>
						//<feed xmlns="http://www.w3.org/2005/Atom">
						//   <title>Example Feed</title>
						//   <link href="http://example.org/"/>
						//    <updated>2003-12-13T18:30:02Z</updated>
						//    <author>
						//        <name>John Doe</name>
						//    </author>
						//    <id>urn:uuid:60a76c80-d399-11d9-b93C-0003939e0af6</id>
						//    <entry>
						//        <title>Atom-Powered Robots Run Amok</title>
						//        <link href="http://example.org/2003/12/13/atom03"/>
						//        <id>urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a</id>
						//        <updated>2003-12-13T18:30:02Z</updated>
						//        <summary>Some text.</summary>
						//    </entry>
						//</feed>
						Elements links = document.getElementsByTag("link");
						String siteUrl;
						if (links.isEmpty()) {
							siteUrl = url.getProtocol() + "://" + url.getHost();
						}else {
							siteUrl = links.get(0).attr("href");
						}
						String title = document.title();
					}else if (!document.getElementsByTag("html").isEmpty()) {
						//<link rel="alternate" type="application/rss+xml" title="TechCrunch Japan &raquo; フィード" href="http://jp.techcrunch.com/feed/" />
						Elements elements = document.getElementsByAttributeValue("type", "application/rss+xml");
						if (elements.isEmpty()) {
							return;
						}
						String feedUrl = elements.get(0).attr("href");
						parseRssXml(feedUrl);
						return;
					} else {
					}
				} catch (MalformedURLException e) {
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}