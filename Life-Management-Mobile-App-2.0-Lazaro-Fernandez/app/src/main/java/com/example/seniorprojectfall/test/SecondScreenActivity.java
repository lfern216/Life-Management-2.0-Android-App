package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.PopupWindow;
import android.view.ViewGroup;
import android.view.MotionEvent;
import android.app.Dialog;
import android.widget.ImageButton;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import java.lang.String;




public class SecondScreenActivity extends AppCompatActivity {

    //buttons for next/previous
    ImageButton nextBtn;

    //pop window variables(first button)
    //private Button btn1;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater; //allows loading a new layout inside the pop up window
    private RelativeLayout relativeLayout;


    //buttons (pop up)
    Button b1, b2,b3,b4,b5,b6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        nextBtn = (ImageButton) findViewById(R.id.imageButtonscreen2);

        //next button actions
        nextBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                Intent i = new Intent(SecondScreenActivity.this,ThirdScreenActivity.class);
                startActivity(i);
            }
        });

        //zoom animation for next (screen2)
        Animation zoomAnimation = AnimationUtils.loadAnimation(this,R.anim.zoom);
        nextBtn.startAnimation(zoomAnimation);

        //pop up activity (first button)
        b1 = (Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondScreenActivity.this);

                View view = LayoutInflater.from(SecondScreenActivity.this).inflate(R.layout.popup1, null);

                TextView title = (TextView) view.findViewById(R.id.title);
                ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);

                imageButton.setImageResource(R.drawable.popup1);

                builder.setPositiveButton(Html.fromHtml("<font color='#0099cc'>RETURN</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(SecondScreenActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setView(view);
                builder.show();

            }
        });

        //pop up activity (second button)

        b2 = (Button) findViewById(R.id.button2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondScreenActivity.this);

                View view = LayoutInflater.from(SecondScreenActivity.this).inflate(R.layout.popup2, null);

                TextView title = (TextView) view.findViewById(R.id.title);
                ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);

                imageButton.setImageResource(R.drawable.popup2);

                builder.setPositiveButton(Html.fromHtml("<font color='#FB8303'>RETURN</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(SecondScreenActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setView(view);
                builder.show();

            }
        });

        //pop up activity (third button)

        b3 = (Button) findViewById(R.id.button3);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondScreenActivity.this);

                View view = LayoutInflater.from(SecondScreenActivity.this).inflate(R.layout.popup3, null);

                TextView title = (TextView) view.findViewById(R.id.title);
                ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);

                imageButton.setImageResource(R.drawable.popup3);

                builder.setPositiveButton(Html.fromHtml("<font color='#FB0303'>RETURN</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(SecondScreenActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setView(view);
                builder.show();

            }
        });

        //pop up activity (fourth button)

        b4 = (Button) findViewById(R.id.button13);

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondScreenActivity.this);

                View view = LayoutInflater.from(SecondScreenActivity.this).inflate(R.layout.popup4, null);

                TextView title = (TextView) view.findViewById(R.id.title);
                ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);

                imageButton.setImageResource(R.drawable.popup4);

                builder.setPositiveButton(Html.fromHtml("<font color='#FB0377'>RETURN</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(SecondScreenActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setView(view);
                builder.show();

            }
        });

        //pop up activity (fifth button)

        b5 = (Button) findViewById(R.id.button14);

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondScreenActivity.this);

                View view = LayoutInflater.from(SecondScreenActivity.this).inflate(R.layout.popup5, null);

                TextView title = (TextView) view.findViewById(R.id.title);
                ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);

                imageButton.setImageResource(R.drawable.popup5);

                builder.setPositiveButton(Html.fromHtml("<font color='#58984A'>RETURN</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(SecondScreenActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setView(view);
                builder.show();

            }
        });

        //pop up activity (six button)

        b6 = (Button) findViewById(R.id.button15);

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondScreenActivity.this);

                View view = LayoutInflater.from(SecondScreenActivity.this).inflate(R.layout.popup6, null);

                TextView title = (TextView) view.findViewById(R.id.title);
                ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);

                imageButton.setImageResource(R.drawable.popup6);

                builder.setPositiveButton(Html.fromHtml("<font color='#9B04FD'>RETURN</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(SecondScreenActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setView(view);
                builder.show();

            }
        });



    } //end of method create

}
