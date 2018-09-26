package com.example.seniorprojectfall.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lazaro on 9/17/17.
 */

public class User implements Parcelable{

    //String id;
    String email;
    String username;
    String password;
    String firstName;
    String lastName;
    String dob;
    boolean coachFlag;
    boolean adminFlag;
    String id;

    public User(){

        email = "";
        username = "";
        password = "";
        firstName = "";
        lastName = "";
        dob = "";
        coachFlag  = false;
        adminFlag = false;
        id = "";
    }

    public User(String email, String username, String firstName, String lastName, String dob, String password, boolean admin, boolean coach, String id) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.coachFlag = coach;
        this.adminFlag = admin;
        this.password = password;
        this.id = id;
    }

    public User(Parcel in){

        this.email = in.readString();
        this.username = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.dob = in.readString();


        int m = 0;
        m = in.readInt();

        int n = 0;
        m = in.readInt();

        this.password = in.readString();
        this.id = in.readString();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCoachFlag() {
        return coachFlag;
    }

    public void setCoachFlag(boolean coachFlag) {
        this.coachFlag = coachFlag;
    }

    public boolean isAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(boolean adminFlag) {
        this.adminFlag = adminFlag;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(email);
        parcel.writeString(username);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(dob);
        parcel.writeInt((coachFlag) ? 1 : 0);
        parcel.writeInt((adminFlag) ? 1 : 0);
        parcel.writeString(password);
        parcel.writeString(id);

    }


    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int i) {
            return new User[i];
        }
    };


}
