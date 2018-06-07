package com.iblesa.androidcook.detail;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.model.Step;

public class DetailActivity extends AppCompatActivity {
    private static final String STEP = "STEP";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Step step = getIntent().getParcelableExtra(STEP);

        FragmentManager fragmentManager = getFragmentManager();
        DetailFragment detailFragment = new DetailFragment();
        fragmentManager.beginTransaction()
                .add(R.id.detail_container, detailFragment)
                .commit();
        detailFragment.setStep(step);


    }

    public void clickNext(View view) {
        Log.d(Constants.TAG, "Click on button to go to Next Step");

    }
    public void clickPrevious(View view) {
        Log.d(Constants.TAG, "Click on button to go to Previous Step");

    }
}
