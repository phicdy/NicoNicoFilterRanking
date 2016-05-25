package com.phicdy.niconicofilterranking.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phicdy.niconicofilterranking.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class CategorySettingActivityFragment extends Fragment {

    public CategorySettingActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_setting, container, false);
    }
}
