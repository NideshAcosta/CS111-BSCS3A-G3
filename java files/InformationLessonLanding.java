package com.example.businessincomefinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class InformationLessonLanding extends AppCompatActivity {
    Button lesson1, lesson2, lesson3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_lesson_landing);

        lesson1 = (Button)findViewById(R.id.lesson1);
        lesson2 = (Button)findViewById(R.id.lesson2);
        lesson3 = (Button)findViewById(R.id.lesson3);

        lesson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(InformationLessonLanding.this, InformationLesson1.class);
                startActivity(i);
            }
        });

        lesson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(InformationLessonLanding.this, InformationLesson2.class);
                startActivity(i);
            }
        });

        lesson3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(InformationLessonLanding.this, InformationLesson3.class);
                startActivity(i);
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);

        bottomNav.setSelectedItemId(R.id.information);

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.information:
                        return true;
                    case R.id.calculation:
                        Intent intent = new Intent(getApplicationContext(), IncomeInput.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}