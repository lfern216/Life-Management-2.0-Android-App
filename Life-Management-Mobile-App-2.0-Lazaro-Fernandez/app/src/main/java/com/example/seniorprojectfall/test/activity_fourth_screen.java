package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.Provider;



public class activity_fourth_screen extends YouTubeBaseActivity {

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    ImageButton androidRightArrowButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_screen);


        androidRightArrowButton = (ImageButton) findViewById(R.id.imageButton2);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);

        androidRightArrowButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                Intent i = new Intent(activity_fourth_screen.this,LoginActivity.class);
                startActivity(i);
            }
        });

        //zoom animation
        Animation zoomAnimation = AnimationUtils.loadAnimation(this,R.anim.zoom);
        androidRightArrowButton.startAnimation(zoomAnimation);


        onInitializedListener = new YouTubePlayer.OnInitializedListener() {

            @Override

            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("sK70sQDDfDQ");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInit){

            }
    };

        //the video will display automatically
        youTubePlayerView.initialize("AIzaSyCexMWxn8PykbQqBGbCPRgbGBN1g6-c70A",onInitializedListener);



    }//end of method
}
