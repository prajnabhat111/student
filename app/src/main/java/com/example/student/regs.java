package com.example.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class regs extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth myauth;
    private EditText name, email, eusn, esem, secc, passw,cpas;
    private Button submit;
    private String vname, vemail, veusn, vsecc, vpassw, sem,vcpas;
    private ProgressBar pg;
    private int type;
    private FirebaseUser ussr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regs);
        name = findViewById(R.id.name2);
        email = findViewById(R.id.email2);
        eusn = findViewById(R.id.eusn2);
        esem = findViewById(R.id.subject1);
        secc = findViewById(R.id.subject2);
        passw = findViewById(R.id.pas2);
        cpas = findViewById(R.id.cpas2);
        pg=findViewById(R.id.progressBar3);
        pg.setVisibility(View.GONE);
        myauth = FirebaseAuth.getInstance();
        findViewById(R.id.sub2).setOnClickListener(this);
    }

    public void registerUser() {

        vname = name.getText().toString().trim();
        vemail = email.getText().toString().trim();
        veusn = eusn.getText().toString().trim();
        vsecc = secc.getText().toString().trim();
        vpassw = passw.getText().toString().trim();
        vcpas = cpas.getText().toString().trim();
        sem = esem.getText().toString().trim();


        if (TextUtils.isEmpty(vemail)||TextUtils.isEmpty(veusn)||TextUtils.isEmpty(vsecc)||TextUtils.isEmpty(vpassw)||TextUtils.isEmpty(sem)||TextUtils.isEmpty(vname)||TextUtils.isEmpty(vcpas))
            Toast.makeText(regs.this,"Fields can't be empty",Toast.LENGTH_LONG).show();
        else if(!vcpas.equals(vpassw))
            Toast.makeText(regs.this, "Please enter the same password", Toast.LENGTH_LONG).show();

        else {

            pg.setVisibility(View.VISIBLE);

            myauth.createUserWithEmailAndPassword(vemail, vpassw)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                //store in db
                                user usr = new user(vname, vemail, veusn, vsecc, sem, type);
                                FirebaseDatabase.getInstance().getReference("Students").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(usr).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        pg.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            Toast.makeText(regs.this, "registration successfull", Toast.LENGTH_LONG).show();
                                            finish();
                                            Intent intent = new Intent(regs.this, mainstud.class);
                                            startActivity(intent);
                                        }
                                        else{
                                            ussr = FirebaseAuth.getInstance().getCurrentUser();
                                            ussr.delete();
                                            Toast.makeText(regs.this, "Sorry registration unsuccessfull", Toast.LENGTH_LONG).show();

                                        }}
                                });

                            } else {
                                pg.setVisibility(View.GONE);
                                Toast.makeText(regs.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.sub2:
                registerUser();
                break;
        }
    }
}

