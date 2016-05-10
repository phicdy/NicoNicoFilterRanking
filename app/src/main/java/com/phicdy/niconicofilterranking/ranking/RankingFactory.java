package com.phicdy.niconicofilterranking.ranking;

import com.phicdy.niconicofilterranking.rss.RssParser;
import com.phicdy.niconicofilterranking.video.NicoVideo;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class RankingFactory {
    public static ArrayList<NicoVideo> generateRanking(Collection<Document> documents) {
        RssParser parser = new RssParser();
        ArrayList<NicoVideo> ranking = new ArrayList<>();
        for (Document document : documents) {
            ArrayList<NicoVideo> videos = parser.parseNicoChartFeed(document);
            ranking.addAll(videos);
        }
        Collections.sort(ranking, new NicoVideoComparator());
        Iterator<NicoVideo> iterator = ranking.iterator();
        String previousUrl = "";
        int index = 0;
        while (iterator.hasNext()) {
            NicoVideo video = iterator.next();
            if (video.getUrl().equals(previousUrl)) {
                ranking.remove(index);
            }
            previousUrl = video.getUrl();
            index = index + 1;
        }
        return ranking;
    }
}
