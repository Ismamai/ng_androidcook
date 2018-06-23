package com.iblesa.androidcook.master;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.detail.DetailActivity;
import com.iblesa.androidcook.detail.DetailFragment;
import com.iblesa.androidcook.master.MasterFragment.OnStepClickListener;
import com.iblesa.androidcook.model.Recipe;
import com.iblesa.androidcook.model.Step;

public class MasterActivity extends AppCompatActivity implements OnStepClickListener {

    public static final String RECIPE = "RECIPE";
    public static final String STEP = "STEP";

    private Recipe mRecipe;
    // This is used to know if we should use one or two panes layout
    private boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        if (findViewById(R.id.master_two_pane_layout) != null) {
            mTwoPane = true;
        }
        mRecipe = getIntent().getParcelableExtra(RECIPE);

        if (mTwoPane) {
            if (savedInstanceState == null) {
                // In two-pane mode, add initial BodyPartFragments to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();
                MasterFragment masterFragment = new MasterFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.master_container, masterFragment)
                        .commit();

//                DetailFragment detailFragment = new DetailFragment();
//
//                fragmentManager.beginTransaction()
//                        .add(R.id.detail_container, detailFragment)
//                        .commit();
            }

        }
    }

    @Override
    public void onStepSelected(Step step) {
        Log.d(Constants.TAG, "Selected step " + step);
        if (mTwoPane) {
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setStep(step);
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.detail_container, detailFragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(STEP, step);
            bundle.putParcelable(RECIPE, mRecipe);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
