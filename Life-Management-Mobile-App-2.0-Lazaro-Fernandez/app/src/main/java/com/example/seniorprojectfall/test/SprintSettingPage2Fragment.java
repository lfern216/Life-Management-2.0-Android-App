package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Natalia on 10/10/2017.
 */

public class SprintSettingPage2Fragment extends Fragment implements TextWatcher {

    static EditText joyAct1goal;
    static EditText joyAct2goal;
    static EditText passionAct1goal;
    static EditText passionAct2goal;
    static EditText contribAct1goal;
    static EditText contribAct2goal;
    static Button submitSettings;

    private TextView joyAct1;
    private TextView joyAct2;
    private TextView passionAct1;
    private TextView passionAct2;
    private TextView contribAct1;
    private TextView contribAct2;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_sprint_setting_page2,container,false);

        joyAct1 = (TextView)rootView.findViewById(R.id.joyAct1);
        joyAct2 = (TextView)rootView.findViewById(R.id.joyAct2);
        passionAct1 = (TextView)rootView.findViewById(R.id.passionAct1);
        passionAct2 = (TextView)rootView.findViewById(R.id.passionAct2);
        contribAct1 = (TextView)rootView.findViewById(R.id.contributionAct1);
        contribAct2 = (TextView)rootView.findViewById(R.id.contributionAct2);

        joyAct1.setText(CoverFlowAdapter.n.get(0));
        joyAct2.setText(CoverFlowAdapter.n.get(1));
        passionAct1.setText(CoverFlowAdapterPassion.n.get(0));
        passionAct2.setText(CoverFlowAdapterPassion.n.get(1));
        contribAct1.setText(ActGivingbackAdapter.givBackActSelected.get(0));
        contribAct2.setText(ActGivingbackAdapter.givBackActSelected.get(1));


        joyAct1goal = (EditText)rootView.findViewById(R.id.joyAct1Goal);
        joyAct1goal.addTextChangedListener(this);

        joyAct2goal = (EditText)rootView.findViewById(R.id.joyAct2Goal);
        joyAct2goal.setVisibility(View.INVISIBLE);
        joyAct2goal.addTextChangedListener(this);

        passionAct1goal = (EditText)rootView.findViewById(R.id.passionAct1Goal);
        passionAct1goal.setVisibility(View.INVISIBLE);
        passionAct1goal.addTextChangedListener(this);

        passionAct2goal = (EditText)rootView.findViewById(R.id.passionAct2Goal);
        passionAct2goal.setVisibility(View.INVISIBLE);
        passionAct2goal.addTextChangedListener(this);

        contribAct1goal = (EditText)rootView.findViewById(R.id.contributionAct1Goal);
        contribAct1goal.setVisibility(View.INVISIBLE);
        contribAct1goal.addTextChangedListener(this);

        contribAct2goal = (EditText)rootView.findViewById(R.id.contributionAct2Goal);
        contribAct2goal.setVisibility(View.INVISIBLE);
        contribAct2goal.addTextChangedListener(this);

        submitSettings = (Button)rootView.findViewById(R.id.submitActGoals);
        submitSettings.setVisibility(View.INVISIBLE);

        submitSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitSettings.setBackgroundResource(R.color.colorGreen);
                SprintSettingActivity.saveSettings();

                Intent i = new Intent(getActivity(),Dashboard.class);
                i.putExtra("userid",SprintSettingActivity.userId);
                i.putExtra("userNameY",SprintSettingActivity.username);
                i.putExtra("passwordY",SprintSettingActivity.password);

                //Transfer info for JOY
                i.putExtra("joySprintId",SprintSettingActivity.sprintJoyid);

                //Transfer info for act1Joy (newly created)
                i.putExtra("joy_activityid1_activityscore",SprintSettingActivity.act1Joy.getActivityScore());
                i.putExtra("joy_activityid1_actualpoints",SprintSettingActivity.act1Joy.getActualPoints());
                i.putExtra("joy_activityid1_categoryid",SprintSettingActivity.act1Joy.getCategoryId());
                i.putExtra("joy_activityid1_activityname",SprintSettingActivity.act1Joy.getName());
                i.putExtra("joy_activityid1_sprintdailypoints",SprintSettingActivity.act1Joy.getSprintDailyPoints());
                i.putExtra("joy_activityid1_targetpoints",SprintSettingActivity.act1Joy.getTargetPoints());
                i.putExtra("joy_activityid1_userid",SprintSettingActivity.act1Joy.getUserId());
                i.putExtra("joy_activityid1_activityid",SprintSettingActivity.activities1joyId);

                //Transfer info for act2Joy
                i.putExtra("joy_activityid2_activityscore",SprintSettingActivity.act2Joy.getActivityScore());
                i.putExtra("joy_activityid2_actualpoints",SprintSettingActivity.act2Joy.getActualPoints());
                i.putExtra("joy_activityid2_categoryid",SprintSettingActivity.act2Joy.getCategoryId());
                i.putExtra("joy_activityid2_activityname",SprintSettingActivity.act2Joy.getName());
                i.putExtra("joy_activityid2_sprintdailypoints",SprintSettingActivity.act2Joy.getSprintDailyPoints());
                i.putExtra("joy_activityid2_targetpoints",SprintSettingActivity.act2Joy.getTargetPoints());
                i.putExtra("joy_activityid2_userid",SprintSettingActivity.act2Joy.getUserId());
                i.putExtra("joy_activityid2_activityid",SprintSettingActivity.activities2joyId);

                //Transfer info for joyCategory (newly created category/sprint in JoySprints of Categories)
                i.putExtra("joy_userJoySprint_categoryid",SprintSettingActivity.arJoySprint.get("categoryId"));
                i.putExtra("joy_userJoySprint_endingdate",SprintSettingActivity.arJoySprint.get("endingDate"));
                i.putExtra("joy_userJoySprint_goal1",SprintSettingActivity.arJoySprint.get("goal1"));
                i.putExtra("joy_userJoySprint_goal2",SprintSettingActivity.arJoySprint.get("goal2"));
                i.putExtra("joy_userJoySprint_goal3",SprintSettingActivity.arJoySprint.get("goal3"));
                i.putExtra("joy_userJoySprint_goal4",SprintSettingActivity.arJoySprint.get("goal4"));
                i.putExtra("joy_userJoySprint_numberofweeks",SprintSettingActivity.arJoySprint.get("numberOfWeeks"));
                i.putExtra("joy_userJoySprint_sprintactivityid1",SprintSettingActivity.arJoySprint.get("sprintActivityId1"));
                i.putExtra("joy_UserJoySprint_sprintactivityid2",SprintSettingActivity.arJoySprint.get("sprintActivityId2"));
                i.putExtra("joy_UserJoySprint_sprintoverallscore",SprintSettingActivity.arJoySprint.get("sprintOverallScore"));
                i.putExtra("joy_UserJoySprint_startingdate",SprintSettingActivity.arJoySprint.get("startingDate"));
                i.putExtra("joy_UserJoySprint_userid",SprintSettingActivity.arJoySprint.get("userId"));


                //Transfer info for PASSION
                i.putExtra("passionSprintId",SprintSettingActivity.sprintPassionid);

                //Transfer info for passionCategory (newly created category/sprint in PassionSprints of Categories)
                i.putExtra("passion_userPassionSprint_categoryid",SprintSettingActivity.arPassionSprint.get("categoryId"));
                i.putExtra("passion_userPassionSprint_endingdate",SprintSettingActivity.arPassionSprint.get("endingDate"));
                i.putExtra("passion_userPassionSprint_goal1",SprintSettingActivity.arPassionSprint.get("goal1"));
                i.putExtra("passion_userPassionSprint_goal2",SprintSettingActivity.arPassionSprint.get("goal2"));
                i.putExtra("passion_userPassionSprint_goal3",SprintSettingActivity.arPassionSprint.get("goal3"));
                i.putExtra("passion_userPassionSprint_goal4",SprintSettingActivity.arPassionSprint.get("goal4"));
                i.putExtra("passion_userPassionSprint_numberofweeks",SprintSettingActivity.arPassionSprint.get("numberOfWeeks"));
                i.putExtra("passion_userPassionSprint_sprintactivityid1",SprintSettingActivity.arPassionSprint.get("sprintActivityId1"));
                i.putExtra("passion_userPassionSprint_sprintactivityid2",SprintSettingActivity.arPassionSprint.get("sprintActivityId2"));
                i.putExtra("passion_userPassionSprint_sprintoverallscore",SprintSettingActivity.arPassionSprint.get("sprintOverallScore"));
                i.putExtra("passion_userPassionSprint_startingdate",SprintSettingActivity.arPassionSprint.get("startingDate"));
                i.putExtra("passion_userPassionSprint_userid",SprintSettingActivity.arPassionSprint.get("userId"));

                //Transfer info for act1Passion (newly created)
                i.putExtra("passion_activityid1_activityscore",SprintSettingActivity.act1Passion.getActivityScore());
                i.putExtra("passion_activityid1_actualpoints",SprintSettingActivity.act1Passion.getActualPoints());
                i.putExtra("passion_activityid1_categoryid",SprintSettingActivity.act1Passion.getCategoryId());
                i.putExtra("passion_activityid1_activityname",SprintSettingActivity.act1Passion.getName());
                i.putExtra("passion_activityid1_sprintdailypoints",SprintSettingActivity.act1Passion.getSprintDailyPoints());
                i.putExtra("passion_activityid1_targetpoints",SprintSettingActivity.act1Passion.getTargetPoints());
                i.putExtra("passion_activityid1_userid",SprintSettingActivity.act1Passion.getUserId());
                i.putExtra("passion_activityid1_activityid",SprintSettingActivity.activities1passionId);

                //Transfer info for act2Passion (newly created)
                i.putExtra("passion_activityid2_activityscore",SprintSettingActivity.act2Passion.getActivityScore());
                i.putExtra("passion_activityid2_actualpoints",SprintSettingActivity.act2Passion.getActualPoints());
                i.putExtra("passion_activityid2_categoryid",SprintSettingActivity.act2Passion.getCategoryId());
                i.putExtra("passion_activityid2_activityname",SprintSettingActivity.act2Passion.getName());
                i.putExtra("passion_activityid2_sprintdailypoints",SprintSettingActivity.act2Passion.getSprintDailyPoints());
                i.putExtra("passion_activityid2_targetpoints",SprintSettingActivity.act2Passion.getTargetPoints());
                i.putExtra("passion_activityid2_userid",SprintSettingActivity.act2Passion.getUserId());
                i.putExtra("passion_activityid2_activityid",SprintSettingActivity.activities2passionId);


                //Transfer info for CONTRIBUTION
                i.putExtra("contributionSprintId",SprintSettingActivity.sprintContribid);

                //Transfer info for contributionCategory (newly created category/sprint in ContributionSprints of Categories)
                i.putExtra("contribution_userContributionSprint_categoryid",SprintSettingActivity.arContrSprint.get("categoryId"));
                i.putExtra("contribution_userContributionSprint_endingdate",SprintSettingActivity.arContrSprint.get("endingDate"));
                i.putExtra("contribution_userContributionSprint_goal1",SprintSettingActivity.arContrSprint.get("goal1"));
                i.putExtra("contribution_userContributionSprint_goal2",SprintSettingActivity.arContrSprint.get("goal2"));
                i.putExtra("contribution_userContributionSprint_goal3",SprintSettingActivity.arContrSprint.get("goal3"));
                i.putExtra("contribution_userContributionSprint_goal4",SprintSettingActivity.arContrSprint.get("goal4"));
                i.putExtra("contribution_userContributionSprint_numberofweeks",SprintSettingActivity.arContrSprint.get("numberOfWeeks"));
                i.putExtra("contribution_userContributionSprint_sprintactivityid1",SprintSettingActivity.arContrSprint.get("sprintActivityId1"));
                i.putExtra("contribution_userContributionSprint_sprintactivityid2",SprintSettingActivity.arContrSprint.get("sprintActivityId2"));
                i.putExtra("contribution_userContributionSprint_sprintoverallscore",SprintSettingActivity.arContrSprint.get("sprintOverallScore"));
                i.putExtra("contribution_userContributionSprint_startingdate",SprintSettingActivity.arContrSprint.get("startingDate"));
                i.putExtra("contribution_userContributionSprint_userid",SprintSettingActivity.arContrSprint.get("userId"));

                //Transfer info for act1Contrib (newly created)
                i.putExtra("contribution_activityid1_activityscore",SprintSettingActivity.act1Contrib.getActivityScore());
                i.putExtra("contribution_activityid1_actualpoints",SprintSettingActivity.act1Contrib.getActualPoints());
                i.putExtra("contribution_activityid1_categoryid",SprintSettingActivity.act1Contrib.getCategoryId());
                i.putExtra("contribution_activityid1_activityname",SprintSettingActivity.act1Contrib.getName());
                i.putExtra("contribution_activityid1_sprintdailypoints",SprintSettingActivity.act1Contrib.getSprintDailyPoints());
                i.putExtra("contribution_activityid1_targetpoints",SprintSettingActivity.act1Contrib.getTargetPoints());
                i.putExtra("contribution_activityid1_userid",SprintSettingActivity.act1Contrib.getUserId());
                i.putExtra("contribution_activityid1_activityid",SprintSettingActivity.activities1contribId);

                //Transfer info for act2Contrib (newly created)
                i.putExtra("contribution_activityid2_activityscore",SprintSettingActivity.act2Contrib.getActivityScore());
                i.putExtra("contribution_activityid2_actualpoints",SprintSettingActivity.act2Contrib.getActualPoints());
                i.putExtra("contribution_activityid2_categoryid",SprintSettingActivity.act2Contrib.getCategoryId());
                i.putExtra("contribution_activityid2_activityname",SprintSettingActivity.act2Contrib.getName());
                i.putExtra("contribution_activityid2_sprintdailypoints",SprintSettingActivity.act2Contrib.getSprintDailyPoints());
                i.putExtra("contribution_activityid2_targetpoints",SprintSettingActivity.act2Contrib.getTargetPoints());
                i.putExtra("contribution_activityid2_userid",SprintSettingActivity.act2Contrib.getUserId());
                i.putExtra("contribution_activityid2_activityid",SprintSettingActivity.activities2contribId);

                //profile image
                i.putExtra("profileImageName",SprintSettingActivity.userProfileImageName);


                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("mylist", SprintSettingActivity.listUsers);

                bundle.putParcelableArrayList("allActivities", SprintSettingActivity.allActivities);
                bundle.putParcelableArrayList("userActivitiesAllList",SprintSettingActivity.userActivitiesAll);
                bundle.putParcelableArrayList("coachesList",SprintSettingActivity.coachList);

                //JOY
                bundle.putParcelableArrayList("categoriesJoyCategories",SprintSettingActivity.currentJoyCategories);
                bundle.putParcelableArrayList("userJoysprintHelperList",SprintSettingActivity.userJoysprintsHelper);
                bundle.putParcelableArrayList("activitiesJOYPrevious",SprintSettingActivity.activitiesjoyPrevious);

                //PASSION
                bundle.putParcelableArrayList("categoriesPassionCategories",SprintSettingActivity.currentPassionCategories);
                bundle.putParcelableArrayList("userPassionsprintHelperList",SprintSettingActivity.userPassionSprintHelper);
                bundle.putParcelableArrayList("activitiesPassionPrevious",SprintSettingActivity.activitiesPassionPrevious);


                //GIVING BACK
                bundle.putParcelableArrayList("categoriesContributionCategories",SprintSettingActivity.currentContributionCategories);
                bundle.putParcelableArrayList("userContributionsprintHelperList",SprintSettingActivity.userContributionSprintHelper);
                bundle.putParcelableArrayList("activitiesContributionPrevious",SprintSettingActivity.activitiesContributionPrevious);

                //bundle.putParcelableArrayList("currentjoyactivitylist", SprintSettingActivity.currentJoyActivities); //total activities (needed for previous cycle)
                i.putExtras(bundle);

                startActivity(i);

            }
        });





        return rootView;

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count){

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after){

    }

    //user input validations
    @Override
    public void afterTextChanged(Editable s) {

        if (s == joyAct1goal.getEditableText()) {
            if (TextUtils.isEmpty(s)) {
                Toast.makeText(getActivity(), "Please enter activity goal number", Toast.LENGTH_LONG).show();
                // Block other textviews
                joyAct2goal.setVisibility(View.INVISIBLE);

            } else {
                long num = Long.parseLong(s.toString());
                if (num < 0 || num > SprintSettingPageFragment.sprintPeriodInDays) {
                    Toast.makeText(getActivity(), "Goal number must be positive and < or = number of days in selected sprint period", Toast.LENGTH_LONG).show();
                    s.clear();
                    // Block other textviews
                } else {
                    // Unblock other textviews
                    joyAct2goal.setVisibility(View.VISIBLE);
                }
            }


        }
        else if (s == joyAct2goal.getEditableText()) {
            if (TextUtils.isEmpty(s)) {
                Toast.makeText(getActivity(), "Please enter activity goal number", Toast.LENGTH_LONG).show();
                // Block other textviews
                passionAct1goal.setVisibility(View.INVISIBLE);

            } else {
                long num = Long.parseLong(s.toString());
                if (num < 0 || num > SprintSettingPageFragment.sprintPeriodInDays) {
                    Toast.makeText(getActivity(), "Goal number must be positive and < or = number of days in selected sprint period", Toast.LENGTH_LONG).show();
                    s.clear();
                    // Block other textviews
                } else {
                    // Unblock other textviews
                    passionAct1goal.setVisibility(View.VISIBLE);
                }
            }


        }
        else if (s == passionAct1goal.getEditableText()) {
            if (TextUtils.isEmpty(s)) {
                Toast.makeText(getActivity(), "Please enter activity goal number", Toast.LENGTH_LONG).show();
                // Block other textviews
                passionAct2goal.setVisibility(View.INVISIBLE);

            } else {
                long num = Long.parseLong(s.toString());
                if (num < 0 || num > SprintSettingPageFragment.sprintPeriodInDays) {
                    Toast.makeText(getActivity(), "Goal number must be positive and < or = number of days in selected sprint period", Toast.LENGTH_LONG).show();
                    s.clear();
                    // Block other textviews
                } else {
                    // Unblock other textviews
                    passionAct2goal.setVisibility(View.VISIBLE);
                }
            }


        }
        else if (s == passionAct2goal.getEditableText()) {
            if (TextUtils.isEmpty(s)) {
                Toast.makeText(getActivity(), "Please enter activity goal number", Toast.LENGTH_LONG).show();
                // Block other textviews
                contribAct1goal.setVisibility(View.INVISIBLE);

            } else {
                long num = Long.parseLong(s.toString());
                if (num < 0 || num > SprintSettingPageFragment.sprintPeriodInDays) {
                    Toast.makeText(getActivity(), "Goal number must be positive and < or = number of days in selected sprint period", Toast.LENGTH_LONG).show();
                    s.clear();
                    // Block other textviews
                } else {
                    // Unblock other textviews
                    contribAct1goal.setVisibility(View.VISIBLE);
                }
            }


        }
        else if (s == contribAct1goal.getEditableText()) {
            if (TextUtils.isEmpty(s)) {
                Toast.makeText(getActivity(), "Please enter activity goal number", Toast.LENGTH_LONG).show();
                // Block other textviews
                contribAct2goal.setVisibility(View.INVISIBLE);

            } else {
                long num = Long.parseLong(s.toString());
                if (num < 0 || num > SprintSettingPageFragment.sprintPeriodInDays) {
                    Toast.makeText(getActivity(), "Goal number must be positive and < or = number of days in selected sprint period", Toast.LENGTH_LONG).show();
                    s.clear();
                    // Block other textviews
                } else {
                    // Unblock other textviews
                    contribAct2goal.setVisibility(View.VISIBLE);
                }
            }


        }
        else if (s == contribAct2goal.getEditableText()) {
            if (TextUtils.isEmpty(s)) {
                Toast.makeText(getActivity(), "Please enter activity goal number", Toast.LENGTH_LONG).show();
                // Block other textviews
                submitSettings.setVisibility(View.INVISIBLE);

            } else {
                long num = Long.parseLong(s.toString());
                if (num < 0 || num > SprintSettingPageFragment.sprintPeriodInDays) {
                    Toast.makeText(getActivity(), "Goal number must be positive and < or = number of days in selected sprint period", Toast.LENGTH_LONG).show();
                    s.clear();
                    // Block other textviews
                } else {
                    joyAct1goal.setKeyListener(null);
                    joyAct2goal.setKeyListener(null);
                    passionAct1goal.setKeyListener(null);
                    passionAct2goal.setKeyListener(null);
                    contribAct1goal.setKeyListener(null);
                    // Unblock submit button
                    submitSettings.setVisibility(View.VISIBLE);
                }
            }


        }
    }


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

}
