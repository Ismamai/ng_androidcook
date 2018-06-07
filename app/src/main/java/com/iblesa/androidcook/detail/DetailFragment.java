package com.iblesa.androidcook.detail;

import android.app.Fragment;
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

    private Step mStep;

    public DetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView mDescription = view.findViewById(R.id.step_desc);
        ImageView mMedia = view.findViewById(R.id.step_media);

        mDescription.setText(mStep.getDescription());
        String videoUrl = mStep.getVideoURL();
        if (videoUrl != null && !videoUrl.isEmpty()) {
            Log.d(Constants.TAG, "VideoURL to load is " + videoUrl);
        } else {

            String thumbnailURL = mStep.getThumbnailURL();
            Log.d(Constants.TAG, "ThumbnailURL is " + thumbnailURL);
            if (thumbnailURL != null && !thumbnailURL.isEmpty()) {
                Picasso.get()
                        .load(thumbnailURL)
                        .placeholder(R.drawable.progress_image)
                        .error(R.drawable.image_placeholder)
                        .into(mMedia);
            } else {
                Picasso.get()
                        .load(R.drawable.image_placeholder)
                        .placeholder(R.drawable.progress_image)
                        .into(mMedia);
            }
        }
        return view;
    }

    /**
     * Specifies the step to display in the detail activity
     * @param step Step to display
     */
    public void setStep(Step step) {
        mStep = step;
    }

}
