package com.example.seniorprojectfall.test;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.view.animation.Animation;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

        ImageButton androidRightArrowButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            androidRightArrowButton = (ImageButton) findViewById(R.id.imageButton);

            final CheckedTextView skip = (CheckedTextView) findViewById(R.id.checkedTextView);


            androidRightArrowButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v){
                    //Toast.makeText(MainActivity.this, "It works",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this,SecondScreenActivity.class);
                    startActivity(i);
                }
            });

            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    skip.setTextColor(Color.BLUE);
                    Intent i = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(i);
                }
            });

            //zoom animation
            Animation zoomAnimation = AnimationUtils.loadAnimation(this,R.anim.zoom);
            androidRightArrowButton.startAnimation(zoomAnimation);



        }

}
