package com.iblesa.androidcook.main;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.master.MasterActivity;
import com.iblesa.androidcook.model.Recipe;
import com.iblesa.androidcook.widget.AndroidCookWidget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.recipe_item_name)
        TextView mTextViewRecipeName;
        @BindView(R.id.recipe_item_image)
        ImageView mImageViewRecipeImage;
        @BindView(R.id.recipe_item_serves)
        TextView mTextViewRecipeServes;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            mContext.getResources();
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
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

        @Override
        public void onClick(View v) {
            Recipe recipe = mRecipes.get(getAdapterPosition());
            String message = String.format("Clicked element %1s", recipe.getName());
            Log.d(Constants.TAG, message);
            Intent intent = new Intent(mContext, MasterActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("RECIPE", recipe);
            intent.putExtras(bundle);

            storeSelectedRecipe(recipe);

            mContext.startActivity(intent);
        }

        /**
         * Stores selected recipe in shared preferences to be used by the widget to display recipe
         * and ingredients list.
         * @param recipe selected recipe
         */
        private void storeSelectedRecipe(Recipe recipe) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            SharedPreferences.Editor edit = defaultSharedPreferences.edit();
            Gson gson = new Gson();
            String ingredients = gson.toJson(recipe.getIngredients());
            edit.putString(Constants.SHARED_PREFERENCE_RECIPE_NAME, recipe.getName());
            edit.putString(Constants.SHARED_PREFERENCE_INGREDIENTS_LIST, ingredients);
            edit.apply();

            // Update widgets on step selected
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(mContext, AndroidCookWidget.class));
            //Trigger data update to handle the GridView widgets and force a data refresh
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_grid_view);
            AndroidCookWidget.updateWidgets(mContext, appWidgetManager, appWidgetIds);
        }
    }
}
