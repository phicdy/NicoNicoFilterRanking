package com.phicdy.niconicofilterranking.video;

import com.phicdy.niconicofilterranking.rss.NicoChartFeed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class NicoVideoTest {

    @Test
    public void testEquals() {
        NicoVideo video1 = NicoChartFeed.FIRST_VIDEO;
        NicoVideo video2 = generateCopy(video1);
        assertTrue(video1.equals(video2));

        video2.setTitle("aaaaaaaaaaaaa");
        assertFalse(video1.equals(video2));
        video2 = generateCopy(video1);
        video2.setPlayCount("aaaaaaaaaaaaa");
        assertFalse(video1.equals(video2));
        video2 = generateCopy(video1);
        video2.setCommentCount("aaaaaaaaaaaaa");
        assertFalse(video1.equals(video2));
        video2 = generateCopy(video1);
        video2.setPlayCount("aaaaaaaaaaaaa");
        assertFalse(video1.equals(video2));
        video2 = generateCopy(video1);
        video2.setMyListCount("aaaaaaaaaaaaa");
        assertFalse(video1.equals(video2));
        video2 = generateCopy(video1);
        video2.setPublishedDate("aaaaaaaaaaaaa");
        assertFalse(video1.equals(video2));
        video2 = generateCopy(video1);
        video2.setThumbnailPath("aaaaaaaaaaaaa");
        assertFalse(video1.equals(video2));
        video2 = generateCopy(video1);
        video2.setUrl("aaaaaaaaaaaaa");
        assertFalse(video1.equals(video2));
        video2 = generateCopy(video1);
        video2.setHourTotalPoint(100000000);
        assertFalse(video1.equals(video2));
    }

    private NicoVideo generateCopy(NicoVideo video) {
        return new NicoVideo(video.getTitle(), video.getUrl(),
                video.getPlayCount(), video.getCommentCount(),
                video.getMyListCount(), video.getPublishedDate(),
                video.getThumbnailPath(), video.getHourTotalPoint());
    }
}
