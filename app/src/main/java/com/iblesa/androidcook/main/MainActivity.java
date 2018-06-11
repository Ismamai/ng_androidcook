package com.iblesa.androidcook.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.iblesa.androidcook.Constants;
import com.iblesa.androidcook.R;
import com.iblesa.androidcook.api.RecipeClient;
import com.iblesa.androidcook.api.RecipeService;
import com.iblesa.androidcook.model.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private MainListRecipesAdapter mRecipesListAdapter;
    @BindView(R.id.rv_main_recipes)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_error_view)
    TextView mErrorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Setting LayoutManager
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Setting Adapter
        mRecipesListAdapter = new MainListRecipesAdapter(this);
        mRecyclerView.setAdapter(mRecipesListAdapter);
        loadRecipes();
    }

    private void checkConnectivity() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (!isConnected) {
            showError();
        }
    }

    private void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);


    }
    private void loadRecipes() {
        checkConnectivity();
        Retrofit client = RecipeClient.getClient();
        RecipeService service = client.create(RecipeService.class);
        Call<List<Recipe>> listCall = service.listRecipes();

        listCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> recipes = response.body();
                int size = 0;
                if (recipes != null && !recipes.isEmpty()) {
                    size = recipes.size();
                }
                String message = String.format("Setting Recipes (%s) %s", size, recipes);
                Log.d(Constants.TAG, message);

                mRecipesListAdapter.setRecipes(recipes);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"There has been an error loading data", Toast.LENGTH_LONG).show();
            }
        });
    }
}
