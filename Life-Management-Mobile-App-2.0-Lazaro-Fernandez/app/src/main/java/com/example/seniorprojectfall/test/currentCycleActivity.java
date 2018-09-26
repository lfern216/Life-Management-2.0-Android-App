package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.Menu;
import com.sccomponents.gauges.ScArcGauge;

import org.w3c.dom.Text;

public class currentCycleActivity extends AppCompatActivity {


    BottomNavigationView navigation;
    private int mMenuId;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            mMenuId = item.getItemId();
            for (int i = 0; i < navigation.getMenu().size(); i++) {
                MenuItem menuItem = navigation.getMenu().getItem(i);
                boolean isChecked = menuItem.getItemId() == item.getItemId();
                menuItem.getIcon().setColorFilter(Color.parseColor("#a8a8a8"),PorterDuff.Mode.SRC_IN);
            }

            switch (item.getItemId()) {
                case R.id.navigation_joy:
                    item.getIcon().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                    populatecurrentJoy();
                    return true;
                case R.id.navigation_passion:

                    item.getIcon().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                    populatecurrentPassion();
                    return true;
                case R.id.navigation_contribution:
                    item.getIcon().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                    populatecurrentContribution();
                    return true;

            }

            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_cycle);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




        TextView g = (TextView) findViewById(R.id.textViewcurrentcycle);

        //+ " Activity Score: " + Dashboard.userActivityJoyid1.activityScore + "\n"
        int f = Integer.parseInt(Dashboard.userActivityJoyid1.activityScore);

        ScArcGauge first = (ScArcGauge) findViewById(R.id.gauge_currentcycle);
        TextView first2 = (TextView) findViewById(R.id.counter_currentcycle);

        first2.setText(f+"");
        first.setHighValue(f);


        g.setText("\n\n\n"
                + " Activity Name: " +  Dashboard.userActivityJoyid1.name + "\n"
                + " Actual Points:  " + Dashboard.userActivityJoyid1.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityJoyid1.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed);


        TextView g2 = (TextView) findViewById(R.id.textViewcurrentcycle2);
        int h1 = Integer.parseInt(Dashboard.userActivityJoyid2.activityScore);

        ScArcGauge first3 = (ScArcGauge) findViewById(R.id.gauge_currentcycle2);
        TextView first4 = (TextView) findViewById(R.id.counter_currentcycle2);

        first4.setText(h1+"");
        first3.setHighValue(h1);


        g2.setText("\n\n\n"
                + " Activity #2 Name: " +  Dashboard.userActivityJoyid2.name + "\n"
                + " Actual Points:  " + Dashboard.userActivityJoyid2.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityJoyid2.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed);


        ImageButton goBack = (ImageButton) findViewById(R.id.imageButton_currentcycle);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    } // end of oncreate


    public void populatecurrentJoy(){


        TextView g = (TextView) findViewById(R.id.textViewcurrentcycle);

        int f = Integer.parseInt(Dashboard.userActivityJoyid1.activityScore);

        ScArcGauge first = (ScArcGauge) findViewById(R.id.gauge_currentcycle);
        TextView first2 = (TextView) findViewById(R.id.counter_currentcycle);

        first2.setText(f+"");
        first.setHighValue(f);


        g.setText("\n\n\n"
                + " Activity Name: " +  Dashboard.userActivityJoyid1.name + "\n"
                + " Actual Points:  " + Dashboard.userActivityJoyid1.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityJoyid1.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed);


        TextView g2 = (TextView) findViewById(R.id.textViewcurrentcycle2);
        int h1 = Integer.parseInt(Dashboard.userActivityJoyid2.activityScore);

        ScArcGauge first3 = (ScArcGauge) findViewById(R.id.gauge_currentcycle2);
        TextView first4 = (TextView) findViewById(R.id.counter_currentcycle2);

        first4.setText(h1+"");
        first3.setHighValue(h1);


        g2.setText("\n\n\n"
                + " Activity #2 Name: " +  Dashboard.userActivityJoyid2.name + "\n"
                + " Actual Points:  " + Dashboard.userActivityJoyid2.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityJoyid2.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed);


    } //end of method



    public void populatecurrentPassion(){

        TextView g = (TextView) findViewById(R.id.textViewcurrentcycle);

        int f = Integer.parseInt(Dashboard.userActivityPassionid1.activityScore);

        ScArcGauge first = (ScArcGauge) findViewById(R.id.gauge_currentcycle);
        TextView first2 = (TextView) findViewById(R.id.counter_currentcycle);

        first2.setText(f+"");
        first.setHighValue(f);


        g.setText("\n\n\n"
                + " Activity Name: " +  Dashboard.userActivityPassionid1.name + "\n"
                + " Actual Points:  " + Dashboard.userActivityPassionid1.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityPassionid1.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed_passion + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed_passion);


        TextView g2 = (TextView) findViewById(R.id.textViewcurrentcycle2);
        int h1 = Integer.parseInt(Dashboard.userActivityPassionid2.activityScore);

        ScArcGauge first3 = (ScArcGauge) findViewById(R.id.gauge_currentcycle2);
        TextView first4 = (TextView) findViewById(R.id.counter_currentcycle2);

        first4.setText(h1+"");
        first3.setHighValue(h1);

        g2.setText("\n\n\n"
                + " Activity #2 Name: " +  Dashboard.userActivityPassionid2.name + "\n"
                + " Actual Points:  " + Dashboard.userActivityPassionid2.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityPassionid2.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed_passion + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed_passion);

    } //end of method

    public void populatecurrentContribution(){

        TextView g = (TextView) findViewById(R.id.textViewcurrentcycle);

        int f = Integer.parseInt(Dashboard.userActivityContributionid1.activityScore);

        ScArcGauge first = (ScArcGauge) findViewById(R.id.gauge_currentcycle);
        TextView first2 = (TextView) findViewById(R.id.counter_currentcycle);

        first2.setText(f+"");
        first.setHighValue(f);


        g.setText("\n\n\n"
                + " Activity Name: " +  Dashboard.userActivityContributionid1.name + "\n"
                + " Actual Points:  " + Dashboard.userActivityContributionid1.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityContributionid1.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed_contribution + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed_contribution);


        TextView g2 = (TextView) findViewById(R.id.textViewcurrentcycle2);
        int h1 = Integer.parseInt(Dashboard.userActivityContributionid2.activityScore);

        ScArcGauge first3 = (ScArcGauge) findViewById(R.id.gauge_currentcycle2);
        TextView first4 = (TextView) findViewById(R.id.counter_currentcycle2);

        first4.setText(h1+"");
        first3.setHighValue(h1);

        g2.setText("\n\n\n"
                + " Activity #2 Name: " +  Dashboard.userActivityContributionid2.name + "\n"
                + " Actual Points:  " + Dashboard.userActivityContributionid2.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityContributionid2.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed_contribution + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed_contribution);

    } //end of method

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

}
