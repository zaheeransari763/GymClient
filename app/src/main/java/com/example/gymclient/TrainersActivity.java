package com.example.gymclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class TrainersActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference TrainerRef;
    RecyclerView TrainRec;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//fullscreen
        setContentView(R.layout.activity_trainers);

        mAuth = FirebaseAuth.getInstance();

        //String currentUserId = mAuth.getCurrentUser().getUid();

        TrainerRef = FirebaseDatabase.getInstance().getReference().child("Trainer");

        TrainRec = (RecyclerView) findViewById(R.id.TrainersRecList);
        TrainRec.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        TrainRec.setLayoutManager(linearLayoutManager);

        //RECYCLER VIEW FOR Dietecians UPDATE(CLOSE)
        startListen();

    }

    @Override
    protected void onStart() {
        super.onStart();
        startListen();
    }

    private void startListen()
    {
        Query query = FirebaseDatabase.getInstance().getReference().child("Trainer").limitToLast(50);
        FirebaseRecyclerOptions<Trainers> options = new FirebaseRecyclerOptions.Builder<Trainers>().setQuery(query, Trainers.class).build();
        FirebaseRecyclerAdapter<Trainers, TrainersActivity.TrainViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Trainers, TrainersActivity.TrainViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull TrainersActivity.TrainViewHolder holder, final int position, @NonNull Trainers model)
            {
                //final String PostKey = getRef(position).getKey();
                holder.setContactt(model.getContactt());
                holder.setExperiencee(model.getExperiencee());
                holder.setEmaill(model.getEmaill());
                holder.setNamee(model.getNamee());
                holder.setSpecialityy(model.getSpecialityy());
            }

            @NonNull
            @Override
            public TrainersActivity.TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainerlayout,parent,false);
                return new TrainersActivity.TrainViewHolder(view);
            }
        };
        TrainRec.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    /*And this is the static class*/
    public static class TrainViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public TrainViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mView = itemView;
        }

        public void setSpecialityy(String speciality)
        {
            TextView special = (TextView) mView.findViewById(R.id.trainer_speciality);
            special.setText(speciality);
        }

        public void setExperiencee(String experience)
        {
            TextView exp = (TextView) mView.findViewById(R.id.trainer_experience);
            exp.setText(experience);
        }

        public void setNamee(String name)
        {
            TextView namee = (TextView) mView.findViewById(R.id.trainer_name);
            namee.setText(name);
        }

        public void setEmaill(String email)
        {
            TextView emaill = (TextView) mView.findViewById(R.id.trainer_email);
            emaill.setText(email);
        }

        public void setContactt(String contact)
        {
            TextView contactt= (TextView) mView.findViewById(R.id.trainer_contact);
            contactt.setText(contact);
        }
    }
}
