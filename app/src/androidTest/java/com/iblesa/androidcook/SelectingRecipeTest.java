package com.iblesa.androidcook;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;

import com.iblesa.androidcook.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;

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
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    @Test
    public void showSelectedRecipeTest() {
        // Uses {@link Espresso#onData(org.hamcrest.Matcher)} to get a reference to a specific
        // gridview item and clicks it.
//        onData(anything()).inAdapterView(withId(R.id.rv_main_recipes)).atPosition(1).perform(click());

        onView(withId(R.id.rv_main_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));        // Checks that the OrderActivity opens with the correct tea name displayed

        onView(withId(R.id.tv_master_recipe_name)).check(matches(withText(NUTELLA_PIE)));
    }
}
