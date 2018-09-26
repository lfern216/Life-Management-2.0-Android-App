package com.example.seniorprojectfall.test;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class viewCoachesActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    String [] elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_coaches);


        ListView list = (ListView) findViewById(R.id.listviewViewCoaches);
        ImageButton backButton = (ImageButton) findViewById(R.id.imageButton_Coaches);

        elements = new String[Dashboard.coachesList.size()];

        for(int i=0;i<elements.length;i++){

            elements[i] = "Full Name: " + Dashboard.coachesList.get(i).firstName + ", " + Dashboard.coachesList.get(i).lastName + "\n"
                    + "Email: " + Dashboard.coachesList.get(i).email + "\n" +
                    "Skills: " + Dashboard.coachesList.get(i).skills + "\n"
                    + "Rating: " + Dashboard.coachesList.get(i).rating + "\n";
        }
//simple_list_item1
        adapter = new ArrayAdapter<String>(this, R.layout.sizeadjuster_coaches, elements);

        list.setAdapter(adapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
