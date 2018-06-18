package com.iblesa.androidcook.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.widget.grid.GridWidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class AndroidCookWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.android_cook_widget);

        //Retrieve selected Recipe
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String recipeName = sharedPreferences.getString(Constants.SHARED_PREFERENCE_RECIPE_NAME, null);
        if (recipeName == null) {
            recipeName = context.getString(R.string.appwidget_text);
        }
        views.setTextViewText(R.id.appwidget_recipe_name, recipeName);

        Intent gridViewIntent = new Intent(context, GridWidgetService.class);
        views.setRemoteAdapter(R.id.widget_grid_view, gridViewIntent);
        views.setEmptyView(R.id.widget_grid_view, R.id.widget_empty_view);

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_grid_view);

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

