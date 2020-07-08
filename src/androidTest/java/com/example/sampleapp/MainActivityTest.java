package com.example.sampleapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Looper;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
    private MainActivity mainActivity;
   // public ActivityTestRule<display> disp = new ActivityTestRule(display.class);
   Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(display.class.getName(),null,false);


    @Before
    public void setUp() throws Exception {
        mainActivity=mActivityRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity=null;
    }


    @Test
    public void onLaunch() throws JSONException {

         View v=mainActivity.findViewById(R.id.recycle);
        assertNotNull(v);

        //onclick the recycleview item goes to another activity
        onView(withId(R.id.recycle)).perform(click());

        Activity a=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(a);
        a.finish();


        //check whether the all the json data populate inside recycleview or not
        onView(withId(R.id.recycle)).check(new RecyclerViewItemCountAssertion(10));

    }

    public class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));
        }
    }

}