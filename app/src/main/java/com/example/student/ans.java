package com.example.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ans extends AppCompatActivity {
    private TextView tv;
    private EditText t1;
    private FirebaseAuth myauth;
    private FirebaseUser ussr;
    private DatabaseReference ref;
    private FirebaseDatabase db;
    private ImageView img;
    String sg,cm;
    Button next, prev;
    Dialog dl;
    private static final long SPLASH_TIME_OUT = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ans);
        myauth = FirebaseAuth.getInstance();
        ussr = myauth.getCurrentUser();
        next = findViewById(R.id.next);


        tv = findViewById(R.id.textView4);
        t1 = findViewById(R.id.editText6);


        ref = FirebaseDatabase.getInstance().getReference().child("questions");
        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();

                    for (String key : dataMap.keySet()) {

                        Object data = dataMap.get(key);

                        try {
                            HashMap<String, Object> userData = (HashMap<String, Object>) data;

                            Tech mUser = new Tech((String) userData.get("eq"), (String) userData.get("ea"));
                            tv.setText((String) mUser.getEq());
                            cm = (String) mUser.getEa();


                            next.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    t1 = findViewById(R.id.editText6);
                                    sg = t1.getText().toString().trim();
                                    validate(sg,cm);
                                }
                            });
                        } catch (ClassCastException cce) {
                            Toast.makeText(ans.this, "error", Toast.LENGTH_LONG).show();

                        }


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ans.this, "error", Toast.LENGTH_LONG).show();
            }
        });
    }



    public void validate(String sg, String cm) {
        //db=FirebaseDatabase.getInstance();
        // String key=db.getReference().child("questions").getKey();
        if(TextUtils.isEmpty(sg))
            Toast.makeText(ans.this,"enter ans",Toast.LENGTH_LONG).show();
                            if (cm.equals(sg)) {
                                Toast.makeText(ans.this,"correct",Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(ans.this,"incorrect",Toast.LENGTH_LONG).show();



                    }


                    //if (dataSnapshot.exists()) {
                    //HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();

                    //for (String key : dataMap.keySet()) {

                    //Object data = dataMap.get(key);

                    //try {
                    // HashMap<String, Object> userData = (HashMap<String, Object>) data;

                    //Tech mUser = new Tech((String) userData.get("eq"), (String) userData.get("ea"));
                    // String sg = dataSnapshot.getValue(String.class);
                    //tv.setText(mUser.getEq());
                    //t1.setText(mUser.getEa());


                    //} catch (ClassCastException cce) {
                    // Toast.makeText(ans.this, "error", Toast.LENGTH_LONG).show();

                    // }
                    //}

                    // }


    public void openincorrect(){
        Intent intent = new Intent(ans.this,ans.class);
        startActivity(intent);
    }
    }

