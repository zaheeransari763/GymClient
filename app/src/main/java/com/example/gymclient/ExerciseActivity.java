package com.example.gymclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class ExerciseActivity extends AppCompatActivity {

    private CardView chest,back,biceps,legs,shoulder,abs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//fullscreen
        setContentView(R.layout.activity_exercise);

        chest=(CardView)findViewById(R.id.chestcard);
        back=(CardView)findViewById(R.id.backcard);
        biceps=(CardView)findViewById(R.id.bicepscard);
        legs=(CardView)findViewById(R.id.legscard);
        shoulder=(CardView)findViewById(R.id.shouldercard);
        abs=(CardView)findViewById(R.id.abscard);


        chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExerciseActivity.this,ChestActivity.class);
                startActivity(intent);
                Toast.makeText(ExerciseActivity.this,"Chest Workouts",Toast.LENGTH_LONG).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExerciseActivity.this,BackActivity.class);
                startActivity(intent);
                Toast.makeText(ExerciseActivity.this,"Back Workouts",Toast.LENGTH_LONG).show();
            }
        });

        biceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExerciseActivity.this,BicepsActivity.class);
                startActivity(intent);
                Toast.makeText(ExerciseActivity.this,"Biceps Workouts",Toast.LENGTH_LONG).show();
            }
        });


        legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExerciseActivity.this,LegsActivity.class);
                startActivity(intent);
                Toast.makeText(ExerciseActivity.this,"Legs Workouts",Toast.LENGTH_LONG).show();
            }
        });

        shoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExerciseActivity.this,ShoulderActivity.class);
                startActivity(intent);
                Toast.makeText(ExerciseActivity.this,"Shoulder Workouts",Toast.LENGTH_LONG).show();
            }
        });

        abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExerciseActivity.this,AbsActivity.class);
                startActivity(intent);
                Toast.makeText(ExerciseActivity.this,"Abs Workouts",Toast.LENGTH_LONG).show();
            }
        });
    }
}
