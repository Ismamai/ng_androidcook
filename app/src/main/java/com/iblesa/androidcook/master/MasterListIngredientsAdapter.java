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

import java.util.List;

public class MasterListIngredientsAdapter extends RecyclerView.Adapter<MasterListIngredientsAdapter.IngredientViewHolder> {
    private Context mContext;
    private List<Ingredient> mIngredients;

    MasterListIngredientsAdapter(Context context, List<Ingredient> ingredients) {
        this.mContext = context;
        this.mIngredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.master_ingredient_list_item, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.bind(mIngredients.get(position));
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView mIngredient;

        IngredientViewHolder(View itemView) {
            super(itemView);
            mIngredient = itemView.findViewById(R.id.tv_ingredient_item);
        }

        void bind(Ingredient ingredient) {
            String content = mContext.getResources().getString(R.string.ingredient_item,
                    ingredient.getIngredient(),
                    ingredient.getQuantity(),
                    ingredient.getMeasure());

            mIngredient.setText(content);
        }
    }
}
