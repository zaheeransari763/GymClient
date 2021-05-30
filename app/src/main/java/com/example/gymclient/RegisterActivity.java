package com.example.gymclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText userName, userEmail, userWeight, userPwd, userDOG,userContact;
    private Button userReg;

    private FirebaseAuth mAuth;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//fullscreen
        setContentView(R.layout.activity_register);

        TextView t = (TextView) findViewById(R.id.text5);
        Typeface myCustomFont= Typeface.createFromAsset(getAssets(),"fonts/Sony_Sketch_EF.ttf");
        t.setTypeface(myCustomFont);//font style




        ProgressDialog loadingbar = new ProgressDialog(this);

        userName = findViewById(R.id.name);
        userEmail = findViewById(R.id.email);
        userWeight = findViewById(R.id.weight);
        userDOG = findViewById(R.id.dog);
        userPwd = findViewById(R.id.Password);
        userContact = findViewById(R.id.contact);
        

        userReg = findViewById(R.id.regbtn);

        mAuth = FirebaseAuth.getInstance();


        userReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });

    }

    private void CreateNewAccount() {

        final String name = userName.getText().toString();
        final String email = userEmail.getText().toString();
        final String weight = userWeight.getText().toString();
        final String password = userPwd.getText().toString();
        final String dog = userDOG.getText().toString();
        final String phone=userContact.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Name is Mandatory...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "E-mail is empty...", Toast.LENGTH_SHORT).show();
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(RegisterActivity.this, "Incorrect E-mail", Toast.LENGTH_SHORT).show();
        }if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Contact is empty...", Toast.LENGTH_SHORT).show();
        } else if (phone.length() != 10) {
            Toast.makeText(this, "Invalid Contact...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(weight)) {
            Toast.makeText(this, "Weight be empty...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(dog)) {
            Toast.makeText(this, "DOJ required...", Toast.LENGTH_SHORT).show();
        }  else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Create Password...", Toast.LENGTH_SHORT).show();
        } else {

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        String ClientID = firebaseUser.getUid();

                        reference = FirebaseDatabase.getInstance().getReference("Clients").child(ClientID);

                        HashMap<String, String> ClientMap = new HashMap();
                        ClientMap.put("Fullname", name);
                        ClientMap.put("EMail",email);
                        ClientMap.put("Contact",phone);
                        ClientMap.put("DateofJoining", dog);
                        ClientMap.put("Weight",weight);
                        ClientMap.put("Password",password);
                        ClientMap.put("image","default");


                        reference.setValue(ClientMap).addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if (task.isSuccessful())
                                {
                                    Intent DashMainIntent = new Intent(RegisterActivity.this,DashboardActivity.class);
                                    DashMainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(DashMainIntent);
                                    finish();
                                }
                            }
                        });

                        Toast.makeText(RegisterActivity.this, "Authenticated Successfully...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        String message = task.getException().getMessage();
                        Toast.makeText(RegisterActivity.this, "Error Occurred ;" + message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}
