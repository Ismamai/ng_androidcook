package com.iblesa.androidcook.master;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.model.Recipe;

public class MasterActivity extends AppCompatActivity {

    public static final String RECIPE = "RECIPE";
    private Recipe mRecipe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(RECIPE)) {
            mRecipe = getIntent().getParcelableExtra(RECIPE);
            Log.d(Constants.TAG, "Recipe selected " + mRecipe);

        }
        setContentView(R.layout.activity_master);


    }

}
