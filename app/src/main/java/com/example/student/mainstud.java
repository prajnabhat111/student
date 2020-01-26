package com.example.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class mainstud extends AppCompatActivity {

    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FirebaseAuth auth;
    Button dbms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainstud);
        dbms = findViewById(R.id.button6);

        setUpToolbar();
        navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(mainstud.this,mainstud.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_notify:
                        Toast.makeText(mainstud.this,"no notifications",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_his:
                        Toast.makeText(mainstud.this,"yeah this is history",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_prog:
                        Toast.makeText(mainstud.this,"yeah this is progress",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_lead:
                        Toast.makeText(mainstud.this,"yeah this is lead board",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_set:
                        Toast.makeText(mainstud.this,"yeah this is settings",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_about:
                        Toast.makeText(mainstud.this,"we are great",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_cont:
                        Toast.makeText(mainstud.this,"hiiiiiiii",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_logout:
                        logOut();
                }
                return false;
            }
        });
        dbms = findViewById(R.id.button6);
        dbms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainstud.this,ans.class);
                startActivity(intent);
            }
        });


    }
    private void setUpToolbar(){
        drawerLayout=findViewById(R.id.drawerLayout);
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
        Intent intent = new Intent(mainstud.this,student.class);
        startActivity(intent);
    }

}
