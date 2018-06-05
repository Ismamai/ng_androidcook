package com.iblesa.androidcook.detail;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.model.Step;

public class DetailFragment extends Fragment {
    private static final String STEP = "STEP";

    private TextView mDescription;
    private ImageView mMedia;

    public DetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        mDescription = view.findViewById(R.id.step_desc);
        mMedia = view.findViewById(R.id.step_media);

        Activity activity = getActivity();

        if (activity != null) {
            Intent intent = activity.getIntent();

            Step step = intent.getParcelableExtra(STEP);
            mDescription.setText(step.getDescription());
        } else {
            Log.e(Constants.TAG, "Unable to retrieve intent STEP from activity");
        }
        return view;
    }

}
