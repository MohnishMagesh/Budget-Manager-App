package com.example.trial_24;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class IncomeCalculator extends AppCompatActivity {
    TextView tv;
    EditText et;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_calculator);

        et = findViewById(R.id.editText19);



        //for extracting data from the previous activity
        Bundle data = getIntent().getExtras();

        String str_salary = data.getString("SalaryKey");
        String str_bonus = data.getString("BonusKey");
        String str_rental = data.getString("RentalKey");
        String str_others = data.getString("OthersKey");

        if(str_salary==null){
            str_salary = "";
        }
        if(str_bonus==null){
            str_bonus = "";
        }
        if(str_rental==null){
            str_rental = "";
        }
        if(str_others==null){
            str_others = "";
        }

        tv = findViewById(R.id.textView19);
        String final_str = str_salary+str_bonus+str_rental+str_others;
        tv.setText(final_str);

        btn = findViewById(R.id.buttonAdd19);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });



    }

    private void Add(){
        String extract = tv.getText().toString();
        String amount  = et.getText().toString();
        if(!amount.isEmpty()){
            Intent intent1 = new Intent(IncomeCalculator.this, IncomeList.class);

            intent1.putExtra("Extracting1", extract);
            intent1.putExtra("Extracting2", amount);

            startActivity(intent1);
        }
    }

}
