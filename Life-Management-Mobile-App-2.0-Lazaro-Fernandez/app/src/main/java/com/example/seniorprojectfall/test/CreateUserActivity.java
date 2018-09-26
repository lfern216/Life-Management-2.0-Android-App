package com.example.seniorprojectfall.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText repeatPassword;
    private EditText username;
    private EditText firstName;
    private EditText lastName;
    private EditText DOB;
    private Button buttonRegister;
    private ProgressDialog progressDialog;

    DatabaseReference databaseReference;
    List<User> listUsers2;

//#ff6b6b
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        progressDialog = new ProgressDialog(this);

        listUsers2 = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        repeatPassword = (EditText) findViewById(R.id.editTextRepeatPassword);
        username = (EditText) findViewById(R.id.editUsername);
        firstName = (EditText) findViewById(R.id.editFirstname);
        lastName = (EditText) findViewById(R.id.editLastName);
        DOB = (EditText) findViewById(R.id.editDOB);
        buttonRegister = (Button) findViewById(R.id.buttonSignup);
        buttonRegister.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                listUsers2.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    User artist = postSnapshot.getValue(User.class);
                    //adding artist to the list
                    listUsers2.add(artist);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        if(view==buttonRegister){
            addUser();
        }
    }

    public static boolean checkDateFormat(String d){


        SimpleDateFormat date = new SimpleDateFormat("mm/dd/yyyy");
        date.setLenient(false);

        try{

            date.parse(d.trim());

        }catch(ParseException p){
            return false;
        }

        return true;

    }


    private void addUser(){

        String firstN = firstName.getText().toString();
        String lastN = lastName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString().trim();
        String repeatP = repeatPassword.getText().toString().trim();
        String currentusername = username.getText().toString();
        String Dob = DOB.getText().toString();

        //input validations


        //First Name
        if((TextUtils.isEmpty(firstN)) || (firstN.length()<=1)){
            Toast.makeText(this,"First Name MUST contain more characters",Toast.LENGTH_SHORT).show();
            return;
        }else {

            if (firstN.length() >= 15) {
                Toast.makeText(this, "First Name is too Long, Please try again", Toast.LENGTH_SHORT).show();
                return;
            }

            char[] traverseName = firstN.toCharArray();

            for (char s : traverseName) {

                if((Character.isDigit(s))){
                    Toast.makeText(this,"First Name CANNOT Digits/Symbols",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Character.isLetter(s)){
                    Toast.makeText(this,"First Name CANNOT Digits/Symbols",Toast.LENGTH_SHORT).show();
                    return;
                }


            }

            //make all first character uppercase for better look in database
            String temp = firstN.substring(0, 1).toUpperCase();
            firstN = temp + firstN.substring(1);
            //Toast.makeText(this,"First Name UPPER " + firstN,Toast.LENGTH_SHORT).show();

        }

        //Last Name
        if((TextUtils.isEmpty(lastN)) || (lastN.length()<=1)){
            Toast.makeText(this,"Last Name MUST contain more characters",Toast.LENGTH_SHORT).show();
            return;
        }else {

            if (lastN.length() >= 16) {
                Toast.makeText(this, "Last Name is too Long, Please try again", Toast.LENGTH_SHORT).show();
                return;
            }

            char[] traverseLast = lastN.toCharArray();

            for (char s : traverseLast) {

                if((Character.isDigit(s))){
                    Toast.makeText(this,"Last Name CANNOT Digits/Symbols",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Character.isLetter(s)){
                    Toast.makeText(this,"Last Name CANNOT Digits/Symbols",Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            //make all first character uppercase for better look in database
            String temp = lastN.substring(0, 1).toUpperCase();
            lastN = temp + lastN.substring(1);
            //Toast.makeText(this,"Last Name UPPER " + lastN,Toast.LENGTH_SHORT).show();
        }

        //Email

        if((TextUtils.isEmpty(email)) || (email.length()<=1)){
            Toast.makeText(this,"Email MUST contain more characters",Toast.LENGTH_SHORT).show();
            return;
        }else {

            if (email.length() >= 40) {
                Toast.makeText(this, "Email is too Long, Please try again", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean validEmail = false;
            boolean validEmail2 = false;

            char[] traverseEmail = email.toCharArray();

            for (char s : traverseEmail) {

                if(s == '@' && !(email.substring(email.length()-1).equals(s+""))){
                    validEmail = true;
                }
                if(s == '.' && !(email.substring(email.length()-1).equals(s+""))){
                    validEmail2 = true;
                }

                if(Character.isSpace(s)){
                    Toast.makeText(this,"Email CANNOT contain spaces",Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            if(validEmail == false || validEmail2==false){
                Toast.makeText(this,"Please enter a valid Email",Toast.LENGTH_SHORT).show();
            }

            //check database for availability
            for(User f: listUsers2){

                String t = f.getEmail();

                if(t.equals(email)){
                    Toast.makeText(this,"Looks like you already have an account, Please use the LOGIN button",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

        //DOB
        if((TextUtils.isEmpty(Dob)) || (Dob.length()<=1)){
            Toast.makeText(this,"Date Of Birth SHOULD be longer",Toast.LENGTH_SHORT).show();
            return;
        }else {

            if(Dob.length()<=10) {

                char temp = Dob.charAt(Dob.length()-1);

                if(!Character.isDigit(temp)){
                    Toast.makeText(this, "Date Of Birth SHOULD have the format MM/DD/YYYY", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!checkDateFormat(Dob)){
                    Toast.makeText(this, "Date Of Birth SHOULD have the format MM/DD/YYYY", Toast.LENGTH_SHORT).show();
                    return;
                }

            }else {
                Toast.makeText(this, "Date Of birth is too Long, Please follow the format MM/DD/YY", Toast.LENGTH_SHORT).show();
                return;
            }
        }


        //username
        if((TextUtils.isEmpty(currentusername)) || (currentusername.length()<=1)){
            Toast.makeText(this,"Username MUST contain more characters",Toast.LENGTH_SHORT).show();
            return;
        }else {

            if (currentusername.length() >= 15) {
                Toast.makeText(this, "Username is too Long, Please try again", Toast.LENGTH_SHORT).show();
                return;
            }

            if (currentusername.length() < 5) {
                Toast.makeText(this, "Username is too short, Please try again", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean a = false;
            boolean b = false;
            int counter = 0;
            char[] traverseLast = currentusername.toCharArray();

            for (char s : traverseLast) {

                if(counter == 0){

                    if(!Character.isLetter(s)) {
                        Toast.makeText(this, "Username CANNOT start with symbols or numbers", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ++counter;
                }

                if(Character.isSpace(s)){
                    Toast.makeText(this,"Username CANNOT contain spaces",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(Character.isDigit(s)){
                    a = true;
                }

                if(Character.isLetter(s)){
                    b = true;
                }

                if((!Character.isLetter(s) && !Character.isDigit(s))){
                  //  Toast.makeText(this,"Username CANNOT contain symbols",Toast.LENGTH_SHORT).show();
                    //return;
                    a= true;
                }
            }

            if(!a){
                Toast.makeText(this,"Username MUST contain characters and at least a number or symbol",Toast.LENGTH_SHORT).show();
                return;
            }

            if(!b){
                Toast.makeText(this,"Username MUST contain characters",Toast.LENGTH_SHORT).show();
                return;
            }

            //check database for availability

            for(User f: listUsers2){

                String t = f.getUsername();

                if(t.equals(currentusername)){
                    Toast.makeText(this,"Looks like you already have an account, Please use the LOGIN button",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

        //Password

        if((TextUtils.isEmpty(password)) || (password.length()<=1)){
            Toast.makeText(this,"Password MUST contain more characters",Toast.LENGTH_SHORT).show();
            return;
        }else {

            if (password.length() >= 16) {
                Toast.makeText(this, "Password is too Long, Please try again", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 5) {
                Toast.makeText(this, "Password is too short, Please try again", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean a = false;
            boolean c = false;
            char[] traverseLast = password.toCharArray();

            for (char s : traverseLast) {


                if(Character.isSpace(s)){
                    Toast.makeText(this,"Password CANNOT contain spaces",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(Character.isDigit(s)){
                    a = true;
                }

                if(Character.isLetter(s)){
                    c = true;
                }

                if((!Character.isLetter(s) && !Character.isDigit(s))){
                    Toast.makeText(this,"Password CANNOT contain symbols",Toast.LENGTH_SHORT).show();
                    return;
                }

            }



            if(!a || !c){
                Toast.makeText(this,"Password MUST contain characters and numbers",Toast.LENGTH_SHORT).show();
                return;
            }
        }

        //Repeat Password

        if(!(password.equals(repeatP))){
            Toast.makeText(this,"Password MUST match the confirm password",Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        //generating unique key
        String id = databaseReference.push().getKey();

        User currentUser = new User(email,currentusername,firstN,lastN,Dob,password,false,false,id);

        //push()
        //databaseReference.push().setValue(currentUser);
        databaseReference.child(id).setValue(currentUser);
        progressDialog.dismiss();
        Toast.makeText(getApplication(),"Saved Successfully",Toast.LENGTH_LONG).show();

        Intent i = new Intent(CreateUserActivity.this,MainJoyActivity.class);
        //Save user id, currentusername, password so that we can use it in the following Activity:
        i.putExtra("userid",id);
        i.putExtra("username",currentusername);
        i.putExtra("password",password);
        startActivity(i);

    } //end of method addData
}
