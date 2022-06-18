package com.example.businessincomefinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class IncomeInput extends AppCompatActivity {

    EditText totalIncome, budgetI1, budgetI2, budgetI3, budgetI4, budgetI5, percentI1, percentI2, percentI3, percentI4, percentI5;
    TextView totalPercentage;
    Button calculate, help;
    Dialog dialog;
    ArrayList<Float> percentArray = new ArrayList<Float>();
    ArrayList<String> budgetArray = new ArrayList<String>();

    int counterData=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_input);


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

        help = findViewById(R.id.help);
        dialog = new Dialog(this);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHelpDialog();
            }
        });


        totalIncome = (EditText)findViewById(R.id.totalIncome);

        budgetI1 = (EditText)findViewById(R.id.budgetI1);
        budgetI2 = (EditText)findViewById(R.id.budgetI2);
        budgetI3 = (EditText)findViewById(R.id.budgetI3);
        budgetI4 = (EditText)findViewById(R.id.budgetI4);
        budgetI5 = (EditText)findViewById(R.id.budgetI5);

        percentI1 = (EditText)findViewById(R.id.percentI1);
        percentI2 = (EditText)findViewById(R.id.percentI2);
        percentI3 = (EditText)findViewById(R.id.percentI3);
        percentI4 = (EditText)findViewById(R.id.percentI4);
        percentI5 = (EditText)findViewById(R.id.percentI5);

        totalPercentage = (TextView) findViewById(R.id.totalPercent);

        calculate = (Button)findViewById(R.id.calculate);

        totalPercentage.setText("0.0");

        percentArray.add(Float.valueOf(0));
        percentArray.add(Float.valueOf(0));
        percentArray.add(Float.valueOf(0));
        percentArray.add(Float.valueOf(0));
        percentArray.add(Float.valueOf(0));

        budgetArray.add("");
        budgetArray.add("");
        budgetArray.add("");
        budgetArray.add("");
        budgetArray.add("");

        percentI1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String percent = (percentI1.getText().toString());
                Boolean isEmptyP = percent.isEmpty();
                getTotalIncome(0,percent,isEmptyP);

            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        percentI2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String percent = (percentI2.getText().toString());
                Boolean isEmpty = percent.isEmpty();
                getTotalIncome(1,percent,isEmpty);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        percentI3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String percent = (percentI3.getText().toString());
                Boolean isEmpty = percent.isEmpty();
                getTotalIncome(2,percent,isEmpty);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        percentI4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String percent = (percentI4.getText().toString());
                Boolean isEmpty = percent.isEmpty();
                getTotalIncome(3,percent,isEmpty);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        percentI5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String percent = (percentI5.getText().toString());
                Boolean isEmpty = percent.isEmpty();
                getTotalIncome(4,percent,isEmpty);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String budget = "";
                budget = budgetI1.getText().toString().trim();
                setBudgetArray(0,budget,budget.isEmpty());

                budget = budgetI2.getText().toString().trim();
                setBudgetArray(1,budget,budget.isEmpty());

                budget = budgetI3.getText().toString().trim();
                setBudgetArray(2,budget,budget.isEmpty());

                budget = budgetI4.getText().toString().trim();
                setBudgetArray(3,budget,budget.isEmpty());

                budget = budgetI5.getText().toString().trim();
                setBudgetArray(4,budget,budget.isEmpty());

                inputValidation();
            }
        });

    }

    private void openHelpDialog() {
        dialog.setContentView(R.layout.help_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button closeHelp = dialog.findViewById(R.id.closeHelp);

        closeHelp.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                            dialog.dismiss();
                                         }
                                     });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public void setBudgetArray(int index, String value, boolean isEmpty)
    {
        if (!isEmpty)
        {
            budgetArray.set(index, value);
        }
        else
        {
            budgetArray.set(index, "");
        }
    }

    public void getTotalIncome(int index, String value, boolean isEmpty)
    {
        float totalP=0;
        if (!isEmpty)
        {
            percentArray.set(index, Float.parseFloat(value));
        }
        else
        {
            percentArray.set(index, Float.valueOf(0));
        }
        int percentArraySize = percentArray.size();
        for(int counterIncome=0; counterIncome<percentArraySize; counterIncome++)
        {
            totalP = totalP + percentArray.get(counterIncome);
        }

        totalPercentage.setText(String.valueOf(totalP));
    }

    public void inputValidation ()
    {
        int incompleteV=0;
        counterData=0;
        ArrayList<Float> finalPercentArray = new ArrayList<Float>();
        ArrayList<String> finalBudgetArray = new ArrayList<String>();
        Boolean totalIncomeIsEmpty = totalIncome.getText().toString().isEmpty();

        for(int counterV=0; counterV<budgetArray.size(); counterV++)
        {
            if (budgetArray.get(counterV) !="" || percentArray.get(counterV)!=0.0)
            {
                counterData++;
            }

            if(budgetArray.get(counterV) =="" && percentArray.get(counterV)!=0.0)
            {
                incompleteV=1;
            }
            else if(budgetArray.get(counterV) !="" && percentArray.get(counterV)==0.0)
            {
                incompleteV=1;
            }
            else if (budgetArray.get(counterV) !="" && percentArray.get(counterV)!=0.0)
            {
                finalBudgetArray.add(budgetArray.get(counterV));
                finalPercentArray.add(percentArray.get(counterV));
            }
        }

        if(totalIncomeIsEmpty)
        {
            Toast.makeText(this, "Please input your total income", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (counterData>1) //may laman atleast 2
            {
                if(incompleteV==1)
                {
                    Toast.makeText(this, "Please complete the pair input fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    float total = Float.parseFloat(totalPercentage.getText().toString());
                    if(total<100)
                    {
                        Toast.makeText(IncomeInput.this, "Please distribute 100%", Toast.LENGTH_SHORT).show();
                    }
                    else if (total>100)
                    {
                        Toast.makeText(IncomeInput.this, "Please don't exceed 100%", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Bundle b = new Bundle();
                        Intent i=new Intent(IncomeInput.this, IncomeResults.class);
                        i.putExtra("finalBudgetArray", finalBudgetArray);
                        i.putExtra("finalPercentArray", finalPercentArray);
                        i.putExtra("totalIncome", Double.parseDouble(totalIncome.getText().toString()));
                        startActivity(i);
                    }

                }
            }
            else if (counterData==1)
            {
                Toast.makeText(this, "Please budget income to atleast 2 categories", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Please input some data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}