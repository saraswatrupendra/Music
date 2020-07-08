package com.example.sampleapp;

import android.view.View;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class displayTest {

    @Rule
    public ActivityTestRule<display> mActivityRule = new ActivityTestRule(display.class);
    private display dis;


    @Before
    public void setUp() throws Exception {
        dis=mActivityRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        dis=null;

    }

    @Test
    public void onLaunch() throws JSONException {

        ArrayList<View> v=new ArrayList<>();
        v.add(dis.findViewById(R.id.dimg));
        v.add(dis.findViewById(R.id.ddesc));
        v.add(dis.findViewById(R.id.dtitle));
        v.add(dis.findViewById(R.id.play));
        v.add(dis.findViewById(R.id.pause));

        for (View a:v){
            assertNotNull(a);
        }

        //onclick action on play pause button
        onView(withId(R.id.play)).perform(click());
        onView(withId(R.id.pause)).perform(click());

    }
}