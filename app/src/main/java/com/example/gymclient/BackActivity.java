package com.example.gymclient;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.VideoView;

import java.util.ArrayList;

public class BackActivity extends AppCompatActivity {

    VideoView videoView;
    ListView listView;
    ArrayList<String> video;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//fullscreen
        setContentView(R.layout.activity_back);


        videoView=(VideoView)findViewById(R.id.videoView);
        listView=(ListView)findViewById(R.id.listview);

        video=new ArrayList<>();
        video.add("Back Fly");
        video.add("Flying Raise");
        video.add("Lateral Raise");
        video.add("One Arm Row");

        adapter=new ArrayAdapter(this,R.layout.listviewstyle,video);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName()+ "/" +R.raw.backfly));
                        break;
                    case 1:
                        videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName() + "/" +R.raw.flyingraise));
                        break;
                    case 2:
                        videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName() + "/" +R.raw.lateralraise));
                        break;
                    case 3:
                        videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName() + "/" +R.raw.onearmrow));
                        break;
                    default:
                        break;



                }

                videoView.requestFocus();
                videoView.start();

            }
        });


    }
    }