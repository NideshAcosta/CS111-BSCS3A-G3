package com.example.businessincomefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingPage extends AppCompatActivity {
    Button landingInfo, landingCalcu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        landingInfo = (Button)findViewById(R.id.landingInfo);
        landingInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LandingPage.this, InformationLessonLanding.class);
                startActivity(i);
            }
        });

        landingCalcu = (Button)findViewById(R.id.landingIncome);
        landingCalcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LandingPage.this, IncomeInput.class);
                startActivity(i);
            }
        });

    }
}