package com.example.trial_24;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private List<ModelClass1> modelClassList1;

    public ExampleAdapter(List<ModelClass1> modelClassList1) {
        this.modelClassList1 = modelClassList1;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        private TextView textView3;
        private TextView textView4;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
        }
        private void setData(String texty3, String texty4){
            textView3.setText(texty3);
            textView4.setText(texty4);
        }
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        String text3 = modelClassList1.get(i).getText3();
        String text4 = modelClassList1.get(i).getText4();
        exampleViewHolder.setData(text3,text4);
    }

    @Override
    public int getItemCount() {
        return modelClassList1.size();
    }
}
