package com.example.sampleapp;

import Adapter.MyAdapter;
import Model.listitem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    RecyclerView.Adapter myadapter;
    List<listitem> lists;
    RequestQueue queue;

    private static String url= "http://starlord.hackerearth.com/studio";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);

     getSupportActionBar().setTitle("MUSIC LIST");


        queue= Volley.newRequestQueue(this);
        recyclerView=findViewById(R.id.recycle);
        recyclerView.hasFixedSize();

        lists=new ArrayList<>();

        if (isWorkingInternetPresent()) {
            jsonexcute();
        }
        else{
            Toast.makeText(this,"Internet not present",Toast.LENGTH_LONG).show();
        }

        }

    private void jsonexcute() {

        JsonArrayRequest jos=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0;i<response.length();i++) {
                              JSONObject job = null;
                              try {
                                  job = response.getJSONObject(i);
                                  listitem item = new listitem(job.getString("song").toString(), job.getString("artists"),
                                          job.getString("cover_image"),job.getString("url"));
                                //  Log.d("qwerty", job.getString("song"));

                                  lists.add(item);

                              } catch (JSONException e) {
                                  Log.d("azsx",e.getMessage());
                                  e.printStackTrace();
                              }
                          }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        myadapter = new MyAdapter(getApplicationContext(), lists);
                        recyclerView.setAdapter(myadapter);




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"cbdcbs",Toast.LENGTH_LONG);

                    }
                });

        queue.add(jos);
    }


    public boolean isWorkingInternetPresent() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getBaseContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

}
