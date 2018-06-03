package com.iblesa.androidcook.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.model.Recipe;

import java.util.List;

public class MainListRecipesAdapter extends RecyclerView.Adapter<MainListRecipesAdapter.RecipeViewHolder> {
    private List<Recipe> mRecipes;
    private final Context mContext;

    public MainListRecipesAdapter(Context context) {
        this.mContext = context;
        this.mRecipes = null;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.mRecipes = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recipe_list_item, parent, false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bind(mRecipes.get(position));

    }

    @Override
    public int getItemCount() {
        if (mRecipes == null) {
            return 0;
        }
        return mRecipes.size();
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewRecipeName;
        ImageView mImageViewRecipeImage;
        TextView mTextViewRecipeServes;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            mContext.getResources();
            mTextViewRecipeName = itemView.findViewById(R.id.recipe_item_name);
            mImageViewRecipeImage = itemView.findViewById(R.id.recipe_item_image);
            mTextViewRecipeServes = itemView.findViewById(R.id.recipe_item_serves);
            Log.d(Constants.TAG, "Created RecipeViewHolder " + this);
        }

        public void bind(Recipe recipe) {
            String message = String.format("Setting RecipeViewHolder (%s) with recipe %s", this, recipe);
            Log.d(Constants.TAG, message);
            mTextViewRecipeName.setText(recipe.getName());
//            mImageViewRecipeImage.setImageResource();
            String servings = mContext.getResources().getString(R.string.recipe_item_serves, recipe.getServings());
            mTextViewRecipeServes.setText(servings);

        }
    }
}
