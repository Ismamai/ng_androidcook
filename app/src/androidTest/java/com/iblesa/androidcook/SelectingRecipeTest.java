package com.iblesa.androidcook;

import android.os.AsyncTask;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;

import com.iblesa.androidcook.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class SelectingRecipeTest {
    public static final String NUTELLA_PIE = "Nutella Pie";
    final CountDownLatch signal = new CountDownLatch(1);

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    @Test
    public void showSelectedRecipeTest() {
        final AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Log.d(Constants.TAG, "TEST: in AsyncTask waiting");

                try {
                    Log.d(Constants.TAG, "TEST: Got To sleep");

                    Thread.sleep(5000L);
                    Log.d(Constants.TAG, "TEST: Awake");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(Constants.TAG, "TEST: signaling countdown");
                signal.countDown();
                return null;
            }
        };
        task.execute();
        Log.d(Constants.TAG, "TEST: Lets wait before we do the test");

        try {
            signal.await(10, TimeUnit.SECONDS);
            Log.d(Constants.TAG, "TEST: We were signaled by the CountDownLatch");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(Constants.TAG, "TEST: Starting the test");

        onView(withId(R.id.rv_main_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));        // Checks that the OrderActivity opens with the correct tea name displayed

        onView(withId(R.id.tv_master_recipe_name)).check(matches(withText(NUTELLA_PIE)));
        Log.d(Constants.TAG, "TEST: Passed");
    }
}
