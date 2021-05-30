package com.example.gymclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView register;
    private Button LoginButton;
    private EditText UserEmail,UserPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//fullscreen
        setContentView(R.layout.activity_main);

        TextView t = (TextView) findViewById(R.id.text1);
        Typeface myCustomFont= Typeface.createFromAsset(getAssets(),"fonts/Sony_Sketch_EF.ttf");
        t.setTypeface(myCustomFont);//font style

        register=(TextView)findViewById(R.id.regtext);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"You are about to register",Toast.LENGTH_LONG).show();




            }

        });

        mAuth = FirebaseAuth.getInstance();

        UserEmail = (EditText) findViewById(R.id.emaill);
        UserPassword = (EditText) findViewById(R.id.passwrd);

        LoginButton = (Button) findViewById(R.id.Lloginbtn);
        LoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AllowUserToLogin();
            }
        });

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            SendUserToMainActivity();
        }
    }



    private void AllowUserToLogin() {
        final String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "E-mail is Mandatory...", Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(MainActivity.this, "Incorrect E-mail", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Pass is Mandatory...", Toast.LENGTH_SHORT).show();
        }
        else

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        SendUserToMainActivity();
                        Toast.makeText(MainActivity.this, "Logged In Successfully...", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        String message = task.getException().getMessage();
                        Toast.makeText(MainActivity.this, "Error Occurred:" + message, Toast.LENGTH_SHORT).show();

                    }
                }
            });

    }

    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(MainActivity.this,DashboardActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();

    }

}
