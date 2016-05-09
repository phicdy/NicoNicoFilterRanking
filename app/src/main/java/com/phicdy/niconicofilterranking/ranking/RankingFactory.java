package com.phicdy.niconicofilterranking.ranking;

import com.phicdy.niconicofilterranking.rss.RssParser;
import com.phicdy.niconicofilterranking.video.NicoVideo;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RankingFactory {
    public static ArrayList<NicoVideo> generateRanking(Collection<Document> documents) {
        RssParser parser = new RssParser();
        ArrayList<NicoVideo> ranking = new ArrayList<>();
        for (Document document : documents) {
            ArrayList<NicoVideo> videos = parser.parseNicoChartFeed(document);
            ranking.addAll(videos);
        }
        Collections.sort(ranking, new NicoVideoComparator());
        return ranking;
    }
}
