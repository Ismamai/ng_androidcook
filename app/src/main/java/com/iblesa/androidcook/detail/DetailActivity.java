package com.iblesa.androidcook.detail;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.model.Recipe;
import com.iblesa.androidcook.model.Step;

public class DetailActivity extends AppCompatActivity {
    private static final String STEP = "STEP";
    private static final String RECIPE = "RECIPE";
    private Step mStep;
    private Recipe mRecipe;
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mStep = getIntent().getParcelableExtra(STEP);
        mRecipe = getIntent().getParcelableExtra(RECIPE);
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getFragmentManager();
            detailFragment = new DetailFragment();
            detailFragment.setStep(mStep);
            fragmentManager.beginTransaction()
                    .add(R.id.detail_container, detailFragment)
                    .commit();
        }
    }

    public void clickNext(View view) {
        Log.d(Constants.TAG, "Click on button to go to Next Step");

        int stepIndex = mRecipe.getSteps().indexOf(mStep);
        if ((stepIndex >= mRecipe.getSteps().size() - 1)) {
            Toast.makeText(this, R.string.already_on_next_step, Toast.LENGTH_SHORT).show();
        } else {
            stepIndex++;
            mStep = mRecipe.getSteps().get(stepIndex);
            updateFragment();
        }
    }

    public void clickPrevious(View view) {
        Log.d(Constants.TAG, "Click on button to go to Previous Step");
        int stepIndex = mRecipe.getSteps().indexOf(mStep);
        if ((stepIndex <= 0)) {
            Toast.makeText(this, R.string.already_on_first_step, Toast.LENGTH_SHORT).show();

        } else {
            stepIndex--;
            mStep = mRecipe.getSteps().get(stepIndex);
            updateFragment();
        }
    }

    private void updateFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        DetailFragment detailFragment = new DetailFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.detail_container, detailFragment)
                .commit();
        detailFragment.setStep(mStep);
    }
}
