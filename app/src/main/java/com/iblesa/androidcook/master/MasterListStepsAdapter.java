package com.iblesa.androidcook.master;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.model.Step;

import java.util.List;

public class MasterListStepsAdapter extends RecyclerView.Adapter<MasterListStepsAdapter.StepViewHolder> {
    private Context mContext;
    private List<Step> mSteps;
    private MasterFragment.OnStepClickListener listener;

    MasterListStepsAdapter(Context context, List<Step> steps, MasterFragment.OnStepClickListener listener) {
        this.mContext = context;
        this.mSteps = steps;
        this.listener = listener;
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

    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mStep;

        StepViewHolder(View itemView) {
            super(itemView);
            mStep = itemView.findViewById(R.id.tv_step_item);
            mStep.setOnClickListener(this);
        }

        void bind(Step step) {
            String content = mContext.getResources().getString(R.string.step_item,
                    step.getId()+1,
                    step.getShortDescription());
            mStep.setText(content);
        }

        @Override
        public void onClick(View v) {
            Log.d(Constants.TAG, "Selected element " + mSteps.get(getAdapterPosition()));
            listener.onStepSelected(mSteps.get(getAdapterPosition()));
        }
    }
}
