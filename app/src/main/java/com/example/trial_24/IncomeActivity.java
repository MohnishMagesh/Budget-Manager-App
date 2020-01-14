package com.example.trial_24;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IncomeActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSalaryCalc();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBonusCalc();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRentalCalc();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toOthersCalc();
            }
        });


    }

    public void toSalaryCalc(){
        Intent i1 = new Intent(this,IncomeCalculator.class);
        String Salary = "Salary";
        i1.putExtra("SalaryKey",Salary);
        startActivity(i1);
    }

    public void toBonusCalc(){
        Intent i2 = new Intent(this,IncomeCalculator.class);
        String Bonus = "Bonus";
        i2.putExtra("BonusKey",Bonus);
        startActivity(i2);
    }

    public void toRentalCalc(){
        Intent i3 = new Intent(this,IncomeCalculator.class);
        String Rental = "Rental";
        i3.putExtra("RentalKey",Rental);
        startActivity(i3);
    }

    public void toOthersCalc(){
        Intent i4 = new Intent(this,IncomeCalculator.class);
        String Others = "Others";
        i4.putExtra("OthersKey",Others);
        startActivity(i4);
    }




}
