package com.example.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 4000;
    private FirebaseAuth myauth;
    private FirebaseUser ussr;
    private DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        myauth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (myauth.getCurrentUser() != null) {
                    ussr = myauth.getCurrentUser();
                    ref = FirebaseDatabase.getInstance().getReference().child("Students").child(ussr.getUid()).child("type");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Integer value = dataSnapshot.getValue(Integer.class);
                            if (value == 1) {
                                finish();
                                opensubmit();
                            } else {
                                finish();
                                openques();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(MainActivity.this, (CharSequence) databaseError, Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    Intent homeIntent = new Intent(MainActivity.this, student.class);
                    startActivity(homeIntent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }

        public void opensubmit(){
            finish();
            Intent intent = new Intent(MainActivity.this,mainstud.class);
            startActivity(intent);
        }
    public void openques(){
        finish();
        Intent intent = new Intent(MainActivity.this,ques.class);
        startActivity(intent);
    }
    }
