package com.example.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class display extends AppCompatActivity implements View.OnClickListener {

    TextView title,description;
    ImageView image;
    ImageButton play,pause;
    Bundle extra;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        getSupportActionBar().setTitle("MUSIC");
        title=findViewById(R.id.dtitle);
        description=findViewById(R.id.ddesc);
        image=findViewById(R.id.dimg);
        play=findViewById(R.id.play);
        pause=findViewById(R.id.pause);
        extra=getIntent().getExtras();
         mp = new MediaPlayer();

        if(extra!=null){

            title.setText(extra.getString("title"));
            description.setText(extra.getString("desc"));
            Picasso.get().load(extra.getString("image")).into(image);

          }

      try {
            mp.setDataSource(extra.getString("url"));
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }




          play.setOnClickListener(this);
          pause.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mp.stop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                play.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
                if(mp.isPlaying()) {
                    mp.stop();
                    mp.reset();
                    mp.release();

                    mp.start();
                }
                else{
                    mp.start();}
                break;


            case R.id.pause:
                pause.setVisibility(View.INVISIBLE);
                play.setVisibility(View.VISIBLE);

                if(mp!=null && mp.isPlaying()){
                    mp.pause();
                    // mp=null;
                }
                break;

        }
    }
}
