package com.example.seniorprojectfall.test;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lazaro on 9/25/17.
 */

    public class CoverFlowAdapter extends BaseAdapter{

        private ArrayList<Joy> data;  //contains all the activities
        private AppCompatActivity activity;
        static ArrayList<String> n = new ArrayList<>(); //contains 2 activities the user will selects
        int positionSaver = 0;
        static int counter = 0;
        private List<View> clickedActivities;  // holds last 2 activities the user clicked on


        public CoverFlowAdapter(AppCompatActivity context, ArrayList<Joy> objects) {
            this.activity = context;
            this.data = objects;
            clickedActivities = new ArrayList<>();
        }

        @Override
        public int getCount() {

            return data.size();
        }


        @Override
        public Joy getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_flow_view, null, false);

                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.gameImage.setImageResource(data.get(position).getImageSource());
            viewHolder.gameName.setText(data.get(position).getName());

            convertView.setOnClickListener(onClickListener(position));

            return convertView;
        }

        private View.OnClickListener onClickListener(final int position) {
            return new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(activity);
                    dialog.setContentView(R.layout.dialog_joy_info);
                    dialog.setCancelable(true); // dismiss when touching outside
                    dialog.setTitle("Activities Selected");

                    ++counter;

                    if (counter == 1){
                        MainJoyActivity.androidRightArrowButton.setVisibility(View.GONE);
                        if (clickedActivities.size() == 2){
                            clickedActivities.get(0).setBackgroundResource(R.color.colorTransparent);
                            clickedActivities.get(1).setBackgroundResource(R.color.colorTransparent);
                            clickedActivities.clear();
                        }
                        v.setBackgroundResource(R.drawable.image_border);
                        clickedActivities.add(v);

                    }

                    else if (counter == 2) {

                        counter = 0;
                        // clear Joy Activities Selected List
                        n.clear();

                        TextView text1 = (TextView) dialog.findViewById(R.id.name2);
                        text1.setText(getItem(positionSaver).getName());
                        ImageView image1 = (ImageView) dialog.findViewById(R.id.image2);
                        image1.setImageResource(getItem(positionSaver).getImageSource());

                        TextView text2 = (TextView) dialog.findViewById(R.id.name);
                        text2.setText(getItem(position).getName());
                        ImageView image2 = (ImageView) dialog.findViewById(R.id.image);
                        image2.setImageResource(getItem(position).getImageSource());

                        if (text1.getText().equals(text2.getText())) {

                            v.setBackgroundResource(R.color.colorTransparent);
                            clickedActivities.clear();
                            Toast.makeText(activity, "Choose 2 different activities", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        } else {
                            v.setBackgroundResource(R.drawable.image_border);
                            clickedActivities.add(v);
                            // at any moment this list needs to contain only 2 activities
                            n.add(text1.getText().toString());
                            n.add(text2.getText().toString());
                            System.out.println("" + n.size());
                            for (String e : n) {
                                System.out.println(e);
                            }
                            dialog.show();
                            Button dialogbt = (Button) dialog.findViewById(R.id.continueButtonjoyDialog);
                            dialogbt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                    MainJoyActivity.androidRightArrowButton.setVisibility(View.VISIBLE);
                                }
                            });
                        }

                    }

                    positionSaver = position;
                }


            };

        }

        private static class ViewHolder {
            private TextView gameName;
            private ImageView gameImage;

            public ViewHolder(View v) {
                gameImage = (ImageView) v.findViewById(R.id.image);
                gameName = (TextView) v.findViewById(R.id.name);
            }
        }

    }
