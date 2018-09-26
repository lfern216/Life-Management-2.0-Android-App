package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.*;


public class LoginActivity extends AppCompatActivity {


    //GENERAL
    EditText username,password;
    Button signIn, registerUser;
    ArrayList<ActivitiesSprint> allActivities;
    String userProfileImageName = "";
    Map<String,String> profileImagesTotal;

    //JOY variables
    ArrayList<Category> userJoysprintsHelper;
    ArrayList<User> listUsers;
    ArrayList<Category> currentJoyCategories;
    ArrayList<ActivitiesSprint> activitiesjoyPrevious;
    String userId;
    String sprintjoyid;
    String currentCategoryuserId; //temporary variable holding the current category user id

    //COACHES
    ArrayList<Coach> coachList;

    //PASSION variables
    ArrayList<Category> userPassionSprintHelper;
    ArrayList<Category> currentPassionCategories;
    ArrayList<ActivitiesSprint> activitiesPassionPrevious;
    Map<String,String> passionIdStartingDateMap;
    String sprintpassionid;
    String currentPassionCategoryId; //temporary variable holding the current category user id

    //GIVING BACK
    ArrayList<Category> userContributionSprintHelper;
    ArrayList<Category> currentContributionCategories;
    Map<String,String> contributionIdStartingDateMap;
    ArrayList<ActivitiesSprint> activitiesContributionPrevious;
    String sprintcontributionid;
    String currentContributionCategoryId; //temporary variable holding the current category user id

    //DATABASE
    DatabaseReference databaseCategories;
    DatabaseReference databaseUsers; //our database reference object
    DatabaseReference databaseActivities;
    DatabaseReference databaseProfileImages;
    DatabaseReference databaseCoaches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //COACH
        coachList = new ArrayList<>();

        //JOY INITIALIZATIONs
        userJoysprintsHelper = new ArrayList<>();
        allActivities = new ArrayList<>();
        listUsers = new ArrayList<>();
        currentJoyCategories = new ArrayList<>();
        activitiesjoyPrevious = new ArrayList<>();

        //PASSION INITIALIZATIONS
        userPassionSprintHelper = new ArrayList<>();
        passionIdStartingDateMap = new TreeMap<>();
        currentPassionCategories = new ArrayList<>();
        activitiesPassionPrevious = new ArrayList<>();

        //GIVING BACK INITIALIZATIONS
        userContributionSprintHelper = new ArrayList<>();
        contributionIdStartingDateMap = new TreeMap<>();
        currentContributionCategories = new ArrayList<>();
        activitiesContributionPrevious = new ArrayList<>();

        //getting the reference of artists node
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        databaseCoaches = FirebaseDatabase.getInstance().getReference("Coaches");
        databaseActivities = FirebaseDatabase.getInstance().getReference("Activities");
        databaseCategories = FirebaseDatabase.getInstance().getReference("Categories");
        databaseProfileImages = FirebaseDatabase.getInstance().getReference("ProfileImgs");
        profileImagesTotal = new TreeMap<>();

        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);

        signIn = (Button) findViewById(R.id.buttonSignInLogin);
        registerUser = (Button) findViewById(R.id.buttonRegisterUserLogin);

        //adding an onclicklistener to button
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //the method is defined below
                //this method is actually performing the write operation
                loginUser();
            }
        });


        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser.setTextColor(Color.WHITE);
                Intent i = new Intent(LoginActivity.this,CreateUserActivity.class);
                finish();
                startActivity(i);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                listUsers.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist

                    User users = postSnapshot.getValue(User.class);
                    //adding artist to the list
                    listUsers.add(users);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //coach

        databaseCoaches.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist

                    String str = postSnapshot.getValue().toString();

                    int skills_pos = str.indexOf("skills=");
                    int firstname_pos = str.indexOf("firstName=");
                    int lastname_pos = str.indexOf("lastName=");
                    int rating_pos = str.indexOf("rating=");
                    int id_pos = str.indexOf("id=");
                    int email_pos = str.indexOf("email=");

                    String temp1 = str.substring((skills_pos+7),(firstname_pos-2));
                    String temp2 = str.substring((firstname_pos+10),(lastname_pos-2));
                    String temp3 = str.substring((lastname_pos+9),(rating_pos-2));
                    String temp4 = str.substring((rating_pos+7),(id_pos-2));
                    String temp5 = str.substring((id_pos+3),(email_pos-2));
                    String temp6 =  str.substring((email_pos+6),(str.length()-1));

                    coachList.add(new Coach(temp1,temp2,temp3,temp4,temp5,temp6));

                    //adding artist to the list
                    //coachList.add(coach);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        databaseCategories.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) { //id

                    if(categorySnapshot.getValue() == null){
                        break;
                    }

                    String[] separator = categorySnapshot.getValue().toString().split(" ");

                    for (int i = separator.length - 1; i >= 0; i--) {

                        if (separator[i].length() > 15 && (separator[i].contains("userId"))) {
                            //get the userId
                            String temp = separator[i];
                            int g = temp.indexOf("=");
                            ++g;

                            currentCategoryuserId = temp.substring(g, temp.length() - 1);

                            DataSnapshot activitiesSnapshottemp = categorySnapshot.child("JoySprints");

                            if(activitiesSnapshottemp.getValue() == null){
                                break;
                            }

                            String[] f = activitiesSnapshottemp.getValue().toString().split("=");

                            String temporary = f[0];
                            String tempId = temporary.substring(1);  //joysprint unique id

                            for (DataSnapshot activitySnapshot2 : activitiesSnapshottemp.getChildren()) { //ids

                                if(activitySnapshot2.getValue() == null){
                                    break;
                                }

                                String[] tempArray = new String[(int) activitySnapshot2.getChildrenCount()];
                                int k = 0;
                                for (DataSnapshot activitySnapshot3 : activitySnapshot2.getChildren()) { //branch

                                    if(activitySnapshot3.getValue() == null){
                                        break;
                                    }

                                    tempArray[k] = activitySnapshot3.getValue().toString();
                                    k++;
                                }

                                String JoySprintId = activitySnapshot2.getKey();

                                //currentJoycategories will have (endingDate, goal1, goal2, goal3, goal4, NumOfWeeks, sprintActid1, sprintactid2, overallscore, startingDate) for that logged-in user
                                currentJoyCategories.add(new Category(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5], tempArray[6],
                                        tempArray[7], tempArray[8], tempArray[9], tempArray[10], currentCategoryuserId,JoySprintId));
                                //System.out.println("hey3 " + activitySnapshot3.getKey()+": "+ activitySnapshot3.getValue(String.class));
                                //returning = tempArray[5];
                            }
                        }
                        break;
                    } //end of for (outer)


                    //PASSION

                    for (int i = separator.length - 1; i >= 0; i--) {

                        if (separator[i].length() > 15 && (separator[i].contains("userId"))) {
                            //get the userId
                            String temp = separator[i];
                            int g = temp.indexOf("=");
                            ++g;


                            currentPassionCategoryId = temp.substring(g, temp.length() - 1);
                            DataSnapshot activitiesSnapshottemp = categorySnapshot.child("PassionSprints");

                            if(activitiesSnapshottemp.getValue() == null){
                                break;
                            }

                            String[] f = activitiesSnapshottemp.getValue().toString().split("=");

                            String temporary = f[0];
                            String tempId = temporary.substring(1);  //joysprint unique id


                            for (DataSnapshot activitySnapshot2 : activitiesSnapshottemp.getChildren()) { //ids

                                if(activitySnapshot2.getValue() == null){
                                    break;
                                }

                                String[] tempArray = new String[(int) activitySnapshot2.getChildrenCount()];
                                int k = 0;
                                for (DataSnapshot activitySnapshot3 : activitySnapshot2.getChildren()) { //branch

                                    if(activitySnapshot3.getValue() == null){
                                        break;
                                    }

                                    tempArray[k] = activitySnapshot3.getValue().toString();
                                    k++;

                                }

                                String passionSprintid = activitySnapshot2.getKey();
                                //currentJoycategories will have (endingDate, goal1, goal2, goal3, goal4, NumOfWeeks, sprintActid1, sprintactid2, overallscore, startingDate) for that logged-in user
                                currentPassionCategories.add(new Category(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5], tempArray[6],
                                        tempArray[7], tempArray[8], tempArray[9], tempArray[10], currentPassionCategoryId,passionSprintid));
                                //System.out.println("hey3 " + activitySnapshot3.getKey()+": "+ activitySnapshot3.getValue(String.class));
                                //returning = tempArray[5];
                            }
                        }
                        break;
                    } //end of for outer (PASSION)


                    // GIVING BACK

                    for (int i = separator.length - 1; i >= 0; i--) {

                        if (separator[i].length() > 15 && (separator[i].contains("userId"))) {
                            //get the userId
                            String temp = separator[i];
                            int g = temp.indexOf("=");
                            ++g;

                            currentContributionCategoryId = temp.substring(g, temp.length() - 1);

                            DataSnapshot activitiesSnapshottemp = categorySnapshot.child("ContributionSprints");

                            if(activitiesSnapshottemp.getValue()== null){
                                break;
                            }

                            String[] f = activitiesSnapshottemp.getValue().toString().split("=");
                            String temporary = f[0];
                            String tempId = temporary.substring(1);  //joysprint unique id


                            for (DataSnapshot activitySnapshot2 : activitiesSnapshottemp.getChildren()) { //ids

                                if(activitySnapshot2.getValue() == null){
                                    break;
                                }

                                String[] tempArray = new String[(int) activitySnapshot2.getChildrenCount()];
                                int k = 0;
                                for (DataSnapshot activitySnapshot3 : activitySnapshot2.getChildren()) { //branch

                                    if(activitySnapshot3.getValue() == null){
                                        break;
                                    }

                                    tempArray[k] = activitySnapshot3.getValue().toString();
                                    k++;
                                }

                                String contributionSprintid = activitySnapshot2.getKey();
                                //currentJoycategories will have (endingDate, goal1, goal2, goal3, goal4, NumOfWeeks, sprintActid1, sprintactid2, overallscore, startingDate) for that logged-in user
                                currentContributionCategories.add(new Category(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5], tempArray[6],
                                        tempArray[7], tempArray[8], tempArray[9], tempArray[10], currentContributionCategoryId,contributionSprintid));
                                //System.out.println("hey3 " + activitySnapshot3.getKey()+": "+ activitySnapshot3.getValue(String.class));
                                //returning = tempArray[5];
                            }
                        }
                        break;
                    }
                } //end of for
            } //end of datachangeMethod

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseProfileImages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot activitySnapshot : dataSnapshot.getChildren()) { //id
                    int temp = activitySnapshot.getValue().toString().length();
                    profileImagesTotal.put(activitySnapshot.getKey(),activitySnapshot.getValue().toString().substring(6,temp-1));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //retrieve activities for JOY (for that user)
        databaseActivities.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot activitySnapshot : dataSnapshot.getChildren()) { //id

                    String activityId = activitySnapshot.getKey();
                    String temp[] = new String[(int) activitySnapshot.getChildrenCount()];
                    int i = 0;
                    for (DataSnapshot activitySnapshot2 : activitySnapshot.getChildren()) { //ids

                        temp[i] = activitySnapshot2.getValue().toString();
                        ++i;
                    }
                    allActivities.add(new ActivitiesSprint(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6],activityId));
                }
            } //end of ondatachange method

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    } //end of onStart method


    private void loginUser() {
        //getting the values to save
        String name = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        boolean isValid = false;
        for(User f: listUsers){

            if(f.getUsername().equals(name) && f.getPassword().equals(pass)){
                //then is an existing user, so login

                Toast.makeText(this, "Logged In ", Toast.LENGTH_LONG).show();
                userId = f.id;
                isValid = true;

            }
        } //end of for

        if(isValid) {


            signIn.setTextColor(Color.WHITE);

            for(Map.Entry g: profileImagesTotal.entrySet()){

                if(g.getKey().toString().contains(userId)){
                    userProfileImageName = g.getValue().toString();
                }
            }

            ArrayList<ActivitiesSprint> userActivitiesAll = new ArrayList<>();

            for (int i = 0; i < allActivities.size(); i++) {


                if (allActivities.get(i).userId.contains(userId)) {
                    //get all the activities for that user

                    userActivitiesAll.add(new ActivitiesSprint(allActivities.get(i).activityScore,
                            allActivities.get(i).actualPoints, allActivities.get(i).categoryId,
                            allActivities.get(i).name, allActivities.get(i).sprintDailyPoints,
                            allActivities.get(i).targetPoints, allActivities.get(i).userId, allActivities.get(i).activityid));
                }
            }


            List<String> tempList = new ArrayList<>(); //list containing all the sprintActivities of the user

            //JOY
            for (int i = 0; i < currentJoyCategories.size(); i++) {


                if (userId.contains(currentJoyCategories.get(i).userId)) {

                    tempList.add(currentJoyCategories.get(i).sprintActivityid1 + " " + currentJoyCategories.get(i).startingDate + " " + currentJoyCategories.get(i).endingDate);
                    tempList.add(currentJoyCategories.get(i).sprintActivityid2 + " " + currentJoyCategories.get(i).startingDate + " " + currentJoyCategories.get(i).endingDate);


                    userJoysprintsHelper.add(new Category(currentJoyCategories.get(i).categoryid, currentJoyCategories.get(i).endingDate,
                            currentJoyCategories.get(i).goal1, currentJoyCategories.get(i).goal2, currentJoyCategories.get(i).goal3,
                            currentJoyCategories.get(i).goal4, currentJoyCategories.get(i).numberOfWeeks, currentJoyCategories.get(i).sprintActivityid1,
                            currentJoyCategories.get(i).sprintActivityid2, currentJoyCategories.get(i).sprintOverallScore,
                            currentJoyCategories.get(i).startingDate, currentJoyCategories.get(i).userId,currentJoyCategories.get(i).sprintid));
                }
            }

            //JOY
            for (int i = 0; i < tempList.size(); i++) {

                for (int k = 0; k < userActivitiesAll.size(); k++) {

                    String [] splitter = tempList.get(i).split(" ");

                    if (userActivitiesAll.get(k).activityid.contains(splitter[0])){

                        //fake the data for later use (categoryid and userid)
                        activitiesjoyPrevious.add(new ActivitiesSprint(userActivitiesAll.get(k).activityScore,userActivitiesAll.get(k).actualPoints,
                                splitter[1], userActivitiesAll.get(k).name, userActivitiesAll.get(k).sprintDailyPoints,
                                userActivitiesAll.get(k).targetPoints, splitter[2], userActivitiesAll.get(k).activityid));
                        break;
                    }
                }
            } //end of for


            //PASSION
            List<String> tempListPassion = new ArrayList<>(); //list containing all the sprintActivities of the user


            for (int i = 0; i < currentPassionCategories.size(); i++) {


                if (userId.contains(currentPassionCategories.get(i).userId)) {

                    tempListPassion.add(currentPassionCategories.get(i).sprintActivityid1 + " " + currentPassionCategories.get(i).startingDate + " " + currentPassionCategories.get(i).endingDate);
                    tempListPassion.add(currentPassionCategories.get(i).sprintActivityid2 + " " + currentPassionCategories.get(i).startingDate + " " + currentPassionCategories.get(i).endingDate);

                    userPassionSprintHelper.add(new Category(currentPassionCategories.get(i).categoryid, currentPassionCategories.get(i).endingDate,
                            currentPassionCategories.get(i).goal1, currentPassionCategories.get(i).goal2, currentPassionCategories.get(i).goal3,
                            currentPassionCategories.get(i).goal4, currentPassionCategories.get(i).numberOfWeeks, currentPassionCategories.get(i).sprintActivityid1,
                            currentPassionCategories.get(i).sprintActivityid2, currentPassionCategories.get(i).sprintOverallScore,
                            currentPassionCategories.get(i).startingDate, currentPassionCategories.get(i).userId,currentPassionCategories.get(i).sprintid));
                }
            }


            for (int i = 0; i < tempListPassion.size(); i++) {

                for (int k = 0; k < userActivitiesAll.size(); k++) {

                    String [] splitter = tempListPassion.get(i).split(" ");

                    if (userActivitiesAll.get(k).activityid.contains(splitter[0])){

                        //fake the data for later use (categoryid and userid)
                        activitiesPassionPrevious.add(new ActivitiesSprint(userActivitiesAll.get(k).activityScore,userActivitiesAll.get(k).actualPoints,
                                splitter[1], userActivitiesAll.get(k).name, userActivitiesAll.get(k).sprintDailyPoints,
                                userActivitiesAll.get(k).targetPoints, splitter[2], userActivitiesAll.get(k).activityid));
                        break;
                    }
                }
            } //end of for



            List<String> tempListContribution = new ArrayList<>(); //list containing all the sprintActivities of the user
            //GIVING BACK
            for (int i = 0; i < currentContributionCategories.size(); i++) {


                if (userId.contains(currentContributionCategories.get(i).userId)) {

                    tempListContribution.add(currentContributionCategories.get(i).sprintActivityid1 + " " + currentContributionCategories.get(i).startingDate + " " + currentContributionCategories.get(i).endingDate);
                    tempListContribution.add(currentContributionCategories.get(i).sprintActivityid2 + " " + currentContributionCategories.get(i).startingDate + " " + currentContributionCategories.get(i).endingDate);

                    userContributionSprintHelper.add(new Category(currentContributionCategories.get(i).categoryid, currentContributionCategories.get(i).endingDate,
                            currentContributionCategories.get(i).goal1, currentContributionCategories.get(i).goal2, currentContributionCategories.get(i).goal3,
                            currentContributionCategories.get(i).goal4, currentContributionCategories.get(i).numberOfWeeks, currentContributionCategories.get(i).sprintActivityid1,
                            currentContributionCategories.get(i).sprintActivityid2, currentContributionCategories.get(i).sprintOverallScore,
                            currentContributionCategories.get(i).startingDate, currentContributionCategories.get(i).userId,currentContributionCategories.get(i).sprintid));
                }
            }


            for (int i = 0; i < tempListContribution.size(); i++) {

                for (int k = 0; k < userActivitiesAll.size(); k++) {

                    String [] splitter = tempListContribution.get(i).split(" ");

                    if (userActivitiesAll.get(k).activityid.contains(splitter[0])){

                        //fake the data for later use (categoryid and userid)
                        activitiesContributionPrevious.add(new ActivitiesSprint(userActivitiesAll.get(k).activityScore,userActivitiesAll.get(k).actualPoints,
                                splitter[1], userActivitiesAll.get(k).name, userActivitiesAll.get(k).sprintDailyPoints,
                                userActivitiesAll.get(k).targetPoints, splitter[2], userActivitiesAll.get(k).activityid));
                        break;
                    }
                }
            } //end of for

            //JOY
            TreeMap<String,String> joydateIdentifier = new TreeMap<String,String>();

            for (int i = 0; i < userJoysprintsHelper.size(); i++) {

                joydateIdentifier.put(userJoysprintsHelper.get(i).startingDate,userJoysprintsHelper.get(i).sprintid);
            }

            String joycurrentDate = "";
            for(Map.Entry<String,String> entr: joydateIdentifier.entrySet()){
                //get the most current startind date
                joycurrentDate = entr.getKey();
                sprintjoyid = entr.getValue();
            }


            //PASSION
            TreeMap<String,String> passiondateIdentifier = new TreeMap<String,String>();

            for (int i = 0; i < userPassionSprintHelper.size(); i++) {

                passiondateIdentifier.put(userPassionSprintHelper.get(i).startingDate,userPassionSprintHelper.get(i).sprintid);
            }

            String passionleastDate = "";
            for(Map.Entry<String,String> entr: passiondateIdentifier.entrySet()){
                passionleastDate = entr.getKey();
                sprintpassionid = entr.getValue();
            }

            //GIVING BACK

            TreeMap<String,String> contributiondateIdentifier = new TreeMap<String,String>();

            for (int i = 0; i < userContributionSprintHelper.size(); i++) {

                contributiondateIdentifier.put(userContributionSprintHelper.get(i).startingDate,userContributionSprintHelper.get(i).sprintid);
            }

            String contributionleastDate = "";
            for(Map.Entry<String,String> entr: contributiondateIdentifier.entrySet()){
                contributionleastDate = entr.getKey();
                sprintcontributionid = entr.getValue();
            }



            //JOY
            Category userJoySprint = new Category();
            for (int i = 0; i < userJoysprintsHelper.size(); i++) {

                if (userJoysprintsHelper.get(i).startingDate.contains(joycurrentDate)) {


                    userJoySprint = new Category(userJoysprintsHelper.get(i).categoryid, userJoysprintsHelper.get(i).endingDate,
                            userJoysprintsHelper.get(i).goal1, userJoysprintsHelper.get(i).goal2, userJoysprintsHelper.get(i).goal3,
                            userJoysprintsHelper.get(i).goal4, userJoysprintsHelper.get(i).numberOfWeeks, userJoysprintsHelper.get(i).sprintActivityid1,
                            userJoysprintsHelper.get(i).sprintActivityid2, userJoysprintsHelper.get(i).sprintOverallScore,
                            userJoysprintsHelper.get(i).startingDate, userJoysprintsHelper.get(i).userId,userJoysprintsHelper.get(i).sprintid);
                }
            }

            //PASSION
            Category userPassionSprint = new Category();
            for (int i = 0; i < userPassionSprintHelper.size(); i++) {

                if (userPassionSprintHelper.get(i).startingDate.contains(passionleastDate)) {


                    userPassionSprint = new Category(userPassionSprintHelper.get(i).categoryid, userPassionSprintHelper.get(i).endingDate,
                            userPassionSprintHelper.get(i).goal1, userPassionSprintHelper.get(i).goal2, userPassionSprintHelper.get(i).goal3,
                            userPassionSprintHelper.get(i).goal4, userPassionSprintHelper.get(i).numberOfWeeks, userPassionSprintHelper.get(i).sprintActivityid1,
                            userPassionSprintHelper.get(i).sprintActivityid2, userPassionSprintHelper.get(i).sprintOverallScore,
                            userPassionSprintHelper.get(i).startingDate, userPassionSprintHelper.get(i).userId,userPassionSprintHelper.get(i).sprintid);
                }
            }

            //GIVING BACK
            Category userContributionSprint = new Category();
            for (int i = 0; i < userContributionSprintHelper.size(); i++) {

                if (userContributionSprintHelper.get(i).startingDate.contains(contributionleastDate)) {

                    userContributionSprint = new Category(userContributionSprintHelper.get(i).categoryid, userContributionSprintHelper.get(i).endingDate,
                            userContributionSprintHelper.get(i).goal1, userContributionSprintHelper.get(i).goal2, userContributionSprintHelper.get(i).goal3,
                            userContributionSprintHelper.get(i).goal4, userContributionSprintHelper.get(i).numberOfWeeks, userContributionSprintHelper.get(i).sprintActivityid1,
                            userContributionSprintHelper.get(i).sprintActivityid2, userContributionSprintHelper.get(i).sprintOverallScore,
                            userContributionSprintHelper.get(i).startingDate, userContributionSprintHelper.get(i).userId,userContributionSprintHelper.get(i).sprintid);
                }
            }

            //JOY
            ActivitiesSprint userActivityJoyid1 = new ActivitiesSprint();
            ActivitiesSprint userActivityJoyid2 = new ActivitiesSprint();

            for (int i = 0; i < userActivitiesAll.size(); i++) {


                if (userActivitiesAll.get(i).activityid.contains(userJoySprint.sprintActivityid1)) {
                    userActivityJoyid1 = new ActivitiesSprint(userActivitiesAll.get(i).activityScore,
                            userActivitiesAll.get(i).actualPoints, userActivitiesAll.get(i).categoryId,
                            userActivitiesAll.get(i).name, userActivitiesAll.get(i).sprintDailyPoints,
                            userActivitiesAll.get(i).targetPoints, userActivitiesAll.get(i).userId, userActivitiesAll.get(i).activityid);
                }

                if (userActivitiesAll.get(i).activityid.contains(userJoySprint.sprintActivityid2)) {

                    userActivityJoyid2 = new ActivitiesSprint(userActivitiesAll.get(i).activityScore,
                            userActivitiesAll.get(i).actualPoints, userActivitiesAll.get(i).categoryId,
                            userActivitiesAll.get(i).name, userActivitiesAll.get(i).sprintDailyPoints,
                            userActivitiesAll.get(i).targetPoints, userActivitiesAll.get(i).userId, userActivitiesAll.get(i).activityid);
                }
            }

            //PASSION
            ActivitiesSprint userActivityPassionid1 = new ActivitiesSprint();
            ActivitiesSprint userActivityPassionid2 = new ActivitiesSprint();

            for (int i = 0; i < userActivitiesAll.size(); i++) {


                if (userActivitiesAll.get(i).activityid.contains(userPassionSprint.sprintActivityid1)) {
                    userActivityPassionid1 = new ActivitiesSprint(userActivitiesAll.get(i).activityScore,
                            userActivitiesAll.get(i).actualPoints, userActivitiesAll.get(i).categoryId,
                            userActivitiesAll.get(i).name, userActivitiesAll.get(i).sprintDailyPoints,
                            userActivitiesAll.get(i).targetPoints, userActivitiesAll.get(i).userId, userActivitiesAll.get(i).activityid);
                }

                if (userActivitiesAll.get(i).activityid.contains(userPassionSprint.sprintActivityid2)) {

                    userActivityPassionid2 = new ActivitiesSprint(userActivitiesAll.get(i).activityScore,
                            userActivitiesAll.get(i).actualPoints, userActivitiesAll.get(i).categoryId,
                            userActivitiesAll.get(i).name, userActivitiesAll.get(i).sprintDailyPoints,
                            userActivitiesAll.get(i).targetPoints, userActivitiesAll.get(i).userId, userActivitiesAll.get(i).activityid);
                }
            }

            //GIVING BACK
            ActivitiesSprint userActivityContributionid1 = new ActivitiesSprint();
            ActivitiesSprint userActivityContributionid2 = new ActivitiesSprint();

            for (int i = 0; i < userActivitiesAll.size(); i++) {


                if (userActivitiesAll.get(i).activityid.contains(userContributionSprint.sprintActivityid1)) {
                    userActivityContributionid1 = new ActivitiesSprint(userActivitiesAll.get(i).activityScore,
                            userActivitiesAll.get(i).actualPoints, userActivitiesAll.get(i).categoryId,
                            userActivitiesAll.get(i).name, userActivitiesAll.get(i).sprintDailyPoints,
                            userActivitiesAll.get(i).targetPoints, userActivitiesAll.get(i).userId, userActivitiesAll.get(i).activityid);
                }

                if (userActivitiesAll.get(i).activityid.contains(userContributionSprint.sprintActivityid2)) {

                    userActivityContributionid2 = new ActivitiesSprint(userActivitiesAll.get(i).activityScore,
                            userActivitiesAll.get(i).actualPoints, userActivitiesAll.get(i).categoryId,
                            userActivitiesAll.get(i).name, userActivitiesAll.get(i).sprintDailyPoints,
                            userActivitiesAll.get(i).targetPoints, userActivitiesAll.get(i).userId, userActivitiesAll.get(i).activityid);

                }
            }

            //SAVE EVERYTHING IS NEEDED
            Intent i = new Intent(LoginActivity.this,Dashboard.class);

            //saving all data do we can use it in the next screen
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("mylist", listUsers);
            bundle.putParcelableArrayList("allActivities", allActivities);
            bundle.putParcelableArrayList("userActivitiesAllList",userActivitiesAll);
            bundle.putParcelableArrayList("coachesList",coachList);

            //JOY
            bundle.putParcelableArrayList("categoriesJoyCategories",currentJoyCategories);
            bundle.putParcelableArrayList("userJoysprintHelperList",userJoysprintsHelper);
            bundle.putParcelableArrayList("activitiesJOYPrevious",activitiesjoyPrevious);


            //PASSION
            bundle.putParcelableArrayList("categoriesPassionCategories",currentPassionCategories);
            bundle.putParcelableArrayList("userPassionsprintHelperList",userPassionSprintHelper);
            bundle.putParcelableArrayList("activitiesPassionPrevious",activitiesPassionPrevious);

            //GIVING BACK
            bundle.putParcelableArrayList("categoriesContributionCategories",currentContributionCategories);
            bundle.putParcelableArrayList("userContributionsprintHelperList",userContributionSprintHelper);
            bundle.putParcelableArrayList("activitiesContributionPrevious",activitiesContributionPrevious);


            i.putExtras(bundle);
            i.putExtra("userNameY",name);
            i.putExtra("passwordY",pass);

            //JOY
            i.putExtra("joySprintId",sprintjoyid);

            //PASSION
            i.putExtra("passionSprintId",sprintpassionid);

            //GIVING BACK
            i.putExtra("contributionSprintId",sprintcontributionid);

            //JOY
            i.putExtra("joy_userJoySprint_categoryid",userJoySprint.categoryid);
            i.putExtra("joy_userJoySprint_endingdate",userJoySprint.endingDate);
            i.putExtra("joy_userJoySprint_goal1",userJoySprint.goal1);
            i.putExtra("joy_userJoySprint_goal2",userJoySprint.goal2);
            i.putExtra("joy_userJoySprint_goal3",userJoySprint.goal3);
            i.putExtra("joy_userJoySprint_goal4",userJoySprint.goal4);
            i.putExtra("joy_userJoySprint_numberofweeks",userJoySprint.numberOfWeeks);
            i.putExtra("joy_userJoySprint_sprintactivityid1",userJoySprint.sprintActivityid1);
            i.putExtra("joy_UserJoySprint_sprintactivityid2",userJoySprint.sprintActivityid2);
            i.putExtra("joy_UserJoySprint_sprintoverallscore",userJoySprint.sprintOverallScore);
            i.putExtra("joy_UserJoySprint_startingdate",userJoySprint.startingDate);
            i.putExtra("joy_UserJoySprint_userid",userJoySprint.userId);

            //JOY
            i.putExtra("joy_activityid1_activityscore",userActivityJoyid1.activityScore);
            i.putExtra("joy_activityid1_actualpoints",userActivityJoyid1.actualPoints);
            i.putExtra("joy_activityid1_categoryid",userActivityJoyid1.categoryId);
            i.putExtra("joy_activityid1_activityname",userActivityJoyid1.name);
            i.putExtra("joy_activityid1_sprintdailypoints",userActivityJoyid1.sprintDailyPoints);
            i.putExtra("joy_activityid1_targetpoints",userActivityJoyid1.targetPoints);
            i.putExtra("joy_activityid1_userid",userActivityJoyid1.userId);
            i.putExtra("joy_activityid1_activityid",userActivityJoyid1.activityid);

            //JOY
            i.putExtra("joy_activityid2_activityscore",userActivityJoyid2.activityScore);
            i.putExtra("joy_activityid2_actualpoints",userActivityJoyid2.actualPoints);
            i.putExtra("joy_activityid2_categoryid",userActivityJoyid2.categoryId);
            i.putExtra("joy_activityid2_activityname",userActivityJoyid2.name);
            i.putExtra("joy_activityid2_sprintdailypoints",userActivityJoyid2.sprintDailyPoints);
            i.putExtra("joy_activityid2_targetpoints",userActivityJoyid2.targetPoints);
            i.putExtra("joy_activityid2_userid",userActivityJoyid2.userId);
            i.putExtra("joy_activityid2_activityid",userActivityJoyid2.activityid);


            //PASSION
            i.putExtra("passion_userPassionSprint_categoryid",userPassionSprint.categoryid);
            i.putExtra("passion_userPassionSprint_endingdate",userPassionSprint.endingDate);
            i.putExtra("passion_userPassionSprint_goal1",userPassionSprint.goal1);
            i.putExtra("passion_userPassionSprint_goal2",userPassionSprint.goal2);
            i.putExtra("passion_userPassionSprint_goal3",userPassionSprint.goal3);
            i.putExtra("passion_userPassionSprint_goal4",userPassionSprint.goal4);
            i.putExtra("passion_userPassionSprint_numberofweeks",userPassionSprint.numberOfWeeks);
            i.putExtra("passion_userPassionSprint_sprintactivityid1",userPassionSprint.sprintActivityid1);
            i.putExtra("passion_userPassionSprint_sprintactivityid2",userPassionSprint.sprintActivityid2);
            i.putExtra("passion_userPassionSprint_sprintoverallscore",userPassionSprint.sprintOverallScore);
            i.putExtra("passion_userPassionSprint_startingdate",userPassionSprint.startingDate);
            i.putExtra("passion_userPassionSprint_userid",userPassionSprint.userId);

            //PASSION
            i.putExtra("passion_activityid1_activityscore",userActivityPassionid1.activityScore);
            i.putExtra("passion_activityid1_actualpoints",userActivityPassionid1.actualPoints);
            i.putExtra("passion_activityid1_categoryid",userActivityPassionid1.categoryId);
            i.putExtra("passion_activityid1_activityname",userActivityPassionid1.name);
            i.putExtra("passion_activityid1_sprintdailypoints",userActivityPassionid1.sprintDailyPoints);
            i.putExtra("passion_activityid1_targetpoints",userActivityPassionid1.targetPoints);
            i.putExtra("passion_activityid1_userid",userActivityPassionid1.userId);
            i.putExtra("passion_activityid1_activityid",userActivityPassionid1.activityid);

            //PASSION
            i.putExtra("passion_activityid2_activityscore",userActivityPassionid2.activityScore);
            i.putExtra("passion_activityid2_actualpoints",userActivityPassionid2.actualPoints);
            i.putExtra("passion_activityid2_categoryid",userActivityPassionid2.categoryId);
            i.putExtra("passion_activityid2_activityname",userActivityPassionid2.name);
            i.putExtra("passion_activityid2_sprintdailypoints",userActivityPassionid2.sprintDailyPoints);
            i.putExtra("passion_activityid2_targetpoints",userActivityPassionid2.targetPoints);
            i.putExtra("passion_activityid2_userid",userActivityPassionid2.userId);
            i.putExtra("passion_activityid2_activityid",userActivityPassionid2.activityid);

            //GIVING BACK
            i.putExtra("contribution_userContributionSprint_categoryid",userContributionSprint.categoryid);
            i.putExtra("contribution_userContributionSprint_endingdate",userContributionSprint.endingDate);
            i.putExtra("contribution_userContributionSprint_goal1",userContributionSprint.goal1);
            i.putExtra("contribution_userContributionSprint_goal2",userContributionSprint.goal2);
            i.putExtra("contribution_userContributionSprint_goal3",userContributionSprint.goal3);
            i.putExtra("contribution_userContributionSprint_goal4",userContributionSprint.goal4);
            i.putExtra("contribution_userContributionSprint_numberofweeks",userContributionSprint.numberOfWeeks);
            i.putExtra("contribution_userContributionSprint_sprintactivityid1",userContributionSprint.sprintActivityid1);
            i.putExtra("contribution_userContributionSprint_sprintactivityid2",userContributionSprint.sprintActivityid2);
            i.putExtra("contribution_userContributionSprint_sprintoverallscore",userContributionSprint.sprintOverallScore);
            i.putExtra("contribution_userContributionSprint_startingdate",userContributionSprint.startingDate);
            i.putExtra("contribution_userContributionSprint_userid",userContributionSprint.userId);

            //GIVING BACK
            i.putExtra("contribution_activityid1_activityscore",userActivityContributionid1.activityScore);
            i.putExtra("contribution_activityid1_actualpoints",userActivityContributionid1.actualPoints);
            i.putExtra("contribution_activityid1_categoryid",userActivityContributionid1.categoryId);
            i.putExtra("contribution_activityid1_activityname",userActivityContributionid1.name);
            i.putExtra("contribution_activityid1_sprintdailypoints",userActivityContributionid1.sprintDailyPoints);
            i.putExtra("contribution_activityid1_targetpoints",userActivityContributionid1.targetPoints);
            i.putExtra("contribution_activityid1_userid",userActivityContributionid1.userId);
            i.putExtra("contribution_activityid1_activityid",userActivityContributionid1.activityid);

            //GIVING BACK
            i.putExtra("contribution_activityid2_activityscore",userActivityContributionid2.activityScore);
            i.putExtra("contribution_activityid2_actualpoints",userActivityContributionid2.actualPoints);
            i.putExtra("contribution_activityid2_categoryid",userActivityContributionid2.categoryId);
            i.putExtra("contribution_activityid2_activityname",userActivityContributionid2.name);
            i.putExtra("contribution_activityid2_sprintdailypoints",userActivityContributionid2.sprintDailyPoints);
            i.putExtra("contribution_activityid2_targetpoints",userActivityContributionid2.targetPoints);
            i.putExtra("contribution_activityid2_userid",userActivityContributionid2.userId);
            i.putExtra("contribution_activityid2_activityid",userActivityContributionid2.activityid);

            //profile image
            i.putExtra("profileImageName",userProfileImageName);

            this.startActivity(i);

        }else{
            Toast.makeText(this, "Username/Password does not match, Please try again ", Toast.LENGTH_LONG).show();
            name = "";
        }

    } //end of method
} //end of class



