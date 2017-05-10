package com.riski.dav.riski;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dav on 24.04.17.
 */

public class LoadRiskiFragment extends Fragment {
    private static String FRAGMENT_TAG = "Frag_tag1";
    EditText link;
    Button decide;
    boolean fileIsLoaded;
    List< Map<Integer,Integer> > mapsList ;
    public static LoadRiskiFragment newInstance() {
        LoadRiskiFragment fragment = new LoadRiskiFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.load_data_fragment, container, false);
        link= (EditText) view.findViewById(R.id.link);
        Button load = (Button)view.findViewById(R.id.load_file);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFile();
            }
        });
        decide = (Button)view.findViewById(R.id.decide);
        decide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fileIsLoaded)
                    parseFileAndSend();
            }
        });
        return view;
    }

    private void parseFileAndSend(){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction().
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                replace(R.id.main_frame, ShowFinalResult.newInstance(), FRAGMENT_TAG);
       // fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(((DecideAllInfoActivity)getActivity()).getSupportActionBar()!=null)
            ((DecideAllInfoActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.header_load));
    }

    private void loadFile(){
        String linkToGoogle = link.getText().toString();
        if(!linkToGoogle.isEmpty()){
            new DownloadWebpageTask(new AsyncResult() {
                @Override
                public void onResult(JSONObject object) {
                    if(object!=null)
                        processJson(object);
                    else
                        Toast.makeText(getContext(), "Ошибка загрузки", Toast.LENGTH_SHORT).show();
                }
            }).execute(getString(R.string.norm_link_google));}
        else{
            fileIsLoaded = false;
            Toast.makeText(getContext(),"Ссылка не оч", Toast.LENGTH_SHORT).show();
        }
    }





    private void processJson(JSONObject object) {
        if (object != null) {
            fileIsLoaded = true;
            Toast.makeText(getContext(), "Файл лоадед, теперь можете считать риски", Toast.LENGTH_SHORT).show();
        }
        else{
            fileIsLoaded = false;
            Toast.makeText(getContext(),"Ссылка не оч", Toast.LENGTH_SHORT).show();
        }
        try {
            JSONArray rows = object.getJSONArray("rows");
            mapsList = new ArrayList<Map<Integer,Integer>>();
            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                for(int i =2; ;i++){
                    JSONObject jsonObject = columns.optJSONObject(i);
                    if(jsonObject != null){
                        if(mapsList.size() <= i-2 || mapsList.size()==0){
                            Map<Integer,Integer> buf= new HashMap<>();
                            int id = columns.getJSONObject(0).getInt("v");
                            int risk = jsonObject.getInt("v");
                            mapsList.add(buf);
                            mapsList.get(i-2).put(id,risk);
                        }else {
                            int id = columns.getJSONObject(0).getInt("v");
                            int risk = jsonObject.getInt("v");
                            mapsList.get(i-2).put(id,risk);
                        }
                    }else
                        break;
                }
            }
            Toast.makeText(getContext(),"Фух с божьей помощью распарсилось слава богу", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
RelationM

}
