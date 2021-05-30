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

public class ShoulderActivity extends AppCompatActivity {

    VideoView videoView;
    ListView listView;
    ArrayList<String> video;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//fullscreen
        setContentView(R.layout.activity_shoulder);



        videoView=(VideoView)findViewById(R.id.videoView);
        listView=(ListView)findViewById(R.id.listview);

        video=new ArrayList<>();
        video.add("Barbell Shrugs");
        video.add("Dumbel Front Raise");
        video.add("Front Raise");
        video.add("Lateral Raise");
        video.add("Shoulder Press");
        video.add("Upright Rows");

        adapter=new ArrayAdapter(this,R.layout.listviewstyle,video);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName()+ "/" +R.raw.barbellshrugs));
                        break;
                    case 1:
                        videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName() + "/" +R.raw.dumbellfrontraise));
                        break;
                    case 2:
                        videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName() + "/" +R.raw.frontraise));
                        break;
                    case 3:
                        videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName() + "/" +R.raw.lateralraise1));
                        break;
                    case 4:
                        videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName() + "/" +R.raw.shoulderpress));
                        break;
                    case 5:
                        videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName() + "/" +R.raw.uprightrows));
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
