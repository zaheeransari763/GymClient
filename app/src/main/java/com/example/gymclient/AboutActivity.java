package com.example.gymclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private ImageView mail,call;
    private  TextView findloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//fullscreen
        setContentView(R.layout.activity_about);

        TextView t = (TextView) findViewById(R.id.abouttext);
        Typeface myCustomFont= Typeface.createFromAsset(getAssets(),"fonts/Sony_Sketch_EF.ttf");
        t.setTypeface(myCustomFont);//font style

        TextView y = (TextView) findViewById(R.id.loc);
        Typeface CustomFont= Typeface.createFromAsset(getAssets(),"fonts/Sony_Sketch_EF.ttf");
        y.setTypeface(CustomFont);//font style

        TextView x = (TextView) findViewById(R.id.add);
        Typeface mCustomFont= Typeface.createFromAsset(getAssets(),"fonts/Sony_Sketch_EF.ttf");
        x.setTypeface(mCustomFont);//font style

        TextView m = (TextView) findViewById(R.id.find);
        Typeface myCustoFont= Typeface.createFromAsset(getAssets(),"fonts/Sony_Sketch_EF.ttf");
        m.setTypeface(myCustoFont);//font style

        TextView n = (TextView) findViewById(R.id.serv);
        Typeface myCusomFont= Typeface.createFromAsset(getAssets(),"fonts/Sony_Sketch_EF.ttf");
        n.setTypeface(myCusomFont);//font style

        TextView o = (TextView) findViewById(R.id.owned);
        Typeface myCstomFont= Typeface.createFromAsset(getAssets(),"fonts/Sony_Sketch_EF.ttf");
        o.setTypeface(myCstomFont);//font style


        mail = (ImageView) findViewById(R.id.mail);
        call = (ImageView) findViewById(R.id.call);

        findloc=(TextView)findViewById(R.id.find);

        findloc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openLocation();
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendmail();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactGym();
            }
        });

    }

    private void openLocation() {

        Intent locIntent = new Intent(Intent.ACTION_VIEW);
        locIntent.setData(Uri.parse("geo:20.894531, 73.505172"));
        startActivity(locIntent);
    }

    private void contactGym() {
        String number = "8600334695";
        Uri call = Uri.parse("tel:" + number);
        Intent contactIntent = new Intent(Intent.ACTION_DIAL,call);
        startActivity(Intent.createChooser(contactIntent,"Choose Calling Client"));
    }

    private void sendmail() {
        Uri uri = Uri.parse("mailto:jha.ranjeet1808@gmail.com");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        startActivity(Intent.createChooser(intent,"Choose Mailing Client"));

    }
}
