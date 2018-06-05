package com.iblesa.androidcook.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


    }

    public void clickNext(View view) {
        Log.d(Constants.TAG, "Click on button to go to Next Step");

    }
    public void clickPrevious(View view) {
        Log.d(Constants.TAG, "Click on button to go to Previous Step");

    }
}
