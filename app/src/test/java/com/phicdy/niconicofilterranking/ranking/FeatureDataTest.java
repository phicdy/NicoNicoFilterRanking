package com.phicdy.niconicofilterranking.ranking;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class FeatureDataTest {

    @Test
    public void isLoaded() {
        int[] categories = new int[] {0,1,2,3};
        FeatureData data = new FeatureData(categories);
        data.setDocument("http://hoge", new Document("hoge"));
        data.setDocument("http://hoge2", new Document("hoge2"));
        data.setDocument("http://hoge3", new Document("hoge3"));
        data.setDocument("http://hoge4", new Document("hoge4"));
        assertTrue(data.isLoaded());
    }
}
