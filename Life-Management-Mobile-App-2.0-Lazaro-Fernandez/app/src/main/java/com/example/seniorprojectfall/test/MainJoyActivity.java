package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Button;
import java.util.ArrayList;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;


/**
 * Created by lazaro on 10/12/17.
 */

public class MainJoyActivity extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private ArrayList<Joy> activitiesJoy;
    static ImageButton androidRightArrowButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        androidRightArrowButton = (ImageButton) findViewById(R.id.imageButtonActivity2);

        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);

        settingDummyData();
        adapter = new CoverFlowAdapter(this, activitiesJoy);
        coverFlow.setAdapter(adapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());


        androidRightArrowButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "It works",Toast.LENGTH_LONG).show();
                Intent in = getIntent();
                String userid = in.getStringExtra("userid");
                String username = in.getStringExtra("username");
                String password = in.getStringExtra("password");
                Intent i = new Intent(MainJoyActivity.this, MainPassionActivity.class);
                i.putExtra("userid",userid);
                i.putExtra("username",username);
                i.putExtra("password",password);
                startActivity(i);
            }
        });

        androidRightArrowButton.setVisibility(View.GONE);
        //zoom animation
        Animation zoomAnimation = AnimationUtils.loadAnimation(this,R.anim.zoom);
        androidRightArrowButton.startAnimation(zoomAnimation);
    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("onScrolledPosition", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("onScrolling", "scrolling");
            }
        };
    }

    private void settingDummyData() {
        activitiesJoy = new ArrayList<>();
        activitiesJoy.add(new Joy(R.drawable.joy_beach2, "Beach"));
        activitiesJoy.add(new Joy(R.drawable.joy_cooking2, "Cooking"));
        activitiesJoy.add(new Joy(R.drawable.joy_cycling3, "Cycling"));
        activitiesJoy.add(new Joy(R.drawable.joy_exercising, "Exercising"));
        activitiesJoy.add(new Joy(R.drawable.joy_movies, "Movies"));
        activitiesJoy.add(new Joy(R.drawable.joy_music, "Music"));
        activitiesJoy.add(new Joy(R.drawable.joy_photography, "Photography"));
        activitiesJoy.add(new Joy(R.drawable.joy_programming, "Programming"));
        activitiesJoy.add(new Joy(R.drawable.joy_reading2, "Reading"));

    }
}

