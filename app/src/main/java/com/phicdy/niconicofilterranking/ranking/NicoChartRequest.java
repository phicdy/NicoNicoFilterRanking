package com.phicdy.niconicofilterranking.ranking;

import android.support.annotation.WorkerThread;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;

public class NicoChartRequest implements Runnable{

    private String url;
    private FeatureData data;

    public NicoChartRequest(String url, FeatureData data) {
        this.url = url;
        this.data = data;
    }

    @WorkerThread
    @Override
    public void run() {
        try {
            Document document = Jsoup.connect(url).get();
            data.setDocument(url, document);
            return;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        data.setDocument(url, null);
    }
}
