package com.riski.dav.riski;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dav on 23.04.17.
 */

public class InputFormActivity extends AppCompatActivity {
    public  static  Map<Integer,Double> riskiValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_form);
        riskiValue = new LinkedHashMap<>();
        for(int i=0;i<5;i++){
            riskiValue.put(i,1.0);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RecyclerView.Adapter adapter = new InputViewAdapter(riskiValue);
        recyclerView.setAdapter(adapter);
        Button button = (Button) findViewById(R.id.next_step);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DecideAllInfoActivity.class);
                startActivity(intent);
            }
        });
        Relatin
    }

}
