package com.example.seniorprojectfall.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lazaro on 11/13/17.
 */

public class Coach implements Parcelable{

    public String email;
    public String firstName;
    public String id;
    public String lastName;
    public String rating;
    public String skills;

    public Coach(String skills, String firstName, String lastName, String rating, String id, String email) {
        this.skills = skills;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.id = id;
        this.email = email;
    }

    public Coach(){
        skills = "";
        firstName = "";
        lastName = "";
        rating = "";
        id = "";
        email = "";
    }

    public Coach(Parcel in){

        this.skills = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.rating = in.readString();
        this.id = in.readString();
        this.email = in.readString();

    }


    public static final Creator<Coach> CREATOR = new Creator<Coach>() {
        @Override
        public Coach createFromParcel(Parcel in) {
            return new Coach(in);
        }

        @Override
        public Coach[] newArray(int size) {
            return new Coach[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(skills);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(rating);
        parcel.writeString(id);
        parcel.writeString(email);

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
