package com.example.businessincomefinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class InformationResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_results);

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

        TextView resultsLabel = (TextView) findViewById(R.id.resultLabel);
        TextView totalScoreLabel = (TextView) findViewById(R.id.totalScoreLabel);
        TextView affirmation = (TextView) findViewById(R.id.affirmation);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        if (score == 5){
            affirmation.setText("PERFECTION!");
        }
        else if (score >= 3){
            affirmation.setText("Good Job!");
        }
        else if (score >= 0){
            affirmation.setText("It's okay. You can always try again.");
        }
        else{
            affirmation.setText(" ");
        }
        resultsLabel.setText(score + "/ 5");

        totalScoreLabel.setText("Total Score :"+ score);
    }

    public void returnTop(View view){
        Intent intent = new Intent(getApplicationContext(),InformationLessonLanding.class);
        startActivity(intent);
    }

}