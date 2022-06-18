package com.example.businessincomefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIMEOUT= 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);
        Animation animation = new AlphaAnimation(1,0);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setStartOffset(500);
        animation.setDuration(1800);

        ImageView image = findViewById(R.id.imageView);
        image.setAnimation(animation);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, LandingPage.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_TIMEOUT);
    }
}