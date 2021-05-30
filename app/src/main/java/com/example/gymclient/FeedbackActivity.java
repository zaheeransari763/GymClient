package com.example.gymclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hsalf.smilerating.SmileRating;

import java.util.HashMap;

public class FeedbackActivity extends AppCompatActivity {

    EditText feedback;
    Button feedbackbtn;

    private FirebaseAuth mAuth;
    private DatabaseReference reference, clientRef;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//fullscreen
        setContentView(R.layout.activity_feedback);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("Feedbacks").child(currentUserId);
        clientRef = FirebaseDatabase.getInstance().getReference().child("Clients").child(currentUserId);

        feedback=(EditText)findViewById(R.id.feedback);
        feedbackbtn=(Button)findViewById(R.id.feedbackbtn);

        feedbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();
            }
        });
    }

    /*private void sendFeedback() {
        clientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    String name = dataSnapshot.child("Fullname").getValue().toString();
                    String proImage = dataSnapshot.child("image").getValue().toString();
                    final HashMap feedMap = new HashMap();
                    feedMap.put("Fullname",name);
                    feedMap.put("profile",proImage);
                    feedMap.put("uid",currentUserId);
                    reference.updateChildren(feedMap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/

    private void sendFeedback() {
        final String feed = feedback.getText().toString();
        if (TextUtils.isEmpty(feed)) {
            Toast.makeText(this, "Feedback Cannot be Empty...", Toast.LENGTH_SHORT).show();
        }
        else {
            FirebaseUser firebaseUser = mAuth.getCurrentUser();
            String ClientID = firebaseUser.getUid();
            clientRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists())
                    {
                        String name = dataSnapshot.child("Fullname").getValue().toString();
                        String proImage = dataSnapshot.child("image").getValue().toString();

                        HashMap<String, Object> feedMap = new HashMap();
                        feedMap.put("Fullname", name);
                        feedMap.put("Profile",proImage);
                        feedMap.put("Feedback",feed);
                        feedMap.put("UID",currentUserId);
                        reference.setValue(feedMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                { Intent DashMainIntent = new Intent(FeedbackActivity.this,DashboardActivity.class);
                                    DashMainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(DashMainIntent);
                                    Toast.makeText(FeedbackActivity.this, "Updated Successfully...", Toast.LENGTH_SHORT).show();
                                    finish(); }
                                else {
                                    String message = task.getException().getMessage();
                                    Toast.makeText(FeedbackActivity.this, "Error Occurred ;" + message, Toast.LENGTH_SHORT).show(); }
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            /*reference= FirebaseDatabase.getInstance().getReference("Feedbacks").child(ClientID);
            HashMap<String, Object> ClientMap = new HashMap();
            ClientMap.put("Feedback", feed);
            reference.updateChildren(ClientMap).addOnCompleteListener(new OnCompleteListener<Void>()
            {
                @Override
                public void onComplete( Task<Void> task)
                {
                    if (task.isSuccessful())
                    { Intent DashMainIntent = new Intent(FeedbackActivity.this,DashboardActivity.class);
                        DashMainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(DashMainIntent);
                        Toast.makeText(FeedbackActivity.this, "Updated Successfully...", Toast.LENGTH_SHORT).show();
                        finish(); }
                    else {
                        String message = task.getException().getMessage();
                        Toast.makeText(FeedbackActivity.this, "Error Occurred ;" + message, Toast.LENGTH_SHORT).show(); }
                }
            });*/
        }
    }
}
