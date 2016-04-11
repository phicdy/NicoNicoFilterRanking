package com.phicdy.niconicofilterranking.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.phicdy.niconicofilterranking.R;
import com.phicdy.niconicofilterranking.rss.RssParser;
import com.phicdy.niconicofilterranking.video.NicoVideo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity implements NicoVideoListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, new NicoVideoListFragment()).commit();

    }

    @Override
    public void onFragmentInteraction(String url) {

    }
}
