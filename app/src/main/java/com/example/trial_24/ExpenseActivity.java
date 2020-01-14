package com.example.trial_24;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExpenseActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btn10 = findViewById(R.id.button10);
        btn11 = findViewById(R.id.button11);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1Click();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2Click();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button3Click();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button4Click();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button5Click();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button6Click();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button7Click();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button8Click();
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button9Click();
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button10Click();
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button11Click();
            }
        });






    }

    public void button1Click(){
        Intent i = new Intent(this,ExpenseCalculator.class);
        String Grocery = "Grocery";
        i.putExtra("GroceryKey",Grocery);
        startActivity(i);
    }

    public void button2Click(){
        Intent i = new Intent(this,ExpenseCalculator.class);
        String Food = "Food";
        i.putExtra("FoodKey",Food);
        startActivity(i);
    }

    public void button3Click(){
        Intent i = new Intent(this,ExpenseCalculator.class);
        String Electricity = "Electricity";
        i.putExtra("ElectricityKey",Electricity);
        startActivity(i);
    }

    public void button4Click(){
        Intent i = new Intent(this,ExpenseCalculator.class);
        String Transportation = "Transportation";
        i.putExtra("TransportationKey",Transportation);
        startActivity(i);
    }

    public void button5Click(){
        Intent i = new Intent(this,ExpenseCalculator.class);
        String Subscriptions = "Subscriptions";
        i.putExtra("SubscriptionsKey",Subscriptions);
        startActivity(i);
    }

    public void button6Click(){
        Intent i = new Intent(this,ExpenseCalculator.class);
        String Electronics = "Electronics";
        i.putExtra("ElectronicsKey",Electronics);
        startActivity(i);
    }

    public void button7Click(){
        Intent i = new Intent(this,ExpenseCalculator.class);
        String Clothing = "Clothing";
        i.putExtra("ClothingKey",Clothing);
        startActivity(i);
    }

    public void button8Click(){
        Intent i = new Intent(this,ExpenseCalculator.class);
        String Books = "Books";
        i.putExtra("BooksKey",Books);
        startActivity(i);
    }

    public void button9Click(){
        Intent i = new Intent(this,ExpenseCalculator.class);
        String Online_payment = "Online_payment";
        i.putExtra("Online_paymentKey",Online_payment);
        startActivity(i);
    }

    public void button10Click(){
        Intent i = new Intent(this,ExpenseCalculator.class);
        String Health = "Health";
        i.putExtra("HealthKey",Health);
        startActivity(i);
    }

    public void button11Click(){
        Intent i = new Intent(this,ExpenseCalculator.class);
        String Others = "Others";
        i.putExtra("OthersKey",Others);
        startActivity(i);
    }










}
