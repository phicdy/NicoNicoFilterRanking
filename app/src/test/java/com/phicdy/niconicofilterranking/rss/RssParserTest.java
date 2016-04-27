package com.phicdy.niconicofilterranking.rss;

import com.phicdy.niconicofilterranking.video.NicoVideo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class RssParserTest{

    @Test
    public void parse() throws Exception {
        Document document = Jsoup.parse(NicoChartFeed.FEED);
        RssParser parser = new RssParser();
        ArrayList<NicoVideo> videos = parser.parseNicoChartFeed(document);
        assertThat(videos.size(), is(NicoChartFeed.ENTRY_SIZE));

        NicoVideo video = videos.get(0);
        assertThat(video.getTitle(), is(NicoChartFeed.FIRST_VIDEO.getTitle()));
        assertThat(video.getUrl(), is(NicoChartFeed.FIRST_VIDEO.getUrl()));
        assertThat(video.getPlayCount(), is(NicoChartFeed.FIRST_VIDEO.getPlayCount()));
        assertThat(video.getCommentCount(), is(NicoChartFeed.FIRST_VIDEO.getCommentCount()));
        assertThat(video.getMyListCount(), is(NicoChartFeed.FIRST_VIDEO.getMyListCount()));
        assertThat(video.getThumbnailPath(), is(NicoChartFeed.FIRST_VIDEO.getThumbnailPath()));
        assertThat(video.getHourTotalPoint(), is(NicoChartFeed.FIRST_VIDEO.getHourTotalPoint()));
    }
}