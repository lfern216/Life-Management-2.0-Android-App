package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class ThirdScreenActivity extends AppCompatActivity {


    FloatingActionButton nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);

        nextButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        nextButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(ThirdScreenActivity.this, activity_fourth_screen.class);
                startActivity(intent);
            }
        });

        //zoom animation
        Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        nextButton.startAnimation(zoomAnimation);


    }


}

