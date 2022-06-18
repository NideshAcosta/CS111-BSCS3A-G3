package com.example.businessincomefinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class IncomeResults extends AppCompatActivity {

    TextView totalIncomeR, budgetO1, budgetO2, budgetO3, budgetO4, budgetO5, percentO1, percentO2, percentO3, percentO4, percentO5, dateTime;
    Button calculateAgain , send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_results);

        Intent intent = getIntent();
        double totalIncome = intent.getDoubleExtra("totalIncome", 0.0);
        ArrayList<String> finalBudgetArray = (ArrayList<String>) getIntent().getSerializableExtra("finalBudgetArray");
        ArrayList<Float> finalPercentArray = (ArrayList<Float>) getIntent().getSerializableExtra("finalPercentArray");

        dateTime = findViewById(R.id.dateTime);

        dateTime.setText(getDateTime());


        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);

        bottomNav.setSelectedItemId(R.id.calculation);

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.calculation:
                        return true;
                    case R.id.information:
                        Intent intent = new Intent(getApplicationContext(), InformationLessonLanding.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        totalIncomeR = (TextView) findViewById(R.id.totalIncomeR);

        percentO1 = (TextView) findViewById(R.id.percentO1);
        percentO2 = (TextView) findViewById(R.id.percentO2);
        percentO3 = (TextView) findViewById(R.id.percentO3);
        percentO4 = (TextView) findViewById(R.id.percentO4);
        percentO5 = (TextView) findViewById(R.id.percentO5);

        budgetO1 = (TextView)findViewById(R.id.budgetO1);
        budgetO2 = (TextView)findViewById(R.id.budgetO2);
        budgetO3 = (TextView)findViewById(R.id.budgetO3);
        budgetO4 = (TextView)findViewById(R.id.budgetO4);
        budgetO5 = (TextView)findViewById(R.id.budgetO5);

        calculateAgain = (Button)findViewById(R.id.calculateAgain);

        totalIncomeR.setText(String.valueOf(totalIncome));
        int arraySize = finalBudgetArray.size();

        switch (arraySize)
        {
            case 1:
                percentO1.setVisibility(View.VISIBLE);
                percentO1.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(0))));
                budgetO1.setVisibility(View.VISIBLE);
                budgetO1.setText(finalBudgetArray.get(0));
                break;
            case 2:
                percentO1.setVisibility(View.VISIBLE);
                percentO1.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(0))));
                budgetO1.setVisibility(View.VISIBLE);
                budgetO1.setText(finalBudgetArray.get(0));

                percentO2.setVisibility(View.VISIBLE);
                percentO2.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(1))));
                budgetO2.setVisibility(View.VISIBLE);
                budgetO2.setText(finalBudgetArray.get(1));
                break;
            case 3:
                percentO1.setVisibility(View.VISIBLE);
                percentO1.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(0))));
                budgetO1.setVisibility(View.VISIBLE);
                budgetO1.setText(finalBudgetArray.get(0));

                percentO2.setVisibility(View.VISIBLE);
                percentO2.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(1))));
                budgetO2.setVisibility(View.VISIBLE);
                budgetO2.setText(finalBudgetArray.get(1));

                percentO3.setVisibility(View.VISIBLE);
                percentO3.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(2))));
                budgetO3.setVisibility(View.VISIBLE);
                budgetO3.setText(finalBudgetArray.get(2));
                break;
            case 4:
                percentO1.setVisibility(View.VISIBLE);
                percentO1.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(0))));
                budgetO1.setVisibility(View.VISIBLE);
                budgetO1.setText(finalBudgetArray.get(0));

                percentO2.setVisibility(View.VISIBLE);
                percentO2.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(1))));
                budgetO2.setVisibility(View.VISIBLE);
                budgetO2.setText(finalBudgetArray.get(1));

                percentO3.setVisibility(View.VISIBLE);
                percentO3.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(2))));
                budgetO3.setVisibility(View.VISIBLE);
                budgetO3.setText(finalBudgetArray.get(2));

                percentO4.setVisibility(View.VISIBLE);
                percentO4.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(3))));
                budgetO4.setVisibility(View.VISIBLE);
                budgetO4.setText(finalBudgetArray.get(3));
                break;
            case 5:
                percentO1.setVisibility(View.VISIBLE);
                percentO1.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(0))));
                budgetO1.setVisibility(View.VISIBLE);
                budgetO1.setText(finalBudgetArray.get(0));

                percentO2.setVisibility(View.VISIBLE);
                percentO2.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(1))));
                budgetO2.setVisibility(View.VISIBLE);
                budgetO2.setText(finalBudgetArray.get(1));

                percentO3.setVisibility(View.VISIBLE);
                percentO3.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(2))));
                budgetO3.setVisibility(View.VISIBLE);
                budgetO3.setText(finalBudgetArray.get(2));

                percentO4.setVisibility(View.VISIBLE);
                percentO4.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(3))));
                budgetO4.setVisibility(View.VISIBLE);
                budgetO4.setText(finalBudgetArray.get(3));

                percentO5.setVisibility(View.VISIBLE);
                percentO5.setText(convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(4))));
                budgetO5.setVisibility(View.VISIBLE);
                budgetO5.setText(finalBudgetArray.get(4));
                break;

        }

        calculateAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(IncomeResults.this, IncomeInput.class);
                startActivity(i);
            }
        });

        send = findViewById(R.id.send);
        send.setOnClickListener(view -> {
            File file = savePhoto();
            if(file!=null)
                sharePhoto(file);
        });

    }

    public double getPart (double totalincome, double percent)
    {
        //not reading ()

        double part = totalincome*percent/100;
        return part;
    }
    public String convertDecimalPlace(double part)
    {
        DecimalFormat percentageFormat = new DecimalFormat("0.00");
        String finalPercentage = percentageFormat.format(part);
        return finalPercentage;

    }

        public String getDateTime()
        {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy | h:mm a");
            String formattedDate = dateFormat.format(calendar.getTime());
            return formattedDate;
        }

        public String getArrayData()
        {
            Intent intent = getIntent();
            double totalIncome = intent.getDoubleExtra("totalIncome", 0.0);
            ArrayList<String> finalBudgetArray = (ArrayList<String>) getIntent().getSerializableExtra("finalBudgetArray");
            ArrayList<Float> finalPercentArray = (ArrayList<Float>) getIntent().getSerializableExtra("finalPercentArray");

            String message="";
            for(int count=0; count<finalBudgetArray.size(); count++)
            {
                message +=  finalBudgetArray.get(count) + " - ₱" + convertDecimalPlace(getPart(totalIncome, finalPercentArray.get(count)));
                if(count!=finalBudgetArray.size()-1)
                {
                    message=message+", ";
                }
            }
            return message;
        }

    private void sharePhoto(File file) {
        Uri uri;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            uri = FileProvider.getUriForFile(this, getPackageName()+".provider", file);
        }
        else
        {
            uri = Uri.fromFile(file);
        }

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Income Budgeting - " + getDateTime());
        intent.putExtra(Intent.EXTRA_TEXT, getArrayData());
        intent.putExtra(Intent.EXTRA_STREAM, uri);

        try{
            startActivity(Intent.createChooser(intent, "Share via"));

        }
        catch(ActivityNotFoundException e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            send.performClick();
        }
        else
        {
            Toast.makeText(this, "Permission is denied", Toast.LENGTH_SHORT).show();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private File savePhoto() {
        if(!checkPermission())
            return null;
        try{
            String dirPath = Environment.getExternalStorageDirectory().toString()+"/BizLens";
            File fileDir = new File(dirPath);
            if (!fileDir.exists())
                fileDir.mkdir();
            String photoPath = dirPath+"/Screenshot_"+new Date().getTime()+".png";
            Bitmap bitmap = takeScreenshot();
            File file = new File(photoPath);
            FileOutputStream fOutStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOutStream);
            fOutStream.flush();
            fOutStream.close();

            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap takeScreenshot() {
        View view = findViewById(R.id.screen);
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private boolean checkPermission() {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},1);
            return false;
        }
        return true;
    }

}