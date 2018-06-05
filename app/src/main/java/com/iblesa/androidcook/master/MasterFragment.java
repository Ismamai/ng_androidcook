package com.iblesa.androidcook.master;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.model.Recipe;
import com.iblesa.androidcook.model.Step;

public class MasterFragment extends Fragment {
    private static final String RECIPE = "RECIPE";
    OnStepClickListener mCallBack;

    public MasterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master, container, false);
        Activity activity = getActivity();
        if (activity != null) {
            Intent intent = activity.getIntent();

            Recipe recipe = intent.getParcelableExtra(RECIPE);

            RecyclerView recyclerView = view.findViewById(R.id.rv_master_ingredients);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            MasterListIngredientsAdapter masterListIngredientAdapter = new MasterListIngredientsAdapter(getContext(), recipe.getIngredients());
            recyclerView.setAdapter(masterListIngredientAdapter);

            RecyclerView stepsRecyclerView = view.findViewById(R.id.rv_master_steps);
            stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            MasterListStepsAdapter masterListStepsAdapter = new MasterListStepsAdapter(getContext(), recipe.getSteps(), mCallBack);
            stepsRecyclerView.setAdapter(masterListStepsAdapter);
        } else {
            Log.e(Constants.TAG, "Unable to retrieve intent from activity");
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnStepClickListener");
        }
    }

    // Used to notify that a Step has been selected
    public interface OnStepClickListener {
        void onStepSelected(Step step);
    }
}

