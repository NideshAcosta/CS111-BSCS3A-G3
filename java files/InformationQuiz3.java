package com.example.businessincomefinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

public class InformationQuiz3 extends AppCompatActivity {
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
            {"Choose one sole proprietorship example. ", "Milk-Tea",
                    "Pepsi-Cola", " Petron", " Nike and Apple"},
            {"Vendors usually sold them late at night or very early in the morning because they were known to be potent aphrodisiacs.", "Balut and Penoy",
                    " Milk-Tea", "Ice Cream", "Kwek-Kwek"},
            {" When did men start wearing T-shirts as undergarments in the United States?", "1800s",
                    " 1700’s", "1900s", "2000s"},
            {" What makes Kwek-Kwek so popular in the Philippines?", "It is ideal for people who live in densely populated areas because of its delicious flavors and low price.", "Due to two factors: first, the humid and hot climate; second, Filipinos' sweet tooth.",
                    " Become popular for self-expression, advertising, protests, and souvenirs.", "All of the choices"},
            {"A fragrance business is a good place to start if you're looking for an entrepreneurial opportunity with __________", "Big profits ",
                    " High volume",
                    "  Deep pockets ",
                    " None of the above"},
            {" If you sell 50 pieces of balut and penoy for 10–12 pesos each, you'll make about ______ per week.",
                    " 3,500-5,250 ", "500-600", "700-1,000", "900-1,800"},
            {"What makes Milk-Tea so popular for teenagers in the Philippines? ", "Attributed to the fact that it is a convenient beverage.",
                    "Become popular for self-expression, advertising, protests, and souvenirs.",
                    "It is ideal for people who live in densely populated areas because of its delicious flavors and low price.", "All of the choices"},
            {"What do you think the term is for the total value of goods sold to a customer? ", "Sales", "Net Profit",
                    " Tax", " Expenses"},

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_quiz3);

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
        countLabel.setText("Lesson 3: Q" + quizCount);

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