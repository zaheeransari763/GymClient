package com.example.gymclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class DashboardActivity extends AppCompatActivity {

    private CardView exercise, trainers, profile, pay, about, logout;
    private FirebaseAuth mAuth;
    Dialog dialogframe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//fullscreen
        setContentView(R.layout.activity_dashboard);

        TextView t = (TextView) findViewById(R.id.dashtext);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/Sony_Sketch_EF.ttf");
        t.setTypeface(myCustomFont);//font style

        mAuth = FirebaseAuth.getInstance();

        exercise = (CardView) findViewById(R.id.exc);
        trainers = (CardView) findViewById(R.id.train);
        profile = (CardView) findViewById(R.id.prof);
        pay = (CardView) findViewById(R.id.pa);
        about = (CardView) findViewById(R.id.abo);
        logout = (CardView) findViewById(R.id.log);

        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ExerciseActivity.class);
                startActivity(intent);
                Toast.makeText(DashboardActivity.this, "Lets browse some exercise", Toast.LENGTH_LONG).show();
            }
        });


        trainers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,TrainersActivity.class);
                startActivity(intent);
                Toast.makeText(DashboardActivity.this, "Lets browse the trainers", Toast.LENGTH_LONG).show();
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
                Toast.makeText(DashboardActivity.this, "Visit Your Profile", Toast.LENGTH_LONG).show();
            }
        });


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, FeedbackActivity.class);
                startActivity(intent);
                Toast.makeText(DashboardActivity.this, "Give a Feedback", Toast.LENGTH_LONG).show();


            }
        });


        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AboutActivity.class);
                startActivity(intent);
                Toast.makeText(DashboardActivity.this, "About Gym", Toast.LENGTH_LONG).show();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                //Toast.makeText(DashboardActivity.this, "Logged out", Toast.LENGTH_LONG).show();
            }
        });

    }

    }

