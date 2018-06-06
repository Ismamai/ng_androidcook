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
import com.squareup.picasso.Picasso;

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
            String videoUrl = step.getVideoURL();
            if (videoUrl != null && !videoUrl.isEmpty()) {
                Log.d(Constants.TAG, "VideoURL to load is " + videoUrl);
            } else {

                String thumbnailURL = step.getThumbnailURL();
                Log.d(Constants.TAG, "ThumbnailURL is " + thumbnailURL);
                if (thumbnailURL!=null && !thumbnailURL.isEmpty()) {
                    Picasso.get()
                            .load(thumbnailURL)
                            .placeholder(R.drawable.image_placeholder)
                            .error(R.drawable.image_placeholder)
                            .into(mMedia);
                } else {
                    Picasso.get()
                            .load(R.drawable.image_placeholder)
                            .into(mMedia);
                }
            }
        } else {
            Log.e(Constants.TAG, "Unable to retrieve intent STEP from activity");
        }
        return view;
    }

}
