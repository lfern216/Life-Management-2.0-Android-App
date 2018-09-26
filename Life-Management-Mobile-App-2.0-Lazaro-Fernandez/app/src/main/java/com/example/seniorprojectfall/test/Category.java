package com.example.seniorprojectfall.test;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;

public class Category implements Parcelable{

    String categoryid;
    String endingDate;
    String goal1;
    String goal2;
    String goal3;
    String goal4;
    String numberOfWeeks;
    String sprintActivityid1;
    String sprintActivityid2;
    String sprintOverallScore;
    String startingDate;
    String userId;
    String sprintid;



    public Category(){

        categoryid = "";
        endingDate = "";
        goal1= "";
        goal2= "";
        goal3= "";
        goal4= "";
        numberOfWeeks= "";
        sprintActivityid1= "";
        sprintActivityid2= "";
        sprintOverallScore= "";
        startingDate= "";
        userId = "";
        sprintid = "";

    }

    public Category(String categoryid, String endingDate, String goal1, String goal2, String goal3, String goal4, String numberOfWeeks, String sprintActivityid1,
                    String sprintActivityid2, String sprintOverallScore, String startingDate, String userId, String sprintid) {
        this.categoryid = categoryid;
        this.endingDate = endingDate;
        this.goal1 = goal1;
        this.goal2 = goal2;
        this.goal3 = goal3;
        this.goal4 = goal4;
        this.numberOfWeeks = numberOfWeeks;
        this.sprintActivityid1 = sprintActivityid1;
        this.sprintActivityid2 = sprintActivityid2;
        this.sprintOverallScore = sprintOverallScore;
        this.startingDate = startingDate;
        this.userId = userId;
        this.sprintid = sprintid;

    }

    public Category(Parcel in){

        this.categoryid = in.readString();
        this.endingDate = in.readString();
        this.goal1 = in.readString();
        this.goal2 = in.readString();
        this.goal3 = in.readString();
        this.goal4 = in.readString();
        this.numberOfWeeks = in.readString();
        this.sprintActivityid1 = in.readString();
        this.sprintActivityid2 = in.readString();
        this.sprintOverallScore = in.readString();
        this.startingDate = in.readString();
        this.userId = in.readString();
        this.sprintid = in.readString();
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoal1() {
        return goal1;
    }

    public void setGoal1(String goal1) {
        this.goal1 = goal1;
    }

    public String getGoal2() {
        return goal2;
    }

    public void setGoal2(String goal2) {
        this.goal2 = goal2;
    }

    public String getGoal3() {
        return goal3;
    }

    public void setGoal3(String goal3) {
        this.goal3 = goal3;
    }

    public String getGoal4() {
        return goal4;
    }

    public void setGoal4(String goal4) {
        this.goal4 = goal4;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public String getNumberOfWeeks() {
        return numberOfWeeks;
    }

    public void setNumberOfWeeks(String numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }

    public String getSprintActivityid1() {
        return sprintActivityid1;
    }

    public void setSprintActivityid1(String sprintActivityid1) {
        this.sprintActivityid1 = sprintActivityid1;
    }

    public String getSprintActivityid2() {
        return sprintActivityid2;
    }

    public void setSprintActivityid2(String sprintActivityid2) {
        this.sprintActivityid2 = sprintActivityid2;
    }

    public String getSprintOverallScore() {
        return sprintOverallScore;
    }

    public void setSprintOverallScore(String sprintOverallScore) {
        this.sprintOverallScore = sprintOverallScore;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(categoryid);
        parcel.writeString(endingDate);
        parcel.writeString(goal1);
        parcel.writeString(goal2);
        parcel.writeString(goal3);
        parcel.writeString(goal4);
        parcel.writeString(numberOfWeeks);
        parcel.writeString(sprintActivityid1);
        parcel.writeString(sprintActivityid2);
        parcel.writeString(sprintOverallScore);
        parcel.writeString(startingDate);
        parcel.writeString(userId);
        parcel.writeString(sprintid);
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int i) {
            return new Category[i];
        }
    };
}

