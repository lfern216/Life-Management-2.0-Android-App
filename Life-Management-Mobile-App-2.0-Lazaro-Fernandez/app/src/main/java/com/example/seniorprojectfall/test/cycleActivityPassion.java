package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sccomponents.gauges.ScArcGauge;

public class cycleActivityPassion extends AppCompatActivity {

    static String element_static_passion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_passion);
        Intent in = getIntent();


        element_static_passion = in.getExtras().getString("theelementpassion");
        String[] s = element_static_passion.split(" ");

        String s6detail = "";
        String s7detail = "";

        if (s[6].length() == 7) {
            s6detail = "0" + s[6].substring(1, 2) + "/" + s[6].substring(2, 4) + "/" + s[6].substring(6);
        } else {
            s6detail = s[6].substring(0, 2) + "/" + s[6].substring(2, 4) + "/" + s[6].substring(6);
        }


        if (s[2].length() == 7) {
            s7detail = "0" + s[2].substring(1, 2) + "/" + s[2].substring(2, 4) + "/" + s[2].substring(6);
        } else {
            s7detail = s[2].substring(0, 2) + "/" + s[2].substring(2, 4) + "/" + s[2].substring(6);
        }


        ScArcGauge first = (ScArcGauge) findViewById(R.id.gauge_previouscycle_passion);
        TextView actScore = (TextView) findViewById(R.id.counter_previouscycle_passion);
        TextView g = (TextView) findViewById(R.id.textView3passion);


        g.setText("\n\n"
                + " Activity Name: " + "\t" + s[3] + "\n"
                + " Actual Points:  " + "\t " + s[1] + "\n"
                + " Target Points:  " + "\t " + s[5] + "\n"
                + " Starting Date:  " + "\t " + s7detail + "\n"
                + " Ending Date:   " + "\t  " + s6detail);


        int temp = Integer.parseInt(s[0]);

        actScore.setText(temp+"");
        first.setHighValue(temp);

        final Button btn = (Button) findViewById(R.id.buttonleavepassion);
        btn.setText("Return");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setTextColor(Color.WHITE);
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

}