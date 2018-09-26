package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.List;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class GivingBackActivity extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private ActGivingbackAdapter actGivingbackAdapter;
    private List<ActGivingback> actGivingbackList = new ArrayList<>();
    static ImageButton nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giving_back);

        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverFlowGivingBack);

        initData();

        // Set Adapter for Cover Flow
        actGivingbackAdapter = new ActGivingbackAdapter(this, actGivingbackList);
        coverFlow.setAdapter(actGivingbackAdapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());

        nextButton = (ImageButton) findViewById(R.id.nextToDashboard);
        //Go to sprint setting activity when click on button
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent in = getIntent();
                String userid = in.getStringExtra("userid");
                String username = in.getStringExtra("username");
                String password = in.getStringExtra("password");
                Intent i = new Intent(GivingBackActivity.this, SprintSettingActivity.class);
                i.putExtra("userid",userid);
                i.putExtra("username",username);
                i.putExtra("password",password);
                startActivity(i);
            }
        });
        nextButton.setVisibility(View.GONE);

        //zoom animation
        Animation zoomAnimation = AnimationUtils.loadAnimation(this,R.anim.zoom);
        nextButton.startAnimation(zoomAnimation);
    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("GivingBackActivity", "position: " + position);
            }

            @Override
            public void onScrolling(){
                Log.i("GivingBackActivity", "scrolling");
            }
        };
    }

    private void initData() {

        actGivingbackList.add(new ActGivingback("Volunteering",R.drawable.give_volunteering));
        actGivingbackList.add(new ActGivingback("Teaching",R.drawable.give_teaching));
        actGivingbackList.add(new ActGivingback("Counseling",R.drawable.give_counseling));
        actGivingbackList.add(new ActGivingback("Mentoring",R.drawable.give_mentoring));
        actGivingbackList.add(new ActGivingback("Helping",R.drawable.give_helping));
        actGivingbackList.add(new ActGivingback("Nonprofit",R.drawable.give_nonprofit));
        actGivingbackList.add(new ActGivingback("Coaching",R.drawable.give_coaching));
        actGivingbackList.add(new ActGivingback("Donating",R.drawable.give_donating));

    }
}
