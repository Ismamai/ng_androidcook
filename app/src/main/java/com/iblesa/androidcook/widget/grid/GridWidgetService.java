package com.iblesa.androidcook.widget.grid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.model.Ingredient;

import java.util.List;

public class GridWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new GridRemoteViewsFactory(this.getApplicationContext());
    }

    static class GridRemoteViewsFactory implements RemoteViewsFactory {
        List<Ingredient> mIngredients;
        private Context mContext;

        public GridRemoteViewsFactory(Context mContext) {
            this.mContext = mContext;

        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            String ingredientsJson = sharedPreferences.getString(Constants.SHARED_PREFERENCE_INGREDIENTS_LIST, null);
            if (ingredientsJson != null) {
                Gson gson = new Gson();
                this.mIngredients = gson.fromJson(ingredientsJson, new TypeToken<List<Ingredient>>() {
                }.getType());
                Log.d(Constants.TAG, "Setting ingredients " + mIngredients.size() + " " + mIngredients.toString());
            }
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (mIngredients == null) {
                return 0;
            } else return mIngredients.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(mContext.getPackageName(),
                    R.layout.widget_ingredient_item);
            views.setTextViewText(R.id.tv_widget_ingredient_item, "- " +mIngredients.get(position).getIngredient());
            Log.d(Constants.TAG, "Setting ingredient for position " + position + " => " + mIngredients.get(position).getIngredient());
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1; // Treat all items in the GridView the same
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
