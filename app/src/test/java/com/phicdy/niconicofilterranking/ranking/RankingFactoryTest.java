package com.phicdy.niconicofilterranking.ranking;

import com.phicdy.niconicofilterranking.rss.NicoChartFeed;
import com.phicdy.niconicofilterranking.video.NicoVideo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RankingFactoryTest {

    @Test
    public void testGenerateRanking() throws Exception {
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(Jsoup.parse(NicoChartFeed.FEED));
        documents.add(Jsoup.parse(NicoChartFeed.FEED2));
        documents.add(Jsoup.parse(NicoChartFeed.FEED3));
        ArrayList<NicoVideo> videos = RankingFactory.generateRanking(documents);
        // Check sort by hour total point
        for (int i = 0; i < videos.size(); i++) {
            if (i == videos.size()-1) break;
            assertTrue(videos.get(i).getHourTotalPoint() >=
                    videos.get(i+1).getHourTotalPoint());
        }
        // Check there are not duplicated URL
        for (int i = 0; i < videos.size(); i++) {
            String url = videos.get(i).getUrl();
            for (int l = i + 1; l < videos.size(); l++) {
                assertNotEquals(url, videos.get(l).getUrl());
            }
        }
        assertThat(videos.get(0).getTitle(), is(NicoChartFeed.FIRST_VIDEO.getTitle()));
        assertThat(videos.get(1).getTitle(), is(NicoChartFeed.SECOND_VIDEO.getTitle()));
        assertThat(videos.get(2).getTitle(), is(NicoChartFeed.THIRD_VIDEO.getTitle()));
        assertThat(videos.get(3).getTitle(), is(NicoChartFeed.FOURTH_VIDEO.getTitle()));
        assertThat(videos.get(4).getTitle(), is(NicoChartFeed.FIFTH_VIDEO.getTitle()));
    }
}