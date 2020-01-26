package com.example.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ques extends AppCompatActivity {
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FirebaseAuth auth;
    ActionBar actionbar;
    Button dbms,aj,ai,se,me,cn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques);
        setUpToolbar();
        navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_home:
                        finish();
                        Intent intent = new Intent(ques.this,ques.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_notify:
                        Toast.makeText(ques.this,"no notifications",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_rec:
                        Toast.makeText(ques.this,"yeah this is recent",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_track:
                        Toast.makeText(ques.this,"yeah this is track",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_set1:
                        Toast.makeText(ques.this,"yeah this is settings",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_about1:
                        Toast.makeText(ques.this,"we are great",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_cont1:
                        Toast.makeText(ques.this,"hiiiiiiii",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_logout1:
                        logOut();
                }
                return false;
            }
        });

        dbms = findViewById(R.id.button6);
        dbms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ques.this,add.class);
                startActivity(intent);
            }
        });

    }
    private void setUpToolbar(){
        drawerLayout=findViewById(R.id.drawerLayout1);
        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
    public void logOut(){
        auth=FirebaseAuth.getInstance();
        auth.signOut();
        finish();
        Intent intent = new Intent(ques.this,student.class);
        startActivity(intent);
    }
}
