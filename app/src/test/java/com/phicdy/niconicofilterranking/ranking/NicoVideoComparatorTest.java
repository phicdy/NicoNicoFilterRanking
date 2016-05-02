package com.phicdy.niconicofilterranking.ranking;

import com.phicdy.niconicofilterranking.video.NicoVideo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NicoVideoComparatorTest {

    @Test
    public void testCompare() {
        NicoVideo video1 = new NicoVideo("hoge", "http://hoge",
                "1000", "100", "10", "1970/1/1 12:00", "http://hoge",
                1000);
        NicoVideo video2 = new NicoVideo("hoge", "http://hoge",
                "1000", "100", "10", "1970/1/1 12:00", "http://hoge",
                1001);
        ArrayList<NicoVideo> videos = new ArrayList<>();
        videos.add(video1);
        videos.add(video2);
        Collections.sort(videos, new NicoVideoComparator());
        assertThat(videos.get(0).getHourTotalPoint(), is(1001));
        assertThat(videos.get(1).getHourTotalPoint(), is(1000));
    }
}