package com.iblesa.androidcook.master;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iblesa.androidcook.R;
import com.iblesa.androidcook.model.Ingredient;
import com.iblesa.androidcook.model.Step;

import java.util.List;

public class MasterListStepsAdapter extends RecyclerView.Adapter<MasterListStepsAdapter.StepViewHolder> {
    private Context mContext;
    private List<Step> mSteps;

    MasterListStepsAdapter(Context context, List<Step> steps) {
        this.mContext = context;
        this.mSteps = steps;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.master_step_list_item, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        holder.bind(mSteps.get(position));
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder {
        TextView mStep;

        StepViewHolder(View itemView) {
            super(itemView);
            mStep = itemView.findViewById(R.id.tv_step_item);
        }

        void bind(Step step) {
            String content = mContext.getResources().getString(R.string.step_item,
                    step.getId()+1,
                    step.getShortDescription());
            mStep.setText(content);
        }
    }
}
