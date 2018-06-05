package com.iblesa.androidcook.detail;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iblesa.androidcook.R;
import com.iblesa.androidcook.model.Step;

public class DetailFragment extends Fragment {
    private static final String STEP = "STEP";

    public DetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        Activity activity = getActivity();
        if (activity != null) {
            Intent intent = activity.getIntent();

            Step step = intent.getParcelableExtra(STEP);
        }
        return view;
    }
}
