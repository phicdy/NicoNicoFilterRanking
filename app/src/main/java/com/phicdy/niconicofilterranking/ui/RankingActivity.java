package com.phicdy.niconicofilterranking.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.phicdy.niconicofilterranking.R;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;

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
    public void OnNicoChartLoadStart() {
        CircularProgressBar progressBar = (CircularProgressBar)findViewById(R.id.circleProgress);
        ((CircularProgressDrawable) progressBar.getIndeterminateDrawable()).start();
    }

    @Override
    public void OnNicoChartLoadFinished() {
        CircularProgressBar progressBar = (CircularProgressBar)findViewById(R.id.circleProgress);
        progressBar.progressiveStop();
    }
}
