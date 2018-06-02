package com.iblesa.androidcook;

import com.iblesa.androidcook.api.RecipeClient;
import com.iblesa.androidcook.api.RecipeService;
import com.iblesa.androidcook.model.Recipe;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.*;


public class intTestService {
    @Test
    public void testService() throws IOException {
        Retrofit client = RecipeClient.getClient();
        RecipeService service = client.create(RecipeService.class);
        Call<List<Recipe>> listCall = service.listRecipes();

        Response<List<Recipe>> response = listCall.execute();
        if (response.isSuccessful()) {
            List<Recipe> body = response.body();
            assertTrue("Body is not null", body != null);
            assertTrue("Returns a list of elements", !body.isEmpty());
            assertTrue("Returns a list of 4 elements", body.size() == 4);
        } else {
            fail();
        }

//        listCall.enqueue(new Callback<List<Recipe>>() {
//            @Override
//            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
//                List<Recipe> body = response.body();
//
//                assertTrue("Returns a list of elements", !body.isEmpty());
//                assertTrue("Returns a list of 4 elements", body.size()==4 );
//            }
//
//            @Override
//            public void onFailure(Call<List<Recipe>> call, Throwable t) {
//                t.printStackTrace();
//                fail();
//
//            }
//        });
    }
}
