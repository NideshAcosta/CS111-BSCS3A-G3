package com.example.businessincomefinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

public class InformationQuiz1 extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private TextView timerCount;

    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            {"What is Sole Proprietorship?", "Business owned by a single person",
                    "Business owned by a cooperative", "Business owned by a partnership", "Business owned by a corporation"},
            {"Which of the following is not the advantage of Sole Proprietorship?", "Personal liability",
                    "Easy to establish", "You have complete control", "No limit to the number of people you can hire"},

            {"Which of the following do you think is the advantage of Sole Proprietorship?", " As the owner, you have complete control.",
                    "After the owner passes away, the business may continue.", "It is simple to raise capital funds.", "fully responsible for any issues."},

            {"Which business relies solely on one person's skills and abilities to run it?", "Sole Proprietorship ", "Partnership",
                    "Corporation", "None of the choices"},

            {"Who is eligible to start a sole proprietorship?", "Anyone",
                    " Your pet",
                    " You and your pals",
                    " Family "},
            {"Which of the following statements do you think is false?  ",
                    "A sole proprietorship does not come to an end. ", " When the owner of a sole proprietorship dies, the business ceases to exist.",
                    "A sole proprietorship business comes to an end whenever the owner decides to close it.",
                    "All of the above."},
            {"What are the characteristics of a sole proprietorship?", "Single Owner ", "No legal entity exists ", " Control by a single individual ", "None of the above"},
            {"What are the two significant risks of sole proprietorship? ", "Personal liability and raising money are difficult ", "Single owner",
                    " No profit-sharing arrangements", "Citizenship and Identity"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_quiz1);

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

        timerCount = findViewById(R.id.timerCount);
        countLabel = (TextView)findViewById(R.id.countLabel);
        questionLabel = (TextView)findViewById(R.id.questionLabel);
        answerBtn1 = (Button)findViewById(R.id.answerBtn1);
        answerBtn2 = (Button)findViewById(R.id.answerBtn2);
        answerBtn3 = (Button)findViewById(R.id.answerBtn3);
        answerBtn4 = (Button)findViewById(R.id.answerBtn4);

        for (int i = 0; i<QUIZ_COUNT; i++){
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][3]);
            tmpArray.add(quizData[i][4]);

            quizArray.add(tmpArray);
        }
        showNextQuestion();
    }

    public void showNextQuestion()
    {
        countLabel.setText("Lesson 1: Q" + quizCount);

        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());
        ArrayList<String> quiz = quizArray.get(randomNum);
        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        quiz.remove(0);
        Collections.shuffle(quiz);

        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));
        quizArray.remove(randomNum);

        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
    }
    private void startCountDown (){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                quizCount++;
                Intent intent = new Intent(getApplicationContext(),InformationResults.class);
                intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount);
                startActivity(intent);

            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int)(timeLeftInMillis / 1000)/60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);


        timerCount.setText(timeFormatted);

        if(timeLeftInMillis < 10000){
            timerCount.setTextColor(Color.RED);
        }else{
            timerCount.setTextColor(Color.WHITE);
        }
    }

    public void checkAnswer(View view){
        countDownTimer.cancel();
        Button answerBtn = (Button)findViewById(view.getId());
        String btnText = answerBtn.getText().toString();
        String alertTitle;
        if(btnText.equals(rightAnswer)){
            alertTitle = "Correct!";
            rightAnswerCount++;
            TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);

            scoreLabel.setText("Score: " + rightAnswerCount + "/5");
        }else{
            alertTitle = "Wrong...";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer: "+rightAnswer);
        builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                if(quizCount == QUIZ_COUNT ){
                    Intent intent = new Intent(getApplicationContext(),InformationResults.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount);
                    startActivity(intent);
                }
                else{
                    quizCount++;
                    showNextQuestion();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

}