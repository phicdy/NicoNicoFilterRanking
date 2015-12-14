package com.phicdy.niconicofilterranking.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.phicdy.niconicofilterranking.R;

public class RankingActivity extends AppCompatActivity implements NicoVideoListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
    }

    @Override
    public void onFragmentInteraction(String url) {

    }
}
