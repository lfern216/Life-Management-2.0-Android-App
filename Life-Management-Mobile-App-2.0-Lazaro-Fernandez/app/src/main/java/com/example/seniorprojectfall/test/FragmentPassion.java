package com.example.seniorprojectfall.test;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sccomponents.gauges.ScArcGauge;
import com.sccomponents.gauges.ScGauge;
import java.lang.Math;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FragmentPassion extends Fragment implements View.OnClickListener{

    //public static String test;
    private Button submitGoalbtn_Passion;
    private EditText q1_passion;
    private EditText q2_passion;
    private EditText q3_passion;
    private EditText q4_passion;
    DatabaseReference databaseUpdateCategories;
    DatabaseReference databaseUpdateActivities;

    TextView textActual1;
    TextView textActual2;

    TextView textTarget1; // nat change
    TextView textTarget2;  // nat change
    TextView counterPassionActivity1; // nat change
    ScArcGauge gaugePassionActivity1; // nat change
    String activity1ScoreStr; // nat change
    int activity1ScoreInt;  // nat change
    TextView counterPassionActivity2; // nat change
    ScArcGauge gaugePassionActivity2; // nat change
    String activity2ScoreStr; // nat change
    int activity2ScoreInt;  // nat change
    TextView counterPassionScore; // nat change
    ScArcGauge gaugePassionScore; // nat change
    String passionScoreStr; // nat change
    int passionScoreInt; // nat change
    static TextView counterPassionLifeScore; // nat change
    static ScArcGauge gaugePassionLifeScore; // nat change
    static int lifeScorePassionInt; // nat change

    /**
     * Created by Natalia on 9/20/2017.
     */

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joy,container,false);

        final ScrollView scroll = (ScrollView)view.findViewById(R.id.scrollViewId);
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        TextView textStartingDate = (TextView)view.findViewById(R.id.startingDateBtn);
        //System.out.println("DATEEE444 " + test);
        textStartingDate.setText(Dashboard.startingDateFixed_passion);

        TextView textEndingDate = (TextView)view.findViewById(R.id.endingDateBtn);
        textEndingDate.setText(Dashboard.endingDateFixed_passion);

        TextView textAct1 = (TextView)view.findViewById(R.id.textViewAct1);
        // Assign variable textAct1 the value of static variable activity1Joy (from TestActivity.java)
        textAct1.setText(Dashboard.userActivityPassionid1.name);
        //textAct1.setText("Inner Peace");

        TextView textAct2 = (TextView)view.findViewById(R.id.textViewAct2);
        textAct2.setText(Dashboard.userActivityPassionid2.name);

        TextView textCategScore = (TextView)view.findViewById(R.id.textViewCategScore);
        textCategScore.setText("Passion Score");

        textActual1 = (TextView)view.findViewById(R.id.textViewActual1);
        textActual1.setText(Dashboard.userActivityPassionid1.actualPoints);

        textActual2 = (TextView)view.findViewById(R.id.textViewActual2);
        textActual2.setText(Dashboard.userActivityPassionid2.actualPoints);

        textTarget1 = (TextView)view.findViewById(R.id.textViewTarget1);  // nat change
        textTarget1.setText(Dashboard.userActivityPassionid1.targetPoints);

        textTarget2 = (TextView)view.findViewById(R.id.textViewTarget2);  // nat change
        textTarget2.setText(Dashboard.userActivityPassionid2.targetPoints);

        TextView mondayDate = (TextView)view.findViewById(R.id.TextViewMonday);
        TextView tuesdayDate = (TextView)view.findViewById(R.id.TextViewTuesday);
        TextView  wednesdayDate= (TextView)view.findViewById(R.id.TextViewWednesday);
        TextView thursdayDate = (TextView)view.findViewById(R.id.TextViewThursday);
        TextView fridayDate = (TextView)view.findViewById(R.id.TextViewFriday);
        TextView saturdayDate = (TextView)view.findViewById(R.id.TextViewSaturday);
        TextView sundayDate = (TextView)view.findViewById(R.id.TextViewSunday);

        // nat change
        // Find the components for gaugePassionActivity1
        counterPassionActivity1 = (TextView) view.findViewById(R.id.counter_joy_activity1);
        gaugePassionActivity1 = (ScArcGauge) view.findViewById(R.id.gauge_joy_activity1);
        activity1ScoreStr = Dashboard.userActivityPassionid1.activityScore;
        activity1ScoreInt = Integer.parseInt(activity1ScoreStr); /// Float or double???

        // Set the features stroke cap style to rounded
        gaugePassionActivity1.findFeature(ScArcGauge.BASE_IDENTIFIER)
                .getPainter().setStrokeCap(Paint.Cap.ROUND);
        gaugePassionActivity1.findFeature(ScArcGauge.PROGRESS_IDENTIFIER)
                .getPainter().setStrokeCap(Paint.Cap.ROUND);

        // If you set the value from the xml that not produce an event so I will change the
        // value from code.
        gaugePassionActivity1.setHighValue(activity1ScoreInt);

        // Each time I will change the value I must write it inside the counter text.
        gaugePassionActivity1.setOnEventListener(new ScGauge.OnEventListener() {
            @Override
            public void onValueChange(float lowValue, float highValue) {
                counterPassionActivity1.setText((int) highValue + "%");

            }
        });

        // Find the components for gaugePassionActivity2
        counterPassionActivity2 = (TextView) view.findViewById(R.id.counter_joy_activity2);
        gaugePassionActivity2 = (ScArcGauge) view.findViewById(R.id.gauge_joy_activity2);
        activity2ScoreStr = Dashboard.userActivityPassionid2.activityScore;
        activity2ScoreInt = Integer.parseInt(activity2ScoreStr); /// Float or double???

        // Set the features stroke cap style to rounded
        gaugePassionActivity2.findFeature(ScArcGauge.BASE_IDENTIFIER)
                .getPainter().setStrokeCap(Paint.Cap.ROUND);
        gaugePassionActivity2.findFeature(ScArcGauge.PROGRESS_IDENTIFIER)
                .getPainter().setStrokeCap(Paint.Cap.ROUND);

        // If you set the value from the xml that not produce an event so I will change the
        // value from code.
        gaugePassionActivity2.setHighValue(activity2ScoreInt);

        // Each time I will change the value I must write it inside the counter text.
        gaugePassionActivity2.setOnEventListener(new ScGauge.OnEventListener() {
            @Override
            public void onValueChange(float lowValue, float highValue) {
                counterPassionActivity2.setText((int) highValue + "%");

            }
        });

        // Find the components for gaugePassionScore
        counterPassionScore = (TextView) view.findViewById(R.id.counter_joy_score);
        gaugePassionScore = (ScArcGauge) view.findViewById(R.id.gauge_joy_score);
        passionScoreStr = Dashboard.userPassionSprint.sprintOverallScore;
        passionScoreInt = Integer.parseInt(passionScoreStr);

        // Set the features stroke cap style to rounded
        gaugePassionScore.findFeature(ScArcGauge.BASE_IDENTIFIER)
                .getPainter().setStrokeCap(Paint.Cap.ROUND);
        gaugePassionScore.findFeature(ScArcGauge.PROGRESS_IDENTIFIER)
                .getPainter().setStrokeCap(Paint.Cap.ROUND);

        // If you set the value from the xml that not produce an event so I will change the
        // value from code.
        gaugePassionScore.setHighValue(passionScoreInt);

        // Each time I will change the value I must write it inside the counter text.
        gaugePassionScore.setOnEventListener(new ScGauge.OnEventListener() {
            @Override
            public void onValueChange(float lowValue, float highValue) {
                counterPassionScore.setText((int) highValue + "%");

            }
        });

        // Find the components for gaugeLifeScore
        counterPassionLifeScore = (TextView) view.findViewById(R.id.counter_life_score);
        gaugePassionLifeScore = (ScArcGauge) view.findViewById(R.id.gauge_life_score);
        lifeScorePassionInt = (int)Math.round((((Integer.parseInt(Dashboard.userJoySprint.sprintOverallScore)) +
                (Integer.parseInt(Dashboard.userPassionSprint.sprintOverallScore)) +
                (Integer.parseInt(Dashboard.userContributionSprint.sprintOverallScore))) / 3.0));

        // Set the features stroke cap style to rounded
        gaugePassionLifeScore.findFeature(ScArcGauge.BASE_IDENTIFIER)
                .getPainter().setStrokeCap(Paint.Cap.ROUND);
        gaugePassionLifeScore.findFeature(ScArcGauge.PROGRESS_IDENTIFIER)
                .getPainter().setStrokeCap(Paint.Cap.ROUND);

        // If you set the value from the xml that not produce an event so I will change the
        // value from code.
        gaugePassionLifeScore.setHighValue(lifeScorePassionInt);

        // Each time I will change the value I must write it inside the counter text.
        gaugePassionLifeScore.setOnEventListener(new ScGauge.OnEventListener() {
            @Override
            public void onValueChange(float lowValue, float highValue) {
                counterPassionLifeScore.setText((int) highValue + "%");
            }
        });
        // end nat change



        //Buttons for Activity #1
        final Button btCalendarDay1 = (Button)view.findViewById(R.id.bt1);

        btCalendarDay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay1.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = "0"+temp.substring(1);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay1.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change


                }else{

                    //means the user click this button for first time
                    String modify = "1"+temp.substring(1);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay1.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }

                //11001001100100
            }
        });

        final Button btCalendarDay2 = (Button)view.findViewById(R.id.bt2);
        btCalendarDay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay2.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,1)+"0"+temp.substring(2);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay2.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change


                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,1)+"1"+temp.substring(2);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay2.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }

            }
        });
        final Button btCalendarDay3 = (Button)view.findViewById(R.id.bt3);
        btCalendarDay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay3.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,2)+"0"+temp.substring(3);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay3.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,2)+"1"+temp.substring(3);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay3.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }



            }
        });
        final Button btCalendarDay4 = (Button)view.findViewById(R.id.bt4);
        btCalendarDay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay4.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,3)+"0"+temp.substring(4);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay4.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change


                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,3)+"1"+temp.substring(4);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay4.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }



            }
        });
        final Button btCalendarDay5 = (Button)view.findViewById(R.id.bt5);
        btCalendarDay5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay5.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,4)+"0"+temp.substring(5);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay5.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change


                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,4)+"1"+temp.substring(5);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay5.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }


            }
        });
        final Button btCalendarDay6 = (Button)view.findViewById(R.id.bt6);
        btCalendarDay6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay6.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,5)+"0"+temp.substring(6);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay6.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change


                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,5)+"1"+temp.substring(6);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay6.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }


            }
        });
        final Button btCalendarDay7 = (Button)view.findViewById(R.id.bt7);
        btCalendarDay7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay7.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,6)+"0"+temp.substring(7);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay7.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,6)+"1"+temp.substring(7);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay7.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay8 = (Button)view.findViewById(R.id.bt8);
        btCalendarDay8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay8.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,7)+"0"+temp.substring(8);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay8.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,7)+"1"+temp.substring(8);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay8.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }

            }
        });
        final Button btCalendarDay9 = (Button)view.findViewById(R.id.bt9);
        btCalendarDay9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay9.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,8)+"0"+temp.substring(9);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay9.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,8)+"1"+temp.substring(9);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay9.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay10 = (Button)view.findViewById(R.id.bt10);
        btCalendarDay10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay10.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,9)+"0"+temp.substring(10);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay10.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,9)+"1"+temp.substring(10);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay10.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay11 = (Button)view.findViewById(R.id.bt11);
        btCalendarDay11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay11.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,10)+"0"+temp.substring(11);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay11.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,10)+"1"+temp.substring(11);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay11.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay12 = (Button)view.findViewById(R.id.bt12);
        btCalendarDay12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay12.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,11)+"0"+temp.substring(12);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay12.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,11)+"1"+temp.substring(12);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay12.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay13 = (Button)view.findViewById(R.id.bt13);
        btCalendarDay13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay13.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,12)+"0"+temp.substring(13);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay13.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,12)+"1"+temp.substring(13);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay13.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay14 = (Button)view.findViewById(R.id.bt14);
        btCalendarDay14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay14.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,13)+"0"+temp.substring(14);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay14.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,13)+"1"+temp.substring(14);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay14.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }

            }
        });

        //Remove buttons(15-21) if sprint is 2 weeks
        final Button btCalendarDay15 = (Button)view.findViewById(R.id.bt15);
        btCalendarDay15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay15.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,14)+"0"+temp.substring(15);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay15.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,14)+"1"+temp.substring(15);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay15.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }
            }
        });


        final Button btCalendarDay16 = (Button)view.findViewById(R.id.bt16);
        btCalendarDay16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay16.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //11001001100100
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,15)+"0"+temp.substring(16);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay16.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,15)+"1"+temp.substring(16);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay16.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }
            }
        });


        final Button btCalendarDay17 = (Button)view.findViewById(R.id.bt17);
        btCalendarDay17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay17.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //110010011001001001011
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,16)+"0"+temp.substring(17);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay17.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,16)+"1"+temp.substring(17);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay17.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }
            }
        });


        final Button btCalendarDay18 = (Button)view.findViewById(R.id.bt18);
        btCalendarDay18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay18.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //110010011001001001011
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,17)+"0"+temp.substring(18);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay18.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,17)+"1"+temp.substring(18);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay18.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }
            }
        });


        final Button btCalendarDay19 = (Button)view.findViewById(R.id.bt19);
        btCalendarDay19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay19.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //110010011001001001011
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,18)+"0"+temp.substring(19);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay19.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,18)+"1"+temp.substring(19);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay19.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }

            }
        });


        final Button btCalendarDay20 = (Button)view.findViewById(R.id.bt20);
        btCalendarDay20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay20.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //110010011001001001011
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,19)+"0"+temp.substring(20);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay20.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,19)+"1"+temp.substring(20);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay20.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }

            }
        });


        final Button btCalendarDay21 = (Button)view.findViewById(R.id.bt21);
        btCalendarDay21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid1.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay21.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                //110010011001001001011
                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,20)+"0"+temp.substring(21);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay21.setBackgroundColor(Color.LTGRAY);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify1;

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,20)+"1"+temp.substring(21);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay21.setBackgroundColor(Color.GREEN);
                    Dashboard.userActivityPassionid1.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid1.actualPoints = countOnes+"";
                    setTextView(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct1ProgressIndicator();
                    // end nat change
                }
            }
        });

        // Buttons for Activity #2
        final Button btCalendarDay22 = (Button)view.findViewById(R.id.bt22);
        btCalendarDay22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay22.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = "0"+temp.substring(1);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay22.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change


                }else{

                    //means the user click this button for first time
                    String modify = "1"+temp.substring(1);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay22.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }
            }
        });
        final Button btCalendarDay23 = (Button)view.findViewById(R.id.bt23);
        btCalendarDay23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay23.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,1)+"0"+temp.substring(2);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay23.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change



                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,1)+"1"+temp.substring(2);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay23.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay24 = (Button)view.findViewById(R.id.bt24);
        btCalendarDay24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay24.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,2)+"0"+temp.substring(3);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay24.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change


                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,2)+"1"+temp.substring(3);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay24.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay25 = (Button)view.findViewById(R.id.bt25);
        btCalendarDay25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay25.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,3)+"0"+temp.substring(4);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay25.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,3)+"1"+temp.substring(4);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay25.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }

            }
        });
        final Button btCalendarDay26 = (Button)view.findViewById(R.id.bt26);
        btCalendarDay26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay26.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,4)+"0"+temp.substring(5);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay26.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,4)+"1"+temp.substring(5);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay26.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay27 = (Button)view.findViewById(R.id.bt27);
        btCalendarDay27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay27.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,5)+"0"+temp.substring(6);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay27.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,5)+"1"+temp.substring(6);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay27.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay28 = (Button)view.findViewById(R.id.bt28);
        btCalendarDay28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay28.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,6)+"0"+temp.substring(7);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay28.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,6)+"1"+temp.substring(7);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay28.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay29 = (Button)view.findViewById(R.id.bt29);
        btCalendarDay29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay29.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,7)+"0"+temp.substring(8);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay29.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,7)+"1"+temp.substring(8);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay29.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay30 = (Button)view.findViewById(R.id.bt30);
        btCalendarDay30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay30.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,8)+"0"+temp.substring(9);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay30.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,8)+"1"+temp.substring(9);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay30.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay31 = (Button)view.findViewById(R.id.bt31);
        btCalendarDay31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay31.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,9)+"0"+temp.substring(10);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay31.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,9)+"1"+temp.substring(10);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay31.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay32 = (Button)view.findViewById(R.id.bt32);
        btCalendarDay32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay32.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,10)+"0"+temp.substring(11);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay32.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,10)+"1"+temp.substring(11);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay32.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay33 = (Button)view.findViewById(R.id.bt33);
        btCalendarDay33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay33.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,11)+"0"+temp.substring(12);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay33.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,11)+"1"+temp.substring(12);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay33.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });
        final Button btCalendarDay34 = (Button)view.findViewById(R.id.bt34);
        btCalendarDay34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay34.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,12)+"0"+temp.substring(13);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay34.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,12)+"1"+temp.substring(13);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay34.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });

        final Button btCalendarDay35 = (Button)view.findViewById(R.id.bt35);
        btCalendarDay35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay35.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,13)+"0"+temp.substring(14);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay35.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,13)+"1"+temp.substring(14);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay35.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }

            }
        });

        //Remove buttons (36-42) if sprint is 2 weeks
        final Button btCalendarDay36 = (Button)view.findViewById(R.id.bt36);
        btCalendarDay36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay36.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,14)+"0"+temp.substring(15);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay36.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,14)+"1"+temp.substring(15);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay36.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });


        final Button btCalendarDay37 = (Button)view.findViewById(R.id.bt37);
        btCalendarDay37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay37.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,15)+"0"+temp.substring(16);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay37.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,15)+"1"+temp.substring(16);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay37.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });


        final Button btCalendarDay38 = (Button)view.findViewById(R.id.bt38);
        btCalendarDay38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay38.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,16)+"0"+temp.substring(17);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay38.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,16)+"1"+temp.substring(17);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay38.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }

            }
        });


        final Button btCalendarDay39 = (Button)view.findViewById(R.id.bt39);
        btCalendarDay39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay39.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,17)+"0"+temp.substring(18);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay39.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,17)+"1"+temp.substring(18);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay39.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }

            }
        });


        final Button btCalendarDay40 = (Button)view.findViewById(R.id.bt40);
        btCalendarDay40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay40.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,18)+"0"+temp.substring(19);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay40.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,18)+"1"+temp.substring(19);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay40.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }

            }
        });


        final Button btCalendarDay41 = (Button)view.findViewById(R.id.bt41);
        btCalendarDay41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay41.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,19)+"0"+temp.substring(20);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay41.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,19)+"1"+temp.substring(20);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay41.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });


        final Button btCalendarDay42 = (Button)view.findViewById(R.id.bt42);
        btCalendarDay42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String temp = Dashboard.userActivityPassionid2.sprintDailyPoints;

                int color = 0;
                Drawable backgroundcolor = btCalendarDay42.getBackground();
                if (backgroundcolor instanceof ColorDrawable) {
                    color = ((ColorDrawable)backgroundcolor).getColor();
                }

                if(color == Color.GREEN){

                    //means its already green so the user wants to DESELECT so update the database
                    String modify1 = temp.substring(0,20)+"0"+temp.substring(21);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                    btCalendarDay42.setBackgroundColor(Color.LTGRAY);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                    String Modifystr = modify1+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change

                }else{

                    //means the user click this button for first time
                    String modify = temp.substring(0,20)+"1"+temp.substring(21);

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("sprintDailyPoints").setValue(modify);
                    btCalendarDay42.setBackgroundColor(Color.GREEN);

                    Dashboard.userActivityPassionid2.sprintDailyPoints = modify;

                    String Modifystr = modify+"";

                    int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                    Dashboard.userActivityPassionid2.actualPoints = countOnes+"";
                    setTextView2(countOnes+"");

                    databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                    databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    // nat change
                    setAct2ProgressIndicator();
                    // end nat change
                }
            }
        });


        String datearranger = "";
        int numberOfweeks = Integer.parseInt(Dashboard.userPassionSprint.numberOfWeeks);

        if(numberOfweeks == 1){

            btCalendarDay8.setVisibility(View.GONE);
            btCalendarDay9.setVisibility(View.GONE);
            btCalendarDay10.setVisibility(View.GONE);
            btCalendarDay11.setVisibility(View.GONE);
            btCalendarDay12.setVisibility(View.GONE);
            btCalendarDay13.setVisibility(View.GONE);
            btCalendarDay14.setVisibility(View.GONE);
            btCalendarDay15.setVisibility(View.GONE);
            btCalendarDay16.setVisibility(View.GONE);
            btCalendarDay17.setVisibility(View.GONE);
            btCalendarDay18.setVisibility(View.GONE);
            btCalendarDay19.setVisibility(View.GONE);
            btCalendarDay20.setVisibility(View.GONE);
            btCalendarDay21.setVisibility(View.GONE);

            //update the current value from start of the week
            String tempStartingDate = textStartingDate.getText().toString();
            String tempEndingDate = textEndingDate.getText().toString();
            System.out.println("thisistemp " + tempStartingDate);
            System.out.println("thisistemp2 " + tempEndingDate);



            try {

                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date startDate = formatter.parse(tempStartingDate);
                Date endDate = formatter.parse(tempEndingDate);
                Calendar start = Calendar.getInstance();
                start.setTime(startDate);
                Calendar end = Calendar.getInstance();
                end.setTime(endDate);

                int counter = 0;

                for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                    // Do your job here with `date`.
                    String[] str = date.toString().split(" ");

                    System.out.println("DATEIS2 completedate " + date.toString());

                    if(counter==7){
                        break;
                    }

                    for(int i=0;i<str.length;i++) {
                        System.out.println("DATEIS2 " + str[2]);


                        if(counter==0){
                            ++counter;
                            btCalendarDay1.setText(str[2]);
                            btCalendarDay22.setText(str[2]);
                            datearranger = str[0];
                            break;

                        }else if(counter==1){
                            ++counter;
                            btCalendarDay2.setText(str[2]);
                            btCalendarDay23.setText(str[2]);
                            break;

                        }else if(counter==2){
                            ++counter;
                            btCalendarDay3.setText(str[2]);
                            btCalendarDay24.setText(str[2]);
                            break;

                        }else if(counter==3){

                            ++counter;
                            btCalendarDay4.setText(str[2]);
                            btCalendarDay25.setText(str[2]);
                            break;

                        }else if(counter==4){

                            ++counter;
                            btCalendarDay5.setText(str[2]);
                            btCalendarDay26.setText(str[2]);
                            break;

                        }else if(counter==5){

                            ++counter;
                            btCalendarDay6.setText(str[2]);
                            btCalendarDay27.setText(str[2]);
                            break;

                        }else{

                            ++counter;
                            btCalendarDay7.setText(str[2]);
                            btCalendarDay28.setText(str[2]);
                            break;
                        }


                    }
                }


            }catch(ParseException e){
                e.printStackTrace();
            }


            //put the selected days in dashboard
            String sprintJoyDailypoints = Dashboard.userActivityPassionid1.sprintDailyPoints;

            for(int i=0;i<7;i++){

                if(sprintJoyDailypoints.substring(0,1).equals("1") && i==0){
                    btCalendarDay1.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(1,2).equals("1") && i==1){
                    btCalendarDay2.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(2,3).equals("1") && i==2){
                    btCalendarDay3.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(3,4).equals("1") && i==3){
                    btCalendarDay4.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(4,5).equals("1") && i==4){
                    btCalendarDay5.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(5,6).equals("1") && i==5){
                    btCalendarDay6.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(6,7).equals("1") && i==6){
                    btCalendarDay7.setBackgroundColor(Color.GREEN);
                }

            } //end of for

            //second activity
            btCalendarDay29.setVisibility(View.GONE);
            btCalendarDay30.setVisibility(View.GONE);
            btCalendarDay31.setVisibility(View.GONE);
            btCalendarDay32.setVisibility(View.GONE);
            btCalendarDay33.setVisibility(View.GONE);
            btCalendarDay34.setVisibility(View.GONE);
            btCalendarDay35.setVisibility(View.GONE);
            btCalendarDay36.setVisibility(View.GONE);
            btCalendarDay37.setVisibility(View.GONE);
            btCalendarDay38.setVisibility(View.GONE);
            btCalendarDay39.setVisibility(View.GONE);
            btCalendarDay40.setVisibility(View.GONE);
            btCalendarDay41.setVisibility(View.GONE);
            btCalendarDay42.setVisibility(View.GONE);

            //update the current value from start of the week

            String sprintJoyDailypoints2 = Dashboard.userActivityPassionid2.sprintDailyPoints;

            for(int i=0;i<7;i++){

                if(sprintJoyDailypoints2.substring(0,1).equals("1") && i==0){
                    btCalendarDay22.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(1,2).equals("1") && i==1){
                    btCalendarDay23.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(2,3).equals("1") && i==2){
                    btCalendarDay24.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(3,4).equals("1") && i==3){
                    btCalendarDay25.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(4,5).equals("1") && i==4){
                    btCalendarDay26.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(5,6).equals("1") && i==5){
                    btCalendarDay27.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(6,7).equals("1") && i==6){
                    btCalendarDay28.setBackgroundColor(Color.GREEN);
                }

            } //end of for



        }else if(numberOfweeks==2){

            btCalendarDay15.setVisibility(View.GONE);
            btCalendarDay16.setVisibility(View.GONE);
            btCalendarDay17.setVisibility(View.GONE);
            btCalendarDay18.setVisibility(View.GONE);
            btCalendarDay19.setVisibility(View.GONE);
            btCalendarDay20.setVisibility(View.GONE);
            btCalendarDay21.setVisibility(View.GONE);


            //update the current value from start of the week
            String tempStartingDate = textStartingDate.getText().toString();
            String tempEndingDate = textEndingDate.getText().toString();

            try {

                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date startDate = formatter.parse(tempStartingDate);
                Date endDate = formatter.parse(tempEndingDate);
                Calendar start = Calendar.getInstance();
                start.setTime(startDate);
                Calendar end = Calendar.getInstance();
                end.setTime(endDate);

                int counter = 0;

                for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                    // Do your job here with `date`.
                    String[] str = date.toString().split(" ");

                    if(counter==14){
                        break;
                    }

                    for(int i=0;i<str.length;i++) {



                        if(counter==0){
                            ++counter;
                            btCalendarDay1.setText(str[2]);
                            btCalendarDay22.setText(str[2]);
                            datearranger = str[0];
                            break;

                        }else if(counter==1){
                            ++counter;
                            btCalendarDay2.setText(str[2]);
                            btCalendarDay23.setText(str[2]);
                            break;

                        }else if(counter==2){
                            ++counter;
                            btCalendarDay3.setText(str[2]);
                            btCalendarDay24.setText(str[2]);
                            break;

                        }else if(counter==3){

                            ++counter;
                            btCalendarDay4.setText(str[2]);
                            btCalendarDay25.setText(str[2]);
                            break;

                        }else if(counter==4){

                            ++counter;
                            btCalendarDay5.setText(str[2]);
                            btCalendarDay26.setText(str[2]);
                            break;

                        }else if(counter==5){

                            ++counter;
                            btCalendarDay6.setText(str[2]);
                            btCalendarDay27.setText(str[2]);
                            break;

                        }else if(counter==6){

                            ++counter;
                            btCalendarDay7.setText(str[2]);
                            btCalendarDay28.setText(str[2]);
                            break;

                        }else if(counter==7){

                            ++counter;
                            btCalendarDay8.setText(str[2]);
                            btCalendarDay29.setText(str[2]);
                            break;

                        }else if(counter==8){

                            ++counter;
                            btCalendarDay9.setText(str[2]);
                            btCalendarDay30.setText(str[2]);
                            break;

                        }else if(counter==9){

                            ++counter;
                            btCalendarDay10.setText(str[2]);
                            btCalendarDay31.setText(str[2]);
                            break;

                        }else if(counter==10){

                            ++counter;
                            btCalendarDay11.setText(str[2]);
                            btCalendarDay32.setText(str[2]);
                            break;

                        }else if(counter==11){

                            ++counter;
                            btCalendarDay12.setText(str[2]);
                            btCalendarDay33.setText(str[2]);
                            break;

                        }else if(counter==12){

                            ++counter;
                            btCalendarDay13.setText(str[2]);
                            btCalendarDay34.setText(str[2]);
                            break;

                        }else{

                            ++counter;
                            btCalendarDay14.setText(str[2]);
                            btCalendarDay35.setText(str[2]);
                            break;
                        }


                    }
                }


            }catch(ParseException e){
                e.printStackTrace();
            }



            //put the selected days in dashboard
            String sprintJoyDailypoints = Dashboard.userActivityPassionid1.sprintDailyPoints;
            for(int i=0;i<14;i++){

                if(sprintJoyDailypoints.substring(0,1).equals("1") && i==0){
                    btCalendarDay1.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(1,2).equals("1") && i==1){
                    btCalendarDay2.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(2,3).equals("1") && i==2){
                    btCalendarDay3.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(3,4).equals("1") && i==3){
                    btCalendarDay4.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(4,5).equals("1") && i==4){
                    btCalendarDay5.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(5,6).equals("1") && i==5){
                    btCalendarDay6.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(6,7).equals("1") && i==6){
                    btCalendarDay7.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(7,8).equals("1") && i==7){
                    btCalendarDay8.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(8,9).equals("1") && i==8){
                    btCalendarDay9.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(9,10).equals("1") && i==9){
                    btCalendarDay10.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(10,11).equals("1") && i==10){
                    btCalendarDay11.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(11,12).equals("1") && i==11){
                    btCalendarDay12.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(12,13).equals("1") && i==12){
                    btCalendarDay13.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(13,14).equals("1") && i==13){
                    btCalendarDay14.setBackgroundColor(Color.GREEN);
                }

            } //end of for


            btCalendarDay36.setVisibility(View.GONE);
            btCalendarDay37.setVisibility(View.GONE);
            btCalendarDay38.setVisibility(View.GONE);
            btCalendarDay39.setVisibility(View.GONE);
            btCalendarDay40.setVisibility(View.GONE);
            btCalendarDay41.setVisibility(View.GONE);
            btCalendarDay42.setVisibility(View.GONE);


            //put the selected days in dashboard
            String sprintJoyDailypoints2 = Dashboard.userActivityPassionid2.sprintDailyPoints;

            for(int i=0;i<14;i++){

                if(sprintJoyDailypoints2.substring(0,1).equals("1") && i==0){
                    btCalendarDay22.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(1,2).equals("1") && i==1){
                    btCalendarDay23.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(2,3).equals("1") && i==2){
                    btCalendarDay24.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(3,4).equals("1") && i==3){
                    btCalendarDay25.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(4,5).equals("1") && i==4){
                    btCalendarDay26.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(5,6).equals("1") && i==5){
                    btCalendarDay27.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(6,7).equals("1") && i==6){
                    btCalendarDay28.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(7,8).equals("1") && i==7){
                    btCalendarDay29.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(8,9).equals("1") && i==8){
                    btCalendarDay30.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(9,10).equals("1") && i==9){
                    btCalendarDay31.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(10,11).equals("1") && i==10){
                    btCalendarDay32.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(11,12).equals("1") && i==11){
                    btCalendarDay33.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(12,13).equals("1") && i==12){
                    btCalendarDay34.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(13,14).equals("1") && i==13){
                    btCalendarDay35.setBackgroundColor(Color.GREEN);
                }

            } //end of for



        }else{
            //display all of them

            //update the current value from start of the week
            String tempStartingDate = textStartingDate.getText().toString();
            String tempEndingDate = textEndingDate.getText().toString();

            try {

                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date startDate = formatter.parse(tempStartingDate);
                Date endDate = formatter.parse(tempEndingDate);
                Calendar start = Calendar.getInstance();
                start.setTime(startDate);
                Calendar end = Calendar.getInstance();
                end.setTime(endDate);

                int counter = 0;

                for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                    // Do your job here with `date`.
                    String[] str = date.toString().split(" ");

                    if(counter==21){
                        break;
                    }

                    for(int i=0;i<str.length;i++) {


                        if(counter==0){
                            ++counter;
                            btCalendarDay1.setText(str[2]);
                            btCalendarDay22.setText(str[2]);
                            datearranger = str[0];
                            break;

                        }else if(counter==1){
                            ++counter;
                            btCalendarDay2.setText(str[2]);
                            btCalendarDay23.setText(str[2]);
                            break;

                        }else if(counter==2){
                            ++counter;
                            btCalendarDay3.setText(str[2]);
                            btCalendarDay24.setText(str[2]);
                            break;

                        }else if(counter==3){

                            ++counter;
                            btCalendarDay4.setText(str[2]);
                            btCalendarDay25.setText(str[2]);
                            break;

                        }else if(counter==4){

                            ++counter;
                            btCalendarDay5.setText(str[2]);
                            btCalendarDay26.setText(str[2]);
                            break;

                        }else if(counter==5){

                            ++counter;
                            btCalendarDay6.setText(str[2]);
                            btCalendarDay27.setText(str[2]);
                            break;

                        }else if(counter==6){

                            ++counter;
                            btCalendarDay7.setText(str[2]);
                            btCalendarDay28.setText(str[2]);
                            break;

                        }else if(counter==7){

                            ++counter;
                            btCalendarDay8.setText(str[2]);
                            btCalendarDay29.setText(str[2]);
                            break;

                        }else if(counter==8){

                            ++counter;
                            btCalendarDay9.setText(str[2]);
                            btCalendarDay30.setText(str[2]);
                            break;

                        }else if(counter==9){

                            ++counter;
                            btCalendarDay10.setText(str[2]);
                            btCalendarDay31.setText(str[2]);
                            break;

                        }else if(counter==10){

                            ++counter;
                            btCalendarDay11.setText(str[2]);
                            btCalendarDay32.setText(str[2]);
                            break;

                        }else if(counter==11){

                            ++counter;
                            btCalendarDay12.setText(str[2]);
                            btCalendarDay33.setText(str[2]);
                            break;

                        }else if(counter==12){

                            ++counter;
                            btCalendarDay13.setText(str[2]);
                            btCalendarDay34.setText(str[2]);
                            break;

                        }else if(counter==13){

                            ++counter;
                            btCalendarDay14.setText(str[2]);
                            btCalendarDay35.setText(str[2]);
                            break;

                        }else if(counter==14){

                            ++counter;
                            btCalendarDay15.setText(str[2]);
                            btCalendarDay36.setText(str[2]);
                            break;

                        }else if(counter==15){

                            ++counter;
                            btCalendarDay16.setText(str[2]);
                            btCalendarDay37.setText(str[2]);
                            break;

                        }else if(counter==16){

                            ++counter;
                            btCalendarDay17.setText(str[2]);
                            btCalendarDay38.setText(str[2]);
                            break;

                        }else if(counter==17){

                            ++counter;
                            btCalendarDay18.setText(str[2]);
                            btCalendarDay39.setText(str[2]);
                            break;

                        }else if(counter==18){

                            ++counter;
                            btCalendarDay19.setText(str[2]);
                            btCalendarDay40.setText(str[2]);
                            break;

                        }else if(counter==19){

                            ++counter;
                            btCalendarDay20.setText(str[2]);
                            btCalendarDay41.setText(str[2]);
                            break;

                        }else{

                            ++counter;
                            btCalendarDay21.setText(str[2]);
                            btCalendarDay42.setText(str[2]);
                            break;
                        }

                    }
                }


            }catch(ParseException e){
                e.printStackTrace();
            }


            //put the selected days in dashboard
            String sprintJoyDailypoints = Dashboard.userActivityPassionid1.sprintDailyPoints;

            for(int i=0;i<21;i++){

                if(sprintJoyDailypoints.substring(0,1).equals("1") && i==0){
                    btCalendarDay1.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(1,2).equals("1") && i==1){
                    btCalendarDay2.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(2,3).equals("1") && i==2){
                    btCalendarDay3.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(3,4).equals("1") && i==3){
                    btCalendarDay4.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(4,5).equals("1") && i==4){
                    btCalendarDay5.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(5,6).equals("1") && i==5){
                    btCalendarDay6.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(6,7).equals("1") && i==6){
                    btCalendarDay7.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(7,8).equals("1") && i==7){
                    btCalendarDay8.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(8,9).equals("1") && i==8){
                    btCalendarDay9.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(9,10).equals("1") && i==9){
                    btCalendarDay10.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(10,11).equals("1") && i==10){
                    btCalendarDay11.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(11,12).equals("1") && i==11){
                    btCalendarDay12.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(12,13).equals("1") && i==12){
                    btCalendarDay13.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(13,14).equals("1") && i==13){
                    btCalendarDay14.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(14,15).equals("1") && i==14){
                    btCalendarDay15.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(15,16).equals("1") && i==15){
                    btCalendarDay16.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(16,17).equals("1") && i==16){
                    btCalendarDay17.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(17,18).equals("1") && i==17){
                    btCalendarDay18.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(18,19).equals("1") && i==18){
                    btCalendarDay19.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(19,20).equals("1") && i==19){
                    btCalendarDay20.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints.substring(20,21).equals("1") && i==20){
                    btCalendarDay21.setBackgroundColor(Color.GREEN);
                }

            } //end of for


            //put the selected days in dashboard
            String sprintJoyDailypoints2 = Dashboard.userActivityPassionid2.sprintDailyPoints;

            for(int i=0;i<21;i++){

                if(sprintJoyDailypoints2.substring(0,1).equals("1") && i==0){
                    btCalendarDay22.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(1,2).equals("1") && i==1){
                    btCalendarDay23.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(2,3).equals("1") && i==2){
                    btCalendarDay24.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(3,4).equals("1") && i==3){
                    btCalendarDay25.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(4,5).equals("1") && i==4){
                    btCalendarDay26.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(5,6).equals("1") && i==5){
                    btCalendarDay27.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(6,7).equals("1") && i==6){
                    btCalendarDay28.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(7,8).equals("1") && i==7){
                    btCalendarDay29.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(8,9).equals("1") && i==8){
                    btCalendarDay30.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(9,10).equals("1") && i==9){
                    btCalendarDay31.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(10,11).equals("1") && i==10){
                    btCalendarDay32.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(11,12).equals("1") && i==11){
                    btCalendarDay33.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(12,13).equals("1") && i==12){
                    btCalendarDay34.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(13,14).equals("1") && i==13){
                    btCalendarDay35.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(14,15).equals("1") && i==14){
                    btCalendarDay36.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(15,16).equals("1") && i==15){
                    btCalendarDay37.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(16,17).equals("1") && i==16){
                    btCalendarDay38.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(17,18).equals("1") && i==17){
                    btCalendarDay39.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(18,19).equals("1") && i==18){
                    btCalendarDay40.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(19,20).equals("1") && i==19){
                    btCalendarDay41.setBackgroundColor(Color.GREEN);
                }else if(sprintJoyDailypoints2.substring(20,21).equals("1") && i==20){
                    btCalendarDay42.setBackgroundColor(Color.GREEN);
                }

            } //end of for
        }

        //traverse circularly to rearrange the names of the buttons
        int position = -1;

        if(datearranger.contains("Mon")){

            position = 0;

        }else if(datearranger.contains("Tue")){

            position = 1;

        }else if(datearranger.contains("Wed")){

            position = 2;

        }else if(datearranger.contains("Thu")){

            position = 3;

        }else if(datearranger.contains("Fri")){

            position = 4;

        }else if(datearranger.contains("Sat")){

            position = 5;

        }else {

            position = 6;
        }

        String[] a = {"M","T","W","Th","F","Sa","S"};


        for (int i = 0; i < a.length; i++) {


            if(i==0){

                mondayDate.setText(a[(i + position) % a.length]);

            }else if(i==1){

                tuesdayDate.setText(a[(i + position) % a.length]);

            }else if(i==2){

                wednesdayDate.setText(a[(i + position) % a.length]);

            }else if(i==3){

                thursdayDate.setText(a[(i + position) % a.length]);

            }else if(i==4){

                fridayDate.setText(a[(i + position) % a.length]);

            }else if(i==5){

                saturdayDate.setText(a[(i + position) % a.length]);

            }else {

                sundayDate.setText(a[(i + position) % a.length]);

            }
            //System.out.println(a[(i + position) % a.length]);
        }



        //GOALS (questionaire)

        q1_passion = (EditText)view.findViewById(R.id.q1Goals);
        q2_passion = (EditText)view.findViewById(R.id.q2Goals);
        q3_passion = (EditText)view.findViewById(R.id.q3Goals);
        q4_passion = (EditText)view.findViewById(R.id.q4Goals);


        submitGoalbtn_Passion = (Button)view.findViewById(R.id.submitButtonGoals);

        submitGoalbtn_Passion.setOnClickListener(this);

        return view;

    }

    public void onButtonClick(Button view){
        view.setBackgroundColor(Color.GREEN);
    }

    @Override
    public void onClick(View view) {

        if(view == submitGoalbtn_Passion){
            checkAnswers();
        }

    } //end of onClick



    public void checkAnswers(){

        String answer1 = q1_passion.getText().toString();
        String answer2 = q2_passion.getText().toString();
        String answer3 = q3_passion.getText().toString();
        String answer4 = q4_passion.getText().toString();

        if(answer1.length()==0) {
            Toast.makeText(getActivity().getApplicationContext(), "Your first answer doesn't have any characters, please try again", Toast.LENGTH_LONG).show();
            return;
        }
        if(answer2.length()==0) {
            Toast.makeText(getActivity().getApplicationContext(), "Your second answer doesn't have any characters, please try again", Toast.LENGTH_LONG).show();
            return;
        }
        if(answer3.length()==0) {
            Toast.makeText(getActivity().getApplicationContext(), "Your third answer don't have any characters, please try again", Toast.LENGTH_LONG).show();
            return;
        }
        if(answer4.length()==0) {
            Toast.makeText(getActivity().getApplicationContext(), "Your fourth answer doesn't have any characters, please try again", Toast.LENGTH_LONG).show();
            return;
        }



        if(answer1.length()>180){
            Toast.makeText(getActivity().getApplicationContext(),"Your first answer has " +
                    answer1.length() + "words. Answers must contain at most 180 characters",Toast.LENGTH_LONG).show();
            return;
        } else if(answer2.length()>180){
            Toast.makeText(getActivity().getApplicationContext(),"Your second answer have " +
                    answer2.length() + "words. Answers must contain at most 180 characters",Toast.LENGTH_LONG).show();
            return;
        }else if(answer3.length()>180){
            Toast.makeText(getActivity().getApplicationContext(),"Your third answer have " +
                    answer3.length() + "words. Answers must contain at most 180 characters",Toast.LENGTH_LONG).show();
            return;
        }else if(answer4.length()>180){

            Toast.makeText(getActivity().getApplicationContext(),"Your last answer has " +
                    answer4.length() + "words. Answers must contain at most 180 characters",Toast.LENGTH_LONG).show();
            return;
        }else{

            //good to go save to db
            Toast.makeText(getActivity().getApplicationContext(),"Goal Submitted",Toast.LENGTH_LONG).show();

            databaseUpdateCategories = FirebaseDatabase.getInstance().getReference("Categories");

            databaseUpdateCategories.child(Dashboard.userActivityPassionid1.categoryId).child("PassionSprints").child(Dashboard.sprintPassionid).child("goal1").setValue(q1_passion.getText().toString());
            databaseUpdateCategories.child(Dashboard.userActivityPassionid1.categoryId).child("PassionSprints").child(Dashboard.sprintPassionid).child("goal2").setValue(q2_passion.getText().toString());
            databaseUpdateCategories.child(Dashboard.userActivityPassionid1.categoryId).child("PassionSprints").child(Dashboard.sprintPassionid).child("goal3").setValue(q3_passion.getText().toString());
            databaseUpdateCategories.child(Dashboard.userActivityPassionid1.categoryId).child("PassionSprints").child(Dashboard.sprintPassionid).child("goal4").setValue(q4_passion.getText().toString());


            q1_passion.setText("");
            q2_passion.setText("");
            q3_passion.setText("");
            q4_passion.setText("");
        }
    }

    // nat change
    public void setAct1ProgressIndicator(){

        if ((Integer.parseInt(textActual1.getText().toString())) > (Integer.parseInt(textTarget1.getText().toString()))){
            activity1ScoreInt = 100;
        }
        else {
            activity1ScoreInt = (int)Math.round(((Integer.parseInt(textActual1.getText().toString()) * 100.0) / (Integer.parseInt(textTarget1.getText().toString()))));
        }
        activity1ScoreStr = activity1ScoreInt + "";
        gaugePassionActivity1.setHighValue(activity1ScoreInt);
        Dashboard.userActivityPassionid1.activityScore = activity1ScoreStr; //maintain a copy global instead of accessing to database
        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
        databaseUpdateActivities.child(Dashboard.userActivityPassionid1.activityid).child("activityScore").setValue(activity1ScoreStr);

        passionScoreInt = (int)Math.round(((activity1ScoreInt + activity2ScoreInt)/ 2.0));
        passionScoreStr = passionScoreInt + "";
        gaugePassionScore.setHighValue(passionScoreInt);
        Dashboard.userPassionSprint.sprintOverallScore = passionScoreStr;
        databaseUpdateCategories = FirebaseDatabase.getInstance().getReference("Categories");
        databaseUpdateCategories.child(Dashboard.userPassionSprint.categoryid).child("PassionSprints").child(Dashboard.sprintPassionid).child("sprintOverallScore").setValue(passionScoreStr);

        lifeScorePassionInt = (int)Math.round((((Integer.parseInt(Dashboard.userJoySprint.sprintOverallScore)) +
                (Integer.parseInt(Dashboard.userPassionSprint.sprintOverallScore)) +
                (Integer.parseInt(Dashboard.userContributionSprint.sprintOverallScore))) / 3.0));
        gaugePassionLifeScore.setHighValue(lifeScorePassionInt);

        FragmentJoy.lifeScoreJoyInt = lifeScorePassionInt;
        FragmentJoy.gaugeJoyLifeScore.setHighValue(FragmentJoy.lifeScoreJoyInt);
        FragmentGivingBack.lifeScoreContributionInt = lifeScorePassionInt;
        FragmentGivingBack.gaugeContributionLifeScore.setHighValue(FragmentGivingBack.lifeScoreContributionInt);

    }

    public void setAct2ProgressIndicator(){

        if ((Integer.parseInt(textActual2.getText().toString())) > (Integer.parseInt(textTarget2.getText().toString()))){
            activity2ScoreInt = 100;
        }
        else {
            activity2ScoreInt = (int)Math.round(((Integer.parseInt(textActual2.getText().toString()) * 100.0) / (Integer.parseInt(textTarget2.getText().toString()))));
        }
        activity2ScoreStr = activity2ScoreInt + "";
        gaugePassionActivity2.setHighValue(activity2ScoreInt);
        Dashboard.userActivityPassionid2.activityScore = activity2ScoreStr; //maintain a copy global instead of accessing to database
        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
        databaseUpdateActivities.child(Dashboard.userActivityPassionid2.activityid).child("activityScore").setValue(activity2ScoreStr);

        passionScoreInt = (int)Math.round(((activity1ScoreInt + activity2ScoreInt)/ 2.0));
        passionScoreStr = passionScoreInt + "";
        gaugePassionScore.setHighValue(passionScoreInt);
        Dashboard.userPassionSprint.sprintOverallScore = passionScoreStr;
        databaseUpdateCategories = FirebaseDatabase.getInstance().getReference("Categories");
        databaseUpdateCategories.child(Dashboard.userPassionSprint.categoryid).child("PassionSprints").child(Dashboard.sprintPassionid).child("sprintOverallScore").setValue(passionScoreStr);

        lifeScorePassionInt = (int)Math.round((((Integer.parseInt(Dashboard.userJoySprint.sprintOverallScore)) +
                (Integer.parseInt(Dashboard.userPassionSprint.sprintOverallScore)) +
                (Integer.parseInt(Dashboard.userContributionSprint.sprintOverallScore))) / 3.0));
        gaugePassionLifeScore.setHighValue(lifeScorePassionInt);

        FragmentJoy.lifeScoreJoyInt = lifeScorePassionInt;
        FragmentJoy.gaugeJoyLifeScore.setHighValue(FragmentJoy.lifeScoreJoyInt);
        FragmentGivingBack.lifeScoreContributionInt = lifeScorePassionInt;
        FragmentGivingBack.gaugeContributionLifeScore.setHighValue(FragmentGivingBack.lifeScoreContributionInt);

    }
    // end nat change


    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void setTextView(String value){
        textActual1.setText(value);
    }

    public void setTextView2(String value){
        textActual2.setText(value);
    }

}
