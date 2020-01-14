package com.example.trial_24;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ExpenseCalculator extends AppCompatActivity {

    TextView tv;
    EditText et;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_calculator);
        et = findViewById(R.id.editText11);


        Bundle data = getIntent().getExtras();

        String str_grocery = data.getString("GroceryKey");
        String str_food = data.getString("FoodKey");
        String str_electricity = data.getString("ElectricityKey");
        String str_transportation = data.getString("TransportationKey");
        String str_subscriptions = data.getString("SubscriptionsKey");
        String str_electronics = data.getString("ElectronicsKey");
        String str_clothing = data.getString("ClothingKey");
        String str_books = data.getString("BooksKey");
        String str_online_payment = data.getString("Online_paymentKey");
        String str_health = data.getString("HealthKey");
        String str_others = data.getString("OthersKey");

        if(str_grocery==null){
            str_grocery = "";
        }
        if(str_food==null){
            str_food = "";
        }
        if(str_electricity==null){
            str_electricity = "";
        }
        if(str_transportation==null){
            str_transportation = "";
        }
        if(str_subscriptions==null){
            str_subscriptions = "";
        }
        if(str_electronics==null){
            str_electronics = "";
        }
        if(str_clothing==null){
            str_clothing = "";
        }
        if(str_books==null){
            str_books = "";
        }
        if(str_online_payment==null){
            str_online_payment = "";
        }
        if(str_health==null){
            str_health = "";
        }
        if(str_others==null){
            str_others = "";
        }

        tv = findViewById(R.id.textView11);
        String final_str = str_grocery+str_food+str_electricity+str_transportation+str_subscriptions+str_electronics+str_clothing+str_books+str_online_payment+str_health+str_others;
        tv.setText(final_str);

        btn = findViewById(R.id.buttonAdd1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });

    }

    private void Add(){
        String extract_hash = tv.getText().toString();
        String amount_hash  = et.getText().toString();
        if(!amount_hash.isEmpty()){
            Intent intent2 = new Intent(ExpenseCalculator.this, ExpenseList.class);

            intent2.putExtra("Extracting3", extract_hash);
            intent2.putExtra("Extracting4", amount_hash);

            startActivity(intent2);
        }
    }
}
