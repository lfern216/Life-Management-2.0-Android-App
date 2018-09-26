package com.example.seniorprojectfall.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lazaro on 10/5/17.
 */


public class ActivitiesSprint implements Parcelable{

    String activityScore;
    String actualPoints;
    String categoryId;
    String name;
    String sprintDailyPoints;
    String targetPoints;
    String userId;
    String activityid;

    public ActivitiesSprint() {


        activityScore = "";
        actualPoints = "";
        categoryId = "";
        name = "";
        sprintDailyPoints = "";
        targetPoints = "";
        userId = "";
        activityid = "";
    }

    public ActivitiesSprint(String activityScore, String actualPoints, String categoryId, String name, String sprintDailyPoints, String targetPoints, String userId, String activityid) {

        this.activityScore = activityScore;
        this.actualPoints = actualPoints;
        this.categoryId = categoryId;
        this.name = name;
        this.sprintDailyPoints = sprintDailyPoints;
        this.targetPoints = targetPoints;
        this.userId = userId;
        this.activityid = activityid;
    }

    public ActivitiesSprint(String activityScore, String actualPoints, String categoryId, String name, String sprintDailyPoints, String targetPoints, String userId) {

        this.activityScore = activityScore;
        this.actualPoints = actualPoints;
        this.categoryId = categoryId;
        this.name = name;
        this.sprintDailyPoints = sprintDailyPoints;
        this.targetPoints = targetPoints;
        this.userId = userId;
    }

    public ActivitiesSprint(Parcel in){


        this.activityScore = in.readString();
        this.actualPoints = in.readString();
        this.categoryId = in.readString();
        this.name = in.readString();
        this.sprintDailyPoints = in.readString();
        this.targetPoints = in.readString();
        this.userId = in.readString();
        this.activityid = in.readString();

    }

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

    public String getActivityScore() {
        return activityScore;
    }

    public void setActivityScore(String activityScore) {
        this.activityScore = activityScore;
    }

    public String getActualPoints() {
        return actualPoints;
    }

    public void setActualPoints(String actualPoints) {
        this.actualPoints = actualPoints;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprintDailyPoints() {
        return sprintDailyPoints;
    }

    public void setSprintDailyPoints(String sprintDailyPoints) {
        this.sprintDailyPoints = sprintDailyPoints;
    }

    public String getTargetPoints() {
        return targetPoints;
    }

    public void setTargetPoints(String targetPoints) {
        this.targetPoints = targetPoints;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(activityScore);
        parcel.writeString(actualPoints);
        parcel.writeString(categoryId);
        parcel.writeString(name);
        parcel.writeString(sprintDailyPoints);
        parcel.writeString(targetPoints);
        parcel.writeString(userId);
        parcel.writeString(activityid);

    }

    public static final Parcelable.Creator<ActivitiesSprint> CREATOR = new Parcelable.Creator<ActivitiesSprint>() {
        public ActivitiesSprint createFromParcel(Parcel in) {
            return new ActivitiesSprint(in);
        }

        @Override
        public ActivitiesSprint[] newArray(int i) {
            return new ActivitiesSprint[i];
        }
    };

}

