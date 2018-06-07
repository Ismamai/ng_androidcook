package com.iblesa.androidcook.master;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.detail.DetailActivity;
import com.iblesa.androidcook.master.MasterFragment.OnStepClickListener;
import com.iblesa.androidcook.model.Recipe;
import com.iblesa.androidcook.model.Step;

public class MasterActivity extends AppCompatActivity implements OnStepClickListener {

    public static final String RECIPE = "RECIPE";
    public static final String STEP = "STEP";

    private Recipe mRecipe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        mRecipe = getIntent().getParcelableExtra(RECIPE);


    }

    @Override
    public void onStepSelected(Step step) {
        Log.d(Constants.TAG, "Selected step " + step);
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(STEP, step);
        bundle.putParcelable(RECIPE, mRecipe);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
