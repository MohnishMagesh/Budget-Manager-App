package com.example.trial_24;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IncomeList extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";
    private static final String TEMP_NAME = "temp.txt";

    private RecyclerView recyclerView;
    List<ModelClass> modelClassList;
    Adapter adapter;
    String extract_dash, amount_dash;
    Button buttonSave;
    Button extractGraph;

    Boolean file_exists = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_list);

        loadData();

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);

        //getting text from IncomeCalculator
        Bundle extras = getIntent().getExtras();
        extract_dash = extras.getString("Extracting1");
        amount_dash = extras.getString("Extracting2");
        addRubbish(extract_dash, amount_dash);
        saveData();

        //Divider decoration
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
//        itemDecoration.setDrawable(
//                ContextCompat.getDrawable(
//                        IncomeList.this, R.drawable.black_divider_hot
//                )
//        );
//        recyclerView.addItemDecoration(itemDecoration);

        //to save the items in our recycler view
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                //retrieve_list();
                //Save1();
                //Save2();

            }
        });

        extractGraph = findViewById(R.id.buttonExtractGraph);
        extractGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                separateIncomes();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int index_position = viewHolder.getAdapterPosition();
                ConstraintLayout constraintLayout = (ConstraintLayout) recyclerView.getChildAt(index_position);
                TextView text10 = constraintLayout.findViewById(R.id.textView1);
                TextView text11 = constraintLayout.findViewById(R.id.textView2);
                String expense_track = text10.getText().toString();
                String amount_track = text11.getText().toString();

                modelClassList.remove(index_position);
                adapter.notifyDataSetChanged();
                deletionFromTextFile(expense_track, amount_track);
                saveData();


            }
        }).attachToRecyclerView(recyclerView);
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(modelClassList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ModelClass>>() {}.getType();
        modelClassList = gson.fromJson(json, type);

        if(modelClassList == null){
            modelClassList = new ArrayList<>();
        }
    }

    //when item is swiped(deleted) it is then removed from the .txt file
    public void deletionFromTextFile(String expense_track, String amount_track){
        String new_extract_dash1,new_amount_dash1,index1;
        FileOutputStream fos2 = null;

        String sCurrentLine;
        FileInputStream fis1 = null;
        BufferedReader br1 = null;
        try {
            fis1 = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis1);
            br1 = new BufferedReader(isr);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos2 = openFileOutput(TEMP_NAME, MODE_APPEND);
            Scanner scan_line = null;
            Boolean truth = true;

            while((sCurrentLine = br1.readLine()) != null) {
                scan_line = new Scanner(sCurrentLine);
                scan_line.useDelimiter("[,\n]");
                new_extract_dash1 = scan_line.next();
                new_amount_dash1 = scan_line.next();
                index1 = scan_line.next();
                if((expense_track.equals(new_extract_dash1)) && (amount_track.equals(new_amount_dash1)) && truth){
                    truth = false;
                    continue;
                }
                try {
                    fos2.write(new_extract_dash1.getBytes());
                    fos2.write(",".getBytes());
                    fos2.write(new_amount_dash1.getBytes());
                    fos2.write(",".getBytes());
                    fos2.write(index1.getBytes());
                    String lineSeparator = System.getProperty("line.separator");
                    fos2.write(lineSeparator.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fos2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scan_line.close();
            Toast.makeText(this, "Saved to "+getFilesDir()+"/"+FILE_NAME, Toast.LENGTH_LONG).show();
            Save1();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CopyFromTempToOriginal();
    }

    public void CopyFromTempToOriginal(){
        FileInputStream fis = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        try {
            fis = openFileInput(TEMP_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos = openFileOutput(FILE_NAME, MODE_APPEND);
            String s;
            Scanner scan_line1 = null;
            while((s = br.readLine()) != null) {
                scan_line1 = new Scanner(s);
                scan_line1.useDelimiter("[,\n]");
                String new_extract_dash2;
                new_extract_dash2 = scan_line1.next();
                String new_amount_dash2;
                new_amount_dash2 = scan_line1.next();
                String index2;
                index2 = scan_line1.next();
                try {
                    fos.write(new_extract_dash2.getBytes());
                    fos.write(",".getBytes());
                    fos.write(new_amount_dash2.getBytes());
                    fos.write(",".getBytes());
                    fos.write(index2.getBytes());
                    String lineSeparator = System.getProperty("line.separator");
                    fos.write(lineSeparator.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                scan_line1.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
            br.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Save2();
    }

    String number_tb_assigned;

    //String that are saved in this format
    String new_extract_dash, new_amount_dash;
    String index;

    //called when amount is inserted into EditText
    public void addRubbish(String extract_dash, String amount_dash){
        String income_name = extract_dash;
        String income_amount = amount_dash;

        SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
        file_exists = settings.getBoolean("FIRST_RUN", false);
        if (!file_exists) {
            Save2();
            Save1();
            Incomes_list(income_name ,income_amount);
            settings = getSharedPreferences("PREFS_NAME", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("FIRST_RUN", true);
            editor.commit();
        } else {
            Incomes_list(income_name ,income_amount);
        }



        modelClassList.add(new ModelClass(extract_dash, amount_dash));

        adapter.notifyDataSetChanged();
    }
    String index_finally_assigned;
    String temp;

    //index number is assigned to new amount and income_name
    public void Incomes_list(String income_name, String income_amount){
        FileOutputStream fos = null;
        try {
            temp = readLastNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        index_finally_assigned = temp;
        try {
            fos = openFileOutput(FILE_NAME, MODE_APPEND);
            fos.write(income_name.getBytes());
            fos.write(",".getBytes());
            fos.write(income_amount.getBytes());
            fos.write(",".getBytes());
            fos.write(index_finally_assigned.getBytes());
            String lineSeparator = System.getProperty("line.separator");
            fos.write(lineSeparator.getBytes());
            Toast.makeText(this, "Saved to "+getFilesDir()+"/"+FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //this will read the last number in the file
    private String readLastNumber() throws IOException {
        String sCurrentLine;
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String lastLine = "";

        while ((sCurrentLine = br.readLine()) != null)
        {
            lastLine = sCurrentLine;
        }
        if(lastLine.equals("")){
            number_tb_assigned = Integer.toString(1);
        }
        else{
            Scanner scan1 = new Scanner (lastLine);
            scan1.useDelimiter("[,\n]");
            while(scan1.hasNext()) {
                new_extract_dash = scan1.next();
                new_amount_dash = scan1.next();
                index = scan1.next();
                int convert = Integer.parseInt(index);
                number_tb_assigned = Integer.toString(convert + 1) ;
            }
            scan1.close();
        }
        br.close();
        return number_tb_assigned;
    }

    public void Save1(){
        String text = "";
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Saved to "+getFilesDir()+"/"+FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Save2(){
        String text = "";
        FileOutputStream fos1 = null;

        try {
            fos1 = openFileOutput(TEMP_NAME, MODE_PRIVATE);
            fos1.write(text.getBytes());
            Toast.makeText(this, "Saved to "+getFilesDir()+"/"+FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos1 != null) {
                try {
                    fos1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void separateIncomes(){
        int Salary=0, Bonus=0, Rental=0, Others=0;
        String income, amount, indice;

        String sCurrentLine;
        FileInputStream fis1 = null;
        BufferedReader br1 = null;
        try {
            fis1 = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis1);
            br1 = new BufferedReader(isr);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Scanner scan_line = null;

            while((sCurrentLine = br1.readLine()) != null) {
                scan_line = new Scanner(sCurrentLine);
                scan_line.useDelimiter("[,\n]");
                income = scan_line.next();
                amount = scan_line.next();
                indice = scan_line.next();
                if(income.equals("Salary")){
                    Salary = Salary + Integer.parseInt(amount);
                }
                if(income.equals("Bonus")){
                    Bonus = Bonus + Integer.parseInt(amount);
                }
                if(income.equals("Rental")){
                    Rental = Rental + Integer.parseInt(amount);
                }
                if(income.equals("Others") ){
                    Others = Others + Integer.parseInt(amount);
                }


            }
            scan_line.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        toIncomeChart(Salary,Bonus,Rental,Others);

    }

    private void toIncomeChart(int Salary, int Bonus, int Rental, int Others){
        Intent intent = new Intent(IncomeList.this, IncomeChart.class);
        String String_Salary = Integer.toString(Salary);
        String String_Bonus = Integer.toString(Bonus);
        String String_Rental = Integer.toString(Rental);
        String String_Others = Integer.toString(Others);
        intent.putExtra("Extract_Salary",String_Salary);
        intent.putExtra("Extract_Bonus",String_Bonus);
        intent.putExtra("Extract_Rental",String_Rental);
        intent.putExtra("Extract_Others",String_Others);
        startActivity(intent);
    }


}
