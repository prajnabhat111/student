package com.example.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;




public class student extends AppCompatActivity implements View.OnClickListener {
    private Button submit;
    private TextView nr;
    private EditText password,email;
    private String pass,em;
    private ProgressBar pg;
    private FirebaseAuth myauth;
    private FirebaseUser ussr;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        myauth = FirebaseAuth.getInstance();



        nr=findViewById(R.id.hyperlinks);
        password = findViewById(R.id.password);
        email =  findViewById(R.id.loginmail);
        pg=findViewById(R.id.progressBar2);
        pg.setVisibility(View.GONE);
        submit = findViewById(R.id.submit);

        findViewById(R.id.submit).setOnClickListener(this);


        nr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentech();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

    }
    public void loginUser(){
        pass = password.getText().toString().trim();
        em = email.getText().toString().trim();

        if (TextUtils.isEmpty(em))
            Toast.makeText(student.this,"please enter the email address",Toast.LENGTH_LONG);

        if(TextUtils.isEmpty(pass))
            Toast.makeText(student.this,"please enter password",Toast.LENGTH_LONG);

        pg.setVisibility(View.VISIBLE);
        myauth.signInWithEmailAndPassword(em,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pg.setVisibility(View.GONE);
                        if(task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        }
                        else{
                            pg.setVisibility(View.GONE);
                            Toast.makeText(student.this, "Email or password incorrect", Toast.LENGTH_LONG).show();
                         }
                    }
                });

    }
    public void opensubmit(){
        finish();
        Intent intent = new Intent(student.this,mainstud.class);
        startActivity(intent);
    }
    public void opentech(){
        finish();
        Intent intent = new Intent(student.this,teacher.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.submit:
                loginUser();
                break;
        }
    }
    private void onAuthSuccess(FirebaseUser user) {

        if (user != null) {
            ref = FirebaseDatabase.getInstance().getReference().child("Students").child(user.getUid()).child("type");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Integer value = dataSnapshot.getValue(Integer.class);
                    if(value == 1) {
                        startActivity(new Intent(student.this, mainstud.class));
                        Toast.makeText(student.this, "You're Logged in as Student", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(student.this, ques.class));
                        Toast.makeText(student.this, "You're Logged in as Teacher", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    Toast.makeText(student.this, (CharSequence) databaseError, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
