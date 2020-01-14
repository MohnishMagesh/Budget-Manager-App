package com.example.trial_24;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class ExpenseChart extends AppCompatActivity {
    int[] yData;
    String[] xData = {"Grocery","Food","Electricity","Transportation","Subscriptions","Electronics",
            "Clothing","Books","Online_payments","Health","Others"};
    int Grocery_1, Food_1, Electricity_1, Transportation_1, Subscriptions_1;
    int Electronics_1, Clothing_1, Books_1, Online_payments_1, Health_1, Others_1;

    PieChart pieChart;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_chart);

        tv = findViewById(R.id.textViewTotalExpenses);

        Bundle data = getIntent().getExtras();

        String str_grocery = data.getString("Extract_Grocery");
        String str_food = data.getString("Extract_Food");
        String str_electricity = data.getString("Extract_Electricity");
        String str_transportation = data.getString("Extract_Transportation");
        String str_subscriptions = data.getString("Extract_Subscriptions");
        String str_electronics = data.getString("Extract_Electronics");
        String str_clothing = data.getString("Extract_Clothing");
        String str_books = data.getString("Extract_Books");
        String str_online_payments = data.getString("Extract_Online_payments");
        String str_health = data.getString("Extract_Health");
        String str_others = data.getString("Extract_Others");


        Grocery_1 = Integer.parseInt(str_grocery);
        Food_1 = Integer.parseInt(str_food);
        Electricity_1 = Integer.parseInt(str_electricity);
        Transportation_1 = Integer.parseInt(str_transportation);
        Subscriptions_1 = Integer.parseInt(str_subscriptions);
        Electronics_1 = Integer.parseInt(str_electronics);
        Clothing_1 = Integer.parseInt(str_clothing);
        Books_1 = Integer.parseInt(str_books);
        Online_payments_1 = Integer.parseInt(str_online_payments);
        Health_1 = Integer.parseInt(str_health);
        Others_1 = Integer.parseInt(str_others);

        yData = new int[]{Grocery_1, Food_1, Electricity_1, Transportation_1, Subscriptions_1,
                Electronics_1, Clothing_1, Books_1, Online_payments_1, Health_1, Others_1};

        pieChart = findViewById(R.id.piechart1);
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);

        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        addDataSet(pieChart);
        int total_expenses = Grocery_1+Food_1+Electricity_1+Transportation_1+Subscriptions_1+Electronics_1+Clothing_1+Books_1+Online_payments_1+Health_1+Others_1;
        String str = "Total expenses = "+total_expenses;

        tv.setText(str);
    }

    public void addDataSet(PieChart chart){
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        for(int i=0; i<yData.length; i++){
            yEntries.add(new PieEntry(yData[i],xData[i]));
        }


        PieDataSet pieDataSet = new PieDataSet(yEntries,"Expenses");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(15);

        ArrayList<Integer> colours = new ArrayList<>();
        colours.add(Color.parseColor("#27AE60"));
        colours.add(Color.parseColor("#2ECC71"));
        colours.add(Color.parseColor("#F1C40F"));
        colours.add(Color.parseColor("#F39C12"));
        colours.add(Color.parseColor("#E67E22"));
        colours.add(Color.parseColor("#D35400"));
        colours.add(Color.parseColor("#17202A"));
        colours.add(Color.parseColor("#283747"));
        colours.add(Color.parseColor("#5D6D7E"));
        colours.add(Color.parseColor("#99A3A4"));
        colours.add(Color.parseColor("#AAB7B8"));
        colours.add(Color.parseColor("#D7DBDD"));

        pieDataSet.setColors(colours);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
