package com.iblesa.androidcook.api;

import com.iblesa.androidcook.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
//https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json
public interface RecipeService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> listRecipes();
}
