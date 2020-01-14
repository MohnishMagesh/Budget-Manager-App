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

public class IncomeChart extends AppCompatActivity {
    int[] yData;
    String[] xData = {"Salary","Bonus","Rental","Others"};
    int Salary_1, Bonus_1, Rental_1, Others_1;

    PieChart pieChart;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_chart);

        tv = findViewById(R.id.textViewTotalIncomes);

        Bundle data = getIntent().getExtras();

        String str_salary = data.getString("Extract_Salary");
        String str_bonus = data.getString("Extract_Bonus");
        String str_rental = data.getString("Extract_Rental");
        String str_others = data.getString("Extract_Others");

        Salary_1 = Integer.parseInt(str_salary);
        Bonus_1 = Integer.parseInt(str_bonus);
        Rental_1 = Integer.parseInt(str_rental);
        Others_1 = Integer.parseInt(str_others);

        yData = new int[]{Salary_1,Bonus_1,Rental_1,Others_1};

        pieChart = findViewById(R.id.piechart);
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);

        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        addDataSet(pieChart);

        int total_incomes = Salary_1+Bonus_1+Rental_1+Others_1;
        String str = "Total incomes = "+total_incomes;

        tv.setText(str);




    }

    public void addDataSet(PieChart chart){
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        for(int i=0; i<yData.length; i++){
            yEntries.add(new PieEntry(yData[i],xData[i]));
        }


        PieDataSet pieDataSet = new PieDataSet(yEntries,"Incomes");
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
