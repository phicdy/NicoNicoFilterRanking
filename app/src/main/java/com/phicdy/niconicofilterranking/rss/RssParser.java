package com.phicdy.niconicofilterranking.rss;

import com.phicdy.niconicofilterranking.video.NicoVideo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class RssParser {

	private static final String LOG_TAG = "RssParser";

	public RssParser() {
	}

	public ArrayList<NicoVideo> parseNicoChartFeed(Document document) {
        ArrayList<NicoVideo> videos = new ArrayList<>();
        if (document == null) {
            return videos;
        }
        Elements entries = document.getElementsByTag("entry");
        for (Element entry : entries) {
            String title = entry.getElementsByTag("title").get(0).text();
            String link = entry.getElementsByTag("link").get(0).attr("href");
            String publishedDateStr = entry.getElementsByTag("published").get(0).text();
            String content = entry.getElementsByTag("content").get(0).text();
            String playCount = getPlayCount(content);
            String commentCount = getCommentCount(content);
            String myListCount = getMyListCount(content);
            String thumbnailPath = getThumbnailPath(content);
            NicoVideo video = new NicoVideo(title, link, playCount, commentCount,
                    myListCount, publishedDateStr, thumbnailPath);
            videos.add(video);
        }
        return videos;
    }

    private String getPlayCount(String content) {
        if (content == null || content.equals("") || !content.startsWith("<p class")) {
            return "0";
        }
        Document d = Jsoup.parse(content);
        if (d == null) {
            return "0";
        }
        Elements elements = d.getElementsByClass("nico-info-total-view");
        if (elements != null && elements.size() == 1) {
            return elements.get(0).text();
        }
        return "0";
    }

    private String getCommentCount(String content) {
        if (content == null || content.equals("") || !content.startsWith("<p class")) {
            return "0";
        }
        Document d = Jsoup.parse(content);
        if (d == null) {
            return "0";
        }
        Elements elements = d.getElementsByClass("nico-info-total-res");
        if (elements != null && elements.size() == 1) {
            return elements.get(0).text();
        }
        return "0";
    }

    private String getMyListCount(String content) {
        if (content == null || content.equals("") || !content.startsWith("<p class")) {
            return "0";
        }
        Document d = Jsoup.parse(content);
        if (d == null) {
            return "0";
        }
        Elements elements = d.getElementsByClass("nico-info-total-mylist");
        if (elements != null && elements.size() == 1) {
            return elements.get(0).text();
        }
        return "0";
    }

    private String getThumbnailPath(String content) {
        if (content == null || content.equals("") || !content.startsWith("<p class")) {
            return "";
        }
        Document d = Jsoup.parse(content);
        if (d == null) {
            return "";
        }
        Elements elements = d.getElementsByTag("img");
        if (elements != null && elements.size() == 1) {
            return elements.get(0).attr("src");
        }
        return "";
    }
}