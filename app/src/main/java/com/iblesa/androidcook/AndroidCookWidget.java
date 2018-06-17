package com.iblesa.androidcook;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iblesa.androidcook.model.Ingredient;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class AndroidCookWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.android_cook_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        //Retrieve selected Recipe
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String recipeName = sharedPreferences.getString(Constants.SHARED_PREFERENCE_RECIPE_NAME, null);
        String ingredientsJson = sharedPreferences.getString(Constants.SHARED_PREFERENCE_INGREDIENTS_LIST, null);
        List<Ingredient> ingredientList;
        if (ingredientsJson != null) {
            Gson gson = new Gson();
            ingredientList = gson.fromJson(ingredientsJson, new TypeToken<List<Ingredient>>() {
            }.getType());
            Log.d(Constants.TAG, ingredientList.toString());
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

