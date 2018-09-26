package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Natalia on 11/14/2017.
 */

public class ChatActivity extends AppCompatActivity {

    private FirebaseListAdapter<ChatMessage> adapter;
    FloatingActionButton fab;
    DatabaseReference databaseReferenceChat;
    String userId;
    String username;
    String firstname;
    String chatId;
    String coachId;
    String coachFirstName;
    String activityName = "Chats";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        databaseReferenceChat = FirebaseDatabase.getInstance().getReference("Chats");

        Intent in = getIntent();
        userId = in.getStringExtra("userid");
        System.out.println("NAT TEST userId" + userId);
        username = in.getStringExtra("username");
        System.out.println("NAT TEST username" + username);
        firstname = in.getStringExtra("firstname");
        System.out.println("NAT TEST firstname" + firstname);
        coachId = in.getStringExtra("coachid");
        coachFirstName = in.getStringExtra("coachfirstname");
        System.out.println("NAT TEST coachfirstname" + coachFirstName);

        onStart();


        //check if this user already has chats in Chats.


        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText input = (EditText) findViewById(R.id.input);

                if (chatId == null){
                    //create new db object to hold this user coach chats
                    chatId = databaseReferenceChat.push().getKey();
                    databaseReferenceChat.child(chatId).child("userId").setValue(userId);
                    databaseReferenceChat.child(chatId).child("coachId").setValue(coachId);
                }

                //when coach's username is added to coach model in db, userId can be replaced with user's username
                databaseReferenceChat.child(chatId).child("ChatMessage").push().setValue(new ChatMessage(input.getText().toString(), userId));
                input.setText("");



            }
        });

        //Load chat messages
        displayChatMessage();

    }

    //find User Coach Chats
    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener to read from db
        databaseReferenceChat.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot chatSnapshot : dataSnapshot.getChildren()) { //id)
                    if ((chatSnapshot.getValue() == null) || (chatSnapshot.child("userId").getValue() == null) || (chatSnapshot.child("coachId").getValue() == null) ) {
                        break;
                    }
                    else{
                        String userIdTmp = chatSnapshot.child("userId").getValue().toString();
                        String coachIdTmp = chatSnapshot.child("coachId").getValue().toString();
                        System.out.println("NAT TEST userIdTmp" + userIdTmp);
                        if ((userIdTmp.equals(userId)) && (coachIdTmp.equals(coachId))) {
                            //get that chatSnapshot id, so that we can add new chats there
                            chatId = chatSnapshot.getKey();
                            System.out.println("NAT TEST chatId" + chatId);

                            displayChatMessage();

                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void displayChatMessage() {
        ListView listOfMessage = (ListView)findViewById(R.id.list_of_message);

        if ((chatId != null) && (databaseReferenceChat.child(chatId).child("ChatMessage") != null)) {
            adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, R.layout.list_item_chat, databaseReferenceChat.child(chatId).child("ChatMessage")) {
                @Override
                protected void populateView(View v, ChatMessage model, int position) {
                    // Get references to the views of list_item_chat.xml
                    TextView messageText = (TextView) v.findViewById(R.id.message_text);
                    TextView messageUser = (TextView) v.findViewById(R.id.message_user);
                    TextView messageTextRight = (TextView) v.findViewById(R.id.message_text_right);
                    TextView messageUserRight = (TextView) v.findViewById(R.id.message_user_right);

                    //when coach's username is added to coach model in db, coach (userId) can be replaced with coach's (user's) usernames
                    if(model.getMessageUser().equals(coachId)) {


                        messageText.setText(model.getMessageText());
                        messageUser.setText(coachFirstName);
                        messageTextRight.setText("");
                        messageUserRight.setText("");

                    }
                    else if(model.getMessageUser().equals(userId)){

                        messageTextRight.setText(model.getMessageText());
                        messageUserRight.setText(firstname);
                        messageText.setText("");
                        messageUser.setText("");
                    }
                }
            };
            listOfMessage.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chat_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_back:

                super.onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }
}
