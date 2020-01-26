package com.example.student;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Map;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class add extends AppCompatActivity {
    FloatingActionButton plus;
    Dialog ed;
    Button done,cancel,next;
    EditText q ,a,b,c,d;
    String eq,ea,eb,ec,e1d;
    private TextView tv,t1,t2,t3,t4;
    private FirebaseAuth myauth;
    private FirebaseUser ussr;
    private DatabaseReference ref;
    private FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add3);
        plus = findViewById(R.id.floatingActionButton);
        ed = new Dialog(this);
        myauth = FirebaseAuth.getInstance();
        ussr = myauth.getCurrentUser();
        next = findViewById(R.id.next);



        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpopup();
            }
        });

        tv=findViewById(R.id.textView4);
        t1=findViewById(R.id.textView5);

        ref = FirebaseDatabase.getInstance().getReference().child("questions");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //Map<String,Object>  map = (Map<String, Object>) dataSnapshot.getValue();

                   // for(String key : map.keySet()) {
                       // Object mmap = map.get(key);


                       // String qe = (String)mmap.get("eq");
                       // String o1 = mmap.get("ea");
                       // String o2 = map.get("eb");
                       // String o3 = map.get("ec");
                      //  String o4 = map.get("e1d");


                       // tv.setText(qe);
                       // t1.setText(o1);
                       // t2.setText(o2);
                       // t3.setText(o3);
                       // t4.setText(o4);

                       // next.setOnClickListener(new View.OnClickListener() {
                            //@Override
                          //  public void onClick(View v) {


                         //   }
                        //});

                    //}

                   HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();

                    for (String key : dataMap.keySet()) {

                        Object data = dataMap.get(key);

                        try {
                            HashMap<String, Object> userData = (HashMap<String, Object>) data;

                            Tech mUser = new Tech((String) userData.get("eq"), (String) userData.get("ea"));
                           // String sg = dataSnapshot.getValue(String.class);
                            tv.setText(mUser.getEq());
                            t1.setText(mUser.getEa());



                        } catch (ClassCastException cce) {
                                 Toast.makeText(add.this,"error",Toast.LENGTH_LONG).show();

                        }

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void showpopup(){
        ed.setContentView(R.layout.popup);
        done= ed.findViewById(R.id.done);
        cancel = ed.findViewById(R.id.button);
        q = ed.findViewById(R.id.editText);
        a = ed.findViewById(R.id.editText2);
        //b = ed.findViewById(R.id.editText3);
        //c = ed.findViewById(R.id.editText4);
        //d = ed.findViewById(R.id.editText5);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eq = q.getText().toString().trim();
                ea = a.getText().toString().trim();
                //eb = b.getText().toString().trim();
                //ec = c.getText().toString().trim();
                //e1d = d.getText().toString().trim();
                if(TextUtils.isEmpty(eq)||TextUtils.isEmpty(ea)) {
                    Toast.makeText(add.this, "please fill in all the details", Toast.LENGTH_LONG).show();
                    showpopup();
                }
                else {
                    Tech t = new Tech(eq, ea);
                    db=FirebaseDatabase.getInstance();
                    String key=db.getReference().child("questions").push().getKey();

                    FirebaseDatabase.getInstance().getReference().child("questions").child(key)
                            .setValue(t).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(add.this, "question added successfully", Toast.LENGTH_LONG).show();
                            ed.dismiss();
                        }
                    });
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed.dismiss();
            }
        });
        ed.getWindow();
        ed.show();
    }
}
