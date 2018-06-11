package com.iblesa.androidcook.detail;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.model.Step;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    private Step mStep;
    private SimpleExoPlayer mExoPlayer;
    @BindView(R.id.step_video)
    SimpleExoPlayerView mSimpleExoPlayerView;
    @BindView(R.id.step_desc)
    TextView mDescription;
    @BindView(R.id.step_media)
    ImageView mMedia;

    public DetailFragment() {
        Log.d(Constants.TAG, "DetailFragment constructor called");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        mDescription.setText(mStep.getDescription());
        String videoUrl = mStep.getVideoURL();
        String thumbnailURL = mStep.getThumbnailURL();

        if (videoUrl != null && !videoUrl.isEmpty()) {
            Log.d(Constants.TAG, "VideoURL to load is " + videoUrl);
            initializePlayer(Uri.parse(videoUrl));
        } else {
            showBackupMedia(mMedia);
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

    private void showBackupMedia(ImageView mMedia) {
        mMedia.setVisibility(View.VISIBLE);
        mSimpleExoPlayerView.setVisibility(View.INVISIBLE);
    }

    /**
     * Method to initialize player
     *
     * @param mediaUri mediaUri
     */
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            //Create an instance of the ExoPlayer
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);

            mSimpleExoPlayerView.setPlayer(mExoPlayer);
            //Prepare the MEdiaSource
            String userAgent = Util.getUserAgent(getActivity(), "AndroidCook");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    /**
     * method to releasePlayer
     */
    private void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    /**
     * Release the player when the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    /**
     * Specifies the step to display in the detail activity
     * @param step Step to display
     */
    public void setStep(Step step) {
        mStep = step;
    }


}
