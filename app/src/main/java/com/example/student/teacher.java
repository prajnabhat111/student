package com.example.student;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class teacher extends AppCompatActivity {
    private Button student;
    private Button teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        student=findViewById(R.id.student);
        teacher=findViewById(R.id.teacher);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openregs();
            }
        });

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openregt();
            }
        });
    }

    public void openregs(){
        finish();
        Intent intent = new Intent(teacher.this,regs.class);
        startActivity(intent);
    }
    public void openregt(){
        finish();
        Intent intent = new Intent(teacher.this,regt.class);
        startActivity(intent);
    }

}
