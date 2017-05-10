package com.riski.dav.riski;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.Map;

/**
 * Created by dav on 23.04.17.
 */

public class InputViewAdapter extends RecyclerView.Adapter<InputViewAdapter.InputViewHolder> {
    Map<Integer,Double> riskiValue;

    public InputViewAdapter(Map<Integer,Double> riskiValue) {
        this.riskiValue = riskiValue;
    }
    @Override
    public InputViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_input_form, parent, false);
        return new InputViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final InputViewHolder holder,int position) {
        Resources res = holder.mView.getResources();
        String[] name = res.getStringArray(R.array.field_name);
        holder.nameField.setText(name[position]);
        holder.spinnerField.setSelection(0);
        holder.spinnerField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                riskiValue.put(holder.getAdapterPosition(),holder.value[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return riskiValue.size();
    }

    class InputViewHolder extends RecyclerView.ViewHolder {
        TextView nameField;
        Spinner spinnerField;
        Double[] value= new Double[]{1.0,2.0,3.0,4.0,5.0};
        View mView;
        InputViewHolder(View view) {
            super(view);
            mView =view;
            nameField = (TextView) view.findViewById(R.id.name_field);
            spinnerField = (Spinner) view.findViewById(R.id.spinner_field);
            ArrayAdapter<Double> adapter = new ArrayAdapter<Double>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, value);
            spinnerField.setAdapter(adapter);
        }
    }
}
