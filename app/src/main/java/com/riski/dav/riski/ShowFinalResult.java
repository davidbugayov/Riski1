package com.riski.dav.riski;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dav on 03.05.17.
 */

public class ShowFinalResult extends Fragment {
    public static ShowFinalResult newInstance() {
        ShowFinalResult fragment = new ShowFinalResult();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.finish_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.finish_table);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        ArrayList<Map<String,Double>> riskiValue = new ArrayList<>();
        for(int i =0 ;i<10;i++){
            riskiValue.add(i,new HashMap<String, Double>());
            riskiValue.get(i).put("Нарушение учебного процесса вследствие потери доступа к требуемым ресурсам локальной сети кафедры (напр., samos)",1.23);
        }
        recyclerView.setAdapter(new FinalResultAdapter(riskiValue));
        return view;
    }
    class FinalResultAdapter extends RecyclerView.Adapter<FinalResultAdapter.FinalResultHolder> {
        ArrayList<Map<String,Double>> riskiValue;

        public FinalResultAdapter(ArrayList<Map<String,Double>> riskiValue) {
            this.riskiValue = riskiValue;
        }
        @Override
        public FinalResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_final_result, parent, false);
            return new FinalResultHolder(itemView);
        }

        @Override
        public void onBindViewHolder(FinalResultHolder holder, int position) {

            Map<String,Double>buf_result = riskiValue.get(position);
            for(String keyBuf :buf_result.keySet()) {
                holder.nameField.setText(keyBuf);
                holder.evaluationField.setText(buf_result.get(keyBuf)+"");
            }
        }
        @Override
        public int getItemCount() {
            return riskiValue.size();
        }

        class FinalResultHolder extends RecyclerView.ViewHolder {
            TextView nameField;
            TextView evaluationField;
            View mView;
            FinalResultHolder(View view) {
                super(view);
                mView =view;
                nameField = (TextView) view.findViewById(R.id.name_field);
                evaluationField = (TextView) view.findViewById(R.id.evaluation);
            }
        }
    }

}
